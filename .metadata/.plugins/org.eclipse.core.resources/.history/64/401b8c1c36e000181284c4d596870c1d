package chapter3.src;

import java.util.Arrays;
import java.util.Comparator;

	public class LengthComparator implements Comparator<String>{
		
		@Override
		public int compare(String o1, String o2) {
			return o1.length()- o2.length();
		}
		
		public static void main(String[] args) {
			String[] list = {"Peter", "Paul", "Maryzzz"};
			
			Arrays.sort(list, new LengthComparator());
			
			for(String str : list) {
				System.out.println(str);
			}
		}
	}
