package chapter6.src;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		
		List<B> bList = new ArrayList<>();
		
		print(bList);
		
	}
	
	public static void print(List<? extends A> list) {
		
	}

}

class A{
	
}

class B extends A{
	
}