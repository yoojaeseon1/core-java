package chapter10.src;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class TreeMapTest {

	public static void main(String[] args) {
		
		ConcurrentMap<String, Integer> map = new ConcurrentSkipListMap<>(Collections.reverseOrder());
		
		
		map.putIfAbsent("1", 1);
		map.putIfAbsent("3", 1);
		map.putIfAbsent("4", 1);
		map.putIfAbsent("2", 1);
		map.putIfAbsent("5", 1);
		
		Iterator<String> keyIter = map.keySet().iterator();
		
		while(keyIter.hasNext()) {
			System.out.println(keyIter.next());
		}
		

	}

}
