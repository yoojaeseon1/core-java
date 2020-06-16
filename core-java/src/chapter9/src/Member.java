package chapter9.src;

import java.io.Serializable;

public class Member implements Serializable{
	
	private String name;
	private String email;
	private int age;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	public Member(String name, String email, int age) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", email=" + email + ", age=" + age + "]";
	}


}
