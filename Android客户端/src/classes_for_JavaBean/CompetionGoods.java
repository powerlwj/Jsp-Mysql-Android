package classes_for_JavaBean;

import java.sql.Date;

public class CompetionGoods {

	private String brand;
	private String model;
	private String type;
	private String price;
	private String priceDate;
	private String picPath;
	private String feature;

	public CompetionGoods() {
		super();
	}

	public CompetionGoods(String brand, String model, String type,
			String price, String priceDate, String picPath) {
		super();
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.price = price;
		this.priceDate = priceDate;
		this.picPath = picPath;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getFeatures() {
		return feature;
	}

	public void setFeatures(String feature) {
		this.feature = feature;
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
	 * @return the priceDate
	 */
	public String getPriceDate() {
		return priceDate;
	}

	/**
	 * @param priceDate
	 *            the priceDate to set
	 */
	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}

	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}

	/**
	 * @param picPath
	 *            the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Override
	public String toString() {
		return "CompetionGoods [brand=" + brand + ", model=" + model
				+ ", type=" + type + ", price=" + price + ", priceDate="
				+ priceDate + ", picPath=" + picPath + "]";
	}

}
