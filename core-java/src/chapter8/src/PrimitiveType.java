package chapter8.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveType {

	public static void main(String[] args) {
		
		
		IntStream stream = IntStream.of(1,2,3,4,5);
		
		int[] values = {1,2,3,4,5,6,7,8,9};
		
		stream = Arrays.stream(values, 0, 4);
		
		stream = IntStream.range(0,100);
		
		stream = IntStream.rangeClosed(0, 100);
		
		stream.forEach(System.out::println);
		
		List<String> stringList = new ArrayList<>();
		
		stringList.add("12");
		stringList.add("234");
		stringList.add("3456");
		stringList.add("456789");
		stringList.add("567890");
		
		Stream<String> words = stringList.stream();
		
		IntStream lengths = words.mapToInt(String::length);
		
//		lengths.forEach(System.out::println);
		
		Stream<Integer> integers = IntStream.range(0, 100).boxed();
		
		
		System.out.println(lengths.max());

	}

}
