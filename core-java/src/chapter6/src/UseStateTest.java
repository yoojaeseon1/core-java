package chapter6.src;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UseStateTest {

	public static void main(String[] args) {
		
//		List<Manager> bosses = new ArrayList<>();
//		List<Employee> empls = bosses;
		
//		Manager[] bosses = new Manager[5];
//		Employee[] empls = bosses;
//		
//		empls[0] = new Employee();
		
		
		List<Employee> empls = new ArrayList<>();
		
		printNames(empls);
	}
	
	public static void printNames(List<? extends Employee> staff) {
		
		Employee e = staff.get(0);
		Employee empl = new Employee();
		Manager Manager = new Manager();
		
//		staff.add(Manager);
		
//		Supervisor supervisor = new Supervisor();
//		staff.add(supervisor);
		
		
	}
	
	public static void printAll(Employee[] staff, Predicate<? super Employee> filter) {
		
		Object obj = new Object();
		
		Person person = new Person();
		
//		filter.test(person);
		
		// T t = new Person();
		
		for(Employee empl: staff) {
			
			if(filter.test(empl)) {
				System.out.println(empl.getSalary());
			}
		}
		
		
	}

}
