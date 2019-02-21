package classes_for_JavaBean;

import java.sql.Date;
import java.sql.Time;

public class Attendence {
	
	private String ID;
	private String realName;
	private int sex;
	private int level;
	private String reporterID;
	private String secondMgrID;
	private String firstMgrID;
	private String directorID;
	private String region;
	private String date;
	private String morninggps;
	private String eveninggps;
	private String time1;
	private String storeName;
	private boolean leave;//请假
	private boolean late;// 迟到
	private String time2;
	private int cindex;
	private int ismorningcheck,iseveningcheck;
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getReporterID() {
		return reporterID;
	}
	public void setReporterID(String reporterID) {
		this.reporterID = reporterID;
	}
	public String getSecondMgrID() {
		return secondMgrID;
	}
	public void setSecondMgrID(String secondMgrID) {
		this.secondMgrID = secondMgrID;
	}
	public String getFirstMgrID() {
		return firstMgrID;
	}
	public void setFirstMgrID(String firstMgrID) {
		this.firstMgrID = firstMgrID;
	}
	public String getDirectorID() {
		return directorID;
	}
	public void setDirectorID(String directorID) {
		this.directorID = directorID;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMorninggps() {
		return morninggps;
	}
	public void setMorninggps(String morninggps) {
		this.morninggps = morninggps;
	}
	public String getEveninggps() {
		return eveninggps;
	}
	
	public int getIsmorningcheck() {
		return ismorningcheck;
	}
	public void setIsmorningcheck(int ismorningcheck) {
		this.ismorningcheck = ismorningcheck;
	}
	public int getIseveningcheck() {
		return iseveningcheck;
	}
	public void setIseveningcheck(int iseveningcheck) {
		this.iseveningcheck = iseveningcheck;
	}
	public void setEveninggps(String eveninggps) {
		this.eveninggps = eveninggps;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public boolean isLeave() {
		return leave;
	}
	public void setLeave(boolean leave) {
		this.leave = leave;
	}
	public boolean isLate() {
		return late;
	}
	public void setLate(boolean late) {
		this.late = late;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public int getCindex() {
		return cindex;
	}
	public void setCindex(int cindex) {
		this.cindex = cindex;
	}
	@Override
	public String toString() {
		return "Attendence [ID=" + ID + ", date=" + date + ", morninggps=" + morninggps + ", eveninggps=" + eveninggps
				+ ", time1=" + time1 + ", time2=" + time2 + ", cindex=" + cindex + "]";
	}
	

}
