package chapter10.src;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ParallelTest {

	public static void main(String[] args) {
		
		
//		List<String> coll = new ArrayList<>();
//		
//		coll.add("ABCD");
//		coll.add("EFGH");
//		coll.add("ABCD");
//		coll.add("ABCD");
//		coll.add("EFGH");
//		
//		long result = coll.parallelStream().filter(s->s.startsWith("A")).count();
//		
//		System.out.println(result);
		
		int[] values = new int[15];
//		
		Arrays.parallelSetAll(values, i -> i % 10);
//		
//		for(int valuesI = 0; valuesI < values.length; valuesI++) {
//			System.out.print(values[valuesI] + " ");
//		}
//		Arrays.parallelSort(values, values.length / 2, values.length);
//		System.out.println();
//		for(int valuesI = 0; valuesI < values.length; valuesI++) {
//			System.out.print(values[valuesI] + " ");
//		}
		
//		String[] words = {"abc","abcd", "a", "ab"};
//		
//		Arrays.parallelSort(words, Comparator.comparing(String::length));
//		
//		for(int wordsI = 0; wordsI < words.length; wordsI++) {
//			System.out.print(words[wordsI] + " ");
//		}
		
		long sum = IntStream.of(values).parallel().sum();
		
		System.out.println(sum);

	}

}
