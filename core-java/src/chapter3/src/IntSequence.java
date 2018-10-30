package chapter3.src;

public interface IntSequence {
	
	int i = 1;
	
//	boolean hasNext();
	default boolean hasNext() {
		return true;
	}
	
	int next();
	
	public static IntSequence digitsOf(int n) {
		return new DigitSequence(n);
	}
}
