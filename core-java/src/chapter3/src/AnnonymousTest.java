package chapter3.src;

public class AnnonymousTest implements Person{

	public static void main(String[] args) {
		
		Person test = new Person() {
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "haha";
			}
		};
		
		System.out.println(test.getName());

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
