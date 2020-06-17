package chapter10.src;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ThreadSaftyStructures {

	public static void main(String[] args) {

		ConcurrentMap<String, Long> map = new ConcurrentHashMap<>();

//		map.put("word", 1L);

//		Long oldValue = map.get("word");

//		Long newValue = oldValue == null ? 1 : oldValue + 1;
		
//		map.put("word", newValue);
		
		map.compute("word", (key,value) -> value == null ? 1 : value+1);
		
		System.out.println(map.get("word"));
		
		map.computeIfPresent("123",  (key,value) -> Long.parseLong(key) + value);
		map.computeIfAbsent("123",  (key) -> Long.parseLong(key) + 1L);
		
		map.putIfAbsent("word", 1L);
		
		System.out.println(map.get("123"));
		
		map.merge("word", 3L, (existingValue, newValue) -> existingValue + newValue);
		
		System.out.println(map.get("word"));
		
		
//		map.merge("word", 1L, Long::sum);

	}

}
