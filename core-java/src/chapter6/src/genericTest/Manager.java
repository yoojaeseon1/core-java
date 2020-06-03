package chapter6.src.genericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager extends Employee{
	
	@Override
	public int compareTo(Employee o) {
		
		return super.compareTo(o);
		
	}
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		List<Integer> addedList = new ArrayList<>();
		
		Collections.sort(new ArrayList<Integer>());
		
		list.addAll(addedList);
		
		
		
	}
	
}
