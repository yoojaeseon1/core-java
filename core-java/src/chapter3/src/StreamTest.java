package chapter3.src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		
		
		List<String> names = new ArrayList<>();
		
		names.add("yoo");
		names.add("kim");
		names.add("shin");
		names.add("kang");
		names.add("lee");
		
		
		Stream<Employee> stream = names.stream().map(Employee::new);
		
		stream.forEach(employee -> System.out.println(employee.getName()));
		
		
	}
}
