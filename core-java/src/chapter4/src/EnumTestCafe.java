package chapter4.src;

public class EnumTestCafe {

	public static void main(String[] args) {
		
		
//		Cafe cafe = Cafe.LATTE;
		Cafe cafe = Cafe.valueOf("LATTE");
		
//		System.out.println(Cafe.valueOf("LATTE"));
//		System.out.println(Cafe.valueOf("LATTE"));
				
//		System.out.println(cafe.ordinal());
		
		Cafe[] menus = Cafe.values();
		
//		for(Cafe menu : menus) {
//			System.out.println(menu);
//		}
		
		System.out.println(cafe.getName());
		
	}
	
}
