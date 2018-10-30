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
		
		SquareSequence square = new SquareSequence();
		
		if(square instanceof IntSequence) {
			System.out.println("sub type");
		} else {
			System.out.println("no sub type");
		}
		if(square instanceof SquareSequence) {
			System.out.println("sub type");
		} else {
			System.out.println("no sub type");
		}
		
	}

}
