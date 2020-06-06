package chapter7.src;

import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;

public class Propertiestest {

	public static void main(String[] args) {
		
		Properties settings = new Properties();
		
		settings.put("width", "200");
		settings.put("title", "Hello, World!");
		String path = "haha";
//		try(OutputStream out = Files.newOutputStream(path)) {
//			
//		}

	}

}
