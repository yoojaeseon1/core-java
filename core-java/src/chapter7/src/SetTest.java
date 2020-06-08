package chapter7.src;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
	
	public static void main(String[] args) {
		
		
		Set<Integer> hashSet = new HashSet<>();
//		Set<Integer> treeSet = new TreeSet<>();
		
		
		NavigableSet<Integer> treeSet = new TreeSet<>();
		
		
		treeSet.add(1);
		treeSet.add(8);
		treeSet.add(3);
		treeSet.add(7);
		treeSet.add(4);
		treeSet.add(2);
		treeSet.add(5);
		treeSet.add(10);
		
		System.out.println(treeSet.higher(8));
		
//		System.out.println("first : " + treeSet.first());
//		
//		for(int num : treeSet) {
//			System.out.println(num);
//		}
//		
//		System.out.println("last : " + treeSet.last());
		
		
//		Set<Integer> subTreeSet = treeSet
		
	}

}
