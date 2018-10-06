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
