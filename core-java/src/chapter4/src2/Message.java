package chapter4.src2;

import java.util.ArrayList;
import java.util.List;

public final class Message {
	
	private String sender;
	private List<String> recipients;
	private String text;
	private int num;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String sender, String text) {
		this.sender = sender;
		this.text = text;
	}
	
	
//	public Message clone() {
//		Message cloned = new Message();
//		
//		cloned.recipients = new ArrayList<>(this.recipients);
//		
//		return cloned;
//		
//	}
	
	
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Message original = new Message();
		Message copied = (Message) original.clone();
		
		original.setSender("before change");
		System.out.println("original's sender : " + original.getSender());
		System.out.println("copied's sender : " + copied.getSender());
		
		copied.setSender("after change");
		System.out.println("original's sender : " + original.getSender());
		System.out.println("copied's sender : " + copied.getSender());
		
		original.setNum(1);
		System.out.println("original's num : " + original.getNum());
		System.out.println("copied's num : " + copied.getNum());
		original.setNum(2);
		System.out.println("original's num : " + original.getNum());
		System.out.println("copied's num : " + copied.getNum());
		
		
	}
	
}
