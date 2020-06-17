package chapter10.src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

final class ImmutableTest {

	final List<Integer> list;
	final int number;
	
	public ImmutableTest() {
		list = new ArrayList<>();
		number = 5;
	}
	
	public static void main(String[] args) {
		
		ImmutableTest test = new ImmutableTest();
		int num = 5;
		Runnable addTask = () -> {
			for(int i = 1; i <= 10; i++) 
			{
				System.out.println("addTask : " + i);
				test.list.add(i);
			}
		};
		
		Runnable addTask2= () -> {
			for(int i = 11; i <= 20; i++) {
//				if(test.list.size() > 0)
//					test.list.remove(0);
				System.out.println("addTask2 : " + i);
				test.list.add(i);
			}
		};
		
		Executor executor = Executors.newCachedThreadPool();
		
		executor.execute(addTask);
		executor.execute(addTask2);
		
		for(int listI = 0; listI < test.list.size(); listI++) {
			System.out.println(test.list.get(listI));
		}
	}


}
