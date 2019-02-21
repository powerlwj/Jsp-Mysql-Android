package classes_for_JavaBean;

import java.sql.Date;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月31日 上午10:40:53
 * 类说明
 */
public class Mess {

	private String senderID;
	private String title;
	private String content;
	private int level;
	private String addtime;
	private String receiverID;
	private String readFlag;

	
	public Mess() {
		super();
	}


	public String getSenderID() {
		return senderID;
	}


	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getAddtime() {
		return addtime;
	}


	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}


	public String getReceiverID() {
		return receiverID;
	}


	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}


	public String getReadFlag() {
		return readFlag;
	}


	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
	
}
