### 슈퍼클래스와 서브클래스

	public class Manager extends Employee {
		
		...

	}

슈퍼클래스 : Employee

서브클래스 : Manager

#### 오버라이딩

- 슈퍼클래스에 정의되어 있는 메소드를 서브클래스에서 재정의 하는 것

- super 지시자를 사용해 슈퍼 클래스의 메소드에 접근할 수 있다.

	public class Manager extends Employee {
		
		private double bonus;		

		public double getSalary(){
			return super.getSalary() + bonus;
		}
	}

- super 지시자를 사용하지 않아도 오버라이딩을 하지 않으면 슈퍼클래스의 메소드에 접근하지만, 오버라이딩 했을 경우는 현재 클래스의 메소드에 접근한다.

- 위의 예시에서 super를 제거하면 Manager의 getSalary를 재귀로 계속 호출해서 stackOverFlow에러가 발생한다. 

- 서브클래스에서 슈퍼클래스의 private변수에 접근할 수 없다.(메소드를 통해서 접근 가능)

- 슈퍼클래스와 메소드 명이 같더라도 리턴 타입, 파라미터의 타입,개수가 다르면 다른 메소드다.(오버라이딩이 아니라 그냥 새로운 메소드를 만드는 것이다.)

- @override : 오버라이딩 받은 메서드에서 리턴 타입 또는 파라미터를 바꿨을 때 컴파일러가 오류를 출력한다.(어노테이션을 달면 실수를 방지할 수 있다.)

리턴타입을 바꿨을 때 : The return type is incompatible with Employee.worksFor(Employee)

파라미터를 바꿨을 때 : The method worksFor(Manager) of type Manager must override or implement a supertype method

- 오버라이딩 할 때 리턴 타입을 서브 클래스의 타입으로 바꿀 수 있다.

Employee 클래스

	public Employee getSupervisor(){
		return new Employee();
	}

Manager 클래스
	
	@Override
	public Manager getSupervisor() {
		return new Manager();
	}

왜?

서브클래스는 슈퍼클래스를 포함하기 때문에


---

#### 서브 클래스 생성

- 서브클래스(Manager)의 생성자는 슈퍼클래스(Employee)의 private 필드(인스턴스 변수)에 접근할 수 없다.
- 그렇기 때문에 슈퍼클래스의 생성자로 해당 필드를 초기화해야 한다.

	public Manager(String name, double salary) {
		
		super(name, salary); // 슈퍼클래스의 생성자 호출. 반드시 첫 번째 문장에서 해야한다.(안하면 에러발생)
		bonus = 0;
	}

슈퍼클래스의 생성자를 반드시 첫줄에 해야하는 이유 : 


- 슈퍼클래스 생성자 호출을 생략할 때는 슈퍼클래스에 인자 없는 생성자가 있어야한다. 인자 없는 생성자가 있으면 슈퍼클래스가 암시적으로 호출된다.


#### 슈퍼클래스 할당

	Manager boss = new Manager();

	Employee empl = boss; // 슈퍼클래스 인스턴스에 할당 가능

	double salary = empl.getSalary(); // Manager의 getSalary 메소드가 호출된다.

메소드 조회 : 가상 머신이 인스턴스의 실제 클래스를 살펴보고 해당 클래스에 맞는 메서드를 찾아 실행하는 것


	Employee[] staff = new Employee[3];
	
	staff[0] = new Employee();
	staff[1] = new Manager();
	staff[2] = new Janitor();

	double sum = 0;

	for(Employee empl : staff) {

		sum += empl.getSalary();
	}

각각의 인스턴스에 정의되어 있는 getSalary를 호출(동적메소드 조회)해 계산할 수 있다.


##### 주의사항

- 슈퍼클래스 할당을 배열에 할 경우

	Manager[] bosses = new Manager[10];
	Employee[] empls = bosses;
	empls[0] = new Employee(); // 실행시간 오류

empls는 Manager 타입의 bosses를 참조한다. bosses에 Employee 인스턴스를 저장할 수 없지만 컴파일러는 올바른 문장으로 인식한다.
(empls의 타입을 Employee로 했으니까)

컴파일러가 잡지 못해 런타임 때 ArrayStoreException을 던져야지만 잡아 낼 수 있다. 



#### 타입 변환


- 슈퍼클래스에 할당 한 서브 클래스는 슈퍼클래스에 있는 메소드만 사용할 수 있다.

	Employee empl = new Manager();
	empl.setBonus(1000); // 컴파일 에러


Employee 클래스에는 setBonus가 정의되어 있지 않기 때문에 에러가 발생한다.

해결방법 : 슈퍼클래스의 참조(reference)를 서브클래스로 바꿔주면 된다.

	if(empl instanceof Manager) {
		Manager mgr = (Manager)empl;
		mgr.setSalary(1000);
	}

#### final 메서드, final 클래스

- 메소드를 final로 선언하면 어느 서브클래스에서도 해당 메소드를 오버라이딩할 수 없다.

- 클래스를 final로 선언하면 서브클래스를 만들 수 없다.(ex) String, LocalTimeURL 등)

	public final class String implements...{

	}


---


### 추상클래스

- 추상 메소드가 포함된 클래스(비 추상메소드(구현된 메소드) 포함 가능)

- 추상메소드 : 구현이 없는 메소드

- 추상 클래스와 추상 메소드에는 abstract 제어자가 붙어야 한다.

- 인터페이스와 다른점 : final이 아닌 필드, 생성자를 가질 수 있다.

- 추상 클래스의 인스턴스는 생성할 수 없다.

	Person p = new Person("Fred"); // 오류

	Person p = new Student("Fred"); // Person의 서브클래스인 Student 인스턴스로 생성할 수 있다.

#### 슈퍼클래스의 필드

- 서브클래스에서 슈퍼클래스의 필드를 사용할 수 있다.

- 필드는 타입/변수명을 그대로 사용하는 것이지 메소드처럼 오버라이딩의 개념이 아니다.(어차피 인스턴스마다 값이 다를 수 있으니까)

- 메소드를 사용하듯이 필드도 가져와서 사용하는 것이다.(제한자가 붙어 있는 경우는 직접 접근이 불가할 수도 있다.)

- 상속받은 필드를 생성자/메소드(setter 등)를 활용해서 초기화 하면 된다.

#### protected 접근

- 같은 패키지에 있는 서브클래스에서 슈퍼클래스의 필드에 직접 접근하고 싶을 때 사용한다.

슈퍼클래스(Employee)

	package chapter4.src;


	public class Employee {
		protected double salary;
		...
	}


서브클래스(Manager)

	package chapter4.src

	public class Manager extends Employee {

		...
		public double getSalary(){
			return salary + bonus; // protected 필드인 salary에 접근할 수 있다.
		}
	}	

---

#### 익명 서브클래스

- 인터페이스를 구현하는 익명 클래스를 만들 수 있는 것처럼 슈퍼클래스를 확장하는 익명 클래스도 만들 수 있다. 익명 서브클래스는 디버깅에 유용하다.

	List<String> names = new ArrayList<String>(100) {
		public boolean add(String element) {
			System.out.println("added element : " + element);
			return super.add(element);
		}
	};
	
	names.add("123");

출력

added element : 123

new ArrayList<String>(100) 의 경우 익명클래스가 아닌 경우 String을 생략해도 자동으로 잡아주지만 익명클래스의 경우는 명시해줘야 한다.

---

#### extends와 implements를 동시에 하는 경우

인터페이스의 default 메소드와 서브클래스의 메소드의 리턴타입/메소드명이 같은 경우

	public interface Named {
		default String getName() {return "";}
	}


	public class Person{
		...
		public String getName(){return name;}

	}

	public class Student extends Person implements Named {

		...
	}


이 상황에서는 항상 슈퍼클래스의 구현이 우선한다.(충돌 해결 불필요)

두 인터페이스 간의 default 메소드에서는 충돌이 일어날 수 있으므로 해결해야 한다.

'클래스 우선' 규칙에 따라 인터페이스에 default 메소드를 추가해도 default 메소드가 생기기 전부터 동작하던 코드에는 영향을 주지 않는다.

---

#### super를 이용한 메소드 표현식

- 인스턴트::인스턴스의 메소드 형태의 람다식을 super::인스턴스의 메소드로 활용할 수 있다.

	public class Worker {
		
		public void work(){
			for(int i = 0; i < 100; i++) {
				System.out.println("working : " + i);
			}
		}
	
	}


	public class ConcurrentWorker extends Worker{
		
		public void work(){
			Thread t = new Thread(super::work);
			t.start();
		}
	}

Thread 생성자 안에는 리턴타입과 파라미터의 타입/구성이 같은 구현된 메소드를 넣으면 되는 것이다. (Worker의 work메소드가 Runnable 인터페이스의 run()메소드의 실행부가 되는 것이다.)

#### 객체

상태(필드)나 행동(메서드)을 가지고 있는 단위다.

클래스는 단순히 객체를 코드로 표현하는 매커니즘이다.(객체가 더 큰 개념)

Object 클래스의 인스턴스는 Object타입이라고 하자.

#### 하나의 파일에는 public이 붙은 클래스나, 인터페이스는 하나만 들어가는 것이 좋다.

---

### Object 클래스의 메서드

자바의 모든 클래스는 Object 클래스를 상속받는다.

#### toString()

인스턴스의 이름을 출력했을 때 나오는 값을 반환한다.

Employee

	@Override
	public String toString() {
		
		return getClass().getName() + "[name=" + name + ",salary=" + salary + "]"; 
	}

	Employee empl = new Employee();
	
	System.out.println("employee's info : " + empl); // String과 인스턴스를 결합해도 toString()메소드가 호출된다.
	System.out.println(empl);
	System.out.println(empl.toString());

출력

employee's info : Employee [salary=0.0, name=null]
chapter4.src2.Employee[name=null,salary=0.0]
chapter4.src2.Employee[name=null,salary=0.0]

- getClass().getName()을 호출하면 서브 클래스에서도 toString()이 올바르게 동작한다.


	public class Manager extends Employee {
		...
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString() + "[bonus=" + bonus + "]";
		}
	}


---

##### Object클래스의 toString()

- 클래스명과 해시코드를 출력한다.

	System.out.println(System.out);

출력

java.io.PrintStream@15db9742

PrintStream 클래스는 toString()을 오버라이딩 하지 않았기 때문에 Object의 toString()의 형식으로 출력된다.

##### 배열의 toString()

- 배열은 Object의 toString()을 오버라이딩해 배열 타입을 원시적인 형식으로 출력하도록 변경되어 있다.

		int[] primes = {2,3,5,7,11,13};
		
		System.out.println(primes);

출력

	[I@15db9742

접두어 "[I"는 정수의 배열을 나타낸다.

배열의 원소를 확인하고 싶을 때는 Arrays.toStirng(array 인스턴스)를 사용하면 된다.

다차원 배열은 Arrays.deepToString()를 사용하면 된다.(3차원 이상도 가능)

	int[] primes = {2,3,5,7,11,13};
	int[][] twoDimentionArray = {{1,2,3},{4,5,6}};
	
	System.out.println(Arrays.toString(primes));
	System.out.println(Arrays.deepToString(twoDimentionArray));

출력

	[2, 3, 5, 7, 11, 13]
	[[1, 2, 3], [4, 5, 6]]

#### equals

- 한 객체를 다른 객체와 같은지 검사한다.

- Object의 equals 메소드는 두 객체에 대한 참조가 같은지(같은 주소의 메모리 영역을 참조하는지) 판단한다.

- 두 인스턴스가 같은 내용을 담고 있다면 같다고 보는 경우에만 equals메서드를 오버라이딩한다.

- equals 메소드를 오버라이딩할 때는 이와 호환되는 hashCode메소드도 반드시 제공해야 한다.(x.equals(y)가 true면 x.hashCode()==y.hashCode()도 true여야 한다.)

두 객체(x,y)의 값(내용)이 같은지 확인한다.


	public class Item {
	
		private String description;
		private double price;
	
		@Override
		public boolean equals(Object obj) {
			// 두 객체가 동일한지 알아보는 검사
			if (this == obj)
				return true;
	
			// 파라미터가 null일 경우 false를 반환해야 한다.
			if (obj == null)
				return false;
	
			// 파라미터가 Item의 인스턴스인지 검사
			if (getClass() != obj.getClass())
				return false;
	
			// 인스턴스의 값이 같은지 검사
	
			Item other = (Item) obj;
	
			return Objects.equals(description, other.description) && price == other.price;
	
		}
	
	}

기본타입의 경우 ==으로 검사한다. 하지만 double의 경우 +-무한대, NaN이 염려되어 Double.equals를 사용한다.

객체일 때는 equals메서드의 널 안전 버전인 Objects.equals를 사용한다. 

	Item x = null;
	Item y= new Item();
	
	System.out.println(x.equals(y)); // nullPointException 에러 발생

	System.out.println(Objects.equals(x, y)); // false 출력


##### 배열의 equals

- Arrays.equals를 사용한다.(길이, 요소 확인)

	int[] nums1 = {1,2,3,4,5};
	int[] nums2 = {1,2,3,4,5};
	
	System.out.println(Arrays.equals(nums1, nums2)); // true

	int[] nums1 = {1,2,3,4,8};
	int[] nums2 = {1,2,3,4,5};
	
	System.out.println(Arrays.equals(nums1, nums2)); // false


---

#### 서브 클래스의 equals

- 슈퍼 클래스의 equals를 먼저 호출한 후 서브클래스에만 있는 필드에 대한 검사를 진행한다.

	public class DiscountedItem extends Item {
	
		private double discount;
		
		@Override
		public boolean equals(Object obj) {
	
			if (!super.equals(obj))
				return false;
			
			DiscountedItem other = (DiscountedItem) obj;
			
			return discount == other.discount;
		}
	}


---

#### instanceof

위의 예시에서

	if(!(obj instanceof Item)) return false;

를 사용한다고 했을 때

obj가 Item의 서브클래스에 속해 있을 때도 true를 반환한다. equals메소드는 대칭으로 동작해야 하기 때문에 맞지않다.

- null이 아닌 x,y가 있을 때 x.equals(y)와 y.equals(x)는 같은 값을 반환해야 한다.(대칭으로 동작)

- x가 Item이고 y는 DiscuontedItem일 때, x.equals(y)는 할인(discount)을 고려하지 않는다.(Item의 equals에는 discount를 확인하는 코드가 없으므로)

- y.equals(x)는 할인을 고려할 수 없다.(Item에는 discount가 없기 때문에 discount를 체크하는 코드에서 에러가 발생한다.)


- instanceof를 사용할 수 있는 상황

동등성의 개념이 슈퍼클래스에 고정되어 있고 서브클래스에서 변하지 않는 경우

equals 메소드를 final로 선언하여 오버라이딩 할 수 없게 만들면 된다.



---

연산자의 경우 같은 값이더라도 주소값이 다르면(다른 인스턴스면) false를 출력하기 때문에 비교하기에 부적절할 수 있다.

		String a = "haha";
		String b = "haha";
		String c = new String("haha");

a.equals(b), a.equals(c) 그리고 a==b는 true이지만 a==c는 false가 된다(a와 c는 개별 인스턴스이기 때문에 참조하는 주소가 다르다.)


### hashCode()

- hashCode와 equals 메서드는 반드시 호환되어야 한다.(x.equals(y)가 true면 x.hashCode()==y.hashCode()도 true여야 한다.)

- 사용자는 equals 메서드에서 두 인스턴스의 hashcode값을 비교하는 조건문을 반드시 넣어야 되기 때문에 equals메서드를 작성하기위해 반드시 hashCode() 메서드도 작성해야 한다.

- 생성 규칙은 만드는 사람이 정하면 된다.

- Object 클래스의 hahsCode()메서드는 특정 정숫값을 출력한다. 인스턴스 별로 다른 코드를 출력한다.

- String 클래스의 경우 같은 문자열을 가지면 같은 hashcode를 가지도록 hashConde()가 오버라이딩 되어있다.

#### String.hashCode()

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }


#### hashCode 메서드의 오버라이딩

- euqlas메서드를 오버라이딩할 때는 hashCode 메소드도 오버라이딩해서 equals가 호환되도록 만들어야 한다.

- 하지 않으면 사용자가 해시 집합이나 해시 맵에 객체를 넣었다가 객체를 잃게 될 수도 있다.

	class Item{
		...

		public int hashCode(){
			returm Objects.hash(decription, price);
		}
	}
    
Ojbects.hash 메서드는 파라미터를 가변으로 받는다. 그리고 null에 안전하다.

파라미터가 null이 아니면 해시코드 리턴

파라미터가 null이면 0 리턴

- 클래스에 배열 인스턴스가 있으면 static 메소드인 Arrays.hashCode로 해당 배열의 해시코드를 계산해야 한다.


### 객체 복제하기(clone메소드 오버라이딩)

- clone 메소드를 오버라이딩 하는일은 복잡하고 필요한 경우도 드물다.

- 목적 : deep copy된 인스턴스를 생성하는 것


	public final class Message {
		
		private String sender;
		private List<String> recipients;
		private String text;
		
	}


	Message specialOffer = new Message();
	Message cloneOfSpecialOffer = specialOffer.clone();

할 경우 얕은 복사가 되어서 모든 필드를 공유하게 된다.(책에는 List만 공유한다고 되어있는데? 해봤는데 다 공유하는데??) 다시해보자


#### 얕은 복사(cloneable 인터페이스 구현)

	public Employee clone() throws CloneNotSupportedException {
		
		return (Employee)super.clone();
	}

유효 범위를 protected에서 public으로 바꿔주고 리턴 타입도 현재 클래스 타입으로 바꿔준다.

왜??

클래스의 유효범위가 public이기 때문에 다른 패키지에서 인스턴스를 만들고 clone메소드도 사용할 수 있어야 하므로

#### 깊은 복사(직접 구현)

	public Message clone() {
		Message cloned = new Message();
		
		cloned.recipients = new ArrayList<>(this.recipients);
		
		return cloned;
		
	}

깊은 복사를 하기 위해서는 직접 구현하면 된다.

---
    
### enumeration(열거)

- 관련있는 상수들의 집합 클래스

- 상수와 mapping되는 연산을 추가할 수도 있다.

- 자주 쓰이니까 잘 알아두자

#### enum 정의하기

	public enum Size {
	
		SMALL, MEDIUM, LARGE, EXTRA_LARGE // enum 상수 생성
	
	}

enum 상수를 생성하는 코드를 가장 먼저 작성해야 한다.
---

#### equals / toString

- equals : 열거타입 값을 비교할 때는 ==를 사용하기 때문에 필요없다.

- toString : enum 객체의 이름을 주는 toString 메서드가 자동으로 주어지므로 구현할 필요없다.

---

#### valueOf / values

	Size notMySize = Size.valueOf("SMALL");
또는

	Size notMySize = Size.SMALL;

출력

SMALL

Size.SMALL을 찾아 출력한다.(toString의 역할)

	Size[] allValues = Size.values();

	for(Size s : allValues) {
		System.out.println(s);
	}

출력

SMALL
MEDIUM
LARGE
EXTRA_LARGE

모든 value를 출력한다.

---

#### 생성자,메서드 필드의 추가

	public enum Size {
		
		SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL"); // enum 상수 생성(괄호는 생성자를 정의해야 사용할 수 있다.)
		
		private String abbreviation;
		
		Size(String abbreviation) { // 생성자
			this.abbreviation = abbreviation;
		}
		
		public String getAbbreviation(){
			return abbreviation;
		}
	
	}


enum의 생성자는 반드시 private여야 한다.(생략하면 private)

생성자를 public이나 protected로 하면 에러 발생 

---

#### 인스턴스의 구현부

	public enum Operation {

		ADD{
			public int eval(int arg1, int arg2) {
				return arg1 + arg2;
			}
		},
		SUBTRACT {
			public int eval(int arg1, int arg2) {
				return arg1 - arg2;
			}
		},
		MULTIPLY {
			public int eval(int arg1, int arg2) {
				return arg1 * arg2;
			}
		},
		DIVIDE {
			public int eval(int arg1, int arg2) {
				return arg1 / arg2;
			}
		};
		
		public abstract int eval(int arg1, int arg2);
	

	}


	Operation op = Operation.valueOf("ADD");
	
	int result = op.eval(1, 2);
	
	System.out.println(result);

출력

	3

---

#### static 필드

- enum 상수가 생성된 다음에 static 필드가 생성된다. 그러므로 enum 생성자에서는 static 필드를 참조할 수 없다.

	public enum Modifier {
		
		PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL, ABSTRACT;
		
		private static int maskbit = 1;
		
		private int mask;
		
		public Modifier(){
			mask = maskBit; // 에러 - maskBit cannot be resolved to a variable
			mask *= 2;
		}
	}


생성자에서 static 필드에 접근할 수 없다.

왜?

enum 상수를 생성하면서 생성자 실행(이 때는 아직 static 필드는 정의되어 있지 않다.)

생성자가 실행되기 이전에는 static 필드가 정의되지 않는다.

##### 해결방법

- static 초기화 블록에서 초기화 하면 된다.

	public enum Modifier {
		
		PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL, ABSTRACT;
	
		static {
			int maskBit = 1;
			for(Modifier m : Modifier.values()) {
				m.mask = maskBit;
				maskBit *= 2;
			}
		}
	}

enum 상수가 생성되고 나면 static 필드 초기화와 static 초기화 블록이 위부터 차례로 실행된다.(생성자를 따로 정의하지 않는 방식)

---

#### enum을 기준으로 switch하기

	public enum Operation{
		
		ADD, SUBTRACT, MULTIPLY, DIVIDE;
		
		public static int eval(Operation op, int arg1, int arg2) {
			int result = 0;
			
			switch(op) {
				case ADD: 
					result = arg1 + arg2;
					break;
				case SUBTRACT:
					result = arg1 - arg2;
					break;
				case MULTIPLY :
					result = arg1 * arg2;
					break;
				case DIVIDE:
					result = arg1 / arg2;
					break;
			}
			return result;
		}
	
		Operation op = Operation.valueOf("ADD");
		int result = op.eval(op, 3, 4);
		System.out.println(result);
	
		op = Operation.valueOf("SUBTRACT");
		result = op.eval(op, 3, 4);
		System.out.println(result);
	
		op = Operation.valueOf("MULTIPLY");
		result = op.eval(op, 3, 4);
		System.out.println(result);
	
		op = Operation.valueOf("DIVIDE");
		result = op.eval(op, 3, 4);
		System.out.println(result);
	}

출력

7
-1
12
0

- switch 문에서는 Operation.ADD가 아니라 ADD를 사용해야 한다.(switch 블록에 쓰인 표현식의 타입에서 타입을 추론한다.)

---

#### 다른 enum활용 예제

tableStatus.java

	public enum TableStatus {
		
		Y("1", true),
		N("0", false);
		
		
		private String table1Status;
		private boolean table2Status;
		
		private TableStatus(String table1Status, boolean table2Status) {
			this.table1Status = table1Status;
			this.table2Status = table2Status;
		}
		
		public String getTable1Status(){
			return this.table1Status;
		}
		
		public boolean getTable2Status(){
			return this.table2Status;
		}
	
	}


OriginTable.java

	public class OriginTable {
		
		public static void main(String[] args) {
			
			TableStatus origin = TableStatus.valueOf("Y");
			
			String table1Status = origin.getTable1Status();
			boolean table2Status = origin.getTable2Status();
			
			System.out.println("table1Status : " + table1Status);
			System.out.println("table2Status : " + table2Status);
			
			
		}
	}


- origin / table1 / table2 에서 특정 속성의 값 중 
	- YES를 나타내는 값이 Y/"1"/true, 
	- NO를 나타내는 값이 N/"0"/false 일 경우
	- 통일 시키는 메소드를 만드는 것보다 위처럼 enum으로 처리해주는 것이 나중에 변환해야할 값을 추가/삭제할 때 편리하다.

- 변환을 위한 새로운 메소드를 추가하는 것은 코드의 양을 불필요하게 증가시킨다.

---

### Class 클래스

객체에 대한 더 많은 정보(ex) 인스턴스가 속한 클래스, 정의되어 있는 메소드 목록)를 얻고 싶을 때 사용한다.

	Student stu = new Student();
	
	Class<?> cl = stu.getClass();
	
	System.out.println(cl.getName());

출력

chapter4.src2.Student
	
클래스의 패키지 경로를 출력한다.

<?> : 제네릭(타입검사)은 6장에서 자세히 알아보자

	String className = "java.util.Scanner";
	
	Class<?> cl = Class.forName(className);
	
	Method[] methods = cl.getMethods();
	
	System.out.println(Arrays.toString(methods));

출력

[public void java.util.Scanner.remove(), public java.lang.String java.util.Scanner.toString(),.....]


Scanner에 정의되어 있는 모든 메소드를 확인할 수 있다.

---

.class 접미어를 사용할 수도 있다.

	Class<?> cl2 = String[].class;
	Class<?> cl3 = Runnable.class;
	Class<?> cl4 = int.class;
	Class<?> cl5 = void.class;
	
	System.out.println(cl2.isArray()); // 배열인지
	System.out.println(cl3.isInterface()); // 인터페이스인지
	System.out.println(cl3.isAnonymousClass()); // 익명클래스인지
	System.out.println(cl4.isPrimitive()); // 기본형 타입인지
	System.out.println(cl5.isMemberClass()); // 멤버클래스인지

출력

true
true
false
false
true

배열은 클래스이지만, 인터페이스, 기본 타입, void는 클래스가 아니다. int.class라는 표현은 적절하지 않지만 받아들이자(class 보단 type이 적절하다.)


#### 배열 타입의 getName

String[].class.getName() : [Ljava.lang.String;

int[].class.getName() : [I

가상 머신 초창기에 사용하던 표기법으로 출력한다.

"java.lang.String[]", "int[]" 와 같은 이름을 얻으려면 getCanonicalName()을 사용하면 된다. 


---

#### == 연산자를 통한 비교

- 가상 머신은 각 타입별로 고유한 Class 객체를 관리하기 때문에 == 연산자로 같은 클래스인지 비교가 가능하다.

	Student stu = new Student();

	System.out.println(stu.getClass() == Student.class); // true

---

#### 리소스 로드하기

- 설정파일이나 이미지처럼 프로그램에 필요한 리소스를 찾아올 수 있다.

	InputStream stream = MyClass.class.getResourceAsStream("test.txt");
	
	Scanner in = new Scanner(stream);
	
	System.out.println(in.nextLine());

출력

hahahoho

- getResourceAsStream의 파라미터로 상대경로나 절대경로를 지정할 수 있다.

	InputStream stream = MyClass.class.getResourceAsStream("/chapter4/src/test.txt");

	InputStream stream = MyClass.class.getResourceAsStream("/chapter4/src2/test.txt");

각각의 디렉토리에 있는 test.txt의 내용을 읽어온다.

- 클래스를 JAR 파일로 패키징할 때 해당 클래스 파일과 리소스(텍스트/이미지 파일 등)을 같이 넣으면 해당 리소스도 찾아올 수 있다.

---

#### 클래스 로더

- 바이트를 로드해서 가상 머신의 클래스나 인터페이스로 변환하는 역할을 한다.

- 자바 프로그램을 실행할 때는 최소 세 가지 클래스 로더가 연관된다.

	1. 부트스트랩 클래스 로더 : 자바 라이브러리 클래스를 로드한다.(jre/lib/rt.jar 파일에서 로드) 부트스트랩 클래스 로더는 가상머신의 일부다.
	2. 확장 클래스로더 : jre/lib/ext 디렉토리에서 "표준확장(standard extensions)"을 로드한다.
	3. 시스템 클래스로더 : 애플리케이션 클래스를 로드한다. 또한, 클래스 패스에 있는 디렉터리와 JAR파일에서 클래스를 찾는다.


- 부트스트랩 클래스 로더에 해당하는 ClassLoader 객체는 없다.(ex) String.class.getClassLoader()는 null을 반환한다.)
- 확장 클래스로더, 시스템 클래스 로더 모두 URLClassLoader 클래스의 인스턴스다.

		URL[] urls = {
				new URL("file:///C://Users//yoo-pc//Desktop//skills.jar") // "file:///" 에서 /를 3개 입력해야 한다. "file://" + 루트부터 시작하는 경로
		};
		
		String className = "algorithmSkills.Combination";
		
		try(URLClassLoader loader = new URLClassLoader(urls)) {
			Class<?> cl = Class.forName(className, true, loader);
			Method[] methods = cl.getMethods();
			System.out.println(Arrays.toString(methods));
			
		}

jar파일에서 읽어와서 그 안의 altorithmSkills 패키지의 Combination 클래스에서 정보를 가져온 것이다.

className은 불러오는 jar파일에 있는 패키지 경로를 읽어오는 것이다.(jar파일에 있지 않은 패키지일경우 ClassNotFoundException에러 발생)
	

#### 컨텍스트 클래스 로더

- 나중에 하자(이해가 안돼ㅜㅜ)

---

#### 서비스 로더

- ServiceLoader 클래스를 이용해 공통 인터페이스를 준수하는 플러그인을 손쉽게 로드할 수 있다.

- 내일하자(이해 안돼ㅜㅜ)

---



### 리플렉션

java.lang.reflect 패키지에 있는 클래스 : Field, Method, Constructor

클래스에 대한 각각의 정보를 확인할 수 있다.


### 객체 조사하기부터

클래스에 정의 되어있는 Field, Method, Constructor를 확인하는 방법

필드 : 인스턴스.getClass().getDeclaredFields();

메소드 : 인스턴스.getClass().getDeclaredMethods();

생성자 : 인스턴스.getClass().getDeclaredConstructors();

### 생성자 없는 객체 생성

class클래스로 생성자 없는 객체를 생성할 수 있다.

		Class<?> cl = emp.getClass();
		
		Employee emp2 = (Employee) cl.newInstance();
		
		
### 자바빈즈(JavaBeans)

인자없는 생성자, 필드의 getter/setter쌍, 기타메서드로 구성된 클래스


