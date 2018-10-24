# 제네릭 프로그래밍

### 제네릭 클래스

타입 파라미터(K, V) 한 개 이상을 받는 클래스

	public class Entry<K,V> {
		...
	}
	
타입 파라미터의 자리에는 래퍼클래스가 와야된다(기본 타입 불가능)


### 제네릭 메서드

타입파라미터를 한 개 이상 받는 메서드 

	public static <T> void swap(T[] array, int i, int j) {
		...
	}

### 타입 경계

타입 파라미터로 받는 파라미터의 타입을 제한 하는 것

	public static <T extends AutoCloseable> void closeAll(ArrayList<T> elems) throws Exception {
		for(T elem : elems) elem.close();
	}
	
	AutoCloseable의 서브타입(ex) PrintStream)만 타입파라미터 자리에 올수 있다.
	

### 와일드 카드

그냥 T로 하는거랑 무슨차이지...?


### 제네릭의 제약

1. 기본타입 인자가 없다

- 래퍼클래스만 인자로 받을 수 있다.

2. 실행시간에는 모든 타입이 row형태다.

3. 타입변수의 인스턴스를 만들 수 없다.

4. 파라미터화 된 타입의 배열을 생성할 수 없다.

Entry<String, Integer>[] entries = new Entry<String, Integer>[100]; 와 같은 형태로 생성할 수 없다.

5. 정적 컨텍스트에서는 클래스 타입 변수가 유효하지 않다.

6. 메서드가 소거 후 충돌하지 않을 수도 있다.