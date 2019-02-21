package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classes_for_JavaBean.Message;
import classes_for_Tools.Db_Manager;
import classes_for_interface.MessageIF;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月31日 上午10:44:14
 * 类说明
 */
public class MessageImplement implements MessageIF {

	Db_Manager db;
	Connection con=db.getConnection();
	Message message;
	
	public MessageImplement() {
		// TODO Auto-generated constructor stub
		this.db=new Db_Manager();
	}
	@Override
	public boolean add(Message message) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO message_t  (senderID,title,content,receiverID,level,addtime,readFlag ) "
		+ "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, message.getSenderID());
			pstm.setString(2, message.getTitle());
			pstm.setString(3, message.getContent());
			pstm.setString(4, message.getReceiverID());
			pstm.setInt(5, message.getLevel());
			pstm.setString(6, message.getAddtime());
			pstm.setString(7, message.getReadFlag());
			System.out.println(pstm);
			int a=pstm.executeUpdate();
			if(a>0)
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
	public List<Message> ordquery() {
		// TODO Auto-generated method stub
		List<Message> list=new ArrayList<Message>();
		String sql="select senderID,title,content,addtime,level  from message_t where level=4";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			System.out.println(pstm.toString());
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				message=new Message();
				message.setSenderID(rs.getString(1));
				message.setTitle(rs.getString(2));
				message.setContent(rs.getString(3));
				message.setAddtime(rs.getString(4));
				message.setLevel(rs.getInt(5));
				list.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Message> sedquery() {
		// TODO Auto-generated method stub
		List<Message> list=new ArrayList<Message>();
		String sql="select senderID,title,content,level,addtime from message_t where level=3";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				message=new Message();
				message.setSenderID(rs.getString(1));
				message.setTitle(rs.getString(2));
				message.setContent(rs.getString(3));
				message.setLevel(rs.getInt(4));
				message.setAddtime(rs.getString(5));
				list.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
