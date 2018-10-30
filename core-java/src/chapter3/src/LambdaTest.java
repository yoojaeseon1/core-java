package chapter3.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaTest {
	
	public static void main(String[] args) {
		
		String[] strList = {"dfaf", "aa", "cccccc"};
		
		
		Arrays.sort(strList, (first, second) -> first.length() - second.length());
		Arrays.sort(strList, (first, second) -> second.length() - first.length());
		
//		for(String str : strList) {
//			System.out.println(str);
//		}
		
		List<String> list = new ArrayList<>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		list.forEach(System.out::println);
		
	}

}
