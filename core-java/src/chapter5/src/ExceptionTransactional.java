package chapter5.src;

import java.io.IOException;

public class ExceptionTransactional {

	public static void main(String[] args) {

		int num = 1;
		
		try{
			
			num++;
			
			throw new IOException();
		
			
		}catch(IOException e) {
			
			System.out.println("num : " + num);
			
		}
			
		
		System.out.println("num : " + num);
		
		
	}

}
