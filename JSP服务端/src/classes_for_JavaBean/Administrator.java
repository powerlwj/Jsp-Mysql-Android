package classes_for_JavaBean;

import java.sql.Date;

public class Administrator {
	
	private String id;
	private String password;
	private String admin_type;
	private Date addtime;
	private String region;
	
	public Administrator()
	{
		super();
	}
	
	public Administrator(String id, String password, String admin_type,
			Date addtime,String region) {
		super();
		this.id = id;
		this.password = password;
		this.admin_type = admin_type;
		this.addtime = addtime;
		this.region=region;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the admin_type
	 */
	public String getAdmin_type() {
		return admin_type;
	}
	/**
	 * @param admin_type the admin_type to set
	 */
	public void setAdmin_type(String admin_type) {
		this.admin_type = admin_type;
	}
	/**
	 * @return the addtime
	 */
	public Date getAddtime() {
		return addtime;
	}
	/**
	 * @param addtime the addtime to set
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	

}
