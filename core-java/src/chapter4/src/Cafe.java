package chapter4.src;

public enum Cafe {

	AMERICANO("아메리카노"), ESPRESSO("에스프레소"), LATTE("라떼"), TEA("차");

	final private String name;

	private Cafe(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}