package chapter3.src;

public class DigitSequence implements IntSequence{
	
	private int number;
	
	public DigitSequence(int n) {
		number = n;
	}
	
	public boolean hasNext() {
		return number != 0;
	}
	
	public int next() {
		int result = number % 10;
		number /= 10;
		return result;
	}

	@Override
	public double average(IntSequence seq, int n) {
		
		return 0;
	}
	
	public static IntSequence digitOf(int n) {
		return new DigitSequence(n);
	}
	
	public int getNum() {
		return 2;
	}
	
	public static void main(String[] args) {
		
//		DigitSequence sequence = new DigitSequence(1234);
//		
//		if(sequence instanceof DigitSequence) {
//			DigitSequence digits = (DigitSequence) sequence;
//		}
		
		IntSequence digits = IntSequence.digitsOf(1729);
		
//		System.out.println(digits.next());
		
		System.out.println(IntSequence.getNum());
//		System.out.println(DigitSequence.getNum());
		
	}
	
}
