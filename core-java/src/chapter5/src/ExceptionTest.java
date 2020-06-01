package chapter5.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) {
		
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int num = Integer.parseInt(br.readLine());
			
			PrintWriter out = new PrintWriter("output.txt");
			
			Scanner in = new Scanner(System.in);
			
		} catch(IOException e) {
			
			throw new StringIndexOutOfBoundsException();
			
		} catch(NumberFormatException e) {
			
		}
		
		
		try(PrintWriter out = new PrintWriter("output.txt")){
			
		} catch(FileNotFoundException ex) {
			
		}
		
	}
}
