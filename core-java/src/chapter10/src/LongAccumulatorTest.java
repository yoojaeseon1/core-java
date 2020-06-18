package chapter10.src;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorTest {

	public static void main(String[] args) {
		
		LongAccumulator accumulator = new LongAccumulator(Long::max, 8);
		
		
//		accumulator.accumulate(5);
//		accumulator.accumulate(5);
//		accumulator.accumulate(5);
		
		Runnable adder1 = () ->{
			accumulator.accumulate(3);
		};
		
		Runnable adder2 = () ->{
			accumulator.accumulate(10);
		};
		
		Runnable adder3 = () ->{
			accumulator.accumulate(5);
		};
		
		Runnable sleeper = () -> {
			try {
				Thread.sleep(3000);
				System.out.println(accumulator.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		};
		
		Executor executor = Executors.newCachedThreadPool();
		
		executor.execute(adder1);
		executor.execute(adder2);
		executor.execute(adder3);
		executor.execute(sleeper);
		
		

	}

}
