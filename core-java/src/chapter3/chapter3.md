# 인터페이스

### 인터페이스 선언

인터페이스의 모든 메서드는 자동으로 public이 된다.

	public class SquareSequence implements IntSequence
	
IntSequence는 SquereSequence의 슈퍼타입이다.(SquereSequence는 서브타입)

- 인터페이스를 구현한 클래스는 인터페이스의 모든 메서드를 구현해야 한다.(일부만 구현한다면 해당 클래스의 제어지를 abstract로 선언해야 한다)

#### 인터페이스 타입으로 변환

	IntSequence digits = new DigitSequence(1729);
	double avg = average(digits, 100);


IntSequence : 수퍼타입

DigitSequence : 서브타입

- 인스턴스를 인터페이스 타입으로 만들 수는 있지만, 인터페이스 타입의 인스턴스를 만들 수는 없다. 모든 객체는 클래스의 인스턴스여야 한다.(인터페이스 만으로는 구현되어 있는 내용이 없으니까)

		IntSequence digits = new IntSequence(1729); // 불가



---

#### instanceof 연산자

- 객체는 해당 클래스 또는 해당 클래스의 슈퍼타입으로만 캐스팅을 할 수 있다.

		IntSequence sequence = new DigitSequence(1729);
	
		String digitString = (String) sequence; // 불가 - sequence의 수퍼클래스가 String이 아니기 때문에 

- classCastException을 피하기 위해 예방차원에서 타입 검사를 할 수 있다.

- 사용법 : 객체 instanceof 타입

- 객체가 타입의 서브타입(또는 같은 타입)일때 true를 반환한다.

		DigitSequence sequence = new DigitSequence(1279);
		List<Integer> list = new ArrayList<>();
		
		System.out.println(sequence instanceof IntSequence); // true
		System.out.println(list instanceof IntSequence); // false

---

#### 인터페이스 확장

- 인터페이스는 또 다른 인터페이스를 확장해서 구현해야할 메소드를 추가할 수 있다.

		public interface Channel extends Closeable{
			boolean isOpen();
		}

- Channel인터페이스를 구현한 클래스는 Closeable인터페이스에 있는 추상 메소드까지 모두 구현해야 한다.

#### 여러 인터페이스 구현

- 클래스는 인터페이스를 몇 개든 구현할 수 있다.
	
		public class FileSequence implements IntSequence, Closeable{
			...
		}

FileSequence의 슈퍼타입은 IntSequence와 Closeable이 된다.

---

#### 상수

- 인터페이스에서 변수를 선언만 할 수는 없다.

- 초기화까지 마친 상태의 변수는 자동으로 public static final(상수)이다.

- 인터페이스는 객체의 상태(변수)가 아니라 동작(메서드)을 명시한다.

		public interface SwingConstants{
	
			int NORTH = 1;
			int NORTH_EAST = 2;
			int EAST = 3;
		}

- 이 상수들은 SwingConstants.NORTH처럼 전체 이름으로 참조할 수 있다.

---

### 정적 메소드와 기본 메소드

#### 정적 메소드


	public interface IntSequence {

		public double average(IntSequence seq, int n);
		
		
		public static IntSequence digitsOf(int n) {
			return new DigitSequence(n);
		}
	}

	IntSequence digits = IntSequence.digitsOf(1729);
	IntSequence digits = new DigitSequence(1729); // 같은 코드다.

- 인터페이스 안에 static으로 만들 경우 구현해야 하고, 인터페이스.메소드로 바로 실행할 수 있다.(class의 static메소드처럼)


	public interface IntSequence{
		...
		public static int getNum() {
			return 1;
		}
	}

	
	public class DigitSequence implements IntSequence{

		...
		
		public static int getNum() {
			return 2;
		}
	}


- 각각 IntSequence/DigitSequence.getNum() 으로만 접근이 가능하다.

- DigitSequence에 

	public int getNum(){
		return 2;
	}

와 같이 오버라이딩을 해도

	System.out.println(DigitSequence.getNum()); // 에러 발생

슈퍼 타입의 메소드가 static이기 때문에 static 메소드를 non-static 메소드로 참조할 수 없다는 에러가 발생한다.

- 즉, 슈퍼타입의 static 메소드는 서브 타입 클래스에서 구현할 수 없다.

---

#### 기본(default) 메서드

- 인터페이스에 default를 붙여 기본메서드를 구현 할 수 있다.

	public interface IntSequence{
		default boolean hasNext() {return true;}
	
	}

- 이 인터페이스를 구현하는 클래스는 hasNext 메서드를 오버라이드하거나 기본 구현을 상속하는 방법 중 하나를 선택할 수 있다.

- 같은 이름의 default메서드를 가진 두 개 이상의 인터페이스를 구현하는 클래스에서는 개발자가 어떤 메서드를 사용할 지 구별 시켜줘야 한다. (구별 시키지 않으면 컴파일러가 어떤 인터페이스의 메소드를 참조할지 선택할 수 없어서 에러가 발생한다.)

	public class Employee implements Person, Identified{
		...
	} 

	- 방법1. Employee 클래스에서 getId 메소드를 작성한다.

	- 방법2. return Person.super.getId()와 같이 특정 인터페이스를 지정한다.(super를 제외하면 인터페이스의 메소드를 static으로 지정해야한다.)

	- 방법3. 두 인터페이스의 메소드를 default 메소드로 구현하지 않는다.

##### 하나의 인터페이스에서만 추상 메소드로 선언한 경우

	interface Identified{
		int getId();
	}

- 언뜻 보면 Person에는 구현이 되어있기 때문에 Person의 메소드를 사용할 수 있을 것이라 생각할 수 있다.

- 하지만, Identified.getId가 수행하길 기대하는 동작이 Person.getId 메서드에서 동작하는지 컴파일러는 알 수 없다.

- 헷갈리기(conflict) 때문에 구현하는 클래스에서 정의하라는 오류 메세지를 출력한다.

---


### 인터페이스의 예

##### Comparable

클래스가 자신이 가지고 있는 객체를 정렬하려면 Comparable 인터페이스를 구현해야 한다.

해당 클래스에 Comparable 인터페이스를 구현하고 Arrays.sort(해당 인스턴스의 배열)을 통해 정렬할 수 있다.

	public class Employee implements Comparable<Employee>{
	
		private int id;
	
		public Employee() {
			// TODO Auto-generated constructor stub
		}
	
		@Override
		public int compareTo(Employee o) {
			return Integer.compare(getId(), o.getId()); // 양수 : 두 번째 인자가 앞으로(오름 차순), 음수 : 첫 번째 인자가 앞으로(내림 차순)
		}
	
		public int getId() {
			return this.id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public static void main(String[] args) {
		
			Employee a = new Employee();
			a.setId(5);
			Employee b = new Employee();
			b.setId(1);
			Employee c = new Employee();
			c.setId(3);
			Employee[] list = {a,b,c};
		
			Arrays.sort(list);
		
			for(Employee emp : list) {
				System.out.println(emp.getId());
			}
		}
	}

###### 주의사항

		@Override
		public int compareTo(Employee o) {
			return Integer.compare(getId(), o.getId());
		}

에서

	return getId() - o.getId();

를 할 때 두 피연산자의 부호가 다르고, 두 정수의 차이를 반환하면 메서드가 제대로 동작하지 않는다. 변수의 범위를 벗어나는 오버플로우가 발생할 수 있기 때문이다.

Integer.compare(num1, num2) 를 사용하면 어떤 정수라도 올바르게 동작한다.

- 부동소수점 타입의 경우 Double.compare를 사용해야만 정확하게 정렬할 수 있다.  
	
- Comparable 인터페이스를 구현하지 않은 클래스를 Arrays.sort()를 통해 정렬할 경우 ClassNotFoundException을 던진다.

---

##### Comparator

- Comparable과 유사하게 사용할 수 있다.

- String의 경우 이미 Comparable 인터페이스를 구현했기 때문에 compareTo메서드를 변경할 수 없다.(개발자가 소유한 클래스가 아니기 때문에)

- 이렇게 Comparable 인터페이스를 이미 구현했고, 그 방식 말고 다른 방식으로 정렬을 원할 때 Comparator를 사용할 수 있다.



똑같이 Arrays.sort를 하지만 인자로 Comparator를 구현한 인터페이스를 추가로 입력한다.

	public class LengthComparator implements Comparator<String>{
		
		@Override
		public int compare(String o1, String o2) {
			return o1.length()- o2.length();
		}
		
		public static void main(String[] args) {
			String[] list = {"Peter", "Paul", "Maryzzz"};
			
			Arrays.sort(list, new LengthComparator());
			
			for(String str : list) {
				System.out.println(str);
			}
		}
	}
	

---

##### Runnable

multithread프로그래밍을 할때 사용한다.

Runnable 인터페이스의 run함수를 정의해서 사용한다.

	public class HelloTask implements Runnable{
		
		@Override
		public void run() {
			
			for(int i = 0; i < 1000; i++) {
				System.out.println(i);
			}
		}
	}


	Runnable task = new HelloTask();
	
	Thread thread = new Thread(task);
	Thread thread2 = new Thread(task);
	
	thread.start();
	thread2.start();

---


## 람다표현식

- 함수형 인터페이스를 입력해야 하는 부분에 대신 사용할 수 있다.(간편해짐)

- 나중에 실행할 수 있게 전달하는 코드 블록. 인스턴스를 생성할 때 아주 편리하다.


- 표현식
	
		(String first, String second) -> first.length() - second.length()
	
		(String first, String second) -> {
			int difference = first.length() - second.length();
			if(difference < 0) return -1;
			else if(difference > 0) return 1;
			else
				return 0;
		}

- 람다 표현식이 파라미터를 받지 않으면 빈 괄호를 사용하면 된다.

		Runnable task = ()->{for(int i = 0; i < 1000; i++) System.out.println(i)};

- 람다 표현식의 파라미터 타입을 추론할 수 있다면 파라미터 타입을 생략할 수 있다.

		Comparator<String> comp = (first, second) -> first.length() - second.length();

	- length() 메소드는 String에만 있기 때문에 파라미터를 String 타입이라고 추론할 수 있다.


- 파라미터가 하나인 경우 괄호를 생략할 수 있다.

	EventHandler<ActionEvent> listener = event -> System.out.println("Oh noes!");

	- (event), (ActionEvent event) 대신 사용할 수 있다.

---

### 함수형 인터페이스

- 추상 메서드 한개만 포함되어있는 인터페이스로 람다식은 함수형 인터페이스에서만 사용할 수 있다.

ex) Runnable, Comparator

- 함수형 인터페이스를 인자로 받는 부분에 "(인자) -> return내용" 처럼 람다식을 입력 해줄 수 있다.

	Arrays.sort(words, (first, second)->first.length - second.length())

	- 두 번째 파라미터의 compare메소드를 호출하면 람다 표현식의 구현부를 실행한다.

- 다른 예시

	List<String> list = new ArrayList<>();

	list.removeIf(e -> e == null);
	
	- removeIf 메소드는 파라미터로 함수형 인터페이스인 Predicate를 받는다. 
	- 위의 코드는 null값인 element를 모두 제거한다.

---

### 메소드 참조


- 람다식을 간편하게 축소시켜 표현 할 수 있다.

- 사용법
	1. 클래스::메소드
	2. 클래스::static메소드
	3. 객체타입(인스턴스)::메소드

- 클래스::메소드

첫 번째 파라미터가 메서드의 수신자가 되고, 나머지 파라미터는 해당 메서드의 파라미터가 된다.

	String::compareToIgnoreCase

	(x,y)-> compareToIgnoreCase(y)

두 코드는 동일하게 실행된다.

- 클래스::static메소드

모든 파라미터가 static메서드의 파라미터로 전달된다.

	Objects::isNull

	x -> Objects.isNull(x)

두 코드는 동일하게 실행된다.

- 객체타입::메소드

		list.forEach(x->System.out.println(x));
		
		list.forEach(System.out::println);

두 코드는 동일하게 실행된다.
		
System.out클래스의 println메서드를 인자로 입력한 것이다.

#### 같은 이름으로 오버라이딩된 메서드가 여러 개일 경우

- 컴파일러는 문맥을 통해 어느 것을 의도했는지 알아내려고 한다.

		List<String> list = new ArrayList<>();

		list.forEach(System.out::println);

일 때는 컴파일러가 println(String) 메서드를 선택한다.

- 클래스와 인자가 예상되는 경우(당연한 것일 경우) 메서드 참조의 방식으로 입력 할 수 있다.

---

### 생성자 참조

- 메서드가 들어가야 할 자리(클래스 or 객체::메서드)에서 new가 들어가는 것을 제외하고는 메서드 참조와 같다.

ex) n->new int[n]   >>>   int[]::new

- 8장의 stream을 배운 후 다시 확인하자

---

### 지연실행 구현

- 람다를 사용하는 핵심 목적

- 어떤 코드를 지금 당장 실행하고 싶을 때는 람다를 사용하지 않는다.

- 코드를 나중에 실행하는 이유

	- 별도의 스레드에서 코드실행
	- 코드를 여러 번 실행
	- 알고리즘의 올바른 지점에서 코드실행(ex) 정렬에서의 비교연산)
	- 특정 이벤트(버튼 클릭, 데이터 수신 등)가 일어날 때 코드 실행
	- 필요할 때만 코드실행

ex)

	public static void repeat(int n, Runnable action) {
		for(int i = 0; i < n; i++) {
			action.run();
		}
	}
	
	repeat(10, ()->System.out.println("Hello, World!"));


- 람다 표현식의 구현부는 action.run()이 호출될 때 실행된다.


ex)

	public static void repeat(int n, IntConsumer action){

		for(int i = 1; i <= n; i++)
			action.accept(i);

	}
	

	repeat(10, i -> System.out.println("execution count : " + i));


- println 메소드는 리턴 타입이 void이기 때문에 추상메소드의 리턴 타입이 void인 함수형 인터페이스를 사용해야 한다.

- 직접 만들기보다는 표준 인터페이스를 사용하는 것이 좋다.(예시에서는 IntConsumer사용)


---

### 직접 함수형 인터페이스 구현

- @FunctionalInterface 어노테이션을 붙이는 이유
	1. 컴파일러가 추상메소드 하나만 있는 인터페이스인지 검사해준다.(코딩 단계에서 오류 발생)
	2. java doc 페이지에 해당 인터페이스가 함수형 인터페이스라는 문장을 남긴다.

- 간단하게라도 직접 만들어보자



---

### 람다식의 유효범위

- 람다식의 유효범위는 중첩블록(지역변수)와 같다.

- 람다식 안에 지역변수와 같은 이름을 가진 파라미터나 지역변수를 선언할 수 없다.

		int first = 0;
		Comparator<String> comp = (first, second) -> first.length() - second.length();

first가 중복선언되어 오류 발생


	public class Application{

		public void doWork(){
			
			Runnable runner = () -> {System.out.println(this.toString())};
			
		}
	} 

- this는 Application을 의미한다.
- 람다식의 유효범위는 doWork와 중첩되고 doWork에서 this를 사용한 것과 람다식 안에 this를 사용한 것은 동일하다.


---

#### 바깥쪽 유효 범위에 속한 변수 접근

	public static void repeatMessage(String text, int count){

		Runnable r = () -> {
			for(int i = 0; i < count; i++){
				System.out.println(text);
			}	
		};

		new Thread(r).start();
	}


람다 표현식 내부가 아니라 바깥쪽 유효 범위에 정의된 파라미터 변수(text, count)에 접근하고 있다.

	repeatMessage("Hello", 1000);

을 호출한다면 repeatMessage의 호출이 반환 된 이후(text, count가 stack영역에서 지워진 이후)에 실행될 수도 있다.

그런데 text, count가 for문이 모두 실행될 지점까지 남아 있을 수 있을까?

- 람다 표현식의 구성

	- 코드 블록
	- 파라미터
	- 자유 변수들의 값(파라미터 변수( (여기에 선언한 변수)->{...} )도 아니고, 코드 내부(코드 블록 내)에서 선언한 변수도 아닌 것)

<br/>

- 람다식을 표현하는 자료 구조(예시의 r)는 반드시 자유변수(text, count)의 값을 저장해야한다.

- 람다표현식이 값을 캡쳐 : 람다표현식에 들어가는 변수가 자유변수 일 경우 모두 실행될 때까지 이 값들을 저장하는 것

- 클로저 : 자유 변수의 값을 사용하는 코드 블록

- 람다 표현식은 값이 변하지 않는 변수만 참조할 수 있다.(text와 count는 파라미터로 받아 변하지 않는 것으로 간주한다.)

		for(int i = 0; i < n; i++){
			new Thread(() -> System.out.println(i)).start();
			// 에러 발생 : Local variable i defined in an enclosing scope must be final or effectively final
		}

자신을 감싸고 있는 유효 범위에 속한 사실상(effectivley) final인 지역 변수만 접근이 가능하다.

꼭 final이 아니더라도 감싸고 있는 메소드의 파라미터의 경우도 예시처럼 사용할 수 있다.

	for(String arg : args){

		new Thread(()->System.out.println(arg)).start();
		// arg를 캡쳐할 수 있다.
	}

이미 초기화 되어있는 args 배열의 값을 가져오는 것이기 때문에 effectively final로 볼 수있다. 그렇기 때문에 캡쳐가 가능하다.

effectively final 규칙 때문에 캡쳐한 변수를 변경할 수 없다.

	public static void repeat(String text, int count, int threads){
		
		Runnable r = () -> {
			while(count > 0){
				count--; // 에러 발생 : Local variable count defined in an enclosing scope must be final or effectively final
				System.out.println(text);
			}
		};
		
		for(int i = 0; i < threads; i++)
			new Thread(r).start();
	}


캡쳐한 변수를 변경할 수 없다는 에러가 발생한다.

---


## 고차 함수

- 함수를 처리(수정)하거나 반환하는 함수

### 함수를 반환하는 메소드

	public static Comparator<String> compareInDirection(int direction) {
		return (x,y) -> direction * x.compareTo(y);
	}

compareInDirection(1) : 오름차순 Comparator 리턴
compareInDirection(2) : 내림차순 Comparator 리턴

	Arrays.sort(friends, compareInDirection(1));

compareInDirection이 고차함수로 사용되었다.

### 함수를 수정하는 메소드

	public static Comparator<String> reverse(Comparator<String> comp) {
		return (x,y) -> comp.compare(y,x);
	}

	reverse(String::compareToIgnoreCase);

String 클래스의 compareToIgnoreCase 메소드로 comp의 compare를 구현한 것이다.

compareToIgnoreCase가 고차함수로 사용되었다.

#### Comparator 인터페이스의 메소드

##### comparing

	Human[] humans = new Human[5];
	
	humans[0] = new Human("5");
	humans[1] = new Human("2");
	humans[2] = new Human("3");
	humans[3] = new Human("1");
	humans[4] = new Human("4");
	
	Arrays.sort(humans, Comparator.comparing(Human::getName)); // 오름차순 정렬한다.

	Arrays.sort(humans, Comparator.comparing(Human::getName)
								  . thenComparing(Human::getFirstName)); // 다음 순위로 정렬할 기준을 지정할 수 있다.(두 개 이상 입력가능)

	Arrays.sort(humans, Comparator.comparing(Human::getName, (s, t) -> s.length() - t.length()));
	// 이름의 길이 순서로 정렬한다.

	Arrays.sort(humans, Comparator.comparingInt(p -> p.getName().length()));
	// 위의 코드와 동일하게 동작한다.(값이 boxing되는 것을 피할 수 있다.)

---

### 지역클래스

- 메서드 안에서 정의된 클래스

- 선언된 메서드 밖에서는 접근할 수 없으므로 public이나 private, protected로 선언할 수 없다.


	private static Random generator = new Random();

	public static IntSequence randomInts(int low, int high) {

		class RandomSequence implements IntSequence {
			public int next(){return low + generator.nextInt(high - low + 1);}
			public boolean hasNext() {return true;}
		}
		return new RandomSequence();
	}


IntSequence에

hasNext()가 default메소드로 구현되어 있고

next()만 추상메소드로 선언되어 있다면

	public static IntSequence randomInts(int low, int high) {
	
		return ()->low + geneator.nextInt(high - low + 1);ㄴ
	}

과 같이 람다식을 사용할 수 있다.

---

### 익명클래스

- 클래스의 이름을 지정하지 않고 선언한 클래스다.

new 인터페이스() {메서드 구현} : 인터페이스와 인터페이스의 메서드를 구현하는 클래스를 정의하고, 이 클래스의 객체를 하나 생성한다.

- 상속, 구현받는 클래스의 메서드를 재정의하는 것이다.

- 구현된 내용이 재사용 될 가능성이 적을 경우 유용하다.

	public static IntSequence randomInts(int row, int high) {

		return new IntSequence(){
			public int next() {return low+generator.nextInt(high - low + 1);}
			public boolean hasNext() {return true;}
		}
	} 

---