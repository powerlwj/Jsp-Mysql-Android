package classes_for_Test;

import classes_for_JavaBean.Attendence;
import classes_for_implement_of_interface.AttendenceImplement;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 上午10:31:05
 * 类说明
 */
public class AttendenceTest {

	Attendence a=new Attendence();
	AttendenceImplement ad=new AttendenceImplement();
	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	java.sql.Time cuTime=new java.sql.Time(System.currentTimeMillis());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AttendenceTest att=new AttendenceTest();
		att.atten();
	}
	
	public void atten()
	{
		
		a.setID("050002");
		a.setDate(currentDate.toString());
//		a.setTime1(cuTime);
		a.setMorninggps("120;256");
		
//		boolean c=ad.checkin(a);
//		System.out.println("签到结果为："+c);
	}

}
