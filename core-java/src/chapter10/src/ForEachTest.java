package chapter10.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForEachTest {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		list.add(6);
		
		for(int element = 1; element <= 1000; element++) {
			list.add(element);
		}
		for(int number : list) {
			System.out.println(number);
		}
		
//		Iterator<Integer> iter = list.iterator();
//		
//		while(iter.hasNext()) {
//			System.out.println(iter.next());
//		}

	}

}
