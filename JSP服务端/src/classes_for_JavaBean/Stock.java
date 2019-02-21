package classes_for_JavaBean;

public class Stock {
	
	private String model;
	private String ownerID;
	private int quantity;
	private int stockAlarm;
	
	
	public Stock() {
		super();
	}
	public Stock(String model, String ownerID, int quantity, int stockAlarm) {
		super();
		this.model = model;
		this.ownerID = ownerID;
		this.quantity = quantity;
		this.stockAlarm = stockAlarm;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the ownerID
	 */
	public String getOwnerID() {
		return ownerID;
	}
	/**
	 * @param ownerID the ownerID to set
	 */
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the stockAlarm
	 */
	public int getStockAlarm() {
		return stockAlarm;
	}
	/**
	 * @param stockAlarm the stockAlarm to set
	 */
	public void setStockAlarm(int stockAlarm) {
		this.stockAlarm = stockAlarm;
	}
	
}
