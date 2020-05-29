package chapter4.src2;

import java.util.Arrays;

public class Employee implements Cloneable{

	protected double salary;
	private String name;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean worksFor(Employee supervisor) {
		return true;
	}

	public Employee getSupervisor() {
		return new Employee();
	}

	@Override
	public Employee clone() throws CloneNotSupportedException {
		
		return (Employee)super.clone();
	}
	
//	@Override
//	public String toString() {
//		
//		return getClass().getName() + "[name=" + name + ",salary=" + salary + "]"; 
//	}
	
	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", name=" + name + "]";
	}
	
	public static void main(String[] args) {
		
//		Employee empl = new Employee();
//		
//		System.out.println("employee's info : " + empl);
//		System.out.println(empl);
//		System.out.println(empl.toString());
//		
//		System.out.println(System.out);
		
		
		int[] primes = {2,3,5,7,11,13};
		int[][] twoDimentionArray = {{1,2,3},{4,5,6}};
		
		System.out.println(primes);
		System.out.println(Arrays.toString(primes));
		System.out.println(Arrays.deepToString(twoDimentionArray));
		
	}



}
