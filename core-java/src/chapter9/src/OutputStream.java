package chapter9.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class OutputStream {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
		Member member = new Member("À¯Àç¼±", "you8054@nate.com", 28);
		
		byte[] serializedMember;
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				
				oos.writeObject(member);
				
				serializedMember = baos.toByteArray();
				
			}
		}
		
//		System.out.println(Base64.getEncoder().encodeToString(serializedMember));
		
		String base64Member = Base64.getEncoder().encodeToString(serializedMember);
		
		byte[] serializedMeber = Base64.getDecoder().decode(base64Member);
		
		try(ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)){
			try(ObjectInputStream ois = new ObjectInputStream(bais)) {
				
				
				Object objectMember = ois.readObject();
				
				Member decodedMember = (Member)  objectMember;
				
//				System.out.println(decodedMember);
			}
		}
		
		String csv = String.format("%s,%s,%d", member.getName(),member.getEmail(),member.getAge());
		System.out.println(csv);
		
	}

}
