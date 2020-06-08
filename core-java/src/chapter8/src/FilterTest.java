package chapter8.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FilterTest {
	
	public static void main(String[] args) {
		
		List<String> words = new ArrayList<>();
		
		
		words.add("1234");
		words.add("5678");
		words.add("9123");
		
		Stream<String> longWords = words.stream().filter(w->w.length()>10);
		
		Stream<String> lowercasesWords = words.stream().map(String::toLowerCase);
		
		Stream<String> firstLetters = words.stream().map(s -> s.substring(0,1));
		
		
		Stream<String> boat = letters("boat");
		
//		boat.forEach(e -> System.out.println(e));
		
		Stream<Stream<String>> resultOfWords = words.stream().map(w->letters(w));
		
		
		Stream<String> flatResult = words.stream().flatMap(w->letters(w));
		
//		flatResult.forEach(e -> System.out.println(e));
		
		
		
//		System.out.println(boat);
		
		
	}
	
	public static Stream<String> letters(String s) {
		List<String> result = new ArrayList<>();
		for(int sI = 0; sI < s.length(); sI++) {
			result.add(s.substring(sI, sI+1));
		}
		return result.stream();
	}
}
