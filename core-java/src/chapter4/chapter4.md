# 오버라이딩

- 서브클래스에서 슈퍼클래스의 private변수에 접근할 수 없다.
- super 키워드는 메서드에만 사용할 수 있다(ex) super.getSalary();)

@override 애너테이션을 달면 오버라이딩 받은 메서드에서 리턴 타입 또는 파라미터를 바꿨
을 때 컴파일러가 오류를 출력한다.(애너테이션을 달면 실수를 방지할 수 있다.)

리턴타입을 바꿨을 때 : The return type is incompatible with Employee.worksFor(Employee)

파라미터를 바꿨을 때 : The method worksFor(Manager) of type Manager must override or implement a supertype method

### 서브 클래스 생성

서브클래스에서 슈퍼 클래스의 private필드에 접근할 수 없기 때문에 슈퍼클래스의 생성자로 해당 변수를 초기화 해야한다.(반드시 서브클래스 생성자의 첫 번째 줄에 작성해야한다.

ex) 

```

	public Manager(String name, int salary) {
		super(name, salary);
	}
```

그리고 해당 변수를 getter 메서드로 확인하고, setter함수로 수정해야 한다.(직접접근 불가, 슈퍼 클래스에 getter, setter 메서드가 있어야 한다.)

```
Employee boss = new Manager();
```

이 경우 타입은 Employee지만 Manager 클래스에서 오버라이딩 된 메서드를 호출한다.

boss.getSalary() 할 때 Manager의 getSalary()를 호출

----

## 타입 변환

		Employee emp = new Manager("jaeseon", 40000000);

서브 클래스에만 정의 되어있는 메서드를 실행 할 경우 메서드를 찾지 못해 오류가 발생한다.

		if(emp instanceof Manager) {
			Manager manager = (Manager)emp;
			manager.setBonus(10000);
			System.out.println(manager.getBonus());
		}
		
서브 클래스의 메서드를 사용하기 위해서는 위의 코드처럼 다운캐스팅 해서 써야 한다.

## final 메서드

메서드를 final로 선언하면 서브클래스에서 오버라이딩 할 수 없다.(오류발생)


# 추상 클래스

인터페이스와 달리 필드와 생성자를 가질 수 있다.

추상클래스의 인스턴스는 생성할 수 없다.

다형성을 활용해 서브클래스의 객체 참조를 담는다면 추상클래스 타입으로 인스턴스를 만들 수 있다.

		Person person = new Student();


### protected 접근

protected로 선언할 경우 같은 패키치에서만 접근이 가능하다.

### 익명 서브클래스

슈퍼클래스를 extends하는 익명클래스를 만들 수 있다. 다음과 같이 디버깅에 유용하다.


		List<String> names = new ArrayList<String>(100) {
			
			@Override
			public void add(int index, String element) {
				super.add(index, element);
				System.out.println("Adding " + element + " at " + index);
			}
		};
		
		
ArrayList를 다시 쓸일이 없다면 add메서드가 실행되있는 익명클래스로 만들어서 파라미터로 넣으면 된다


	invite(new ArrayList<String>(){{add("jaeseon"); add("jaemin")}})
	