package chapter10.src;

import java.text.NumberFormat;

import javax.swing.text.html.parser.Parser;

public class ThreadLocalTest {
	
//	public static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	public static final ThreadLocal<NumberFormat> currencyFormat = ThreadLocal.withInitial(()->NumberFormat.getCurrencyInstance());
	
	public ThreadLocalTest() {
		
	}
	
	public static void main(String[] args) {
		
		ThreadLocalTest test = new ThreadLocalTest();
		
//		String amountDue = test.currencyFormat.format(1000);
		
		String amountDue = test.currencyFormat.get().format(1000);
		
		System.out.println(amountDue);
		

	}

}
