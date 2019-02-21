package classes_for_JavaBean;

import java.sql.Date;

public class Employee {
	
	private String ID;
	private String password;
	private String realName;
	private int sex;
	private Date birthday;
	private String paperType;
	private String paperNo;
	private String tel;
	private String email;
	private int level;
	private String reporterID;
	private String secondMgrID;
	private String firstMgrID;
	private String directorID;
	private String region;
	private Date addtime;
	private String own;
	private Boolean Is_Special;
	
	
	
	public Employee() {
		super();
	}
	public Employee(String iD, String password, String realname, int sex,
			Date birthday, String paperType, String paperNo, String tel,
			String email, int level, String reporterID, String secondMgrID,
			String firstMgrID, String directorID, String region,Date addtime) {
		super();
		ID = iD;
		this.password = password;
		this.realName = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.paperType = paperType;
		this.paperNo = paperNo;
		this.tel = tel;
		this.email = email;
		this.level = level;
		this.reporterID = reporterID;
		this.secondMgrID = secondMgrID;
		this.firstMgrID = firstMgrID;
		this.directorID = directorID;
		this.region = region;
		this.addtime=addtime;
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
	 * @return the realname
	 */
	public String getRealname() {
		return realName;
	}
	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realName = realname;
	}
	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getOwn() {
		return own;
	}
	public void setOwn(String own) {
		this.own = own;
	}
	public Boolean getIs_Special() {
		return Is_Special;
	}
	public void setIs_Special(Boolean is_Special) {
		Is_Special = is_Special;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the paperType
	 */
	public String getPaperType() {
		return paperType;
	}
	/**
	 * @param paperType the paperType to set
	 */
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	/**
	 * @return the paperNo
	 */
	public String getPaperNo() {
		return paperNo;
	}
	/**
	 * @param paperNo the paperNo to set
	 */
	public void setPaperNo(String paperNo) {
		this.paperNo = paperNo;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the reporterID
	 */
	public String getReporterID() {
		return reporterID;
	}
	/**
	 * @param reporterID the reporterID to set
	 */
	public void setReporterID(String reporterID) {
		this.reporterID = reporterID;
	}
	/**
	 * @return the secondMgrID
	 */
	public String getSecondMgrID() {
		return secondMgrID;
	}
	/**
	 * @param secondMgrID the secondMgrID to set
	 */
	public void setSecondMgrID(String secondMgrID) {
		this.secondMgrID = secondMgrID;
	}
	/**
	 * @return the firstMgrID
	 */
	public String getFirstMgrID() {
		return firstMgrID;
	}
	/**
	 * @param firstMgrID the firstMgrID to set
	 */
	public void setFirstMgrID(String firstMgrID) {
		this.firstMgrID = firstMgrID;
	}
	/**
	 * @return the directorID
	 */
	public String getDirectorID() {
		return directorID;
	}
	/**
	 * @param directorID the directorID to set
	 */
	public void setDirectorID(String directorID) {
		this.directorID = directorID;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "查到的关键数据："+password+region+level+own;
	}
	
	
	

}
