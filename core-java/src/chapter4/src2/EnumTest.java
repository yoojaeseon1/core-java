package chapter4.src2;

public class EnumTest {

	public static void main(String[] args) {
		
		
		Size notMySize = Size.valueOf("LARGE");
//		
		System.out.println(notMySize.getAbbreviation());
//		
//		Size[] allValues = Size.values();
//		
//		for(Size s : allValues) {
//			System.out.println(s);
//		}
		
		Operation op = Operation.valueOf("ADD");
		int result = op.eval(op, 3, 4);
		System.out.println(result);
		op = Operation.valueOf("SUBTRACT");
		result = op.eval(op, 3, 4);
		System.out.println(result);
		op = Operation.valueOf("MULTIPLY");
		result = op.eval(op, 3, 4);
		System.out.println(result);
		op = Operation.valueOf("DIVIDE");
		result = op.eval(op, 3, 4);
		System.out.println(result);
		

	}

}
