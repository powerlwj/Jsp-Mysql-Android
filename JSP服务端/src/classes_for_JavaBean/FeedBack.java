package classes_for_JavaBean;
/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月30日 下午8:45:17
 * 类说明
 */
public class FeedBack {
	
	private String ID;
	private String content;
	
	
	public FeedBack() {
		super();
	}
	public FeedBack(String iD, String content) {
		super();
		ID = iD;
		this.content = content;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
