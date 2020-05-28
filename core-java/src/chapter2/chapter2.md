# 객체지향 프로그래밍

### 접근자메서드

호출 대상객체를 변경하지 않는 메서드


	List<Integer> arr = new ArrayList<>();

	arr.get(0); // 인덱스 0번 째 값을 확인(접근)만 한다.


### 변경자 메서드

호출 대상 객체를 변경하는 메서드

	arr.set(0, 45); // 인덱스 0번 째 값을 45로 변경한다.


### 객체참조

자바는 변수에 객체에 대한 참조만 담을 수 있다. 실제 객체는 어딘가에 있고, 참조는 객체를 찾아내는 구현체 고유의 방법이다.

객체 참조를 담고 있는 변수를 다른 변수에 할당하면 같은 객체에 대한 참조 두 개를 갖게 된다.

	List<String> people = friends; // people과 friends는 같은 객체를 참조한다.

	people.add(“Paul”);

people은 객체가 아닌 크기가 2인 배열 리스트 객체에 대한 참조다. people의 내용을 변경하면 같은 객체를 참조하고 있는 모든 참조 변수의 값이 변경된다.

### 필드(인스턴스 변수)

클래스 내의 모든 변수를 의미한다.

	public class Employee{
	   private String name;
	   private double salary;
	   ...
	}

필드는 name과 salary가 포함된다.

자바에서 필드는 보통 private로 선언한다. private로 선언하면 같은 클래스에 속한 메서드만 변수에 접근할 수 있다. 메서드를 통해 변경할 수 있는 변수를 제어하고, 내부 표현을 변경하기 위함이다.

	public void raiseSalary(double byPercent) { // 선언부
		// 구현부
	   double raise = salary * byPercent / 100;
	   salary += raise; 
	   
	}

메서드는 클래스의 선언부에 선언한다.


	public class Employee{ 
	   // 클래스 선언부
	   private String name;
	   private double salary;
	   
	   public void raiseSalary(double byPercent) { // 메서드 선언부
			// 메서드 구현부
		   double raise = salary * byPercent / 100;
		   salary += raise; 
	   	   
	   }
	}


### 인스턴스 메서드 호출

	Employee fred = new Employee();
	
	fred.raiseSalary(5);

raiseSalary 메서드는 클래스의 인스턴스에 동작한다. 이런 메서드를 인스턴트 메서드라고 한다. 

자바에서는 static을 선언하지 않은 메서드는 모두 인스턴스 메서드이다.

### this 참조

지역변수와 필드를 구별하기 위해 사용한다.

	public void raiseSalary(double byPercent) { 
		double raise = this.salary * byPercent / 100;
		this.salary += raise; 
	}

salary가 메서드 내부에서 선언되어있지 않으면 필드를 사용하게 되지만 코드상에서 명확히 구별하기 위해 this를 사용한다.


### 생성자(Constructor) 구현

메서드 선언과 비슷하지만 생성자의 이름은 클래스 이름과 같고 반환 타입이 없다.

	public Employee(String name, double salary) {
	   this.name = name;
	   this.salary = salary;
	}

생성자는 new 연산자로 실행하고 new 연산자는 생성된 객체의 참조를 반환한다.

Employee james = new Employee(“James Bond”, 500000);


	public Employee() {

	}

비어있는 생성자는 따로 소스를 입력하지 않아도 자동으로 생성해준다.


### 오버로딩(같은 클래스에서의 재정의)

생성자의 인자에 따라 여러 버전의 생성자를 만들 수 있다.

	public Employee(double salary) {
	   this.name = “”;
	   this.salary = salary;
	}
	
	Employee james = new Employee(“James Bond”, 500000); // Employee(String, double) 생성자 호출
	
	Employee james = new Employee(400000); // Employee(double) 생성자 호출

이 경우 생성자가 오버로드(중복정의) 되었다고 한다.


### 필드의 기본 초기화

인스턴스 변수를 생성자 안에서 설정하지 않으면 자동으로 기본 값으로 설정된다.
숫자는 0, boolean 값은 false, 객체 참조는 null이 기본 값이다.


### 필드 초기화

생성자가 실행되기 전에 실행되는 부분으로 같은 인스턴스 변수를 생성자에서 실행하면 값이 덮어 씌워진다.

	public class Employee {
	   private String name = “”; // 생성자가 아닌 인스턴스 변수의 선언부에서 초기화를 한다.
	}

### 최종(final) 변수

final로 선언된 인스턴스 변수는 모든 생성자가 작업을 마치기 전에 초기화 해야한다. 초기화 한 후에는 해당 변수를 다시 수정할 수 없다.

	public class Employee{
	   private final String ID;
	   ...
	}

### 인자 없는 생성자

인자 없이 생성자를 만들 수 있다. 적절한 기본 값으로 설정한 객체를 생성한다.

	public Employee() {
	   name = “”;
	   salary = 0;
	}

### 정적(static) 변수

클래스 안에 있는 변수를 static으로 선언하면 해당 변수는 클래스당 하나만 있게 된다.(인스턴스를 여러 개 생성해도 유일하다.)

	public class Employee {
	   private static int lastId = 0;
	   private int id;
	   ...
	   public Employee() {
		lastId++;
		id = lastId;
	   }
	}

모든 Employee객체는 각자의 필드인 id를 보유한다. 하지만 lastId변수는 오직 하나만 있다. lastId변수는 특정 인스턴스가 아닌 클래스 자체에 속한다.


### 정적상수

public class System{
   public staitc final PrintStream out;
}


### 정적 초기화 블록

static으로 선언한 필드를 여러 개 초기화 할 때 유용한다.

	private static List<Integer> list = new ArrayList<>(); 
	private static int year;
	private static int month;
	private static int day;
	private static int a;
	
	
	static{
		for(int i = 0; i < 10; i++) {
			list.add(i);
		}
		year = 2018;
		month = 10;
		day = 31;
	}

non-static 필드의 초기화를 시도 하면 오류가 발생한다.


### 정적 메서드

객체를 생성하지 않고 클래스 내의 메서드를 호출하기 위해 사용한다.

	public class Math {
	   public static double pow(double base, double exponent) {
		...
	   }
	}

### 팩토리 메서드

클래스의 새로운 인스턴스를 반환하는 정적 메서드이다.

	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	NumberFormat percentFormatter = NumberFormat.getPercentInstance();

인자가 없는 생성자는 두 개씩 만들 수 없으므로 팩토리 메서드를 사용할 수 없다.
new NumberFormat(...)은 NumberFormat 객체를 반환하지만 팩토리 메서드를 통해서 서브클래스의 객체를 반환할 수 있다.

### 패키지

##### 선언

점으로 구분된 식별자 목록(ex) java.util.regex). 도메인 이름을 뒤집어 사용하는 것이 좋다.

ex) horstmann.com >> com.horstmann.corejava

클래스를 패키지 안에 넣으려면 소스파일의 첫 문장에 package 문을 추가해야 한다.

### 클래스 임포트(import)

import 문을 사용하면 전체 이름 없이도 클래스를 사용할 수 있다. 필수가 아닌 편의를 위해 사용한다.

##### 사용

	import java.util.Random;
	
	Random generator = new Random();

##### 미사용

	java.util.Random generator = new java.util.Random();

와일드 카드를 사용하면 패키지내 모든 클래스를 임포트 할 수 있다.

	import java.util.*;

클래스만 임포트할 수 있다.(패키지는 임포트 할 수 없다.)

java.*(X)

java.util.*(O)

##### 정적 임포트

클래스 명을 사용하지 않고도 메서드를 호출 할 수 있다.

import static java.lang.Math.*;

sqrt(pow(x,2) + pow(y,2)); // Math.sqrt와 Math.pow를 의미한다.

기본 패키지에 속한 클래스의 정적메서드나 필드는 임포트 할 수 없다.

### 정적 중첩 클래스

클래스 내부에 정의된 클래스 중에서 static으로 선언된 클래스다.

#### private로 만든 경우

	public class Invoice {
		private static class Item{
			String description;
			int quantity;
			double unitPrice;
		
			double price() { 
				return quantiry * unitPrice;
			}
		}
		private List<Item> items = new ArrayList<>();
		...
		
	}

Item 클래스는 Invoice 안에서 비공개로 되어 있어서 오직 Invoice의 메서드에서만 접근할 수 있다.

그렇기 때문에 Item클래스의 필드를 굳이 비공개로 만들지 않아도 된다.

클래스 내부에서만 사용 할 수 있다.


#### public으로 만든 경우

	public class Invoice { // 외부 클래스
		public static class Item{ // 정적 중첩클래스
			String description;
			int quantity;
			double unitPrice;
		
			double price() { 
				return quantiry * unitPrice;
			}
		
			private List<Item> items = new ArrayList<>();
			...
	}

Invoice를 import한 곳에서 Invoice.Item을 사용해서 Item 인스턴스를 생성할 수 있다.

### 내부클래스

중첩 클래스에서 static이 아닌 클래스를 의미한다.


	public class Network { // 외부클래스
		
		public class Member{ // 중첩클래스(내부클래스)
			private String name;
			private List<Member> friends;
			
			public Member(String name) {
				this.name = name;
				friends = new ArrayList<>();
			}
			
			public void leave(){
				members.remove(this); // 실제로는 outer.members.remove(this)가 호출
			}
		}
		
		private List<Member> members;
		
		public Member enroll(String name) {
			Member newMember = new Member(name);
			members.add(newMember);
			return newMember;
		}

		
	}

중첩클래스의 인스턴스(fred)가 외부 클래스의 어느 인스턴스(members)에 속하는지 알아야 하는 경우 사용한다.

	public class Network { // 외부클래스
		
		public class Member{ // 중첩클래스(내부클래스)
			...
			
			public Member(String name) {
				this.name = name;
				friends = new ArrayList<>();
			}
			
			public void leave(){
				unenroll(this); // 실제로 outer.unenroll(this)가 호출
			}
		}

	public void unenroll(Member m){...}

	}


---

#### 내부 클래스용 특수 문법 규칙

	외부클래스.this

위의 코드가 외부 클래스 참조를 나타낸다.

	public void leave(){
		Network.this.members.remove(this);
	}

여기서 Network.this는 필수가 아니다. members로만 참조하면 암시적으로 외부 클래스를 참조한다.

하지만 명시적으로 필요한 경우가 있다.

	public class Network {
		
		public class Member{
			...
			public boolean belongsTo(Network n){
				return Network.this == n;
			}
		}
	}

회원이 특정 네트워크(인스턴스)에 속하는 지를 검사하기 위해서는 명시를 해줘야 한다.

---

	public class Network {
		
		public class Member{
			public Member enroll(String name) {
				Member newMember = new Member(name); // Member newMember = this.new Member(name)의 축약형이다.
				members.add(newMember);
				return newMember;
			}
		}
	}

### 주석

/** 로 시작해서 */로 끝낸다.

##### 클래스주석

반드시 클래스 선언부 바로 앞에 위치해야 한다.

	/**
	 * 현재의 날짜를 표현한다.
	 * @author jaeseonyoo
	 * @version 1.8
	 *
	 */
	public class Person { ...


### 메서드 주석

각 메서드 주석은 해당 메서드 바로 앞에 붙인다.


	/**
	 * 직원의 급여를 인상한다.
	 * @param byPercent 급여인상 백분율(예를 들어 10은 10%를 의미함)
	 * @return 인상금액
	 */
	public double raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
		return raise;
	}

### 변수주석

일반적으로 정적상수만 문서화 한다.

	/**
	 *  연간 일수(윤년제외)
	 */
	public static final int DAYS_PER_YEAR = 365;


### 일반 주석

@since 태그로 해당 기능을 이용할 수 있는 버전을 적을 수 있다.

	/**
	 * 직원의 급여를 인상한다.
	 * @since version 1.8.1
	 * @param byPercent 급여인상 백분율(예를 들어 10은 10%를 의미함)
	 * @return 인상금액
	 */
	public double raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
		return raise;
	}


### 링크

참고할 만한 클래스, 메서드, 변수를 적는다.
클래스를 메서드나 변수와 구분할 때는 #를 사용한다.

@see package.class#member or method label : 해당 패키지의 필드/메소드 링크

@see <a href="URL">label</a> : URL 링크

@see "String" : 전달할 정보(링크X)

		/**
		 * @see chapter2.src.Invoice#raiseSalary(double)
		 * @see <a href="https://www.naver.com">naver</a>
		 * @see "Core Java for the Impatient"
		 */


ctrl + 마우스 클릭하면 해당 클래스/링크로 이동할 수 있다.
