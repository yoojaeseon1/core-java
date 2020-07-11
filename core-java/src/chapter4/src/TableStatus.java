package chapter4.src;

public enum TableStatus {
	
	Y("1", true),
	N("0", false);
	
	
	private String table1Status;
	private boolean table2Status;
	
	private TableStatus(String table1Status, boolean table2Status) {
		this.table1Status = table1Status;
		this.table2Status = table2Status;
	}
	
	public String getTable1Status(){
		return this.table1Status;
	}
	
	public boolean getTable2Status(){
		return this.table2Status;
	}

}
