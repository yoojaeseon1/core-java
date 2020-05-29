package chapter4.src2;

public class DiscountedItem extends Item {

	private double discount;
	
	private String str;
	
	@Override
	public boolean equals(Object obj) {

		if (!super.equals(obj))
			return false;
		
		DiscountedItem other = (DiscountedItem) obj;
		
		return discount == other.discount;
	}
}