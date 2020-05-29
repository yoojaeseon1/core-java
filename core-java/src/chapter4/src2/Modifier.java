package chapter4.src2;

public enum Modifier {
	
	PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL, ABSTRACT;
	
//	private static int maskbit = 1;
	
	private int mask;
	
//	public Modifier(){
//		mask = maskBit;
//		mask *= 2;
//	}
	
	static {
		int maskBit = 1;
		for(Modifier m : Modifier.values()) {
			m.mask = maskBit;
			maskBit *= 2;
		}
	}

}
