package chapter10.src;

import java.util.concurrent.atomic.AtomicLong;

public class Atomic {

	private static AtomicLong nextNumber;
	private static AtomicLong largestNumber;
	
	public Atomic() {
		nextNumber = new AtomicLong();
		largestNumber = new AtomicLong();
	}
	
	public static void main(String[] args) {
		
		AtomicLong nextNumber = new AtomicLong();
		
		System.out.println(nextNumber.get());
		
		System.out.println(nextNumber.incrementAndGet());
		
		long compared = 1000;
		
		
		
		nextNumber.set(Math.max(nextNumber.get(), compared));
		
		nextNumber.updateAndGet(x->Math.max(x, compared));
		
		nextNumber.accumulateAndGet(compared, Math::max);
		
	}

}
