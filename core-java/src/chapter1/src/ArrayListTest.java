package chapter1.src;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
	
	public static void main(String[] args) {
		
		
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		
		
		list1.add(1);
		list2.add(1);
		
//		System.out.println(list1.get(0) == list2.get(0));
		
		for(int num : list1) {
			System.out.println(num);
		}
		
//		int[] nums = new int[100];
//		
//		for(int numsI = 0; numsI< nums.length; numsI++) {
//			nums[numsI] = numsI;
//		}
//		
//		int sumNums = 0;
//		
//		for(int num : nums) {
//			sumNums += num;
//		}
		
		
		printArray(5);
		
		
		
		
	}
	
	public static void printArray(int num, int... array) {
		
		for(int element : array) {
			System.out.println(element);
		}
	}
}
