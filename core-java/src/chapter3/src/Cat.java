package chapter3.src;

public class Cat implements Animal {

	@Override
	public void eatSomething(String foodName) {
		
		System.out.println("eat : " + foodName);

	}
	
	

	public static void main(String[] args) {
		
		Animal cat = new Cat();
		
		cat.eatSomething("can");
		
		System.out.println(cat.getAge());
		
	}
}
