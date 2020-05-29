package chapter4.src2;

import java.util.Arrays;
import java.util.Objects;

public class Item {

	private String description;
	private double price;

	@Override
	public boolean equals(Object obj) {
		// �� ��ü�� �������� �˾ƺ��� �˻�
		if (this == obj)
			return true;

		// �Ķ���Ͱ� null�� ��� false�� ��ȯ�ؾ� �Ѵ�.
		if (obj == null)
			return false;

		// �Ķ���Ͱ� Item�� �ν��Ͻ����� �˻�
		if (getClass() != obj.getClass())
			return false;
		
		if(!(obj instanceof Item))
			return false;

		// �ν��Ͻ��� ���� ������ �˻�

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
