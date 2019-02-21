package classes_for_JavaBean;

import java.util.ArrayList;


public class Target {

	private String model;
	private int achieved;
	private String type;
	private String month;
	private String price;// 销售价格
	private String marketPrice;// 市场价格
	private String ownerID;// 负责员工
	private String remain;
	private String targetType;// 任务类型
	private String targetTime;// 任务时间
	private String targetTime2;// 任务时间2, 针对特殊任务
	//private String totalPrice;//按销售额分配
	private int index;
	private int target; // 数量销售任务
	private int targetAmount; // 金额销售任务
	private int amountprices;
	private int amountpricesachieve;

	// private String startTime;
	// private String endTime;

	public Target() {
		super();
	}

	

	public Target(String model, int achieved, String type,
			String month, String price, String marketPrice, String ownerID,
			String remain, String targetType, String targetTime,
			String targetTime2, int index, int target,
			int targetAmount) {
		super();
		this.model = model;
		//this.target = target;
		this.achieved = achieved;
		this.type = type;
		this.month = month;
		this.price = price;
		this.marketPrice = marketPrice;
		this.ownerID = ownerID;
		this.remain = remain;
		this.targetType = targetType;
		this.targetTime = targetTime;
		this.targetTime2 = targetTime2;
		//this.totalPrice = totalPrice;
		this.index = index;
		this.target = target;
		this.targetAmount = targetAmount;
	}



	public int getAmountprices() {
		return amountprices;
	}



	public void setAmountprices(int amountprices) {
		this.amountprices = amountprices;
	}



	public int getAmountpricesachieve() {
		return amountpricesachieve;
	}



	public void setAmountpricesachieve(int amountpricesachieve) {
		this.amountpricesachieve = amountpricesachieve;
	}



	/**
	 * @return the targetType
	 */
	public String getTargetType() {
		return targetType;
	}

	/**
	 * @param targetType
	 *            the targetType to set
	 */
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the achieved
	 */
	public int getAchieved() {
		return achieved;
	}

	//public String getTotalPrice() {
	//	return totalPrice;
	//}

	public int getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(int targetAmount) {
		this.targetAmount = targetAmount;
	}

	//public void setTotalPrice(String totalPrice) {
	//	this.totalPrice = totalPrice;
	//}

	/**
	 * @param achieved
	 *            the achieved to set
	 */
	public void setAchieved(int achieved) {
		this.achieved = achieved;
	}

	/**
	 * @return the remain
	 */
	public String getRemain() {
		return remain;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param remain
	 *            the remain to set
	 */
	public void setRemain(String remain) {
		this.remain = remain;
	}

	/**
	 * @return the targetTime
	 */
	public String getTargetTime() {
		return targetTime;
	}

	/**
	 * @return the targetTime
	 */
	public String getTargetTime2() {
		return targetTime2;
	}

	/**
	 * @param targetTime
	 *            the targetTime to set
	 */
	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}

	/**
	 * @param targetTime
	 *            the targetTime to set
	 */
	public void setTargetTime2(String targetTime2) {
		this.targetTime2 = targetTime2;
	}

	/**
	 * @return the internalModel
	 */

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(int target) {
		this.target = target;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the marketPrice
	 */
	public String getMarketPrice() {
		return marketPrice;
	}

	/**
	 * @param marketPrice
	 *            the marketPrice to set
	 */
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * @return the ownerID
	 */
	public String getOwnerID() {
		return ownerID;
	}

	/**
	 * @param ownerID
	 *            the ownerID to set
	 */
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}



	@Override
	public String toString() {
		return "Target [model=" + model + ", achieved=" + achieved + ", type=" + type
				+ ", ownerID=" + ownerID + ", target=" + target + ", targetAmount=" + targetAmount
				+ ", amountprices=" + amountprices + ", amountpricesachieve=" + amountpricesachieve + "]";
	}

	/**
	 * @return the startTime
	 */
	// public String getStartTime() {
	// return startTime;
	// }

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	// public void setStartTime(String startTime) {
	// this.startTime = startTime;
	// }

	/**
	 * @return the endTime
	 */
	// public String getEndTime() {
	// return endTime;
	// }

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	// public void setEndTime(String endTime) {
	// this.endTime = endTime;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	

}
