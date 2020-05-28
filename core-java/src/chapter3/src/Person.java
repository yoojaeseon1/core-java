package chapter3.src;

public interface Person {
	String getName();
	
	default int getId() {
		return 0;
	}
}
