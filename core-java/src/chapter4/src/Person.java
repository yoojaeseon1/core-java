package chapter4.src;

public abstract class Person {
	
	
	private String name;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	
	public final String getName() {
		return this.name;
	}
	
	public abstract int getId();
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
		Person person = new Student();
//		
//		List<String> names = new ArrayList<String>(100) {
//			
//			@Override
//			public void add(int index, String element) {
//				super.add(index, element);
//				System.out.println("Adding " + element + " at " + index);
//			}
//		};
//		
//		names.add(0, "haha");
		
		
		Student stu2 = new Student();
		Student stu3 = new Student();
		Student stu5 = new Student();
		
//		
//		
//		System.out.println(stu);
		
		String a = "haha";
		String b = "haha";
		String c = new String("haha");
//		
//		System.out.println(a.equals(b));
//		System.out.println(a==b);
		
		
		System.out.println(stu5.hashCode());
		System.out.println(stu3.hashCode());
		System.out.println(stu2.hashCode());
		
//		String a = "haha";
		
		System.out.println(a.hashCode());
		
		
	}

}

class Student extends Person{

	private int stuNum;
	private int score;
	
	
	public void test(Person p) {
		
	}
	
	
	@Override
	public String toString() {
		return getClass().getName()+"Student [stuNum=" + stuNum + ", score=" + score + "]";
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public int getId() {
		return 0;
	}
}


