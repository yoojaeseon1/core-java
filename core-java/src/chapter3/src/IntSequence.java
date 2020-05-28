package chapter3.src;

public interface IntSequence {
	
	int i = 1;
	
//	boolean hasNext();
	default boolean hasNext() {
		return true;
	}
	
	public int next();
	public double average(IntSequence seq, int n);
	
//	public static double average(IntSequence seq, int n) {
//		int count = 0;
//		double sum = 0;
//		while(seq.hasNext() && count < n) {
//			count++;
//			sum += seq.next();
//		}
//		
//		return count == 0 ? 0 : sum / count;
//	}
	
	public static IntSequence digitsOf(int n) {
		return new DigitSequence(n);
	}
	
	public static int getNum() {
		return 1;
	}
}
