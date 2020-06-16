### 직렬화

- heap, stack영역에 상주되어 있는 인스턴스, 로컬변수들을 바이트 형태로 변환하는 기술
- 다시 되돌리는 역직렬화도 포함된 개념

	Member member = new Member("유재선", "you8054@nate.com", 28);
	
	byte[] serializedMember;
	
	try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
		try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
			
			oos.writeObject(member);
			
			serializedMember = baos.toByteArray();
			
		}
	}
	
	System.out.println(Base64.getEncoder().encodeToString(serializedMember));


- try안에 resource를 생성할 경우 try문이 종료될 때 자동으로 resource가 종료된다.
- resource는 AutoCloseable 인터페이스를 구현해야하고 AutoCloseable인터페이스의 close()메소드가 호출되면서 종료되는 것이다.

---

### 역직렬화
	
	String base64Member = Base64.getEncoder().encodeToString(serializedMember);
	
	byte[] serializedMeber = Base64.getDecoder().decode(base64Member);
	
	try(ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)){
		try(ObjectInputStream ois = new ObjectInputStream(bais)) {
			
			
			Object objectMember = ois.readObject();
			
			Member decodedMember = (Member) objectMember;
			
			System.out.println(decodedMember);
		}
	}


---

### Java의 직렬화

- 자바 직렬화는 자바 시스템에서의 개발에 최적화되어 있다.

- 복잡한 데이터 구조의 클래스의 객체라도 직렬화 기본 조건만 지키면 큰 작업 없이 바로 직렬화가 가능하다.(역직렬화도 마찬가지)