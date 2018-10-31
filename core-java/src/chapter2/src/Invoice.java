package chapter2.src;

/**
 * 품목명을 포함하는 청구서를 표현한다.
 * @author jaeseonyoo
 * @version 1.1
 */

public class Invoice {
	
	/**
	 *  연간 일수(윤년제외)
	 */
	public static final int DAYS_PER_YEAR = 365;
	
	private int salary;
	
	public Invoice() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 직원의 급여를 인상한다.
	 * @param byPercent 급여인상 백분율(예를 들어 10은 10%를 의미함)
	 * @return 인상금액
	 * @since version 1.1
	 * 
	 */
	public double raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
		return raise;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
