package chapter8.src;

import java.math.BigInteger;
import java.util.stream.Stream;

public class CreationTest {

	public static void main(String[] args) {
		
		String[] array = {"a", "b"};
		
		Stream<String> words = Stream.of(array);
		
		Stream<Integer> numbers = Stream.of(1,2,3,4,5);
		
		Stream<String> silence = Stream.empty();
		
		Stream<String> echos = Stream.generate(() -> "hahahoho");
		
		Stream<Double> randoms = Stream.generate(Math::random);
		
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n-> n.add(BigInteger.ONE));
		
		
		
	}

}