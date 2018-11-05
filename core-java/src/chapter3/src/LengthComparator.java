package chapter3.src;

import java.util.Arrays;

	public class LengthComparator{
		
		public static void main(String[] args) {
			String[] list = {"Peter", "Paul", "Maryzzz"};
			
			Arrays.sort(list);
			
			for(String str : list) {
				System.out.println(str);
			}
		}

		public int compareTo(String o1, String o2) {
			// TODO Auto-generated method stub
			return o1.length()- o2.length();
		}
	}
