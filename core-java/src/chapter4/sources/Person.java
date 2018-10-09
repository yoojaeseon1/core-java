package chapter4.sources;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
	
	
	private String name;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	
	public final String getName() {
		return this.name;
	}
	
	public abstract int getId();
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
		Person person = new Student();
		
		List<String> names = new ArrayList<String>(100) {
			
			@Override
			public void add(int index, String element) {
				super.add(index, element);
				System.out.println("Adding " + element + " at " + index);
			}
		};
		
		names.add(0, "haha");
		
	}

}

class Student extends Person{

	@Override
	public int getId() {
		return 0;
	}
	
}