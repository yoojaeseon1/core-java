## 병행 프로그래밍

### Thread(스레드)

- task : 처리하는 작업의 단위
- 스레드는 1개 이상의 task를 묶어 실행할 수 있다.
- 간단한 task의 경우 하나의 스레드에서 모두 처리하는 것이 효율적이다.(스레드를 실행하는데 발생하는 비용을 줄이는 것이 좋다.)
- 강도 높은 계산을 하는 task의 경우는 스레드와 task를 1:1로 만들고, 하나의 프로세서에서 하나의 스레드만 실행되도록 하는 것이 효율적이다.(프로세서 안에서 스레드 간의 context switching하는데 발생하는 오버헤드를 줄일 수 있다.)
- 멀티 스레딩에서의 context switching은 같은 process내에서만 이루어진다.

		Runnable hellos = () -> {
			for(int i = 1; i <= 1000; i++) {
				System.out.println("Hello " + i);
			}
		};
		
		Runnable goodbyes = () -> {
			for(int i = 1; i <= 1000; i++) {
				System.out.println("goodbye " + i);
			}
		};
		
		Executor executor = Executors.newCachedThreadPool();
		
		executor.execute(hellos);
		executor.execute(goodbyes);
		
		// Executor executor = Executors.newFixedThreadPool(5);
		
		int processors = Runtime.getRuntime().availableProcessors(); // 현재 시스템의 프로세서의 개수


출력

Hello 1~1000
goodbye 1~100

무작위로 실행된다.(여러개의 스레드가 실행 순서를 보장하지 않고 실행된다.)

프로그램이 마지막 출력 후 바로 종료되지 않는다.(프로그램은 스레드 pool에서 나온 스레드가 잠시 유휴(idle, 가동되지 않는) 상태에 있어서 Executor가 종료시킬 때 종료된다.)

---
	Executors.newCachedThreadPool()

- 프로그램에 최적화 되어있는 ExecutorService 인스턴스를 리턴한다.
- 프로그램에는 대부분의 시간을 대기하면서 보내는 task가 다수 포함되어 있다.
- 대기중인 유휴 스레드(idle thread)에서 실행되지만, 모든 스레드가 실행 중이면 새로운 스레드가 할당된다.
- 장시간 동안 유휴 상태인 스레드는 종료된다.

---

	Executor executor = Executors.newFixedThreadPool(numThread);

- 고정된 개수의 스레드 풀을 가지는 ExecutorService 인스턴스를 리턴한다.
- 고정된 개수만큼의 모든 스레드가 실행중일 때 실행해야할 task가 있으면 대기하다 종료되는 스레드가 있을 때 실행된다.

---

### Future / ExecutorService

- sub task
	- 하나의 task를 완료하기 위한 결과물을 만들어내는 task 안의 세분화 된 task.
	- Callable 인터페이스의 call 메소드를 구현해 만들 수 있다.
	- 여러 개의 Callable인터페이스를 구현한 클래스의 인스턴스를 List에 넣어 하나의 task로 묶을 수 있다.
	- 병행으로 실행된다.
	- 실행 순서를 보장하지 않는다.
	- sub task들이 모두 완료될 때 까지 해당 task에서 block된다.

			public interface Callable<V> {
				V call() throws Exception;
			}

---

#### Future

- 하나의 task를 여러 개의 sub task로 나눌 때 각각의 sub task의 결과 값을 담는 인스턴스
- task는 이미 submit 또는 invokeAll/Any에서 실행됐다.

- 메소드들

	- V	get() : sub task가 완료된 결과 값을 리턴한다.
	- V	get(long timeout, TimeUnit unit) : 실행이 완료되어 결과 값을 리턴하거나 타임아웃에 이를 때까지 블록한다. 
	- boolean	cancel(boolean mayInterruptIfRunning) : task 취소를 시도한다.
	- boolean	isCancelled() : cancel을 통한 취소여부를 리턴한다.(취소 되었으면 true)
	- boolean	isDone() : task가 결과 값을 리턴하고 정상적으로 완료되었는지 여부를 리턴한다.(정상적으로 종료되었으면 true)

---

#### ExecutorService
- Executors.newCachedThreadPool/Executors.newCachedThreadPool가 리턴하는 인스턴스
- sub task들을 future 인스턴스로 변환해 실행할 수 있도록 해준다.

	ExecuteService executor = Executors.newCachedThreadPool();
	Callable<V> task = ...;
	Future<V> result = executor.submit(task); // 하나의 sub task를 Future 인스턴스로 만들어 준다. 


---

	String[] words = { "abcde", "hahaoho", "pop", "ppp" };

	List<Callable<Long>> tasks = new ArrayList<>(); // sub task를 묶을 List 생성

	for (String word : words) {
		tasks.add(() -> { // Callable 인터페이스를 구현한 익명 클래스를 tasks에 추가한다.
			long pCount = 0;
			for (int wordI = 0; wordI < word.length(); wordI++) {
				if (word.charAt(wordI) == 'p')
					pCount++;
			}
			return pCount;
		});
	}

	List<Callable<Long>> otherTasks = new ArrayList<>();
	
	for (String word : words) {
		otherTasks.add(() -> {
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

	ExecutorService executor = Executors.newCachedThreadPool(); // ExecutorService인스턴스 생성(Executor 아님)

	List<Future<Long>> results = executor.invokeAll(tasks); // submit을 사용하지 않고 List로 한 번에 넣을 경우 사용(여기서 sub task가 실행된다.)
	List<Future<Long>> otherResults = executor.invokeAll(otherTasks); // 바로 위의 task가 완료되기 전까지 block된다.

	long total = 0;

	for (Future<Long> result : results)
		total += result.get(); // sub task들로부터 결과 값을 리턴 받아 tatal에 추가한다.
	
	System.out.println("total : " + total);

	}


출력(첫 번째 시도)

	task
	task
	pCount : 0
	task
	task
	pCount : 2
	pCount : 3
	pCount : 0
	otherTask
	pCount : 0
	otherTask
	otherTask
	pCount : 2
	pCount : 0
	otherTask
	pCount : 3
	total : 10

출력(두 번째 시도)

	task
	task
	task
	task
	pCount : 3
	pCount : 2
	pCount : 0
	pCount : 0
	otherTask
	otherTask
	otherTask
	otherTask
	pCount : 0
	pCount : 3
	pCount : 2
	pCount : 0
	total : 10


- 출력으로 확인할 수 있는 부분

	- 하나의 task가 전부 끝나기 전까지 blocking된다.(1~1000까지 출력하는 task를 2개 실행해도 blocking되어 task1~1000, otherTask1~1000이 순서대로 출력된다.)
	- task안의 sub task가 병행으로 실행되고 실행순서가 보장되지 않는다.
	- Future는 task가 모두 실행되야 결과 값을 가져오기 때문에 get()은 항상 submit/invoke 이후에 실행된다.

---

#### sub task들이 완료될 때까지 호출한 task를 block하고 싶지 않을 때

- ExecutorCompletionService를 사용하면 된다.

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



출력

task1~1000, otherTask1~1000이 섞여서 나온다.(실행할 때마다 섞이는 개수가 다르다.)

---

#### invokeAny

- sub task 중 하나가 예외를 던지지 않고 완료하면 Future의 값을 리턴하고 다른 sub task를 취소한다.
- 일치하는 대상을 발견한 즉시 결론을 내릴 수 있는 검색에서 유용하다.

	String word = ...;
	Set<Path> files = ....;
	List<Callable<Path>> tasks = new ArrayList<>();
	for(Path p : files) tasks.add(
		()->{if(p에 단어가 있으면) return p;
			else
				throw Exception;	
			}
	)

	Path found = executor.invokeAny(tasks); // sub task들이 실행되고 return이 될 경우 다른 sub task를 종료시킨다.

return하지 않는 경우에 예외를 던져버리면 된다.

---
