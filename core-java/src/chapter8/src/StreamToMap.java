package chapter8.src;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToMap {

	public static void main(String[] args) {
		
		Person[] persons = new Person[3];
		
		persons[0] = new Person(123, "yoo");
		persons[1] = new Person(123, "yoo11");
		persons[2] = new Person(345, "yoo22");
		
		Stream<Person> stream = Stream.of(persons);
		
		Map<Integer, String> idToName = stream.collect(Collectors.toMap(Person::getId, Person::getName));
//		Map<Integer, Person> idToName = stream.collect(Collectors.toMap(Person::getId, Function.identity(), (existValue, newValue)->{return newValue;}));
		
//		System.out.println();
		
		for(Entry person : idToName.entrySet()) {
			
			System.out.println("key : " + person.getKey());
			System.out.println("value : " + person.getValue());
			
		}
		

	}

}

class Person{
	
	private int id;
	private String name;
	
	public Person() {
		
	}

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
