## 컬렉션 프레임워크(Collection Framwork)

- Set은 내부적으로 Map을 사용하고 있다.(HashSet은 HashMap, TreeSet은 TreeMap, LinkedHashSet은 LinkedHashMap 사용)


---

### List

#### ArrayList

- 배열기반 List 구현(내부적으로 Object타입의 배열을 사용한다.)

- 메모리상에 Sequential하게 위치하고 있기 때문에 RandomAccess를 구현해서 이 방식으로 검색한다.

- RandomAccess : 해당 타입이 메모리 상에 sequential하게 위치하고 있을 때 메모리상의 주소를 계산해 바로 접근하는 것(무작위로 접근한다기 보단 sequential하게 처음부터 접근하지 않는 다는 것이 더 적절하다.)

- 시간복잡도 : O(1) : 계산된 위치로 바로 검색하기 때문

- 검색은 빠르나 List의 크기를 늘리거나, 중간에 삽입/삭제 시 Sequential하게 만들기 위해 영향을 받는 인덱스의 메모리 상 위치를 모두 이동시켜야한다.(매우 비효율적)

---

#### LinkedList

- 인스턴스(내부 클래스인 Node) 기반 구현

		private static class Node<E> {
	        E item;
	        Node<E> next;
	        Node<E> prev;
	
	        Node(Node<E> prev, E element, Node<E> next) {
	            this.item = element;
	            this.next = next;
	            this.prev = prev;
	        }
	    }

- 메모리 상에 Sequential하게 위치하고 있지 않기 때문에 검색이 불리하다.

- 탐색을 Iterator로 한다.(무조건 headNode부터 순차적으로 탐색(O(n)))

- 검색은 비효율적이나 List의 크기를 늘리거나, 중간에 삽입/삭제 시 메모리 상에 Sequential하게 위치하지 않기 때문에 영향을 받는 노드들 끼리만 참조를 수정하면 된다.


---

### Iterator(반복자)

- element의 순회를 위한 인터페이스

- 특정 순서로 element를 순회한다.(set, map의 인덱스가 없으므로 정해진 순서가 없다.)

- forEach(향상된 for)문으로 set/list를 실행하면 내부적으로 iterator를 생성하고 iterator.next()메소드를 호출한다.(map은 entry(key,value동시에)/key/value로 구분해서 iterator를 호출해야 한다.)


	public class IteratorTest {
	
		public static void main(String[] args) {
			
			Set<Integer> set = new HashSet<>();
			
			set.add(5);
			set.add(3);
			
			for(Integer num : set) {
				System.out.println(num);
			}
			
			Iterator<Integer> iter = set.iterator();
			
	
		}
	
	}


![IteratorTest](../imgs/IteratorTest.JPG)

1. HashSet 메소드 생성

2. element를 add하기 전에 Integer 타입으로 오토박싱

3. set.add메소드를 호출해 set에 추가

4. iterator 생성

5. hasNext메소드 호출

---

### Set(집합)

#### HashSet

- HashMap에서 key값만 사용한다.
	
HashMap 생성자

	   public HashSet() {
	        map = new HashMap<>();
	   }

- value는

	private static final Object PRESENT = new Object();

PRESENT 인스턴스로 채운다.(사용하지 않으니까 하나로 돌려막아도 상관없다.)

- 내부적으로 HashTable을 사용한다.

- Iterator메소드를 호출하면 HashMap.keyset.Iterator() 메소드를 호출한다.

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

---

#### TreeSet

- TreeMap에서 key값만 사용한다.

- 정렬방식을 지정할 수 있고 iterator를 통해 값을 확인할 때 지정한 정렬 기준에 따라 key값을 확인할 수 있다.

- 정렬방식을 지정하는 방법

	- 제네릭 타입으로 사용하는 클래스에서 Comparable 인터페이스를 구현한다.
	- 생성자의 인자로 Comparator 인터페이스를 전달한다.

##### sortedSet

- NavigableSet 인터페이스를 상속받았다.
- TreeSet에 구현되어 있다.

	SortedSet<Integer> sortedSet = new TreeSet<>();
	
	sortedSet.add(3);
	sortedSet.add(1);
	sortedSet.add(2);
	

	System.out.println(sortedSet.first());
	System.out.println(sortedSet.last());

출력

1
3

---

##### NavigableSet

- TreeSet에 구현되어 있다.

	NavigableSet<Integer> navigableSet = new TreeSet<>();
	
	navigableSet.add(3);
	navigableSet.add(1);
	navigableSet.add(5);
	navigableSet.add(4);
	navigableSet.add(2);

	System.out.println(navigableSet.higher(2)); // 큰 값중에 가장 가까운 값
	System.out.println(navigableSet.celilng(2)); // 이상의 값(파라미터 포함)
	System.out.println(navigableSet.floor(5)); // 이하의 값(파라미터 포함)
	System.out.println(navigableSet.lower(5)); // 작은 값중 가장 가까운 값

출력

3
2
5
4

##### Comparable vs Comparator

- Comparable : 구현한 클래스의 기본 정렬방식으로 사용된다
- Comparator : 기본 정렬방식 이외의 방식으로 정렬할 때 사용된다.


	int[] array = {1,2,3,4,5};
	Arrays.sort(array); 


Integer 클래스가 Comparable 인터페스를 구현했고, array를 Integer로 boxing하고 Integer 클래스의 CompareTo 메소드의 정렬방식으로 정렬을 한다.(바이트 코드에서는 박싱하는 코드를 찾아볼 수가 없네? 당연한건데) 

---


### Map

- key와 value의 쌍으로 데이터를 저장한다.

- key값은 유일한 값이어야 한다.(이미 있는 key값을 넣을 경우 가장 최근의 value로 저장된다.)

- Set은 Map에서 key만 사용하는 것이다.

- Set과 마찬가지로 key의 정렬 방식을 정하고 싶으면 TreeMap을 사용하면 된다.

- get 메소드는 key값이 없으면 null을 반환한다.

	map.getOrDefault(key, key가 없을 때 출력 값);

key 값이 없으면 설정한 값을 출력한다.(null값을 리턴하지 않도록 할 수 있다. if문에서 활용하기 좋을 듯. contains->get을 한 번에 처리 가능)


	map.merge(1, 1, Integer::sum);

값이 1인 key가 있으면 1을 증가시킨다.

	Integer.sum(int a, int b);

merge의 마지막 파라미터에 들어가는 메소드의 파라미터가 (oldValue, 두 번째 파라미터)이기 때문에 sum메소드의 호출이 가능한 것이다.(파라미터가 2개니까) 

---

#### Entry

- Map 인터페이스 내부에 인터페이스로 정의되어있다.

		interface Map<K,V> {
			...
			
			interface Entry<K,V> {...}
			
			...
		}

---


#### forEach

	for(Entry<Integer,Integer> entry : map.entrySet()) {
		
		int key = entry.getKey();
		int value = entry.getValue();
		
		System.out.println("key : " + key);
		System.out.println("value : " + value);
		
		
	}

위의 코드를 forEach를 활용해 간단하게 만들 수 있다.


	map.forEach((k,v) ->{
		System.out.println("key : " + k);
		System.out.println("value : " + v);
	});
		
key,value를 동시에 가져올 때 forEach 메소드를 사용하면 Iterator인스턴스를 만들 필요가 없다.

주의사항

- forEach 밖에서 선언된 local variable을 사용할 수 없다.(이유는 찾아보자)

---


#### HashMap

- key값을 정렬하지 않는 map

##### 구조

- HashMap에서만 사용되는 inner class인 Node(Map.Entry를 구현)가 있다.

node class

	    static class Node<K,V> implements Map.Entry<K,V> {
	        final int hash;
	        final K key;
	        V value;
	        Node<K,V> next;
	
	        Node(int hash, K key, V value, Node<K,V> next) {
	            this.hash = hash; // Object의 hashCode메소드를 활용해 만든다.
	            this.key = key;
	            this.value = value;
	            this.next = next; // 가장 뒤에 넣기 때문에 null이 된다.
	        }
	
	        public final K getKey()        { return key; }
	        public final V getValue()      { return value; }
	        public final String toString() { return key + "=" + value; }
	
	        public final int hashCode() {
	            return Objects.hashCode(key) ^ Objects.hashCode(value);
	        }
	
	        public final V setValue(V newValue) { // 중복된 key값을 넣었을 때 호출된다.(HashMap에선 사용되지 않음)
	            V oldValue = value;
	            value = newValue;
	            return oldValue;
	        }
	
	        public final boolean equals(Object o) {
	            if (o == this)
	                return true;
	            if (o instanceof Map.Entry) {
	                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	                if (Objects.equals(key, e.getKey()) &&
	                    Objects.equals(value, e.getValue()))
	                    return true;
	            }
	            return false;
	        }
	    }

---

노드 인스턴스를 생성하는 메소드

    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash, key, value, next);
    }


---

put 메소드를 내부에서 실행되는 메소드


	final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {...}


- hash : key값으로 hash값을 만들어 낸다.
- key/value : 입력받은 key/value값
- onlyIfAbsent : true일 경우 기존에 있던 key값에 put해도 value값을 교체하지 않는다.
- evict : false일 경우 table(put된 Node가 저장되어 있는 array) 생성모드가 된다.(뭔말인지 모르겠네)

---

#### TreeMap

- key값을 iterator를 통해 원하는 정렬방식의 순서대로 추출할 수 있다.

- put할 떄마다 정렬을 한다.

- Comparator 인터페이스가 구현되어 있으면 compare 호출 -> 없으면 Comparable 인터페이스의 compareTo 호출

- comparable을 구현하지 않고, Comparator도 없는데 put 할경우 ClassCastExcetion을 던진다.(Runtime Exception)

		compare(Object k1, Object k2){
			return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
            : comparator.compare((K)k1, (K)k2);
		}


모든 타입을 받기 위해서 Object를 사용하지만

	(Comparable<? super K>)k1

순수한 Object 인스턴스는 Comparable 인터페이스를 구현하지 않기 때문에 캐스팅 했을 때 compareTo 메소드가 구현되어있지 않다면 Exception이 발생하는 것이다.


- TreeMap은 HashMap의 Node 대신 static final class인 TreeMap.Entry를 사용한다.(Map.Entry 구현)

- Node의 기능 + 순서와 관련된 메소드가 추가되어있다.(ex) getFirstEntry, getLastEntry, successor 등)

---

#### HashMap vs TreeMap

- 기능적 차이
	- TreeMap은 key값을 원하는 방식으로 정렬할 수 있다.

- 구조적 차이
	- TreeMap은 정렬하기 위해 Comprable인터페이스를 구현한 클래스를 key로 사용하거나, 또는 key타입의 Comparator를 구현해서 생성자의 파라미터로 넣어야한다.


	- HashMap은 내부 클래스로 Node 사용(Map.Entry 구현)
	- TreeMap은 내부 클래스로 Entry(TreeMap.Entry) 사용(Map.Entry 구현, 정렬을 위해 더 많은 메소드가 있다.)

#### TreeMap.Entry가 private static class인 이유

- TreeMap 클래스 내부에서만 생성할 수 있도록 하기 위해서
- Map.Entry는 인터페이스고 그것을 구현한 TreeMap.Entry는 TreeMap 안에서 탐색을 위한 node의 용도로만 사용된다.


---

#### SortedMap / NavigableMap

- SortedSet과 NavigableSet은 대응되는 Map에서 key값만 사용한 것이다.

 

---


### Properties

- key/value가 모두 Object인 HashTable을 상속받는다.(하지만 key/value가 모두 String인 경우에만 사용한다.)

- 주로 프로그램의 설정 옵션을 저장하는 용도

		settings.put("width", "200");
		settings.put("title", "Hello, World!");
		String path = "haha";
		try(OutputStream out = Files.newOutputStream(path)) {
			settings.store(out, "Program Properties");
		}

		
출력

	#Program Preperties
	#Sum June 06 21:49:05 CET 2020
	width=200
	title=Hello, World\!


---

### EnumSet

		public enum Weekday {
			
			MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
		
		}


		Set<Weekday> always = EnumSet.allOf(Weekday.class);
		
		Set<Weekday> never = EnumSet.noneOf(Weekday.class);

		Set<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
		
		Set<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
		System.out.println("always : " + always);
		System.out.println("never : " + never);
		System.out.println("workday : " + workday);
		System.out.println("mwf : " + mwf);


출력

	always : [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]
	never : []
	workday : [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY]
	mwf : [MONDAY, WEDNESDAY, FRIDAY]

---

### Stack

- LIPO(Last In First Out) 의 구조를 가지로 있는 자료구조

- Stack 클래스가 있지만 ArrayDeque를 사용하는 것이 더 빠르다.

	- 이유 : ArrayDeque가 RandomAccess의 방식으로 접근하기 때문에 자료의 검색이 더 빠르다.
	- 근데 RandomAccess를 구현하지 않는데??(구현한 인터페이스에서도)


사용법

	ArrayDeque<String> stack = new ArrayDeque<>();

	stack.push("Peter");
	stack.pop();


---

### Queue

- FIFO(First In First Out)의 구조를 가지고 있다.

- 보통

		Queue<String> queue = new LinkedList<>();
	를 사용하지만 ArrayDeque를 사용하는 것이 더 좋다.(stack과 같은 이유)


사용법

	Queue<String> queue = new ArrayDeque<>();

	queue.add("Peter"); // enqueue
	queue.remove(); // dequeue


---

### PriorityQueue

- Comparable 인터페이스를 구현한 클래스를 compareTo의 정렬기준에 따라 pop을 한다.

- 또는 Comparator 인터페이스를 생성자의 인자로 넣어준다.(compare의 정렬기준에 따라 pop)

- TreeSet과는 달리 순회할 때 정렬된 순서로 보여주지 않는다.

- pop되는 element만 정확하게 나오도록 되어있다.(시간 낭비X)

		Queue<Integer> queue = new PriorityQueue<>();
		
		queue.add(5);
		queue.add(2);
		queue.add(4);
		queue.add(3);
		queue.add(1);
		
		while(queue.size() > 0) {
			int element = queue.remove();
			System.out.println(element);
		}


출력

1
2
3
4
5

---

### WeakHashMap

- 키의 reference가 사라지면(참조하는 key값이 null이 되면) 해당 key값을 제거해야한다.

- 하지만 Garbarge Collector는 해당 Map의 key가 하나라도 살아있으면 전체를 지우지 않는다.

- WeakHashMap은 reference가 사라지면 GC를 실행해서 해당 key객체를 메모리에서 삭제한다.


---

#### Strong Reference vs Weak Reference vs Soft Reference  

##### Strong Reference(강한참조)

- 가장 일반적인 참조유형

		Integer num = new Integer(5);

num은 값이 1인 Integer 객체에 강한 참조를 가진다.

강한 참조를 가지는 객체는 GC의 대상이 되지 않는다.

---

##### Weak Reference(약한 참조)

- 강한 참조를 가지던 객체가 null값을 가지게 되었을 때를 말한다.

- 메모리가 부족하지 않더라도 GC의 대상이 된다.

강제로 Weak Reference하는 코드

	WeakReference<Integer> weak = new WeakReference<>(num);

---

##### soft Reference(부드러운 참조)

- 강한 참조를 가지던 객체가 null값을 가지게 되었을 때를 말한다.

- 메모리가 부족하지 않으면 GC의 대상이 되지 않는다.(Weak Reference와의 차이점)

강제로 Soft Reference하는 코드

	SoftReference<Integer> weak = new SoftReference<>(num);


---

### view

- Collection 인터페이스를 구현한 객체

- 자료구조를 읽는 것만 가능하고 저장하지는 않는다.


---


#### 범위

	List<String> sentence = new ArrayList<>();
	List<String> nextFive = sencence.subList(5,10); // 5~9사이에 있는 요소에 접근



	Set<String> words = new TreeSet<>();

	sortedSet<String> asOnly = words.subSet("a", "b"); // subList와 마찬가지로 두 번째 파라미터는 포함하지 않는다.

---

#### emptyView / singletoneView(빈 뷰 / 싱글톤 뷰)

- emptyView : 비어있는 set/map/list의 view. element를 추가할 수 없다.

- singletoneView : element가 하나만 들어가있는 set/map/list의 view. 최초 메소드에 파라미터로 넣은 element 이후로는 element를 추가할 수 없다.


	List<Integer> emptyList = Collections.emptyList();
	List<Integer> singletoneList = Collections.singletonList(5);
	

	emptyList.add(5); // UnsupportedOperationException 발생
	singletoneList.add(5) // UnsupportedOperationException 발생


Collection / List/ Set / SortedSet / NavigatableSet / Map / SortedMap / NavigatableMap 형태로 view를 만들 수 있다.

exception은 동일하다.


---

#### Unmodifiable view(수정불가 뷰)

- 수정을 원하지 않을 경우 Collection 자료구조의 수정불가 뷰를 리턴하면 된다.

		List<Integer> arrayList = new ArrayList<>();
		
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		
		List<Integer> unmodifiableView = Collections.unmodifiableList(arrayList);
		
		unmodifiableView.add(5); // UnsupportedOperationException 발생

Collection / List/ Set / SortedSet / NavigatableSet / Map / SortedMap / NavigatableMap의 형태로 view를 만들 수 있다.

---