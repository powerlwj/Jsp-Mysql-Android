package classes_for_interface;

import classes_for_JavaBean.Attendence;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 上午9:32:16
 * 类说明
 */
public interface AttendenceIF {
	
	//上班签到
	public int checkin(Attendence attendence);
	//晚上下班
	public int checkout(Attendence attendence);
	//上下班签到否？
	public int ischecked(Attendence attendence);
	
}
