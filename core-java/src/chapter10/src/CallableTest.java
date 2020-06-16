package chapter10.src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		String[] words = { "abcde", "hahaoho", "pop", "ppp" };

		List<Callable<Long>> tasks = new ArrayList<>();
		for (String word : words) {
			tasks.add(() -> {
				long pCount = 0;
				System.out.println("task");
				for (int wordI = 0; wordI < word.length(); wordI++) {
					if (word.charAt(wordI) == 'p')
						pCount++;
				}
				System.out.println("pCount : " + pCount);
				return pCount;
			});
		}
		List<Callable<Long>> otherTasks = new ArrayList<>();
		
		for (String word : words) {
			tasks.add(() -> {
				System.out.println("otherTask");
				long pCount = 0;
				for (int wordI = 0; wordI < word.length(); wordI++) {
					if (word.charAt(wordI) == 'p')
						pCount++;
				}
				System.out.println("pCount : " + pCount);
				return pCount;
			});
		}

		ExecutorService executor = Executors.newCachedThreadPool();
//		ExecutorService otherExecutor = Executors.newCachedThreadPool();

		List<Future<Long>> results = executor.invokeAll(tasks);
		List<Future<Long>> otherResults = executor.invokeAll(otherTasks);
		
//		long resultUsingAny = executor.invokeAny(tasks);

		long total = 0;

		for (Future<Long> result : results) {
			System.out.println("future");
			total += result.get();
		}
		
		System.out.println("total : " + total);
//		System.out.println(resultUsingAny);

	}

}
