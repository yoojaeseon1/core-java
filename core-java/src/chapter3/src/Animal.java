package chapter3.src;

public interface Animal {
	
	void eatSomething(String foodName);
	
	
	default int getAge(){
		return 5;
	}

}
