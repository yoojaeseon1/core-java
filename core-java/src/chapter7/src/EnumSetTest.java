package chapter7.src;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetTest {

	public static void main(String[] args) {
		
		Set<Weekday> always = EnumSet.allOf(Weekday.class);
		
		Set<Weekday> never = EnumSet.noneOf(Weekday.class);

		Set<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
		
		Set<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
		System.out.println("always : " + always);
		System.out.println("never : " + never);
		System.out.println("workday : " + workday);
		System.out.println("mwf : " + mwf);
	}

}
