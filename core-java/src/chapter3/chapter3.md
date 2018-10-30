# 인터페이스

### 인터페이스 선언

인터페이스의 모든 메서드는 자동으로 public이 된다.

	public class SquareSequence implements IntSequence
	
IntSequence는 SquereSequence의 슈퍼타입이다.

### instanceof 연산자

객체는 해당 클래스 또는 해당 클래스의 슈퍼타입으로만 캐스팅을 할 수 있다.

classCastException을 피하기 위해 예방차원에서 타입 검사를 할 수 있다.

객체 instanceof 타입

객체가 타입의 서브타입(또는 같은 타입)일때 true를 반환한다.

### 상수

인터페이스에서 변수를 선언만 할 수는 없다.

초기화까지 마친 상태의 변수는 자동으로 public static final(상수)이다.

인터페이스는 객체의 상태(변수)가 아니라 동작(메서드)을 명시한다.

### static 메서드

Integer.parseInt(String)와 같이 static 메서드를 인터페이스에 넣어서 사용할 수 있다.

용도 : 서브타입의 클래스를 생성할 때 등등

### 기본(default) 메서드

인터페이스에 default를 붙여 기본메서드를 구현 할 수 있다.

같은 이름의 default메서드를 가진 두개 이상의 인터페이스를 구현하는 클래스에서는 개발자가 어떤 메서드를 사용할 지 구별 시켜줘야 한다.

방법1. return Person.super.getId()와 같이 특정 인터페이스를 지정한다.(super를 제외하면 인터페이스의 메서드를 static으로 지정해야한다.)

방법2. 두 인터페이스의 메서드를 기본메서드로 구현하지 않는다.


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
		
			return Integer.compare(getId(), o.getId());
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
	
Comparable 인터페이스를 구현하지 않은 클래스를 Arrays.sort()를 통해 정렬할 경우 ClassNotFoundException을 던진다.

##### Comparator

문자열을 사전순이 아닌 길이순으로 정렬하고자 할 때 사용한다. 똑같이 Arrays.sort를 하지만 인자로 Comparator를 구현한 인터페이스를 추가로 입력한다.

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
	
##### Runnable

multithread프로그래밍을 할때 사용한다.

Runnable 인터페이스의 run함수를 정의해서 사용한다.


# 람다표현식

### 함수형 인터페이스

추상 메서드 한개만 포함되어있는 인터페이스로 람다식은 함수형 인터페이스에서만 사용할 수 있다.

ex) Runnable, Comparator

함수형 인터페이스를 인자로 받는 부분에 "(인자) -> return 내용" 처럼 람다식을 입력 해줄 수 있다.

### 메서드 참조

람다식을 간편하게 축소시켜 표현 할 수 있따.

1. 클래스::메서드
2. 클래스::static메서드
3. 객체타입::메서드

		List<String> list = new ArrayList<>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		list.forEach(System.out::println);
		
System.out클래스의 println메서드를 인자로 입력한 것이다.

클래스와 인자가 예상되는 경우(당연한 것일 경우) 메서드 참조의 방식으로 입력 할 수 있다.

### 생성자 참조

메서드가 들어가야 할 자리(클래스 or 객체::메서드)에서 new가 들어가는 것을 제외하고는 메서드 참조와 같다.

ex) n->new int[n]   >>>   int[]::new


### 람다식의 유효범위

람다식의 유효범위는 중첩블록(지역변수)와 같다.

람다식 안에 지역변수와 같은 이름을 가진 파라미터나 지역변수를 선언할 수 없다.

람다표현식이 값을 캡쳐 : 람다표현식에 들어가는 변수가 자유변수 일 경우 이 값들을 저장하는 것

자유변수 : 파라미터 변수도 아니고 코드 내부에서 선언한 변수도 아닌 것(ex) for루프의 i)

### 지역클래스

메서드 안에서 정의된 클래스

선언된 메서드 밖에서는 접근할 수 없으므로 public이나 private로 선언할 수 없다.

### 익명클래스

클래스의 이름을 지정하지 않고 선언한 클래스다.

new 인터페이스() {메서드 구현}

상속, 구현받는 클래스의 메서드를 재정의하는 것이다.

구현된 내용이 재사용 될 가능성이 적을 경우 유용하다.

