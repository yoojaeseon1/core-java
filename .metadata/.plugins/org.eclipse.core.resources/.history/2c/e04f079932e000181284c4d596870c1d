# 기본 프로그래밍 구조

###HelloWorld

	public class HelloWorld {
	
		public static void main(String[] args) {
		
			System.out.println("Hello, world");
		}
	}

HelloWorld : 클래스 명

main : 메서드 명(프로그램이 실행될 때 첫 번째로 호출되는 메서드)

static : 특정 객체를 대상으로 동작하지 않게 한다(C의 global)

void : 반환 값이 없다.


###자바 프로그램 컴파일, 실행


javac로 컴파일 > java로 실행


1단계 : javac(java compiler) 명령으로 소스코드를 바이트코드(중간표현)로 컴파일하여 class파일에 저장한다.

ex) javac HelloWorld.java (실행시 동일 디렉토리에 HelloWorld.class 파일이 생성)


2단계 : java(jvm 런처) 명령으로 jvm을 구동해 클래스 파일의 바이트 코드를 실행

ex) java HelloWorld



### 메서드 호출

객체.메서드명(인자목록)

	System.out.println(“Hello, World”);

    “HelloWorld”.length();

System.out은 객체이자 PrintStream 클래스의 인스턴스이고 PrintStream 클래스에 println함수가 정의되어있다.

“HelloWolrd”는 String 클래스의 인스턴스이고 String 클래스에 length() 함수가 정의되어 있다.


### 기본 타입(간단한 데이터 타입)

##### 정수타입

소수점 없는 숫자, 음수도 허용

int 4bytes –2,147,483,648 ~ 2,147,483,647(20억 이상)

long 8bytes –9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807

short 2bytes –32,768 ~ 32,767

byte 1byte –128 ~ 127

long 정수 리터럴 : 접미어 L을 붙여 작성한다.

ex ) long test = 40000000L


##### 부동 소수점

float 4bytes +-3.40282347E+38F(유효자릿수 6~7)

double 8bytes +-1.79769313486231570E+308(유효자릿수 15)

부동소수점 수가 많을 때는 float, 소수점 자리가 많을 때는 double을 사용한다.


##### char타입

UTF-16 문자 인코딩의 ‘코드유닛(코드 단위)’, 작은 따옴표로 감싼다.

ex) char a = ‘J’;


##### boolean 타입

false와 true 두 가지 값을 가진다.


### 변수

##### 선언

변수의 타입과 이름을 지정

ex) int total = 0;

int : 타입
total : 이름
= 0 : 변수의 값을 초기화

ex) Random generator = new Random();

첫 번째 Random은 generator의 타입, 두 번째는 new 표현식(클래스의 인스턴스 생성)

#####이름

낙타표기법이 일반적이다.(camelCase)

낙타표기법 : 여러 단어로 구성되어 있을 때 각 단어의 첫 글자를 대문자로 쓰는 것(조금 길어져도 이해하기 쉽게 정하자)

ex) countOfInvalidInputs


##### 상수

한 번 할당하면 변경할 수 없는 값, final 키워드를 사용한다. 변수명은 대문자로 작성하는 것이 관례다.

한 번만 초기화를 할 수 있으므로 선언만 하고 나중에 초기화해도 상관없다.

ex) 		
final int DAYS_IN_FEBRUARY;	
if(leapYear) {
	DAYS_IN_FEBRUARY = 29;
} else {
	DAYS_IN_FEBRUARY = 28;
}


### 산술연산


##### 숫자 타입 변환

1. 피연산자 중 하나가 double 타입이면 다른 하나를 double로 변환한다.
2. 피연산자 중 하나가 float 타입이면 다른 하나를 float로 변환한다.
3. 피연산자 중 하나가 long 타입이면 다른 하나를 long로 변환한다.
4. 그 외에는 두 피연산자를 int로 변환한다.

정보손실이 없는 경우는 언제나 합법적인 변환

ex) byte에서 short, int, long double 로 변환
short에서 short, int, long double 로 변환
int에서 long double 로 변환

주의) float f = 123456789; 의 경우 f는 실제로 1.234567892E8이 된다.

##### 관계 연산자(피연산자가 2개)

a == b : a와 b가 같으면 true
a != b : a와 b가 다르면 true
<(작음), >(큼), <=(작거나 같음), >=(크거나 같음)

##### 논리 연산자(피연산자가 2개)

&& : 논리곱(AND)
|| : 논리합(OR)
! : 논리부정(NOT)

ex) 0 <= n && n < length

##### 조건 연산자(피연산자가 3개)

- 조건이 true면 첫 번째 값이 결과, false면 두 번 째 갑이 결과가 된다

ex) time < 12 ? “am” : “pm” 


##### 큰숫자

long(정수형)이나 double(부동소수점) 타입의 크기가 충분하지 않은 경우 BigInteger, BigDecimal클래스로 전환한다.

정수형
ex) BigInteger n = BigInteger.valueOf(8765432101234526789L);
- long형을 BigInteger로 변환

자바는 객체에 연산자를 사용할 수 없으므로 반드시 메서드를 호출해야 한다.

BigInteger r = BigInteger.valueOf(5).multiply(n.add(k)); // r=5*(n+k)

부동소수점
ex) BigDecimal.valueOf(2,0).subtract(BigDecimal.valueOf(11,1)); // result : 0.9


### 문자열

##### 문자열 연결

+ 연산자로 문자열을 연결할 수 있다.

String location = “java”;
String greeting = “Hello ” + location // result : “Hello java” 


int age = 42;
String output = age + “ years”; // result : “42 years”

##### 부분 문자열

String greeting = “Hello, World!”;
String location = greeting.substring(7,12) // location의 값은 “World” 가 된다.

String name = “peter, Paul, Mary”;
String[] result = names.split(“, ”); // [“Peter”,“Paul”,“Mary”] 가 된다.

##### 문자열 비교

location.equals(“World”); // location이 “World”면 true를 반환한다.

주의)

location == “World” 와 같은 형식으로 비교하면 안된다!!
location과 “World”가 메모리상에서 같은 객체일 때만 true를 출력(jvm에서 각 리터럴 문자열의 인스턴스는 오직 한 개씩만 있기 때문에 true가 나올 수 없다. “World”==“World”일 경우에만 true)

String의 경우 null값을 초기화 할 수 있다.

ex) String middleName = null;

문자열과 리터럴 문자열을 비교할 때는 리터럴 문자열을 앞쪽에 두는 게 좋다

ex) if(“World”.equals(location))... // location이 null일 경우에도 정상적으로 동작(물론 false를 반환)

##### 숫자와 문자열 사이의 반환

정수를 문자열로 : Integer.toString(정수)

문자열을 정수로 : Integer.parseInt(문자열)

부동소수점 수를 문자열로 : Double.toString(3.14);

문자열을 부동소수점 수로 : Double.parseDouble(“3.14”);


### 입력과 출력

##### 입력 읽어오기

표준입력스트림으로 읽기를 할 경우 System.in은 바이트 한 개를 읽어오는 메서드이기 때문에 문자열과 숫자를 읽으려면 System.in에 연결된 Scanner를 생성해야 한다.

Scanner in = new Scanner(System.in);

##### 한줄 읽기

String name = in.nextLine();

##### 공백으로 구분된 단어 한 개

String firstName = in.next();

##### 정수형

int age = in.nextInt();


##### 서식 지정출력

System.out.printf(“%8.2f”, 1000.0/3.0); // 전체 8자리 중 2자리가 소수부

결과

   333.33 (앞 두칸은 빈칸)

### 제어 흐름

분기(if문, switch문)

##### if문

if(조건) {
   분기
} else if(조건) {
   if다음으로 확인할 분기
} else {
   if와 else if의 조건에 맞지 않은 조건을 실행할 분기
}

ex)

if(count > 0) {
   double average = sum / count;
   System.out.println(average);
} else if(count == 0) {
   System.out.println(0);
} else {
   System.out.println(“Huh?”);
}


##### switch문

제한된 개수의 상숫값을 기준으로 표현식을 검사할 때 사용한다.

switch(count) {
   case 0:
	output = “None”;
	break;
   case1:
	output = “One”;
	break;
   case2:
   case3:
   case4:
   case5:
	output = Integer.toString(count);
	break;
   default:
	output=“many”;
	break;
}

일치하는 case 레이블을 실행하고 일치하는 항목이 없을 경우 default레이블(있으면)을 실행한다.

각 case별로 break문을 빠뜨리면 일치하는 레이블을 실행하고 다음 case문으로 내려가서 조건을 확인한다.

case 레이블에 사용할 수 있는 타입

char, byte, short, int
문자열 리터럴
열거 값

### 루프

##### while문

루프의 개수가 고정되지 않을 경우에 적합하다.

Random generator = new Random();

while(sum < target) {
   int next = generator.nextInt(10);
   sum += next;
   count++;
}

##### do/while문 

조건을 평가하기 전에 루프 구현부를 실행해야 할 경우

int next;
do{
   next = generator.nextInt(10);
   count++;
} while(count < 10);


##### for문

루프의 반복횟수가 i고정되어있을 때 적합하다.

for(int i = 0; i <= 20; i++) {
   int next = generator.nextInt(10);
   sum += next;
}

선언부에서 변수를 선언하는 대신 기존 변수를 초기화 할 수도 있다.

for(i = 1; i <= target; i++) // 기존 변수 i를 사용한다.
for(int i = 0, j = n – 1; i < j; i++, j--) // 여러 변수를 선언/초기화 하고 여러 변수를 업데이트 할 수있다.
for(;;) // 무한루프

##### 중단과 계속

루프를 중간에 빠져나오기 위해서는(루프의 끝으로 이동) break문을 사용해야 한다.

while(true) {
   String input = in.nextInt();
   if(input.equals(“Q”)) break; // 표준 입출력으로 “Q”를 입력했을 경우 반복문을 빠져나온다.
}

현재 루프 반복의 끝으로 건너뛰기 위해서는 continue문을 사용한다.

for(int i = 1; i <= target; i++){
   int input = in.nextInt();
   if(n < 0) continue; // i++로 건너뛴다.
}

outer:
while(...) {
   while(...) {
	...
	if(...) break outer;
   }
}
// 레이블을 붙인 break는 이 위치로 건너뛰게 한다.


##### 지역 변수의 유효 범위

지역변수의 유효범위는 번수 선언 지점에서 해당 선언을 감싸고 있는 블록의 끝까지다.

while(...) {
   System.out.prinltn(...);
   String input = in.next() // input의 유효범위 시작
   ...
   // input의 유효범위 종료
}


public static void main(String[] args) { // args의 유효범위 시작
   ...

   // args의 유효범위 종료
}


### 배열과 배열리스트(ArrayList)

##### 배열

같은 타입의 아이템(항목)을 모으는 데 사용한다.

선언

String[] names;

초기화

names = new String[100];

선언 & 초기화

String[] names = new String[100];

new 연산자로 배열을 선언하면 배열이 기본 값으로 채워진다.
(char를 포함한) 숫자타입은 0으로
boolean타입은 false로
객체 배열은 null로 채워진다.

원하는 값을 알고 있을 때 선언&초기화

int[] primes = {2,3,5,7,11,13}

##### 기본타입의 래퍼 클래스

기본타입 : int, byte, short, long, char, float, double, boolean
래퍼클래스 : Integer, Byte, Short, Long, Charactor, Float, Double, Boolean

ex) ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(42);
int first = numbers.get(0);

제네릭클래스는 기본타입(int)을 타입파라미터로 사용할수 없기 때문에 int의 래퍼클래스인 Integer를 사용했다.

42는 int형이므로 오토박싱(autoboxing)과정을 거쳐 42를 담는 Integer객체가 자동으로 만들어진다.

get함수는 Integer 객체를 반환하지만 int에 할당하기위해 이 객체 내부의 int값을 돌려주도록 언박싱(unboxing)된다.

래퍼 타입의 경우 ==, != 연산자를 사용해서 비교할 수 없다. equals 메서드를 호출해야한다.
==,!= 연산자는 객체의 내용이 아니라 객체 참조를 비교하기 때문


##### 향상된 for 루프

int sum = 0;
for(int n : numbers) {
   sum += n;
}

배열의 인덱스 값이 아니라 요소를 순회한다. 변수 n 은 numbers[0], numbers[1]등을 할당 받는다. 배열 리스트에도 사용 가능하다.


### 배열, 배열리스트의 복사

int[] numbers = primes;
numbers[5] = 42; // primes[5]도 42가 된다.

위와 같은 방식으로 초기화 할 경우 두 배열은 같은 배열을 참조한다.

하나의 배열을 공유하지않고 사본을 만들기 위해서는 Arrays.copyOf를 사용한다.

int[] copiedPrimes = Arrays.copyOf(primes, primes.length);


ArrayList<String> people = friends;
people.set(0, “mary”); // freiends의 0번째 인덱스도 “mary”가 된다.

배열 리스트도 같은 방식으로 동작하므로 사본을 만들기 위해서는 다음과 같이 초기화한다.

ArrayList<String> copiedFriends = new ArrayList<>(friends);


배열을 배열리스트로 복사할 경우

String[] name = “....”;
ArrayList<String> friends = new ArrayList<>(Arrays.asList(names));


### 명령줄 인자

public static void main(String[] args) {
   ...
}

command창에서 실행할 경우

java 클래스명 arg1 args2 arg3...과 같은 방식으로 입력해 main문에서 args[0], args[1], args[2]...로 사용할 수 있다.


### 다차원 배열

배열의 배열로 구성한다.

	int[][] square = {
   		{16,3,2,13},
   		{3,10,11,8},
   		{9,6,7,12},
   		{4,15,14,1}
	};


요소에 접근하기 위해서는 대괄호 쌍 두 개를 사용한다.

	int element = square[1][2];

초기값을 지정하지 않을 때는 반드시 new 연산자를 사용하고 행과 열의 개수를 지정해야 한다.

	int[][] square = new int[4][4];

배열 리스트도 다차원 배열과 같은 형식으로 선언할 수 있다.

ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();


### 가변인자

메서드의 인자의 개수를 고정하지 않고 받을 수 있도록 정의할 수 있다.

public static double average(double... values)

values가 double형 배열로 메서드 안에서 사용된다. double형 배열을 인자로 넣어도 무관하다.

public static double average(double value, double... values)

인자를 최소 1개는 받아서 실행하도록 만들 수 있다.