package chapter4.src2;

import java.util.Arrays;
import java.util.Objects;

public class Item {

	private String description;
	private double price;

	@Override
	public boolean equals(Object obj) {
		// 두 객체가 동일한지 알아보는 검사
		if (this == obj)
			return true;

		// 파라미터가 null일 경우 false를 반환해야 한다.
		if (obj == null)
			return false;

		// 파라미터가 Item의 인스턴스인지 검사
		if (getClass() != obj.getClass())
			return false;
		
		if(!(obj instanceof Item))
			return false;

		// 인스턴스의 값이 같은지 검사

		Item other = (Item) obj;

		return Objects.equals(description, other.description) && price == other.price;

	}
	
	public static void main(String[] args) {
		
//		Item x = null;
//		Item y= new Item();
		
		
//		System.out.println(x.equals(y));
//		System.out.println(Objects.equals(x, y));
		
		
		int[] nums1 = {1,2,3,4,5};
		int[] nums2 = {1,2,3,4,5};
		
		
		System.out.println(Arrays.equals(nums1, nums2));
		
	}

}
