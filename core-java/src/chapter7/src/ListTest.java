package chapter7.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		
		
//		List<Integer> linkedList = new LinkedList<>();
//		List<Integer> arrayList = new ArrayList<>();
//		
//		Collection<Integer> list = new ArrayList<>();
		
//		List<Integer> list = Collections.singletonList(5);
//		
//		list.add(5);
//		
		List<Integer> arrayList = new ArrayList<>();
		
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		
		List<Integer> unmodifiableView = Collections.unmodifiableList(arrayList);
		
		unmodifiableView.add(5);

	}

}
