package chapter5.src;

import java.util.Arrays;

public class FinallyTest {
	
	private String name;
	private int age;
	private int[] numbers;
	
	public static void main(String[] args) {
		
		FinallyTest test = new FinallyTest();
		
//		System.out.println("main getName : " + test.getName());
//		System.out.println("main this.name : "+ test.name);
		System.out.println("main getAge : "+test.getAge());
		System.out.println("main this.age : "+test.age);
		
		System.out.println("-----------");
		
		System.out.println("main getNumbers : " + Arrays.toString(test.getNumbers()));
		System.out.println("main this.numbers : " + Arrays.toString(test.numbers));
		
	}
	
	public FinallyTest() {
		numbers = new int[5];
		numbers[0] = 0;
		numbers[1] = 1;
		numbers[2] = 2;
		numbers[3] = 3;
		numbers[4] = 4;
	}
	
	public int[] getNumbers(){
		
		
		try{
			this.numbers[0] = 10;
			return this.numbers;
			
		} catch(ArrayIndexOutOfBoundsException ex) {
			
		} finally{
			
			this.numbers[0] = 20;
			System.out.println("finally getNumbers : " + this.numbers[0]);
			
			return this.numbers;
			
		}
	}
	public String getName(){
		
		try{
			this.name = "try";
			return this.name;
//			System.out.println("end try");
		} catch(StringIndexOutOfBoundsException ex) {
			
		} finally{
			System.out.println("finally name : " + this.name);
			this.name="finally";
		}
		System.out.println("end getName");
		return name;
		
	}
	
	public int getAge(){
		
		try{
			this.age = 5;
			return this.age;
			
		} catch(Exception ex) {
			
		} finally{
			System.out.println("finally age : " + this.age);
			this.age = 10;
		}
		System.out.println("end getAge");
		return this.age;
		
	}
	

}
