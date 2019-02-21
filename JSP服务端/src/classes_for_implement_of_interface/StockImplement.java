package classes_for_implement_of_interface;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.Stock;
import classes_for_Tools.Db_Manager;
import classes_for_interface.StockIF;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 下午4:01:17
 * @类说明：库存实现类
 */
public class StockImplement implements StockIF {

	Stock stock2;
	Db_Manager db;
	@SuppressWarnings("static-access")
	Connection con=db.getConnection();
	
	public StockImplement() {
		// TODO Auto-generated constructor stub
		this.db=new Db_Manager();
	}
	@Override
	public boolean add(Stock stock) {
		// TODO Auto-generated method stub
		String sql="insert into stock_t(ownerID,model,quantity,stockAlarm) values (?,?,?,?);";
		
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, stock.getOwnerID());
			pstm.setString(2, stock.getModel());
			pstm.setInt(3, stock.getQuantity());
			pstm.setInt(4, stock.getStockAlarm());
			
			if(pstm.executeUpdate()!=0)
			{
				return true;
			}else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delet(Stock stock) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Stock stock) {
		// TODO Auto-generated method stub
		String sql="update stock_t set quantity=?,stockAlarm=? where model=? and ownerID=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setInt(1, stock.getQuantity());
			pstm.setInt(2, stock.getStockAlarm());
			pstm.setString(3, stock.getModel());
			pstm.setString(4, stock.getOwnerID());
			System.out.println("pstm.toString():"+pstm.toString());
			if(pstm.executeUpdate()!=0)
			{
				return true;
			}else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Stock query(Stock stock) {
		// TODO Auto-generated method stub
		
		String sql="select quantity,stockAlarm from stock_t where model=? and ownerID=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, stock.getModel());
			pstm.setString(2, stock.getOwnerID());
			ResultSet rs=pstm.executeQuery();
			if(rs.next())
			{	stock2=new Stock();
				stock2.setQuantity(rs.getInt(1));
				stock2.setStockAlarm(rs.getInt(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stock2;
	}
	@Override
	public List<Stock> viewmystock(Stock stock) {
		// TODO Auto-generated method stub
		List<Stock> list=new ArrayList<Stock>();
		String sql="SELECT model ,quantity  FROM stock_t   WHERE ownerID=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setString(1, stock.getOwnerID());
		
		ResultSet rs=pstm.executeQuery();
		while (rs.next()) {
			Stock s=new Stock();
			s.setModel(rs.getString(1));
			s.setQuantity(rs.getInt(2));
			list.add(s);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
