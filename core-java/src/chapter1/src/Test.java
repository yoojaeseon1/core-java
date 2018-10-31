package chapter1.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void print(int...values) {
//		for(int value : values) {
//			System.out.println(value);
//		}
		
		for(int i = 0; i < values.length; i++) {
			System.out.println(values[i]);
		}
	}

	public static void main(String[] args) {
		
//		String a = "hello";
//		String b = "hello";
//		String c = new String("hello");
		
//		System.out.printf("%8.2f", 1000.0/3.0);
		
//		Integer a = new Integer(5); 
//		Integer b = new Integer(5); 
//		Integer c = new Integer(5);
//		
//		System.out.println(a.equals(b));
		
		String[] original = {"a", "b", "c", "d"};
		
//		String[] copy = original;
		String[] copy = Arrays.copyOf(original, original.length);
		
//		System.out.println(copy[1]);
//		
//		original[1] = "e";
//		
//		System.out.println(copy[1]);
		
		List<String> copyList = new ArrayList<>(Arrays.asList(original)); 
		
		List<String> copyList2 = new ArrayList<>(copyList);
		
		copyList.set(1, "e");
		
//		
//		for(String str : copyList2) {
//			System.out.println(str);
//		}
		
		print(1,2,3,4,5,6,7,8,9);

	}
}
