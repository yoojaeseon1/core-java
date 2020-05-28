package chapter3.src;

public class SquareSequence implements IntSequence{
	
	private int i;
	
	public boolean hasNext() {
		return true;
	}
	
	public int next() {
		i++;
		return i*i;
	}
	
	public static void main(String[] args) {
		
		IntSequence square = new SquareSequence();
		
//		System.out.println("square.i : " + square.i);
		
		double avg = square.average(square, 100);
		
		System.out.println("avg : " + avg);
		
//		if(square instanceof IntSequence) {
//			System.out.println("sub type");
//		} else {
//			System.out.println("no sub type");
//		}
//		if(square instanceof SquareSequence) {
//			System.out.println("sub type");
//		} else {
//			System.out.println("no sub type");
//		}
		
	}

	@Override
	public double average(IntSequence seq, int n) {
		int count = 0;
		double sum = 0;
		while(seq.hasNext() && count < n) {
			count++;
			sum += seq.next();
		}
		
		return count == 0 ? 0 : sum / count;
	}

}
