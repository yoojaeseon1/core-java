## Stream

- java 8 버전에서 나왔다.(람다와 함께 등장)

- 특징

	- 지연 연산
	- Collection 인터페이스에 구현되어 있다.
	- 스트림 자체에 데이터를 추가할 수 없다.(스트림을 지원하는 Collection에 저장 가능)
	- 스트림 연산은 원본(Collection 객체)를 변경하지 않는다.
	- "어떻게 처리할 것인가"가 아니라 "무엇을 처리할까"에 초점이 맞춰져 있다.

<br/>

	List<String> words = new ArrayList<>();
	
	long count = words.stream() // 또는 words.parallelStream()
			.filter(w->w.length() > 12) // 이 조건에 맞게
			.count(); // count를 실행(무엇을 처리할지에 초점이 맞춰져 있다.)
	
	System.out.println(count);


parallelStream을 사용하면 필터링과 카운팅을 병렬로 수행한다.

### parallelStream

- 연산을 병렬로 처리할 수 있게 해준다.(multi-thread)
- 1 프로세서당 1 스레드를 생성해 사용한다.

---

### Stream 생성

	String[] array = {"a", "b"};
	
	Stream<String> words = Stream.of(array); // 가변 인자 파라미터이기 때문에 배열도 받을 수 있다.

	Stream<String> numbers = Stream.of("a", "b");
	
	String<String> silence = Stream.empty(); // element가 없는 stream을 반환한다.

	(빈 스트림은 element가 없을 때 null대신 사용할 수 있다.)


generate메소드는 파라미터로 함수형 인터페이스인 Supplier<T> 받는다.(파라미터가 없고 T를 리턴하는 메소드 get이 있다.)

	Stream<String> echos = Stream.generate(() -> "echo");
	
	Stream<Double> randoms = Stream.generate(Math::random);


타입은 Stream의 제네릭 타입으로 추론하게 된다.

---

### iterate(seed, function)

seed 값을 function에 적용하고 function에 적용되어 리턴 된 값을 계속해서 function에 적용시킨다.

무한스트림을 만들 때 사용한다.

무한 수열을 만들려면

	Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n-> n.add(BigInteger.ONE));

	// 12345..... 무한수열이 만들어진다.

맨처음 seed인 0을 1과 더한다. function의 결과 값인 1에 1을 더하고, 또 다시 결과 값인 2에 1을 더하고..를 반복한다.

해당 함수를 이전 결과에 반복하여 적용하는 iterate 메소드를 사용할 수도 있다.

---

### filter

- Collection 객체에서 특정 조건에 맞는 element만 뽑아서 stream을 만들고 싶은 경우에 사용할 수 있다.

	words.add("1234");
	words.add("5678567");
	words.add("9123");
	
	Stream<String> longWords = words.stream().filter(w->w.length()>5);

filter메소드의 파라미터는 Predicate<T>이므로 T를 받아 boolean을 리턴하는 메소드다.

---

### map

- Collection 객체에서 element들을 원하는 방식으로 변환해서 stream을 만들고 싶은 경우에 사용할 수 있다.

	Stream<String> lowercasesWords = words.stream().map(String::toLowerCase);
	
	Stream<String> firstLetters = words.stream().map(s -> s.substring(0,1));


List인 words의 element를 모두 소문자로 바꾸거나 첫 문자만 뽑아서 stream을 만들 수 있다.


#### map과 filter를 동시에 사용할 수도 있다.

- map으로 변환한 결과에서 filter를 적용해 조건에 맞는 것만 추출하는 것
- filter를 통해 조건에 맞는 것을 추출한 후 map으로 변환

filter를 여러개, map을 여러개 붙여서 사용할 수도 있다.

	Stream<String> stream = words.stream().filter(s->s.charAt(0)=='1').map(s->s.substring(0,3));

	Stream<String> stream = words.stream().map(s->s.substring(0,4)).filter(s->s.charAt(0) == '5');

---

### flatMap

- 배열로 감싸져 있는 모든 element를 단일 element stream으로 변환해준다.

	
	// String 객체에서 문자 하나하나로 나눈 stream을 리턴한다. 
	public static Stream<String> letters(String s) {
		List<String> result = new ArrayList<>();
		for(int sI = 0; sI < s.length(); sI++) {
			result.add(s.substring(sI, sI+2));
		}
		return result.stream();
	}

	
words에

	words.add("1234");
	words.add("5678567");
	words.add("9123");

를 했다면

	Stream<Stream<String>> result = words.stream().map(w->letters(w));


[["12","34"],["56","78"],["91","23"]]

과 같은 stream이 만들어진다.

대신 flatMap을 사용한다면

	Stream<String> flatResult = words.stream().flatMap(w->letters(w));

["12","34","56","78","91","23"]

배열단위를 무시해 단일 element로 stream이 만들어진다.

여러 배열에 들어있는 element를 하나의 배열로 보고 원하는 element를 찾거나 변환할 때 유용하다

---

### 서브스트림 추출

스트림.limit(n) : 첫 번째 ~ n개로 만든 stream 리턴

스트림.skip(n) : 처음부터 n개의 element를 제외한 stream 리턴

	String[] words = {"1","2","3","4","5"};
	
	Stream<String> wordsStream = Stream.of(words).skip(2); // [3,4,5]
	
	wordsStream = Stream.of(words).limit(3); // [1,2,3]

Stream.concat(스트림, 스트림) : 두 스트림을 연결한 스트림을 리턴한다.

	Stream<String> combined = Stream.concat(letters("Hello"), letters("World")); 
	// ["H", "e", "l", "l", "o", "W" "o", "r", "l", "d"] 리턴

---

### 중복을 제거한 stream 추출

스트림.distinct() : 원본 스트림에서 중복을 제거한 스트림을 반환한다. 순서는 동일하고, 중복은 인접해 있지 않아도 해당된다.

	Stream<String> uniqueWords = Stream.of("1","2","3","1","3","4","5").distinct();

---

### 정렬된 stream 추출

- sorted : Comparable에 의해 정렬을 하거나 Comparator를 받아서 정렬을 할 수 있다.

	List<String> words = new ArrayList<>();

	words.add("12345");
	words.add("5678");
	words.add("91234");

	Stream<String> longestFirst = words.stream().sorted(); // 인자를 받지 않으면 Comparable인터페이스를 구현한 클래스여야 한다.
	
	Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());

	// Comparator를 인자로 받아서 Comparable과 다른 정렬방식을 사용할 수도 있다.


Comparable인터페이스를 구현하지 않은 클래스에서 sorted()를 할 경우에는

sorted()를 호출할 때에는 문제없이 동작하고

stream을 사용하려고 할 때 ClassCastException이 발생한다.(sorted할 때 발생해야 할 것 같은데 왜 그런진 모르겠다.)

	Stream<Person> personStream = persons.stream().sorted();
	
	personStream.forEach(e->System.out.println(e)); // 이 때 ClassCastException을 던진다.

---

### peek

- 원본과 동일한 element가 포함된 다른 스트림을 리턴한다.(원본 복사)
- element를 추출할 때마다 전달받은 메소드를 호출한다.(디버깅 할 때 유용)
- element에 실제 접근할 때 메소드를 호출한다.

	Object[] powers = Stream.iterate(1.0, p -> p * 2)
			.peek(e -> System.out.println("Fetching " + e))
			.limit(20).toArray();


출력

Fetching 1.0
Fetching 2.0
Fetching 4.0
Fetching 8.0
Fetching 16.0
Fetching 32.0
Fetching 64.0
Fetching 128.0
Fetching 256.0
Fetching 512.0
Fetching 1024.0
Fetching 2048.0
Fetching 4096.0
Fetching 8192.0
Fetching 16384.0
Fetching 32768.0
Fetching 65536.0
Fetching 131072.0
Fetching 262144.0
Fetching 524288.0

---