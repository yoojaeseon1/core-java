package chapter6.src.genericTest;

import java.util.ArrayList;
import java.util.List;

public class WordList extends ArrayList<String>{

	public boolean add(String e) {
		
		 return super.add(e);
		 
	}
	
	public static void main(String[] args){
		
		List<Integer> list = new ArrayList<>();
		
//		if(list instanceof ArrayList<? extends List>){
//			System.out.println("haha");
//		}
		
		
		List<?> empls = new ArrayList<>();
		
//		empls = new ArrayList<Integer>();
		
//		empls.add(new Employee());
		
		
		
//		list = (List<Integer>)empls;
		
	}

}
