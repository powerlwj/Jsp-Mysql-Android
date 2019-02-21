package classes_for_JavaBean;

public class EmployeeLevel {

	private String ID;
	private int level;
	
	
	public EmployeeLevel() {
		super();
	}
	public EmployeeLevel(String iD, int level) {
		super();
		ID = iD;
		this.level = level;
	}
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
