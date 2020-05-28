package chapter2.src;

import java.util.ArrayList;
import java.util.List;

public class Network {
	
	
	/**
	 * ���� �ϼ�(���� ����)
	 */
	
	private final int DAYS_PER_YEAR = 365;
	
	

	public class Member {
		private String name;
		private List<Member> friends;

		public Member(String name) {
			this.name = name;
			friends = new ArrayList<>();
		}

		public void leave() {
			members.remove(this);
		}
	}
	
	private List<Member> members;

	
	/**
	 * @since version 1.8.1
	 * @param name
	 *            ���ο� ����� �̸�
	 * @return ������� Member �ν��Ͻ�
	 */
	public Member enroll(String name) {
		Member newMember = new Member(name);
		members.add(newMember);
		return newMember;
	}

	
	
	public static void main(String[] args) {

		
		/**
		 * @see chapter2.src.Invoice#raiseSalary(double) raiseSalary
		 * 
		 * @see <a href="https://www.naver.com">naver</a>
		 * @see "Core Java for the Impatient"
		 */
		
		
		Network myFace = new Network();
		Network.Member fred = myFace.enroll("fred");

		fred.leave();

	}

}
