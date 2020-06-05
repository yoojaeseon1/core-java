package chapter7.src;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapTest {

	public static void main(String[] args) {
		
		Map<Integer,Integer> map = new HashMap<>();
		
		
		
		map.put(1, 30);
		map.put(1, 20);
		map.put(1, 10);
		map.put(2, 20);
		map.put(3, 30);
		map.put(4, 40);
		
//		System.out.println(map.get(1));
//		
//		System.out.println(map.getOrDefault(2, -1));
//		
//		map.merge(1, 1, Integer::sum);
//		System.out.println(map.get(1));
		
		for(Entry<Integer,Integer> entry : map.entrySet()) {
			
			int key = entry.getKey();
			int value = entry.getValue();
			
			System.out.println("key : " + key);
			System.out.println("value : " + value);
			
			
		}
		
		map.forEach((key,value) ->{
			System.out.println("key : " + key);
			System.out.println("value : " + value);
		});
		

	}

}
