package chapter3.src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LazyEvaluationTest {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		
		for(int element = 1; element <= 10; element++) {
			list.add(element);
		}
		
		
		System.out.println(
				list.stream()
				.filter(i -> {
					System.out.println("i < 6 : " + i);
					return i < 6;
				})
				.filter(i -> {
					System.out.println(" i % 2 : " + i);
					return i % 2==0;
				})
				.map(i -> { // functional interface : Function
					System.out.println("i = i*10 : " + i);
					return i*10;
				})
				.collect(Collectors.toList())
				);	
	}

}
