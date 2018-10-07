# 인터페이스

서비스 공급자와 자신의 객체가 해당 서비스를 사용할 수 있게 하려는 클래스가 있을 때, 이두 클래스 사이의 계약을 표현하는 메커니즘

```
public interface IntSequence {
   boolean hasNext();
   int next();
}
```

인터페이스 내 모든 메소드는 자동으로 public이 된다. 의도를 더 명확히 드러내기 위해 public을 작성해도 무관하다.


인터페이스에 있는 메서드로도 average 메서드를 구현 할 수 있다.

IntSequence를 implements한 클래스가 인자 seq로 들어가야 한다.

```
public static double average(IntSequence seq, int n) {
   int count = 0;
   double sum = 0;
   while(seq.hasNext() && count < n) {
	count++;
	sum += seq.next();
   }
   return count == 0 ? 0 : sum / count;
}
```

--------

```
public class SquareSequence implements IntSequence {

   private int i;

   public boolean hasNext() {
	return true;
   }

   public int next() {
	i++;
	return i * i;
   }
}
```

구현하려는 클래스는 인터페이스의 메서드를 반드시 public으로 선언해야 한다. 그렇지 않으면 메서드는 기본적으로 패키지 접근이 된다. 하지만 인터페이스는 공개(public)접근이므로 컴파일러가 오류를 보고할 수 있다.

------------

## 여러 인터페이스 구현

클래스는 인터페이스를 몇 개든 구현할 수 있다.

public class FileSequence implements IntSequence, Closeable {
   ...
}

FileSequence 클래스는 IntSequence와 Closeable을 슈퍼타입으로 둔다.


------------

## 상수

인터페이스에서 정의한 변수는 자동으로 public static final이 된다.

```
public interface SwingConstants {
   int NORTH = 1;
   int NORTH_EAST = 2;
   int EASE = 3;
   ...
}
```

위의 3개의 변수는 상수로 취급한다.

-----------------

## 정적 메서드

intSequence digits = IntSequence.digitsOf(1729);

IntSequence 인터페이스를 구현한 클래스 중에서 구현된 메소드를 호출하지만 어떤 클래스여도 상관없다.

```
public interface IntSequence {
   ...
   public static IntSequence digitsOf(int n) {
	return new DigitSequence;
   }
}
```

-------

## 기본 메서드

```
public interface IntSequence{
   default boolean hasNext() { return true; }
}
```

이 인터페이스를 구현한 클래스는 hasNext메서드를 오버라이드하거나 기본구현을 상속해야한다.

----------

## 기본 메서드의 충돌

두 개 이상의 인터페이스를 구현하는 클래스에서 인터페이스들에 같은 이름, 파라미터를 가진 기본 메서드를 가지고 있을 때 확실히 구분해줘야 한다.

```
public class Employee implements Person, Identified {
   public int getId() { return Identified.super.getId(); }
}
```

구현하는 클래스에서 직접 구현하거나, 메서드를 구현하지 않고 abstract로 선언하면 된다.

--------------

## Comparable 인터페이스

숫자의 크기, 문자열의 알파벳순으로 정렬 할 때 사용한다.

```
public class Employee implements Comparable<Employee> {
   ...
   public int compareTo(Employee other) {
	return Integer.compare(getId(), other.getId());
   }
}
```

return getId() - other.getId(); 의 경우 인자 중에 음수가 있을 경우 제대로 작동하지 않는다.

-------

## Comparator 

문자열의 경우 사전 순서가 아닌 증가하는 길이 순서로 비교해야 한다면 Comparator인터페이스를 구현해야 한다.

```
public interface Comparator<T> {
   int compare(T first, T second);
}

class LenfthComparator implements Comparator<String> {
   public int compare(String first, String second) {
	return first.length() - second.length;
   }
}

String[] test = {"asda", "bv", "cdsfad", "d"};
		
for(int i = 0; i < test.length; i++) {
	System.out.print(test[i] + " ");
}
		
Arrays.sort(test, new LengthComparator());		

for(int i = 0; i < test.length; i++) {
	System.out.println(test[i] + " ");
	}

```

## Runnable 인터페이스

특정 작업(Task)을 별도의 스레드에서 실행하거나 실행 스레드 풀에 넣어야 할 경우에 사용한다.(멀티스레드 사용)
run() 메서드 한 개만 갖는다.

```
class HelloTask implements Runnable{
   public void run() {
	for(int I = 0; I < 1000; I++) {
	   System.out.println(“Hello, World!”);
	}
   }
}

Runnable Task = new HelloTask();
Thread thread = new Thread(Task);
thread.start();
```

# 람다표현식(파라미터 변수가 있는 표현식)

(String first, String second) -> first.length() - second.length()


(String first, String second) -> {
   int difference = first.length() - second.length();
   if(difference < 0) return –1;
   else if (difference > 0) return 1;
   else return 0;
}

Runnable task = () -> { for (int i = 0; i <1000; i++) doWork(); }

람다 표현식의 파라미터 타입을 추론할 수 있다면 파라미터 타입을 생략할 수 있다.

```
Comparator<String> comp = (first, second) -> first.legnth() - second.length();
```

## 함수형 인터페이스

람다 표현식은 추상 메서드가 한 개만 포함된 인터페이스에만 사용할 수 있는데, 이러한 인터페이스를 함수형 인터페이스라고 한다.

```
Arrays.sort(words, (first, second) -> first.length() - second.length());


public interface Predicate<T> {
   boolean test(T t);
   // 추가적인 기본 메서드와 정적 메서드
}
```

ArrayList의 removeIf 메서드는 파라미터로 Predicate 인터페이스를 받는다.

list.removeIf(e -> e == null);


## 메서드 참조
```
Arrays.sort(strings, (x, y) -> x.compareToIgnoreCase(y));
```

이 코드 대신 다음 메서드 표현식을 전달할 수도 있다.

```
Arrays.sort(strings, String::compareToIgnoreCase);

String::compareToIgnoreCase 는 람다표현식 (x, y) -> x.compareToIgnoreCase(y)에 대응하는 메서드 참조다.
```

세가지 형태

클래스::인스턴스메서드
클래스::정적메서드
객체::인스턴스메서드


## 생성자 참조

메서드의 이름이 new라는 점만 제외하면 메서드 참조와 같다.

```
Stream<Employee> stream = names.stream()map(Emplyee::new);
```

## 지연 실행

람다를 사용하는 핵심목적은 지연 실행이다.

지연실행하는 이유
1. 별도의 스레드에서 코드실행
2. 코드를 여러 번 실행
3. 알고리즘의 올바른 지점에서 코드실행(ㅇ이를테면 정렬에서의 비교연산 같은 경우)
4. 어떤 일(버튼클릭, 데이터 수신 등)이 일어날 때 코드실행
5. 필요할 때만 코드실행



몇 번째 반복을 수행하고 있는지 액션에 알리려고 한다.

```
public interface IntConsumer{
   void accept(int value);
}

public static void repeat(int n, IntConsumer action) {
   for(int i = 0; i < n; i++) action.accept(o);
}

repeat메서드를 호출하는 방법

repeat(10, i -> System.out.println(“Countdown : ” + (9 – i)));
```

## 자신만의 함수형 인터페이스 구현하기

표준 함수형 인터페이스가 적합하지 않은 상황이 생기면 직접 인터페이스를 구현해야 한다.

```
@FunctionalInterface
public interface PixelFunction {
   Color apply(int x, int y);
}

BufferedImage createImage(int width, int height, PixelFunction f) {
   BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

   for(int x = 0; x < width; x++)
	for(int y = 0; y < height; y++) {
	   Color color = f.apply(x, y);
	   image.setRGB(x, y, color.getRGB());
	}
   return image;
}
```

이 함수를 호출하려면 람다 표현식을 전달해야 한다.

```
BufferedImage franchFlag = createImage(150, 100, (x, y) -> x < 50 ? Color.BLUE : x < 100 ? Color.WHITE : color.RED);
```

## 람다 표현식의 유효 범위

람다 안에서 지역변수와 이름이 같은 파라미터나 지역변수와 이름이 같은 지역변수를 선언하면 안된다.

```
int first = 0;
Comparator<String> comp = (first, second) -> first.length() - second.length();
   // first변수가 이미 지역변수로 선언되어 있으므로 오류가 발생한다.
```


## 지역클래스

메서드 안에 정의 된 클래스다.

```
private static Ramdom generagor = new Random();

public static IntSequence randomInts(int low, int high) {
   class RandomSequence implments IntSequence {
	public int next() { return low + generator.nextInt(high – low + 1); }
	public boolean hasNext() {return true;}
   }

   return new RandomSequence();
}
```

지역 클래스는 메서드 바깥에서 접근할 수 없으므로 public이나 private으로 선언할 수 없다.


## 익명 클래스

특정 목적으로 딱 한번만 사용하는 경우 클래스를 익명으로 만들 수 있다.

```
public static IntSequence randomInts(int low, int high) {
   return new IntSequence() {
	public int next() { return low + generator.nextInt(high – low + 1); }
	public boolean hasNext() { return true; }
   }
}
```
