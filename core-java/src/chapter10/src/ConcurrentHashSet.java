package chapter10.src;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashSet {

	public static void main(String[] args) {

		Set<String> words = ConcurrentHashMap.newKeySet();
		
		
		Map<String, Integer> hashMap = new HashMap<>();
		
		ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
		
		Set<String> concurrentSet = concurrentMap.keySet();

		hashMap.put("1", 1);
		hashMap.put("2", 2);
		hashMap.put("3", 3);

		Set<String> hashSet = hashMap.keySet();
		
		hashSet.remove("2");
		
		int finededValue = hashMap.getOrDefault("2", 0);
		
		System.out.println(finededValue);

	}

}
