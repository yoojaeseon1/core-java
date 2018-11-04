package chapter4.src;

import java.io.InputStream;
import java.util.Scanner;

public class ClassTest {

	public static void main(String[] args) throws ClassNotFoundException {
		
		String className = "java.util.Scanner";
		
//		Class<?> cl = Class.forName(className);
		
//		Class<?> cl = java.util.Scanner.class;
		
//		Class<?> cl = int.class;
		
		Student person = new Student();
		
//		Class<?> cl = person.getClass();
//		
//		System.out.println(cl.getName());
		
		Class<?> cl1 = Runnable.class;
		
//		System.out.println(cl1.toString());
		
		InputStream stream = MyClass.class.getResourceAsStream("./test.txt");
		
		Scanner in = new Scanner(stream);
		
		in.nextLine();
		
//		File dirFile = new File("./");
//		
//		File[] fileList = dirFile.listFiles();
//		
//		for(File file : fileList) {
//			System.out.println(file.getName());
//		}
	}
}