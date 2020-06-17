package chapter10.src;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnTest {

	public static void main(String[] args) {
		
		List<Integer> list = new CopyOnWriteArrayList<>();
		
		Set<Integer> set = new CopyOnWriteArraySet<>();

	}
}
