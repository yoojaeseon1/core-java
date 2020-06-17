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

연산의 결과 값은 long 타입으로 리턴된다.

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

Collection 객체에서 추출한 것이 아닌 자체적으로 스트림을 만드는 것이다.

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

- 배열로 감싸져 있는 모든 element를 단일 element stream으로 변환해준다.(stream을 1차원으로 펼쳐 내는 것)

	
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

### 단순 리덕션

- 리덕션 : stream으로부터 결과 값을 얻어내는 연산. 앞에서 사용한 count가 리덕션의 한 종류다.

	List<String> list = new ArrayList<>();
	
	list.add("1");
	list.add("2");
	list.add("3");
	list.add("4");
	list.add("5");
	list.add("6");
	
	Stream<String> words = list.stream();
	
	Optional<String> largest = words.max(String::compareToIgnoreCase); // 가장 큰 값이 들어 있는 Optional 객체를 리턴
	
	System.out.println("largest : " + largest.orElse("")); // null일 경우 파라미터로 쓰인 문자열 리턴
	
	Optional<String> startWithQ = words.filter(s->s.startsWith("Q")).findFirst(); // Q로 시작하는 문자들만 filter하고 그 중 첫 번째 element를 Optional 객체에 초기화
	
	startWithQ = words.parallel().filter(s->s.startsWith("Q")).findAny();
	
	boolean aWordStartsWithQ = words.parallel().anyMatch(s->s.startsWith("Q"));


특정 element가 아니라 일치하는 임의의 element를 얻을 때는 병렬화로 처리하는 것이 효과적이다.

---

### Optional

- stream에서 리덕션한 결과 값을 안전하게 보관하는 장치
- stream이 null일 경우 빈 Optional을 사용하기 때문에 null 값에서 생기는 예외를 예방할 수 있다.

	List<String> list = new ArrayList<>();
	
	list.add("1");
	list.add("2");
	list.add("3");
	list.add("4");
	list.add("5");
	list.add("6");
	
	Stream<String> words = list.stream();
	
	Optional<String> largest = words.max(String::compareToIgnoreCase);
	
	System.out.println("largest : " + largest.orElse("")); // output : 6
	
	String result = optional.orElseGet(()->System.getProperty("user.dir")) // 필요할 때만 메소드가 호출된다.

	String result = optional.orElseThrow(IllegalStateException::new)) // Exception 객체를 돌려주는 메소드가 호출된다.


optionalValue.ifPresent(v->v를 처리할 내용) : 옵션 값이 있을 때 해당 메소드로 값이 전달 된다.(없으면 아무일도 안일어난다.)

	optional.ifPresent(v->list.add(v)); // 해당 element를 list에 추가한다.

	optional.ifPresent(list::add); // 위와 같은 동작을 한다.

---

ifPresent는 동작을 할 뿐 리턴하는 값이 없다. 메소드의 결과 값을 확인하고 싶으면 map을 사용하면 된다.

	Optional<Boolean> added = optional.map(list::add);

optional이 있을 때는 Optional으로 감싼 true 또는 false 값이고, 없을 때는 비어있는 Optional이다.

---

Optional 값을 사용하지 않는 방법

- Optional을 올바르게 사용하지 않으면 객체에 직접 접근해서 null값에 접근하는 것보다 나을 것이 없다.

	Optional<T> optionalvalue = ...;
	optionalValue.get().someMethod() // optionalValue에 값이 없다면 noSuchElementException을 던진다.

따라서 이 코드가

	T value = ...;
	value.someMethod()

보다 안전한 건 아니다.

---

	if(optionalValue.isPresent()) optionalValue.get().someMethod();

Optional 객체가 값을 담고 있는지 확인한 후 메소드를 실행한다.

	if(value != null) value.someMethod();

이전 코드와 같은 기능을 한다.(그러면 Optional을 사용하는 것이랑 안쓰고 null일 때 처리하는 것이랑 무슨차이지??)

---

### Option 값 생성

	public static Optional<Double> inverse(Double x) {
		// return x==0 ? Optional.empty() : Optional.of(1 / x);
		return Optional.ofNullable(1 / x);
	}

Optional.ofNullable(obj)은 obj가 null이 아니면 Optional.of(obj)를 아니면 Optional.empty()를 리턴한다.


---

## Option 값 함수 합성하기

- Stream에서 나온 결과 값인 Optional 인스턴스와 또 다른 Optional인스턴스를 합성해 새로운 결과 값을 만들어 내는 것

- 두 개(그 이상)의 값을 합성하기 위해서는 합성을 위한 메소드를 사용해야 한다.

- 그것이 flatMap

	public static Optional<Double> inverse(Double x) {
		// return x==0 ? Optional.empty() : Optional.of(1 / x);
		return Optional.ofNullable(1 / x);
	}
	
	public static Optional<Double> squareRoot(Double x) {
		return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
	}

	double x = -4.0;

	Optional<Double> result = inverse(x).flatMap(FlatMapTest::squareRoot);
	Optional<Double> result = Optional.of(-4.0).flatMap(FlatMapTest::inverse).flatMap(FlatMapTest::squareRoot);

- inverse나 squareRoot 중 하나가 Optional.empty()를 리턴하면 전체 결과 값이 비어있게 된다.

- map()은 안되는 이유?

	 inverse(x).map(FlatMapTest::squareRoot);

을 하게되면 inverse와 squareRoot를 map한 결과값은 Optional<Optional<Double>>이 된다. result의 타입이 Optional<Double>이기 때문에 원하는 결과 값을 얻을 수 없다.

flatMap을 사용해 Optional<Double> 타입으로 합성된 결과 값을 만들어내는 것이다.


---

### 결과 모으기

- stream으로 모은 결과 값을 자료구조, 문자열로 변환해 사용할 수 있다.

- collect() : stream의 element를 다른 자료구조로 모을 때 사용한다. 인자로 Collectors인터페이스의 인스턴스를 받는다.

	String[] array = { "1", "2", "3", "4", "55" };

	Stream<String> stream = Stream.of(array);

	stream.forEach(System.out::println); // element를 임의의 순서대로 순회한다.(element를 스트림 순서대로 처리되는 것을 보장하지 않는다. 근데 순서대로 하던데??)

	stream.forEachOrdered(System.out::println);  // element를 스트림 순서대로 처리하는 것이 보장된다.

	String[] result = stream.toArray(String[]::new);

	for(String element : result) {
		System.out.println(element);	
	}

	List<String> resultList = stream.collect(Collectors.toList());
	
	Set<String> resultSet = stream.collect(Collectors.toSet()); 
	
	TreeSet<String> resultTreeSet =
	stream.collect(Collectors.toCollection(TreeSet::new));

	String resultString = stream.collect(Collectors.joining());  // stream을 하나의 문자열로 합친다. 
	String resultString = stream.collect(Collectors.joining(", ")); // element 사이사이에 넣을 구분자를 지정할 수있다.
	// result : 1, 2, 3, 4, 5


	String resultString =
	stream.map(Object::toString).collect(Collectors.joining(", ")); // String이 아닌 타입이 있을 경우 String으로 변환해주고 합쳐야 한다.

	System.out.println(resultString);

	IntSummaryStatistics summary = stream.collect(Collectors.summarizingInt(String::length));
	
	double averageWordLength = summary.getAverage();
	
	double maxWordLength = summary.getMax();
	
	System.out.println(averageWordLength);
	System.out.println(maxWordLength);


---

#### 주의할 점

	String[] result = stream.toArray(String[]::new);

toArray는 Object[]을 리턴한다. runtime때는 stream의 타입은 low타입으로 되어있다. 그렇기 때문에 정확한 타입의 배열이 필요할 때는 위와 같이 파라미터를 넣어주면 된다.

Stream과 다른 타입으로 변환한 이후에 결과 값을 확인하려고 하면

	System.out.println(result[0]);

java.lang.ArrayStoreException: java.lang.String (runtime Exception) 

Exception이 발생한다.

---

한 번 끝까지 순회한 stream은 사용할 수 없다.

또 사용하려고 하면

java.lang.IllegalStateException: stream has already been operated upon or closed

Exception이 발생한다.


	 String[] result = stream.toArray(String[]::new);

	 List<String> resultList = stream.collect(Collectors.toList()); // IllegalStateException 발생


---

summarizing(Int/Long/Double) : 스트림 결과를 합계, 평균, 최댓값, 최솟값으로 리듀스하는 메소드


	IntSummaryStatistics summary = stream.collect(Collectors.summarizingInt(String::length));

	double averageWordLength = summary.getAverage();
	
	double maxWordLength = summary.getMax();
	
	System.out.println(averageWordLength); // 1.2
	System.out.println(maxWordLength); // 2.0


Collectors.summarizing(Int/Long/Double) 메소드들은 스트림 인스턴스를 숫자로 매핑하는 메소드(String::length)를 받아 합계, 평균, 최댓값, 최솟값을 동시에 계산해서 (Int/Long/Double)SummaryStatistics 타입의 결과를 리턴한다.

---

### Map으로 모으기

- Stream객체에 클래스의 인스턴스 타입으로 저장되어 있을 경우 Map으로 변환할 수 있다.

	Person[] persons = new Person[3];
	
	persons[0] = new Person(123, "yoo");
	persons[1] = new Person(123, "yoo11");
	persons[2] = new Person(345, "yoo22");
	
	Stream<Person> stream = Stream.of(persons);

	Map<Integer, String> idToName = stream.collect(Collectors.toMap(Person::getId, Person::getName));
	// java.lang.IllegalStateException: Duplicate key yoo 발생


	Map<Integer, Person> idToName = stream.collect(Collectors.toMap(Person::getId, Function.identity(), (existValue, newValue)->{return newValue;}));
	// key값이 중복될 경우 세 번째 파라미터의 메소드로 인해 새로 들어온 value로 갱신된다.


- collect의 3번째 파라미터로 병합함수(merge function)을 전달해주면 함수에 따라 중복된 key값이 들어왔을 때 value값을 정하게 된다.

- Function.identity() : value가 인스턴스 그 자체여야 할 경우 사용한다.(위의 예시에서는 해당 Person의 인스턴스가 된다.)

---

	Map<Integer, Person> idToPerson = stream.collect(
			Collectors.toMap(
					Person::getId,
					Function.identity(),
					(existingvalue, newValue) -> {throw new IllegalStateException();},
					TreeMap::new
					)
			);
	}

위와 같이 중복된 key 값이 있을 경우 Exception을 던질 수도 있다.

네 번째 파라미터를 원하는 자료구조의 생성자를 호출하면 해당 자료구조로 만들어서 리턴한다.

---

### grouping / partitioning

- 특성이 같은 값을 그룹으로 만들 수 있다.
- 파라미터가 하나일 경우 같은 key를 가지는 value들이 List에 추가된다.
- groupingBy(key,value), partitioningBy(key, value)
- value값이 여러 개일 경우 List를 사용하고 따로 지정할 경우엔 해당 자료구조를 사용한다.


	Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
	
	Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));
	
	List<Locale> swissLocales = countryToLocales.get("CH");
	
	System.out.println(swissLocales.toString());

출력

	[fr_CH, de_CH, it_CH]


---

- partitioningBy() : 특성이 있는지 없는지(true/false)로 grouping을 할 때 사용하면 편리하다.
	
	Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
	
	List<Locale> englishLocales = englishAndOtherLocales.get(true);
	
	System.out.println(englishLocales.toString());

출력

	[en_US, en_SG, en_MT, en, en_PH, en_NZ, en_ZA, en_AU, en_IE, en_CA, en_IN, en_GB]

---


### 다운스트림 컬렉터

	Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
	
	Map<String, Set<Locale>> countryToLocaleSet = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet())); 

collect의 결과 값을 List가 아니라 Set으로 받는다.
	
---
	
	Map<String, Long> countryToLocaleCounts = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));

key는 country 이름, value는 각 country의 locale개수가 된다.

---


- Stream 라이브러리에는 그룹으로 묶인 element를 리듀스하는데 사용하는 Collector 몇 가지가 있다.


	City[] citiesArray = new City[5];
	
	citiesArray[0] = new City("New Work", "Queens", 10000);
	citiesArray[1] = new City("New Work", "Bronx", 20000);
	citiesArray[2] = new City("Texas", "yoooo", 30000);
	citiesArray[3] = new City("New Work", "Manhattan", 40000);
	citiesArray[4] = new City("Texas", "yoo", 50000);
	
---

summing(Int/Long/Double) : 함수 파라미터를 하나 받아서 함수에 해당하는 필드의 합계를 구한다. 

	Stream<City> cities = Stream.of(citiesArray);
	
	Map<String, Integer> stateToCityPopulation = cities.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
	for(Entry city : stateToCityPopulation.entrySet()) {
		System.out.println("key : " + city.getKey());
		System.out.println("value : " + city.getValue());
		System.out.println("----------");
	}

출력
	key : Texas
	value : 80000
	----------
	key : New Work
	value : 70000
	----------

key : state, value : state의 인구 총합

---

maxBy / minBy : Comparator를 파라미터로 받아서 해당 필드의 최대/최소 값을 구한다.
	
	Map<String, Optional<City>> stateToLargestCity = cities.collect(Collectors.groupingBy(City::getState,
			Collectors.maxBy(Comparator.comparing(City::getPopulation))));
	
	for (Entry<String, Optional<City>> city : stateToLargestCity.entrySet()) {
		System.out.println("key : " + city.getKey());
		System.out.println("value : " + city.getValue().toString());
		System.out.println("----------");
	}

출력

	key : Texas
	value : Optional[City [state=Texas, name=yoo, population=50000]]
	----------
	key : New Work
	value : Optional[City [state=New Work, name=Manhattan, population=40000]]
	----------

key : state, value : state에 있는 city 중 인구가 가장 많은 city의 필드 정보 

---
	
	Map<String, Optional<String>> stateToLongestCityName = cities.collect(
			Collectors.groupingBy(City::getState,
					Collectors.mapping(City::getName,
							Collectors.maxBy(Comparator.comparing(String::length))))
			);
			
	for(Entry<String, Optional<String>> city : stateToLongestCityName.entrySet()) {
		System.out.println("key : " + city.getKey());
		System.out.println("value : " + city.getValue().toString());
		System.out.println("----------");
	}

출력

	key : Texas
	value : Optional[yoooo]
	----------
	key : New Work
	value : Optional[Manhattan]
	----------

key : state, value : 가장 이름이 긴 city의 이름
	

---

	Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(
			Collectors.groupingBy(City::getState,
					Collectors.summarizingInt(City::getPopulation))
			);
	
	for(Entry city : stateToCityPopulationSummary.entrySet()) {
		System.out.println("key : " + city.getKey());
		System.out.println("value : " + city.getValue().toString());
		System.out.println("----------");
	}

출력

	key : Texas
	value : IntSummaryStatistics{count=2, sum=80000, min=30000, average=40000.000000, max=50000}
	----------
	key : New Work
	value : IntSummaryStatistics{count=3, sum=70000, min=10000, average=23333.333333, max=40000}
	----------

key : state, value : 해당 state의 최대/최소 인구, 인구 평균/합

---

### reduce() (리덕션연산)

- reduce 메소드는 스트림에서 값을 계산한다.

		List<Integer> values = new ArrayList<>();
		
		values.add(1);
		values.add(2);
		values.add(3);
		values.add(4);
		values.add(5);
		
		Optional<Integer> sum = values.stream().reduce((x,y) -> x+y);
		//Optional<Integer> sum = values.stream().reduce(Integer::sum);


위의 reduce메소드는 values의 모든 element를 더한다.(v0+v1+...+v4)

- 리덕션 연산이 결합법칙을 지원해야 병렬 스트림을 통한 효율적인 리덕션이 가능하다.

결합법칙 : (x op y) op z = x op (y op z)

어떻게 결합하던지 결과값이 같아야 한다.


결합법칙이 적용되지 않는 연산은 뺄셈 뿐이다.

x = 1
y = 2
z = 3;

(x - y) - z = (-1) -3 = -4

x - (y - z) = 1 - (-1) = 2


---

항등값을 사용한 계산

	Integer sum = values.stream().reduce(0, Integer::sum);

첫 번째 파라미터로 항등 값(0)을 넣으면 스트림이 비어 있을 때 항등 값을 리턴한다.

그렇기 떄문에 Optional<Integer>를 사용할 필요가 없다.(Optional을 사용하지 않아도 null이 발생할 일이 없으니까)

---

### 특정 프로퍼티의 합계를 구하는 경우

- 스트림의 element와 결과의 타입이 다른 경우에

	int result = words.reduce(0,
		(total, word) -> total + word.length(),
		(total1, total2) -> total1 + total2);

세 번째 인자로 결과를 결합할 떄 사용할 메소드를 추가해주면 된다.

---

### 기본타입 스트림

- 기본타입을 래퍼 객체로 감싸는 것은 비 효율적이다.
- 기본타입 스트림에는 IntStream/LongStream/DoubleStream이 있다.
- IntStream : int, short, char, byte, boolean
- LongStream : long
- DoubleStream : double, float

		IntStream stream = IntStream.of(1,2,3,4,5);
		
		int[] values = {1,2,3,4,5,6,7,8,9};
		
		stream = Arrays.stream(values, 0, 4); // 기본형 배열인 value에서 0~3(4미만)의 index에 있는 값을 stream에 초기화한다.
		
		stream = IntStream.range(0,100); // 0~99(상한값 제외)까지 100개의 int값을 stream에 초기화한다.
		
		stream = IntStream.rangeClosed(0, 100); // 0~100(상한값 포함)까지 101개의 int값을 stream에 초기화한다.
		
		
		List<String> stringList = new ArrayList<>();
		
		stringList.add("12");
		stringList.add("234");
		stringList.add("3456");
		stringList.add("456789");
		stringList.add("567890");
		
		Stream<String> words = stringList.stream();
		
		IntStream lengths = words.mapToInt(String::length);
		
		Stream<Integer> integers = IntStream.range(0, 100).boxed();
		
		System.out.println(lengths.max());


mapToInt() : 객체에서 기본형 변수 stream을 만들 수 있다.

boxed() : 기본형 변수를 래퍼 클래스로 boxing한다.

- 기본타입 스트림에는 sum/average/max/min 메서드가 있다. 각각의 메서드는 변수 타입에 따라 Optional(Int/Long/Double)을 리턴한다.

- Optional(Int/Long/Double)에는 get메소드 대신 getAs(Int/Long/Double)메소드가 있다.

- summaryStatistics 메소드는 스트림의 함계,평균,최댓값,최솟값을 동시에 보고할 수 있는 (Int/Long/Double)SummaryStatistics 객체를 리턴한다.

---

### 병렬 스트림

- 스트림의 연산을 병렬로 처리할 수 있도록 해준다.

		Stream<String> parallelWords = words.parallelStream();

- 순차 스트림을 병렬 스트림으로 바꿀 수도 있다.

		Stream<String> parallelWords = Stream.of(wordArary).parallel();


---

병렬 스트림의 결과 값은 순차 스트림으로 처리했을 때와 같아야 한다. 즉, 임의의 순서대로 실행해도 결과 값이 같아야 한다.


	List<String> words = new ArrayList<>();
	
	words.add("12");
	words.add("2");
	words.add("3345");
	words.add("43451");
	words.add("5");
	
	
	Map<Integer, Long> shortWordCounts = 
			words.parallelStream()
			.filter(s->s.length() < 12)
			.collect(Collectors.groupingBy(String::length, Collectors.counting()));

	for(Entry entry : shortWordCounts.entrySet()) {
		System.out.println("key : " + entry.getKey());
		System.out.println("value : " + entry.getValue());
	}

출력

	key : 1
	value : 2
	----------
	key : 2
	value : 1
	----------
	key : 4
	value : 1
	----------
	key : 5
	value : 1
	----------




---

- 임의의 순서대로 처리해도 결과 값이 같아야 하니까 순서에 대한 요구사항을 버리면 더 효과적으로 병렬화 할 수 있다.(stream.underordered() 메소드 사용)

	Stream<String> sample = words.parallelStream().underordered().limit(n);


sample 스트림은 이후의 연산에서 순서를 신경쓰지 않아도 되니까 더 효율적으로 처리할 수 있다.

---

- 병렬 처리로 맵 병합

		Map<Integer, List<String>> result = words.parallelStream().collect(
				Collectors.groupingByConcurrent(String::length)
				);

		for(Entry entry : result.entrySet()) {
			System.out.println("key : " + entry.getKey());
			System.out.println("value : " + entry.getValue());
			
		}

출력

	key : 1
	value : [5, 1]
	----------
	key : 2
	value : [12]
	----------
	key : 4
	value : [1234]
	----------
	key : 5
	value : [12345]
	----------

---

- 다음과 같이 순서와 무관하게 counting만 하면 되는 다운 스트림 컬렉터를 사용하는 것도 신경쓰지 않아도 된다.

	Map<Integer, Long> wordCounts = words.parallelStream()
			.collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));

출력

	key : 1
	value : 2
	----------
	key : 2
	value : 1
	----------
	key : 4
	value : 1
	----------
	key : 5
	value : 1
	----------


---


