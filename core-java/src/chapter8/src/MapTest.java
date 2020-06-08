package chapter8.src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MapTest {

	public static void main(String[] args) {
		
		
		List<String> words = new ArrayList<>();
		
		
		words.add("1234123");
		words.add("5678123");
		words.add("2123123");
		words.add("3123123");
		words.add("4123123");
		words.add("9123123");
		
		Stream<String> stream = words.stream().filter(s->s.charAt(0)=='1').map(s->s.substring(0,3));
//		Stream<String> stream = words.stream().map(s->s.substring(0,4)).filter(s->s.charAt(0) == '5');
		
		
		
		stream.forEach(e->System.out.println(e));
		
		

	}

}
