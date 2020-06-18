package chapter10.src;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class ProcessTest {

	public static void main(String[] args) throws IOException {
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe");
		
//		Process p = builder.start();
//		
//		OutputStream processIn = p.getOutputStream();
//		
//		InputStream processOut = p.getInputStream();
//		
//		InputStream processErr = p.getErrorStream();
//		
//		String text = "hahahoho";
//		
//		byte[] textToByte = text.getBytes();
//		
//		processIn.write(textToByte);
//		
//		builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		
		Process p = new ProcessBuilder("cmd.exe", "mkdir", "test")
				.directory(Paths.get("/").toFile())
				.start();
//		
		try(Scanner in = new Scanner(p.getInputStream())) {
			while (in.hasNextLine())
				System.out.println(in.nextLine());
//			in.nextLine();
		}
		
//		Map<String, String> env = builder.environment();
//		
//		env.put("LANG", "fr_FR");
//		env.remove("JAVA_HOME");
//		Process process = builder.start();
	}
}
