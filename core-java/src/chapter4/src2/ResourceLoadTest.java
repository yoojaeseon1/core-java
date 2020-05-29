package chapter4.src2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class ResourceLoadTest {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
//		InputStream stream = MyClass.class.getResourceAsStream("/chapter4/src2/test.txt");
//		
//		Scanner in = new Scanner(stream);
//		
//		System.out.println(in.nextLine());
		
		URL[] urls = {
				new URL("file:///C://Users//yoo-pc//Desktop//skills.jar")
		};
		
		String className = "algorithmSkills.Combination";
		
		try(URLClassLoader loader = new URLClassLoader(urls)) {
			Class<?> cl = Class.forName(className, true, loader);
			Method[] methods = cl.getMethods();
			System.out.println(Arrays.toString(methods));
			
		}
	}

}
