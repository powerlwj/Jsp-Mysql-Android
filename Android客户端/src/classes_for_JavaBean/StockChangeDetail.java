package classes_for_JavaBean;

import java.sql.Date;

public class StockChangeDetail {
	
	int id;
	String model;
	String  addtime;
	String comment;
	String txOwnerID;
	String rxOwnerID;
	int txQuantity;
	int rxQuantity;
	int changeQuantity;
	int newTxQuantity;
	int newRxQuantity;
	
	
	public StockChangeDetail() {
		super();
	}
	
	public StockChangeDetail(int id,String model, String addtime, String comment,
			String txOwnerID, String rxOwnerID, int txQuantity, int rxQuantity,
			int changeQuantity, int newTxQuantity, int newRxQuantity) {
		super();
		this.id=id;
		this.model = model;
		this.addtime = addtime;
		this.comment = comment;
		this.txOwnerID = txOwnerID;
		this.rxOwnerID = rxOwnerID;
		this.txQuantity = txQuantity;
		this.rxQuantity = rxQuantity;
		this.changeQuantity = changeQuantity;
		this.newTxQuantity = newTxQuantity;
		this.newRxQuantity = newRxQuantity;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTxOwnerID() {
		return txOwnerID;
	}
	public void setTxOwnerID(String txOwnerID) {
		this.txOwnerID = txOwnerID;
	}
	public String getRxOwnerID() {
		return rxOwnerID;
	}
	public void setRxOwnerID(String rxOwnerID) {
		this.rxOwnerID = rxOwnerID;
	}
	public int getTxQuantity() {
		return txQuantity;
	}
	public void setTxQuantity(int txQuantity) {
		this.txQuantity = txQuantity;
	}
	public int getRxQuantity() {
		return rxQuantity;
	}
	public void setRxQuantity(int rxQuantity) {
		this.rxQuantity = rxQuantity;
	}
	public int getChangeQuantity() {
		return changeQuantity;
	}
	public void setChangeQuantity(int changeQuantity) {
		this.changeQuantity = changeQuantity;
	}
	public int getNewTxQuantity() {
		return newTxQuantity;
	}
	public void setNewTxQuantity(int newTxQuantity) {
		this.newTxQuantity = newTxQuantity;
	}
	public int getNewRxQuantity() {
		return newRxQuantity;
	}
	public void setNewRxQuantity(int newRxQuantity) {
		this.newRxQuantity = newRxQuantity;
	}
	

}
