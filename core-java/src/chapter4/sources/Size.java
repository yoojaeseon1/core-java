package chapter4.sources;

public enum Size {
	SMALL("S"), DEMIUM("D"), LARGE("L"), EXTRA_LARGE("XL");
	
	private String abbreviation;
	
	Size(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	
};