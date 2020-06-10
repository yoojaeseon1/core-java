package chapter8.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SimpleReductionTest {
	
	public static void main(String[] args) {
		
		
		List<String> list = new ArrayList<>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		
		Stream<String> words = list.stream();
		
		Optional<String> largest = words.max(String::compareToIgnoreCase);
		
		System.out.println("largest : " + largest.orElse(""));
		
		
		
		Optional<String> startWithQ = words.filter(s->s.startsWith("Q")).findFirst();
		
		startWithQ = words.parallel().filter(s->s.startsWith("Q")).findAny();
		
		boolean aWordStartsWithQ = words.parallel().anyMatch(s->s.startsWith("Q"));
		
		
		
		
	}

}
