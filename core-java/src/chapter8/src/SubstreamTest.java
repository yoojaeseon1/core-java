package chapter8.src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SubstreamTest {

	public static void main(String[] args) {
		
//		Stream<Double> randoms = Stream.generate(Math::random).limit(100);
//		
////		randoms.forEach(e->System.out.println(e));
//		
//		String[] words = {"1","2","3","4","5"};
//		
//		Stream<String> wordsStream = Stream.of(words).skip(2);
//		
//		wordsStream.forEach(e->System.out.println(e));
//		
//		System.out.println("---------");
//		wordsStream = Stream.of(words).limit(3);
//		
//		wordsStream.forEach(e->System.out.println(e));
	
		
		Stream<String> combined = Stream.concat(letters("Hello"), letters("World"));
		
		combined.forEach(e->System.out.print("\""+e+"\", "));
		
		
		

	}
	
	
	public static Stream<String> letters(String s) {
		List<String> result = new ArrayList<>();
		for(int sI = 0; sI < s.length(); sI++) {
			result.add(s.substring(sI, sI+1));
		}
		return result.stream();
	}

}
