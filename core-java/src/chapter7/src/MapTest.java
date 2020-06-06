package chapter7.src;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

public class MapTest {

	public static void main(String[] args) {
		
//		Map<Integer,Integer> map = new HashMap<>();
//		
//		Map<Person, Integer> treeMap = new TreeMap<>();
//		
//		Object obj = new Object();
//		
//		treeMap.put(new Person(), 5);
		
//		Integer num = new Integer(5);
//		
//		map.put(1, 30);
//		map.put(1, 20);
//		map.put(1, 10);
//		map.put(2, 20);
//		map.put(3, 30);
//		map.put(4, 40);
		
//		System.out.println(map.get(1));
//		
//		System.out.println(map.getOrDefault(2, -1));
//		
//		map.merge(1, 1, Integer::sum);
//		System.out.println(map.get(1));
		
//		for(Entry<Integer,Integer> entry : map.entrySet()) {
//			
//			int key = entry.getKey();
//			int value = entry.getValue();
//			
//			System.out.println("key : " + key);
//			System.out.println("value : " + value);
//			
//			
//		}
//		
//		map.forEach((key,value) ->{
//			System.out.println("key : " + key);
//			System.out.println("value : " + value);
//		});
		
		
		Map<String, Integer> map = new WeakHashMap<>();
		
		WeakReference<Map<String, Integer>> weak = new WeakReference<>(map);
		
		testEmptyView(Collections.emptyMap());
//		testEmptyView(Collections.singletonMap("haha", 1));
		
		

	}
	
	public static void testEmptyView(Map<String, Integer> map) {
		
		map.put("hihi", 1);
		
		System.out.println(map.size());
		
		
	}

}

class Person {
	
	private String name;
	private int age;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	
}
