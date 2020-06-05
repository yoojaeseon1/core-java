package chapter7.src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IteratorTest {

	public static void main(String[] args) {
		
		Set<Integer> set = new HashSet<>();
		
		List<Integer> list = new LinkedList<>();
		
		Map<String, Integer> map = new HashMap<>();
		
		set.add(5);
		set.add(3);
		
		for(Integer num : set) {
			System.out.println(num);
		}
		
		Iterator<Integer> iter = set.iterator();
		

	}

}
