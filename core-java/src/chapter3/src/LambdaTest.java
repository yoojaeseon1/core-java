package chapter3.src;

import java.util.Comparator;

public class LambdaTest {
	
	public static void main(String[] args) {
		
//		String[] strList = {"dfaf", "aa", "cccccc"};
//		
//		
//		Arrays.sort(strList, (first, second) -> first.length() - second.length());
//		Arrays.sort(strList, (first, second) -> second.length() - first.length());
		
//		for(String str : strList) {
//			System.out.println(str);
//		}
		
//		List<String> list = new ArrayList<>();
//		
//		list.add("a");
//		list.add("b");
//		list.add("c");
//		list.add("d");
//		
//		list.forEach(System.out::println);
		
//		int first = 0;
//		Comparator<String> comp = (first, second) -> first.length() - second.length();
		
		
		reverse(String::compareToIgnoreCase);


	}
	
	public static Comparator<String> reverse(Comparator<String> comp) {
		return (x,y) -> comp.compare(y,x);
	}
	
//	public static void repeat(String text, int count, int threads){
//		
//		Runnable r = () -> {
//			while(count > 0){
//				count--;
//				System.out.println(text);
//			}
//		};
//		
//		for(int i = 0; i < threads; i++)
//			new Thread(r).start();
//	}
	
	

}
