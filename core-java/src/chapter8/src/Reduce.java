package chapter8.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Reduce {

	public static void main(String[] args) {
		
		List<Integer> values = new ArrayList<>();
		
		values.add(1);
		values.add(2);
		values.add(3);
		values.add(4);
		values.add(5);
		
//		Optional<Integer> sum = values.stream().reduce((x,y) -> x+y);
//		Optional<Integer> sum = values.stream().reduce(Integer::sum);
		
		Integer sum = values.stream().reduce(0, Integer::sum);
		
		System.out.println(sum.toString());

	}

}
