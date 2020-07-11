package chapter4.src;

import java.util.ArrayList;
import java.util.List;

public class DeepCopyTest {
	
	public static void main(String[] args) {
		
		List<Integer> source = new ArrayList<>();
		
		source.add(1);
		source.add(2);
		source.add(3);
		source.add(4);
		source.add(5);
		
		List<Integer> copied = new ArrayList<>(source);
//		List<Integer> copied = source.subList(0, source.size());
		
		copied.remove(1);
		
		source.forEach(System.out::println);
		
		System.out.println("----------");
		
		copied.forEach(System.out::println);
		
		
	}

}
