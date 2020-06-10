package chapter8.src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;import org.omg.Messaging.SyncScopeHelper;

public class ChangeStreamTest {

	public static void main(String[] args) {
		
		
		Stream<String> uniqueWords = Stream.of("1","2","3","1","3","4","5").distinct();
		
//		uniqueWords.forEach(e->System.out.println(e));
		
//		List<String> words = new ArrayList<>();
//		
//		
//		words.add("12345");
//		words.add("5678");
//		words.add("91234");
//		
//		Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
//		
//		longestFirst.forEach(e->System.out.println(e));
		
//		List<Person> persons = new ArrayList<>();
//		
//		persons.add(new Person("yoo", 25));
//		persons.add(new Person("haha", 25));
//		persons.add(new Person("adsfa", 29));
		
//		Stream<Person> personStream = persons.stream().sorted();
		
//		personStream.forEach(e->System.out.println(e));
		
		
		Object[] powers = Stream.iterate(1.0, p -> p * 2)
				.peek(e -> System.out.println("Fetching " + e))
				.limit(20).toArray();
		System.out.println("------");
		for(Object power : powers) {
			System.out.println(power);
		}
		

	}
}