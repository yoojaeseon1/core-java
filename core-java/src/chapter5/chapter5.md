### 예외 던지기

예외 상황에 대해서 코드를 수정하는게 아니라 호출하는 쪽으로 "너가 오류를 냈어(입력 값 잘못 입력 등)"라고 알려주는 것.

던진다 : 오류는 메소드에서 발생하지만 호출하는 쪽으로 책임을 넘긴다.



---

Error vs Exception

https://docs.oracle.com/javase/8/docs/api/

여기서 error 정보 확인하기

---

### 예외 계층

- Throwable은 모든 예외의 슈퍼클래스

- Error/Runtime Exception은 비검사 예외들의 슈퍼클래스

- Exception은 검사 예외 Exception들의 슈퍼클래스


- 비검사 예외(Unchecked Exception): Runtime Exception(실행해야 잡을 수 있는 오류)

ex) ClassNotFoundException : 클래스를 로더를 실행하는 도중 발생(컴파일 후 .class 파일을 JVM에 올리는 과정)

- 검사 예외(Checked Exception) : Compile Exception(실행하기 전(compilie 중)에 잡아 낼 수 있는 오류)

컴파일러가 exception을 검사하면 검사 예외, 검사하지 않으면 비검사 예외


---

### 검사 예외 선언하기


- 메소드 선언부의 throws 절에 던질 예외를 나열한다.(해당 메소드에서 throw를 하던지, 호출하는 쪽으로 throws를 하던지)

- 나열할 exception들이 여러 개면 공통의 슈퍼클래스를 대신 명시할 수 있다.

- 해당 메소드를 오버라이딩 한 메소드에서는 슈퍼클래스 메소드의 exception보다 상위 exception, 관련 없는 exception을 잡을 수 없다.

---

### 예외 잡기

#### try-catch

	try{
	
	} catch(Exception클래스 exception변수) {
	
		핸들러1
	} catch(Exception클래스 exception변수) {
	
		핸들러2
	} catch(Exception클래스 exception변수) {
	
		핸들러3
	}


핸들러를 여러 개 두어서 여러 개의 Exception을 잡을 수 있다. 위에서부터 순서대로 확인하기 때문에 위에부터 가장 세부(서브) exception부터 확인하는 것이 좋다.

못잡으면 더 큰범위에 있는 exception에서 확인하는게 상식적으로 맞으니까



	try{
	
	} catch(exception클래스 | exception클래스 | exception클래스 exception변수) {
		핸들러
	}

이렇게 사용할 경우에는 나열된 exception클래스를 던지는 메소드를 try에서 호출해야지만 exception변수를 호출할 수 있다.??

---

#### try-with-resource

- try문에서 exception을 도중에 catch할 때 사용중이던 resource의 정리가 필요한 경우 사용한다.

(정리 없이 핸들러로 넘어가 버리면 파일의 경우 일부만 쓰기가 될 수도 있는 비정상적인 경우가 발생한다.)

- resource가 되는 클래스는 반드시 AutoCloseable 인터페이스를 구현한 클래스여야 한다.

- try문에서 도중에 exception을 catch하거나 끝까지 실행되 종료되었을 경우 AutoCloseable 인터페이스의 close메소드를 강제로 호출하기 때문이다.

- 그렇기 때문에 따로 close메소드를 호출하지 않아도 된다.

ex
	try(PrintWriter out = new PrintWriter("output.txt")){
		
	} catch(FileNotFoundException ex) {
		
	}


##### resource가 두 개 이상인 경우

	try(Scanner in = new Scanner(System.in); PrintWriter out= new PrintWriter("output.txt")) {
		실행문
	}


- resource가 두 개 이상이 경우에는 초기화의 역순으로 close메소드가 실행된다. (위의 예제에서는 out-> in 의 순서)

- close 메소드에서 예외를 클래스들도 있다. exception을 catch하거나 try문이 종료되어 close메소드가 실행될 때 exception이 발생하면 해당 예외를 호출한 쪽으로 exception를 던진다.


- close에서 발생하는 exception은 원래 잡으려는 exception(Primary Exception)들보다 중요도가 떨어진다. 주 예외를 잡고 난 이후에 발생하는 exception이기 때문이다.


	try{
	
	} catch(IOException ex) {
		Throwable[] secondaryexceptions = ex.getSuppressed();
	}


위 처럼 부 예외(Secondary Exception)를 추출할 수 있다.

---

### finally

- try문에서 exception을 잡던지, 끝까지 실행하고 종료하던지 마지막으로 실행할 코드를 작성한다.

- try문에 return이 있어도 finally를 실행한다.

왜??




- try-catch문에 return이 있어도 finally의 코드를 실행한다.

- finally안에서 변수 값을 수정할 경우 기본형 변수는 return 이후에 변경이 적용되고, 인스턴스는 return 이전에 적용된다.(return 이후에는 기본형/인스턴스 모두 변경되어있다.)

왜??

try-catch에 return 이 있을 경우

기본자료형 : 새로운 변수에 값을 복사

인스턴스 : heap영역에 올라간 주소를 복사

한 후 finally를 실행을 하고 기본형/인스턴스의 값을 변경 후 종료되면

기본형 변수는 복사된 새로운 변수를 return(finally에서 변경된 값 return에 반영X)

인스턴스는 참조하는 주소 값에 있는 인스턴스의 값을 변경(finally 변경된 값 return에 반영O)

---

스택 추적(Stack Trace)

- 메소드 실행 도중에 exception이 발생했을 때 stack 영역에 남아있던 메소드 들을 출력한다.
 
	public static void func1() {
		System.out.println("func1 start");
		func2();
		System.out.println("func1 end");
	}

	public static void func2() {

		System.out.println("func2 start");
		func3();
		System.out.println("func2 end");
	}
	
	public static void func3() {

		System.out.println("func3 start");
		
		try{
			
			Class<?> cl = Class.forName("myClass");
			
		} catch(ClassNotFoundException ex) {
			
			StackTraceElement[] frames = ex.getStackTrace();
			
			for(StackTraceElement frame : frames) {
				System.out.println(frame);
			}
			
			ex.printStackTrace();
		}
		

		System.out.println("func3 end");
	}


출력

	func1 start
	func2 start
	func3 start
	java.lang.ClassNotFoundException: myClass
		at java.net.URLClassLoader.findClass(Unknown Source) // 확장/시스템 클래스로더 인스턴스
		at java.lang.ClassLoader.loadClass(Unknown Source)
		at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source) // 시스템 클래스로더
		at java.lang.ClassLoader.loadClass(Unknown Source)
		at java.lang.Class.forName0(Native Method)
		at java.lang.Class.forName(Unknown Source)
		at chapter5.src.StackTraceTest.func3(StackTraceTest.java:30)
		at chapter5.src.StackTraceTest.func2(StackTraceTest.java:20)
		at chapter5.src.StackTraceTest.func1(StackTraceTest.java:13)
		at chapter5.src.StackTraceTest.main(StackTraceTest.java:7)
	func3 end
	func2 end
	func1 end

- frame : trace에 찍히는 한줄 한줄
- stackTrace는 frame들이 스택영역에 쌓여있는 순서대로 맨 위부터 출력한 것이다.


ex.stacktrace()의 결과와

	StackTraceElement[] frames = ex.getStackTrace();
	
	for(StackTraceElement frame : frames) {
		System.out.println(frame);
	}

의 결과는 같다.

- stackTrace를 찍지 않아도 console창에 나오는 이유 : IDE에서 처리해주는 것. IDE를 사용하지 않는 서버에서는 직접 찍어서 파일에 저장하고 나중에 로그를 확인하여 에러를 찾는다.

- 


ClassLoader 클래스

- 부트스트랩 클래스로더의 역할을 한다.

- ClassLoader 인스턴스는 각각 부모 ClasLoader인스턴스를 갖고 있다.


---

Launcher$AppClassLoader 클래스

- 시스템 클래스로더

- URLClassLoader를 상속받는다.

- CLASSPATH에 있는 각가의 디렉토리나 .jar파일을 URL로 변환하여 AppClassLoader에 전달되며, AppClassLoader의 생성자에서는 이 URL을 URLClassLoader 생성자에 전달한다.

---

#### Objects.requireNonNull

	public void func3(String nullString) {
		
		this.direction = Objects.requireNonNull(nullString,"executed requireNonNull");
		...
	}


출력

	func1 start
	func2 start
	Exception in thread "main" java.lang.NullPointerException: executed requireNonNull
		at java.util.Objects.requireNonNull(Unknown Source)
		at chapter5.src.NonNullTest.func3(NonNullTest.java:32)
		at chapter5.src.NonNullTest.func2(NonNullTest.java:26)
		at chapter5.src.NonNullTest.func1(NonNullTest.java:19)
		at chapter5.src.NonNullTest.main(NonNullTest.java:13)

requiredNonNull이 호출되었기 때문에 문제의 원인을 찾기 수월 해졌다.


---	


### 단정(Assertion)

- 변수의 값이 가져야할 범위, 특정한 값을 가지는지 확인하기 위한 용도(프로그램의 신뢰도를 높여준다.)

- 런타임 단계에서 실행된다.

- -ea옵션으로 단정을 활성화 시켜줘야 한다.

java -ea:클래스명 : 특정 클래스에서만 assertion을 활성화 시킨다.

java -da:클래스명 : 특정 클래스에서만 assertion을 비활성화 시킨다. 


사용방법


	int a = 11;
	
	assert a < 10;


출력
	
	Exception in thread "main" java.lang.AssertionError
		at chapter5.src.AssertTest.main(AssertTest.java:9)

---

	int a = 11;
	
	assert a < 10 : " a must be < 10";

출력

	Exception in thread "main" java.lang.AssertionError:  a must be < 10
		at chapter5.src.AssertTest.main(AssertTest.java:9)


---