package chapter4.src;

public class EnumTest {

	public static void main(String[] args) {

		Size notMySize = Size.valueOf("SMALL");

		Size[] allValues = Size.values();
		
		System.out.println(notMySize);

		 for(Size s : Size.values()) {
			 System.out.println(s);
		 }
		
//		int result = eval(Operation.ADD, 1, 2);
//		
//		System.out.println(result);
//		
//		Size sizeTest = Size.valueOf("LARGE");
//		
//		System.out.println(sizeTest.getAbbreviation());
	}

	public static int eval(Operation op, int arg1, int arg2) {
		int result = 0;

		switch (op) {
		case ADD:
			result = arg1 + arg2;
			break;
		case SUBTRACT:
			result = arg1 - arg2;
			break;
		case MULTIPLY:
			result = arg1 * arg2;
			break;
		case DIVIDE:
			result = arg1 / arg2;
			break;
		}

		return result;
	}
}