### 제네릭

- 타입을 유동적으로 받을 수 있도록 해주는 장치

#### 제네릭 클래스

- 클래스 정의할 때 타입 파라미터를 1개 이상 받는 클래스

- 타입 파라미터는 와일드카드(<>)에 명시한다.

- 타입 파라미터에는 참조변수만 올 수 있다.(기본형 변수는 래퍼클래스 타입으로 명시해야 한다.)

- 제네릭 타입의 매개변수

	- E : Element

	- K : Key

	- N : Number

	- T : Type

	- V : Value

	- ? : Wild Card(모든 객체타입의 자료형, 내부적으로는 Object로 인식)


- 해당 타입으로 필드의 타입, 메소드의 파라미터, 리턴타입으로 지정할 수 있다.

	public class Entry<K,V>{
	
		private K key;
		private V value;
	
		public Entry(K key, V value) {
	
			this.key = key;
			this.value = value;
		}
	
		public K getKey(){return this.key};
		public V getvalue(){return this.value};
	
	}



- 인스턴스 생성

	Entry<String,Integer> entry = new Entry<>();


new Entry<>의 타입파라미터는 생략해도 선언에 있는 타입으로 자동으로 지정된다.


---

#### 제네릭 메소드

- 메소드를 정의할 때 타입파라미터를 1개 이상 받는 메소드

- 일반 클래스 / 제네릭 클래스 모두 사용할 수 있다.

- 제어자와 리턴타입 사이에 타입 파라미터를 명시한다.

		public static <T> void swap(T[] array, int i, int j) {
		
			T temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			
		}

		swap(array, 0, 1);

- 메소드 호출 시 타입 파라미터를 명시하지 않아도 컴파일러가 추론한다. (array에 타입 파라미터를 사용하고 타입이 String이니까)


		Entry.<String>swap(array, 0 ,1);

- 명시를 해 줄경우 더 자세한 오류메세지를 받을 수 있다.(강제로 던지면 쓰나 안쓰나 같은데 나중에 해보자)


---

#### 타입 경계(제한)

- 타입 파라미터로 받을 수 있는 타입을 제한 할 수 있다.

- T extends 제한할 클래스/인터페이스

	public static <T extends AutoCloseable> void closeAll(List<T> elements) throws Exception{

		for(T element : elements) {
			element.close();
		}
	}


closeAll의 타입파라미터는 AutoCloseable의 서브타입으로 제한된다.

extends에 명시하는

- 인터페이스는 원하는만큼 지정가능
- 클래스는 하나만 지정가능(지정할 때는 
- 목록 중 첫 번째 항목으로 둬야 한다.)

---

#### 타입 가변성과 와일드 카드

- 와일드카드 : ?(모든 객체타입의 자료형, 내부적으로는 Object로 인식)
	

제네릭 타입은 불변하기 때문에 하위 클래스인 bosses를 참조할 수 없다.(같은 클래스만 가능)

		List<Manager> bosses = new ArrayList<>();
		List<Employee> empls = bosses;
		empls.add(new Employee());

---

배열은 서브 클래스의 인스턴스를 참조할 수 있다.(공변성) 하지만 하위 클래스타입의 배열에서 상위 클래스타입의 인스턴스를 생성하면
	
		Manager[] bosses = new Manager[5];
		Employee[] empls = bosses;
	
		empls[0] = new Employee(); // java.lang.ArrayStoreException 발생(실행해야 잡히니까 Runtime Exception)


위와같은 exception을 발생시킨다.


---

#### 서브타입의 와일드카드

	<? extends className>

- 서브타입의 와일드 카드를 사용할 때는 읽기만 가능하고 쓰기는 불가능하다.


		public static void printNames(List<? extends Employee> staff) {
		
			for(int staffI = 0; staffI < staff.size(); staffI++){
		
				Employee empl = staff.get(staffI);
				System.out.println(empl.getName());
			}
		}

empl 인스턴스에는 Employee 또는 Employee의 서브 클래스 인스턴스만 올 수 있다. 그렇기 때문에

	Employee e = staff.get(staffI);

는 항상 옳다.(공변성을 띈다.)

- 공변성 : 타입 T를 확장(extends)한 자식 타입에 대해 허용 (다형성과 같은 의미로 봐도 크게 상관없을 듯)


하지만

	staff.add(x); // x는 Employee의 서브클래스 인스턴스


불가능하다.(제네릭 타입을 파라미터로 받는 모든 메소드에 해당)

왜?? : x는 Employee 또는 Employee의 어떤 서브클래스더라도 가능하다.

하지만 Manager와 Janitor같은 Employee의 서브클래스의 경우

Manager 타입에 Janotor를 add

Janitor 타입에 Manager를 add

하는 것은 불가능 하기 때문이다.(컴파일러가 거부한다.)

- null을 전달할 수 있지만, null 객체가 아니다.


- 정리

	<? extends Employee>

를 Employee로 변환할 수 있다. : Employee를 포함한 서브클래스들은 모두 Employee타입으로 받을 수 있다.


But

어떤 것도 절대 

	<? extends Employee>

로 변환할 수 없다.

왜?? : Employee를 상속 받았다고 해서 그 클래스들 끼리 슈퍼-서브의 관계라는 것을 보장할 수 없으니까.

	<? extends Employee>

는 "Employee 또는 Employee의 서브클래스인 모든 클래스"를 의미한다. Manager/Janitor가 Employee의 서브클래스인 것은 맞지만

	staff.add(new Manager());

에서 ?의 타입이 Manager(또는 Manager의 슈퍼 클래스의)의 슈퍼클래스 라는 것을 보장하지 못한다.

---

#### 슈퍼타입의 와일드카드

	<? super ClassName>

- 서브타입의 와일드카드와 반대로 생각하면 된다.

		public static void printAll(Employee[] staff, Predicate<? super Employee> filter) {
			
			Object obj = new Object();
			
			filter.test(new Person()); // 1. 에러
			
			// ? t = new Person();  // 
			
			for(Employee empl: staff) {
				
				if(filter.test(empl)) {
					System.out.println(empl.getSalary());
				}
			}	
		}


1번 코드는

	? t = new Person();

와 같다고 볼 수 있다.(제네릭타입을 파라미터로 받는 모든 메소드에 해당)

?는 "Employee보다 super클래스인 모든 클래스"를 의미한다.

Person이 Employee의 슈퍼클래스인 것은 맞지만, ?의 타입으로 초기화 할 수 있는(? 또는 ?의 서브클래스)클래스 라는 것을

보장하지 못한다.(반공변의 특성)

- 반공변(contravariant) : 타입 T의 상위(부모)타입에 대해서 허용

- 보통 제네릭 함수형 인터페이스를 메소드 파라미터로 지정할 때는 super 와일드카드를 사용해야 한다.
	- 이유는 하면서 생각해보자


---

#### 타입변수 + 와일드카드

- 타입변수에 와일드카드를 같이 사용해 메소드의 사용 범위를 서브/슈퍼 클래스로 넓힐 수 있다.

		public static <T> void printAll(T[] elements, Predicate<T> filter) // 1번

		public static <T> void printAll(T[] elements, Predicate<? super T> filter) // 2번


1번 메소드는 T타입의 배열로 T타입하고만 element의 비교가 가능하다.

하지만 T는 슈퍼클래스의 모든 필드/메소드를 포함하기 때문에 슈퍼클래스와의 비교도 가능하다.

그렇기 때문에 슈퍼클래스와의 비교도 가능하도록 2번처럼 확장할 수 있다.

---

	public void addAll(Collection<? extends E> c)


메소드의 파라미터로 addAll메소드가 있는 인터페이스의 제네릭 타입 또는 그 타입의 서브클래스가 와야 한다는 것이다.

<E> 만 명시한다면 같은 타입만 받을 수 있지만

공변성을 생각하면 서브클래스 타입도 추가할 수 있기 때문에 메소드의 사용 범위가 확장되는 것이다.


---

	public static <T extends Comparable<? super T>> void sort(List<T> list)


T는 Comparable 인터페이스를 구현한 클래스여야 한다.

 
	public interface Comparable<T> {
		
		public int compareTo(T o);
	}

Comparable은 제네릭 타입이기 때문에 타입 파라미터를 명시해야 한다.

T가 특정 클래스를 상속 받았다면(확장 했다면) 슈퍼클래스의 compareTo메소드를 상속받는다.

Employee 클래스

	public class Employee implements Comparable<Employee>{
	
	
		@Override
		public int compareTo(Employee o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}


Manager 클래스
	
	public class Manager extends Employee{
		
		@Override
		public int compareTo(Employee o) {
			
			return super.compareTo(o);
			
		}
	}	

Employee를 상속받은 Manager는 Employee의 compareTo 메소드를 상속받아 그대로 사용하기 때문에 파라미터의 타입이 슈퍼클래스로 되어있다.

	public static <T extends Comparable<? super T>> void sort(List<T> list)

즉 T가 Comparable인터페이스를 구현한 클래스를 상속 받았다면 T의 제네릭 타입은 슈퍼클래스로 되어있다는 것이다.

그렇기 때문에 Comparable의 제네릭 타입은 T또는 T의 슈퍼타입이 와야 한다.


	Comparable<T>

만 사용해도 T타입으로만 사용이 가능하지만 와일드카드를 같이 사용해 사용성을 확장한 것이다.

---

#### 경계(extends / super)가 없는 와일드카드

- 타입파라미터를 제한하는 것이 중요하지 않을 때 사용할 수 있다.

	public static boolean hasNulls(List<?> elements) {

		for(Object e : elements){
			if(e==null)
				return true;
		}
		return false;
	}


list의 타입은 중요하지 않다. 모든 인스턴스는 null이 될 수 있기 때문이다.

	public static <T> boolean hasNulls(List<T> list)

를 해도 같은 동작을 하지만 특정 타입으로 제한하는 것이 아니기 때문에 T의 의미는 크지 않다.

---

#### 와일드카드 캡처

- 와일드카드는 메소드 안에서 파라미터의 타입으로만 사용가능하다.(타입으로 사용될 수 없다.)

		public static void swap(List<?> elements, int i, int j) { // 와일드 카드를 포함하는 일반 메소드(제네릭 메소드X)
	
			? temp = elements.get(i); // 에러 발생
	
			elements.set(i, elements.get(j));
			elements.set(j, temp);
		}

헬퍼 메소드를 사용해 해결할 수 있다.


	public static void swap(List<?> elements, int i, int j) {

		swapHelper(elements, i, j);
	}

	public static <T> void swapHelper(List<T> elements, int i, int j) { // 제네릭 메소드

		T temp = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, temp);
	}


컴파일러는 ?가 뭔지 모른다. 하지만 ?는 특정 타입을 나타내기 때문에 제네릭 메서드를 호출해도 된다.

swapHelper의 타입파라미터 T는 swap의 와일드카드 타입(?)을 "캡처"한 것이다.

캡처했을 때의 장점 : API사용자가 제네릭 메서드보다 이해하기 쉬운 List<?>를 볼 수 있다는 것이다.


---

### JVM에서의 제네릭

#### 타입소거(Type Erasure)

- .java파일에서 타입 파라미터로 정의된 필드의 타입/ 메소드의 리턴타입을 컴파일 과정에서 Object로 변환한다.(low type으로 변환했다고 한다.)

	public class Entry<K, V> {
		private K key;
		private V value;
		
		public Entry(){
			
		}
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	
		public K getKey() {
			return key;
		}
	
		public V getValue() {
			return value;
		}
	}

의 코드가 컴파일 단계에서

	private Object key;
	private Object value;
	
	public Entry(){
		
	}
	
	public Entry(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

로 변환된다.

---

Entry의 제네릭 타입이

	public class Entry<K extends Comparable<? super K> & Serializable, V extends Serializable>{...}


일 경우

	public class Entry{

		private Comparable key;
		private Serializable value;

		...
	}

로 변환된다. (& 사용한 것 알아보자)

K가 Comparable 인터페이스를 구현했다면 K 타입인 key는 Comparable을 구현한 어떤 클래스도 올 수 있다.

V도 마찬가지

---

#### 타입 변환 연산자 삽입

	Entry<String, Integer>

일 떄 key와 value는 반드시 String, Integer 타입인 것을 보장받아야 한다.


key로 

	String key = "jaeseon";

	Object key = "jaeseon";


을 넣는다면 key의 값이 중복되지만 타입이 다르므로 둘다 넣을 수 있다.

	String key = entry.getKey();

그렇기 떄문에 실행 시간에 안전성 검사를 해야한다.

일 때 컴파일러는 타입변환 연산자를 삽입(캐스팅)한 코드를 생성한다.(low type으로 변환된 코드)

	String key = (String)entry.getKey();

---

#### 브릿지 메소드

- 컴파일러가 제네릭 타입의 메소드를 호출할 때

변수들의 타입을 Object로 변환 -> 제네틱타입으로 다운 캐스팅하는 메소드

- 동적 메소드 조회 : 컴파일러는 이렇게 .java파일의 메소드와 대응하는 브릿지 메소드를 만든 후 브릿지 메소드를 찾아 호출하는 것

	public class WordList extends ArrayList<String>{
		
		public boolean add(String e) {
			
			 return super.add(e);
			 
		}

		public String get(int i) {
			return super.get(i).toLowerCase();
		}
	}


	WordList words = new WordList();
	List<String> strings = words;
	String.add("Java");


컴파일러가

	public boolean add(Object e) {
		return add((String) e);
	}

와 같은 브릿지 메소드를 만들고 호출한다.

get 메소드의 경우 WordList클래스에

String get(int)
Object get(int)

두 가지 메소드가 있다.

컴파일러는 두 번째 메소드에 해당하는 브릿지 메소드만들어서 호출한다.

자바 문법으로는 불가능한 소스다.(메소드 시그니처로 구분하기 때문이다.)

메소드 시그니처 : 메소드명, 파라미터 타입의 종류/개수(리턴타입/exception X)

하지만 JVM에서는 메소드를 이름, 파라미터 타입, 리턴 타입으로 구분하기 때문에 컴파일이 완료된 .class파일에서는 가능한 문법이다.

---

### 제네릭의 제약

#### 기본타입 파라미터가 없다.

- 컴파일 단계에서 타입 파라미터로 생성된 변수의 타입을 모두 Object로 바꾸기 때문이다.

- 기본형 변수는 객체가 아니기 때문에 Object타입이 될 수 없다.

---

#### 실행시간에는 모든 타입이 low 형태

	if(a instanceof ArrayList<String>)

- 컴파일 과정을 거치면서 low 타입으로 변한다. 그 이후의 런타임 단계에서 역시 위의 ArrayList가 String 타입을 담고 있는지 알 수 없다.

- 컴파일 과정에서 이미 정확한 타입을 알 수 없는 low type으로 변하기 때문에 컴파일 에러가 발생한다.

		C:\Users\yoo-pc\Desktop\compileTest>javac Entry.java
		Entry.java:66: error: illegal generic type for instanceof
		                if(list instanceof ArrayList<String>) {
		                                            ^
		1 error


cmd창에서 컴파일러를 실행하면 위와 같은 오류가 발생한다.


