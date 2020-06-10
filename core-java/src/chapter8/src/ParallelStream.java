package chapter8.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ParallelStream {

	public static void main(String[] args) {

		List<String> words = new ArrayList<>();

		words.add("12");
		words.add("1");
		words.add("1234");
		words.add("12345");
		words.add("5");

		Map<Integer, Long> shortWordCounts = words.parallelStream().filter(s -> s.length() < 12)
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));

//		Map<Integer, List<String>> result = words.parallelStream()
//				.collect(Collectors.groupingByConcurrent(String::length));
//		for (Entry<Integer, List<String>> entry : result.entrySet()) {
//			System.out.println("key : " + entry.getKey());
//			System.out.println("value : " + entry.getValue());
//			System.out.println("----------");
//		}

		Map<Integer, Long> wordCounts = words.parallelStream()
				.collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));

		for (Entry<Integer, Long> entry : wordCounts.entrySet()) {
			System.out.println("key : " + entry.getKey());
			System.out.println("value : " + entry.getValue());
			System.out.println("----------");
		}
	}

}
