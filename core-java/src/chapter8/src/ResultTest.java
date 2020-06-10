package chapter8.src;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultTest {

	public static void main(String[] args) {

		String[] array = { "1", "2", "3", "4", "55" };

		Stream<String> stream = Stream.of(array);

		// stream.forEach(System.out::println);

		// stream.forEachOrdered(System.out::println);

		 String[] result = stream.toArray(String[]::new);

		// for(String element : result) {
		// System.out.println(element);
		// }

		 List<String> resultList = stream.collect(Collectors.toList());
		//
		// Set<String> resultSet = stream.collect(Collectors.toSet());
		//
		// TreeSet<String> resultTreeSet =
		// stream.collect(Collectors.toCollection(TreeSet::new));

		// String resultString = stream.collect(Collectors.joining());
		// String resultString = stream.collect(Collectors.joining(", "));

		// String resultString =
		// stream.map(Object::toString).collect(Collectors.joining(", "));

		// System.out.println(resultString);

		IntSummaryStatistics summary = stream.collect(Collectors.summarizingInt(String::length));
		
		double averageWordLength = summary.getAverage();
		
		double maxWordLength = summary.getMax();
		
		System.out.println(averageWordLength);
		System.out.println(maxWordLength);

	}

}
