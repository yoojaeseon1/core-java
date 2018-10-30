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
		return result;
	}

}
