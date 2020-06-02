### 제네릭

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
- 클래스는 하나만 지정가능(지정할 때는 경계목록 중 첫 번째 항목으로 둬야 한다.)

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

empl 인스턴스에는 Employee의 서브 클래스 인스턴스만 올 수 있다. 그렇기 때문에

	Employee e = staff.get(staffI);

는 항상 옳다.(공변성을 띈다.)

- 공변성 : 타입 T를 확장(extends)한 자식 타입에 대해 허용 (다형성과 같은 의미로 봐도 크게 상관없을 듯)


하지만

	staff.add(x); // x는 Employee의 서브클래스 인스턴스


불가능하다.(제네릭 타입을 파라미터로 받는 모든 메소드에 해당)

왜?? : x는 Employee의 어떤 서브클래스더라도 가능하다.

하지만 Manager와 Janitor같은 Employee의 서브클래스의 경우

Manager 타입에 Janotor를 add

Janitor 타입에 Manager를 add

하는 것은 불가능 하기 때문이다.(컴파일러가 거부한다.)

- null을 전달할 수 있지만, null 객체가 아니다.


- 정리

<? extends Employee>를 Employee로 변환할 수 있다. : Employee를 포함한 서브클래스들은 모두 Employee타입으로 받을 수 있다.


But

어떤 것도 절대 <? extends Employee>로 변환할 수 없다.

왜?? : Employee를 상속 받았다고 해서 그 클래스들 끼리 슈퍼-서브의 관계라는 것을 보장할 수 없으니까.

	<? extends Employee>

는 "Employee의 서브클래스인 모든 클래스"를 의미한다. Manager/Janitor가 Employee의 서브클래스인 것은 맞지만

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