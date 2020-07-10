package chapter3.src;

import java.util.Arrays;

public class Employee implements Comparable<Employee>{
	
	private int id;
	private String name;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String name){
		this.name = name;
	}
	
	@Override
	public int compareTo(Employee o) {
		
		return Integer.compare(getId(), o.getId());
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		
		Employee a = new Employee();
		a.setId(5);
		Employee b = new Employee();
		b.setId(1);
		Employee c = new Employee();
		c.setId(3);
		Employee[] list = {a,b,c};
		
		Arrays.sort(list);
		
		for(Employee emp : list) {
			System.out.println(emp.getId());
		}
		
	}
	
}


//public class Employee implements Person, Identified{
//	
//	@Override
//	public String getName() {
//		return null;
//	}
//	
//	@Override
//	public int getId() {
//		// TODO Auto-generated method stub
//		return Person.super.getId();
//	}
//}
