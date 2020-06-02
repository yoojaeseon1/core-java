### 제네릭

#### 제네릭 클래스

- 클래스 정의할 때 타입 파라미터를 1개 이상 받는 클래스

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

#### 타입 경계

- 타입 파라미터로 받을 수 있는 타입을 제한 할 수 있다.

	public static <T extends AutoCloseable> void closeAll(List<T> elements) throws Exception{

		for(T element : elements) {
			element.close();
		}
	}


closeAll의 타입파라미터는 AutoCloseable의 서브타입으로 제한된다.