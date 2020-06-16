package chapter10.src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorCompletionServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		String[] words = { "abcde", "hahaoho", "pop", "ppp" };

		List<Callable<Integer>> tasks = new ArrayList<>();
		tasks.add(() -> {
			for (int i = 1; i < 1000; i++) {
				System.out.println("task : " + i);
			}
			return  0;
		});

		List<Callable<Integer>> otherTasks = new ArrayList<>();

		otherTasks.add(() -> {
			for (int i = 1; i < 1000; i++) {
				System.out.println("otherTask : " + i);
			}
			return  0;
		});

		ExecutorService executor = Executors.newCachedThreadPool();

		ExecutorCompletionService<Integer> service = new ExecutorCompletionService<>(executor);

		for (Callable<Integer> task : tasks)
			service.submit(task);

		for (Callable<Integer> task : otherTasks)
			service.submit(task);

	}

}
