package chapter3.src;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {

	public static void main(String[] args) {
		
		Human[] humans = new Human[5];
		
		
		humans[0] = new Human("5");
		humans[1] = new Human("2");
		humans[2] = new Human("3");
		humans[3] = new Human("1");
		humans[4] = new Human("4");
		
		Arrays.sort(humans, Comparator.comparing(Human::getName)
													.thenComparing(Human::getAge)
													.thenComparing(Human::getPhoneNum));
		
		for(Human human : humans) {
			System.out.println(human.getName());
		}
		

	}

}
