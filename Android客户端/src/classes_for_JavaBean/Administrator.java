package classes_for_JavaBean;

import java.sql.Date;



/**
 * @author powerliu
 * 类说明：JavaBean类：一般属性都是与相应数据表中的字段一一对应，再生成他们的get和set方法即可。
 *一般改动数据表之后就要修改相应的javaBean,因为json数据的传输，特别是gson数据的解析，
 *经常用到JavaBean的实例对象
 */


public class Administrator {
	
	
	
	private String id;
	private String password;
	private String admin_type;
	private Date addtime;
	
	
	public Administrator()
	{
		super();
	}
	
	public Administrator(String id, String password, String admin_type,
			Date addtime) {
		super();
		this.id = id;
		this.password = password;
		this.admin_type = admin_type;
		this.addtime = addtime;
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
