package chapter10.src;

public class InterruptionTest {

	public static void main(String[] args) {

		Runnable task = () -> {

			for (int i = 1; i <= 1000; i++) {

				if (Thread.currentThread().isInterrupted())
					return;
				System.out.println(i);
			}
		};

		Runnable task2 = () -> {

			try {
				for (int i = 1; i <= 1000; i++) {
					if (i == 500)
						Thread.sleep(10000);
					System.out.println(i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	}
}
