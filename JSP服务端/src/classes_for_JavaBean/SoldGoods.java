package classes_for_JavaBean;

import java.sql.Date;

public class SoldGoods {

	private String imei;
	private String type;
	private String model;
	private String soldPrice;
	private String sellerID;
	private String soldDate;
	private int soldFlag;
	private int soldNumber;
	private int Price2;
	private String targetTime,targetTime2;
	private String time1,time2;
	
	
	public String getTargetTime() {
		return targetTime;
	}


	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}


	public String getTargetTime2() {
		return targetTime2;
	}


	public void setTargetTime2(String targetTime2) {
		this.targetTime2 = targetTime2;
	}


	public SoldGoods() {
		super();
	}


	public String getTime1() {
		return time1;
	}


	public void setTime1(String time1) {
		this.time1 = time1;
	}


	public String getTime2() {
		return time2;
	}


	public void setTime2(String time2) {
		this.time2 = time2;
	}


	public SoldGoods(String imei, String type, String model, String soldPrice,
			String sellerID, String soldDate, int soldFlag) {
		super();
		this.imei = imei;
		this.type = type;
		this.model = model;
		this.soldPrice = soldPrice;
		this.sellerID = sellerID;
		this.soldDate = soldDate;
		this.soldFlag = soldFlag;
	}


	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}


	public int getPrice2() {
		return Price2;
	}


	public void setPrice2(int price2) {
		Price2 = price2;
	}


	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}


	/**
	 * @return the soldNumber
	 */
	public int getSoldNumber() {
		return soldNumber;
	}


	/**
	 * @param soldNumber the soldNumber to set
	 */
	public void setSoldNumber(int soldNumber) {
		this.soldNumber = soldNumber;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the soldPrice
	 */
	public String getSoldPrice() {
		return soldPrice;
	}


	/**
	 * @param soldPrice the soldPrice to set
	 */
	public void setSoldPrice(String soldPrice) {
		this.soldPrice = soldPrice;
	}


	/**
	 * @return the sellerID
	 */
	public String getSellerID() {
		return sellerID;
	}


	/**
	 * @param sellerID the sellerID to set
	 */
	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}


	/**
	 * @return the soldDate
	 */
	public String getSoldDate() {
		return soldDate;
	}


	/**
	 * @param soldDate the soldDate to set
	 */
	public void setSoldDate(String soldDate) {
		this.soldDate = soldDate;
	}


	/**
	 * @return the soldFlag
	 */
	public int getSoldFlag() {
		return soldFlag;
	}


	/**
	 * @param soldFlag the soldFlag to set
	 */
	public void setSoldFlag(int soldFlag) {
		this.soldFlag = soldFlag;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SoldGoods [imei=" + imei + ", type=" + type + ", model="
				+ model + ", soldPrice=" + soldPrice + ", sellerID=" + sellerID
				+ ", soldDate=" + soldDate + ", soldFlag=" + soldFlag + "]";
	}
	
	
	
}
