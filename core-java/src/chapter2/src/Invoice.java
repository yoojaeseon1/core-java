package chapter2.src;


public class Invoice {
	
	public static final int DAYS_PER_YEAR = 365;
	
	private int salary;
	
	public Invoice() {
		// TODO Auto-generated constructor stub
	}
	
	
	public double raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
		return raise;
	}
	
	

	public static void main(String[] args) {
		
		

	}

}
