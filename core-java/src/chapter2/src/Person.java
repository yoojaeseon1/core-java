package chapter2.src;

import java.util.ArrayList;
import java.util.List;

/**
 * 현재의 날짜를 표현한다.
 * @author jaeseonyoo
 * @version 1.1
 * @see chapter2.src.Person#setYear(int)
 *
 */
public class Person {
	
	private static List<Integer> list = new ArrayList<>(); 
	private static int year;
	private static int month;
	private static int day;
	private static int a;
	
	
	static{
		for(int i = 0; i < 10; i++) {
			list.add(i);
		}
		year = 2018;
		month = 10;
		day = 31;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public void setYear(int year) {
		year = year;
	}
	

	public static void main(String[] args) {
		
		Person a = new Person();
		Person b = new Person();
		Person c = new Person();
		
		System.out.println(Person.year);
	}
}
