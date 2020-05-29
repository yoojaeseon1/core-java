package chapter4.src2;

public enum Operation {
	
	
//	ADD{
//		public int eval(int arg1, int arg2) {
//			return arg1 + arg2;
//		}
//	},
//	SUBTRACT {
//		public int eval(int arg1, int arg2) {
//			return arg1 - arg2;
//		}
//	},
//	MULTIPLY {
//		public int eval(int arg1, int arg2) {
//			return arg1 * arg2;
//		}
//	},
//	DIVIDE {
//		public int eval(int arg1, int arg2) {
//			return arg1 / arg2;
//		}
//	};
//	
//	public abstract int eval(int arg1, int arg2);
	
	
	
	ADD, SUBTRACT, MULTIPLY, DIVIDE;
	
	public static int eval(Operation op, int arg1, int arg2) {
		int result = 0;
		
		switch(op) {
			case ADD: 
				result = arg1 + arg2;
				break;
			case SUBTRACT:
				result = arg1 - arg2;
				break;
			case MULTIPLY :
				result = arg1 * arg2;
				break;
			case DIVIDE:
				result = arg1 / arg2;
				break;
		}
		return result;
	}

}
