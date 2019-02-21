package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.StockChangeDetail;
import classes_for_Tools.Db_Manager;
import classes_for_interface.StockChangeDetailIF;

public class StockChangeDetailIMP implements StockChangeDetailIF {
	
	Db_Manager db;
	Connection con;
	StockChangeDetail stockcd;
	
	public  StockChangeDetailIMP() {
		// TODO Auto-generated constructor stub
		db=new Db_Manager();
		con=db.getConnection();
	}

	@Override
	public List<StockChangeDetail> showdetail(String id) {
		// TODO Auto-generated method stub
		List<StockChangeDetail> list=new ArrayList<StockChangeDetail>();
//		String  sql="SELECT * FROM stockchangedetail_t WHERE txOwnerID=?  OR rxOwnerID=? ";
		String  sql="SELECT * FROM stockchangedetail_t WHERE txOwnerID=?  OR rxOwnerID=? and YEAR(addtime)=YEAR(NOW())  and addtime>=DATE_SUB(NOW(), INTERVAL 3 MONTH) ";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, id);
			System.out.println("StockChangeDetail>>>"+pstm.toString());
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				stockcd=new StockChangeDetail();
				stockcd.setId(rs.getInt(1));
				stockcd.setModel(rs.getString(2));
				stockcd.setTxQuantity(rs.getInt(3));
				stockcd.setRxQuantity(rs.getInt(4));
				stockcd.setChangeQuantity(rs.getInt(5));
				stockcd.setNewTxQuantity(rs.getInt(6));
				stockcd.setNewRxQuantity(rs.getInt(7));
				stockcd.setTxOwnerID(rs.getString(8));
				stockcd.setRxOwnerID(rs.getString(9));
				stockcd.setAddtime(rs.getString(10));
				stockcd.setComment(rs.getString(11));
				list.add(stockcd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean updatestockdetail(StockChangeDetail stockdetail) {
		// TODO Auto-generated method stub
		String sql="update stockchangedetail_t set comment=? where model=? and txOwnerID=? or rxOwnerID=? and addtime=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, stockdetail.getComment());
			pstm.setString(2, stockdetail.getModel());
			pstm.setString(3, stockdetail.getTxOwnerID());
			pstm.setString(4, stockdetail.getRxOwnerID());
			pstm.setString(5, stockdetail.getAddtime());
			System.out.println("stock change detail update sql"+pstm.toString());
			if(pstm.executeUpdate()>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
