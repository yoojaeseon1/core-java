package chapter3.src;

import java.util.Random;

public class CaptureTest {

	public static void main(String[] args) {
		
		 

	}
	
	private static Random generator = new Random();
	
	public static IntSequence randomInts(int low, int high) {
		
		class RandomSequence implements IntSequence{

			@Override
			public int next() {
//				low++;
				return low + generator.nextInt(high - low + 1);
			}

			@Override
			public double average(IntSequence seq, int n) {
				return n;
				// TODO Auto-generated method stub 
			}
			
		}
		
		return new RandomSequence();
		
	}
	
	private int num = 5;
	
	public int addNumber(int number) {
		num++;
		number++;
		
		class Number{
			
			public int changeNumber(){
//				number++;
				
				return 5;
			}
			
		}
		
		return number;
	}

}
