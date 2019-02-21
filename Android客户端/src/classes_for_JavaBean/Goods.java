package classes_for_JavaBean;

public class Goods {
	
	private String internalModel;
	private String model;
	private String type;
	private String configInfo;
	private String region;
	private String picPath;
	
	
	
	public Goods() {
		super();
	}
	public Goods(String internalModel, String model, String type,
			String configInfo, String region, String picPath) {
		super();
		this.internalModel = internalModel;
		this.model = model;
		this.type = type;
		this.configInfo = configInfo;
		this.region = region;
		this.picPath = picPath;
	}
	/**
	 * @return the internalModel
	 */
	public String getInternalModel() {
		return internalModel;
	}
	/**
	 * @param internalModel the internalModel to set
	 */
	public void setInternalModel(String internalModel) {
		this.internalModel = internalModel;
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
	 * @return the configInfo
	 */
	public String getConfigInfo() {
		return configInfo;
	}
	/**
	 * @param configInfo the configInfo to set
	 */
	public void setConfigInfo(String configInfo) {
		this.configInfo = configInfo;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}
	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	

}
