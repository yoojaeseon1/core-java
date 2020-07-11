package chapter4.src;

public class OriginTable {
	
	public static void main(String[] args) {
		
		TableStatus origin = TableStatus.valueOf("Y");
		
		String table1Status = origin.getTable1Status();
		boolean table2Status = origin.getTable2Status();
		
		System.out.println("table1Status : " + table1Status);
		System.out.println("table2Status : " + table2Status);
		
		
	}
}
