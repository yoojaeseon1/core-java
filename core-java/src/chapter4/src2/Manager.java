package chapter4.src2;

import java.util.ArrayList;
import java.util.List;

public class Manager  extends Employee{
	
	
	private double bonus;
	
	public Manager() {
		salary = 1000;
	}
	
	public Manager(String name, double salary) {
		
//		super(name, salary);
		salary = 1000;
		bonus = 0;
		
	}



	@Override
	public double getSalary() {
		
		return salary + 5000;
		
	}
	
	
	public void setBonus(double bonus){
		this.bonus = bonus;
	}
	

	public boolean worksFor(int num) {
		return true;
	}
	
	@Override
	public Manager getSupervisor() {
		// TODO Auto-generated method stub
		return new Manager();
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "[bonus=" + bonus + "]";
	}

	public static void main(String[] args) {
		
//		Manager manager = new Manager("yoo", 1000.0);
//		
//		manager.getSalary();
//		
//		System.out.println(manager.getSalary());
		
		Employee empl = new Manager();
		
//		if( empl instanceof Manager) {
//			Manager mgr = (Manager) empl;
//			mgr.setBonus(1000);
//		} else{
//			System.out.println("haha");
//		}
		
//		System.out.println(empl.getSalary());
		
		List<String> names = new ArrayList<String>(100) {
			public boolean add(String element) {
				System.out.println("added element : " + element);
				return super.add(element);
			}
		};
		
		names.add("123");
	}
	
	
}
