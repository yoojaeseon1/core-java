package chapter5.src;

public class IndexOutSubException extends RuntimeException{
	
	public IndexOutSubException() {
		// TODO Auto-generated constructor stub
	}
	
	public IndexOutSubException(String message) {
		super(message);
	}
	
	public IndexOutSubException(Throwable cause) {
		cause.initCause(cause);
	}
	
	public IndexOutSubException(String message, Throwable cause) {
		super(message);
		cause.initCause(cause);
	}
	
	public static void main(String[] args) {		
		try{
			throw new ArrayIndexOutOfBoundsException();
		} catch(IndexOutOfBoundsException ex) {
			
		}
	}

}
