package chapter10.src;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

	public static void main(String[] args) {
		
		LongAdder count = new LongAdder();
		
		
		Runnable adder1 = () ->{
			count.add(5);
			count.increment();
		};
		
		Runnable adder2 = () ->{
			count.increment();
		};
		
		Runnable adder3 = () ->{
			count.add(3);
			count.increment();
		};
		
		Runnable sleeper = () -> {
			try {
				Thread.sleep(3000);
				System.out.println(count.sum());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		};
		
		Executor executor = Executors.newCachedThreadPool();
		
		executor.execute(adder1);
		executor.execute(adder2);
		executor.execute(adder3);
		executor.execute(sleeper);
		
		
		
//		System.out.println(count.sum());

	}

}
