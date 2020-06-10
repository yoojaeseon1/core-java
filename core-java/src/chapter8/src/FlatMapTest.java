package chapter8.src;

import java.util.Optional;

public class FlatMapTest {

	public static void main(String[] args) {
		
		
		double x = 0;
		
		
		Optional<Double> result = inverse(x).flatMap(FlatMapTest::squareRoot);
//		Optional<Double> result = Optional.of(-4.0).flatMap(FlatMapTest::inverse).flatMap(FlatMapTest::squareRoot);
		
//		Optional<Integer> result = pow(x).map(FlatMapTest::squareRoot); 
		
		
//		System.out.println(result.toString());
		
		
		

	}
	
	public static Optional<Double> inverse(Double x) {
//		return x==0 ? Optional.empty() : Optional.of(1 / x);
		return Optional.ofNullable(1 / x);
	}
	
	public static Optional<Double> squareRoot(Double x) {
		return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
	}
	
	public static Optional<Integer> pow(Double x) {
		return x < 0 ? Optional.empty() : Optional.of((int)Math.pow(x, 2));
	}

}
