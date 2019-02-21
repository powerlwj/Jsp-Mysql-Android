package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes_for_JavaBean.Attendence;
import classes_for_JavaBean.GoodsType;
import classes_for_JavaBean.Message;
import classes_for_Tools.Db_Manager;
import classes_for_interface.AttendenceIF;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 上午9:47:49
 * 类说明
 */
public class AttendenceImplement implements AttendenceIF {

	Db_Manager db=null;
	Connection con=db.getConnection();
	Attendence attendence;
	boolean flag;
	
	public AttendenceImplement() {
		// TODO Auto-generated constructor stub
		this.db=new Db_Manager();
	}
	@Override
	public int checkin(Attendence attendence) {
		// TODO Auto-generated method stub
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
//		String sql="insert into attence_t(ID,date,time1,morninggps,ismorningcheck) values (?,?,?,?,?) ";
		String sql="UPDATE attence_t SET time1=? , morninggps=? , ismorningcheck=? "
				+ "WHERE ID=? and date=? and  ismorningcheck='0'";
		
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, attendence.getTime1());
			pstm.setString(2, attendence.getMorninggps());
			pstm.setInt(3, 1);
			pstm.setString(4, attendence.getID());
			pstm.setString(5, attendence.getDate());
			System.out.println("checkIN"+pstm.toString());
			int a=pstm.executeUpdate();
			if(a!=0)
			{
				if(a==1)
				{
					return 1;
				}else
				{
					return 2;
				}
				
			}else if(a==0)
			{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	@Override
	public int checkout(Attendence attendence) {
		// TODO Auto-generated method stub
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		String sql="UPDATE attence_t SET time2=? , eveninggps=? , iseveningcheck=? WHERE ID=? and date=? and eveninggps='0' and  ismorningcheck='1'";
		System.out.println("当前日期："+currentDate.toString());
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, attendence.getTime2());
			pstm.setString(2, attendence.getEveninggps());
			pstm.setInt(3, 1);
			pstm.setString(4, attendence.getID());
			pstm.setString(5, currentDate.toString());
			int a=pstm.executeUpdate();
			if(a!=0)
			{
				if(a==1)
				{
					return 1;
				}else
				{
					return 2;
				}
				
			}else if(a==0)
			{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int ischecked(Attendence attendence) {
		// TODO Auto-generated method stub
		
		int index=0;
		String sql = "";
		if(attendence.getCindex()==1)
		{
			 sql="select ismorningcheck from attence_t  WHERE ID=? and date=?";
		}else if(attendence.getCindex()==2)
		{
			 sql="select iseveningcheck  from attence_t  WHERE ID=? and date=?";
		}
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, attendence.getID());
			pstm.setString(2, attendence.getDate());
			System.out.println(pstm.toString());
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				index=rs.getInt(1);
			}
			return index;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return index;
	}

}
