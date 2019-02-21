package classes_for_JavaBean;

public class GoodsType {

	private String id;
	private String type;
	

	public GoodsType() {
		super();
	}

	public GoodsType(String type) {
		super();
		this.type = type;
		this.id=id;
	}

	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	
	
}
