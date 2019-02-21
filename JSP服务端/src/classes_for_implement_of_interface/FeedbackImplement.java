package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.FeedBack;
import classes_for_Tools.Db_Manager;
import classes_for_interface.FeedbackIF;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月30日 下午8:50:41
 * 类说明
 */
public class FeedbackImplement implements FeedbackIF {

	Db_Manager db;
	Connection con=db.getConnection();
	public FeedbackImplement() {
		// TODO Auto-generated constructor stub
		this.db=new Db_Manager();
	}
	@Override
	public boolean add(FeedBack feedback) {
		// TODO Auto-generated method stub
		String sql="insert into feedback_t(ID,content) values (?,?)";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, feedback.getID());
			pstm.setString(2, feedback.getContent());
			
			if(pstm.executeUpdate()!=0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(FeedBack feedback) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FeedBack> query() {
		// TODO Auto-generated method stub
		List<FeedBack> list=new ArrayList<FeedBack>() ;
		FeedBack f;
		String sql="select * from feedback_t";
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				f=new FeedBack();
				f.setID(rs.getString(1));
				f.setContent(rs.getString(2));
				list.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

}
