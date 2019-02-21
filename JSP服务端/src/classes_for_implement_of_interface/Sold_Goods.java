package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes_for_JavaBean.Employee;
import classes_for_JavaBean.Goods;
import classes_for_JavaBean.SoldGoods;
import classes_for_Tools.DateSwitch;
import classes_for_Tools.Db_Manager;
import classes_for_interface.SoldGoodsIF;

public class Sold_Goods implements SoldGoodsIF {

	Db_Manager db = null;
	Connection con = db.getConnection();
	SoldGoods soldgoods;
	boolean flag = false;
	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

	public Sold_Goods() {
		// TODO Auto-generated constructor stub
		this.db = new Db_Manager();
	}

	@Override
	public boolean add(SoldGoods soldgoods) {
		// TODO Auto-generated method stub

		String sql = "insert into soldgoods_t(imei,type,soldPrice,"
				+ "model,sellerID,soldDate,soldFlag)values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setString(1, soldgoods.getImei());
			pstm.setString(2, soldgoods.getType());
			pstm.setString(3, soldgoods.getSoldPrice());
			pstm.setString(4, soldgoods.getModel());
			pstm.setString(5, soldgoods.getSellerID());
			pstm.setString(6, currentDate.toString());
			pstm.setInt(7, soldgoods.getSoldFlag());

			System.out.println("Sold_Goods调试:>>" + soldgoods.getImei());
			if (pstm.executeUpdate() > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public SoldGoods query(SoldGoods soldgoods) {

		return null;
	}

	@Override
	public List<SoldGoods> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(SoldGoods soldgoods) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM soldgoods_t WHERE imei=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, soldgoods.getImei());
		int a=	pstm.executeUpdate();
		if(a>0){
			return true;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	public List<String> queryModel(SoldGoods soldgoods) {
		// TODO Auto-generated method stub
		// soldgoods.setType("Mobile");
		System.out.println("请求的商品类型为：" + soldgoods.getType());
		List<String> list = new ArrayList<String>();
		String sql = "select model from goods_t where type=?";

		try {
			PreparedStatement pstn = con.prepareStatement(sql);
			System.out.println("sql语句：" + sql);

			pstn.setString(1, soldgoods.getType());

			ResultSet rs = pstn.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
				System.out.println("Sold_goods测试：" + rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Map<Integer, Integer> chart(Goods goods) {
		// TODO Auto-generated method stub
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		DateSwitch ds = new DateSwitch();
		String sql = "SELECT  MONTH(soldDate), COUNT(*)  from soldgoods_t WHERE YEAR(soldDate)=YEAR(NOW()) AND model=? GROUP BY MONTHNAME(soldDate)";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, goods.getModel());
			System.out.println(pstm.toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				map.put((rs.getInt(1)), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("大小：" + map.size() + "___" + map.toString());
		return map;
	}

	public List<SoldGoods> querysimple(SoldGoods soldGoods) {
		// TODO Auto-generated method stub
		List<SoldGoods> list = new ArrayList<SoldGoods>();
		String sql = "SELECT t1.imei,t1.type,t1.model,t1.soldDate ,t1.soldPrice FROM soldgoods_t AS t1,"
				+ "employee_t t2 WHERE t1.sellerID=? AND t2.ID=t1.sellerID AND t1.soldDate  BETWEEN ? and ? AND soldFlag='1'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, soldGoods.getSellerID());
			pstm.setString(2, soldGoods.getTargetTime());
			pstm.setString(3, soldGoods.getTargetTime2());
			System.out.println(pstm.toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				SoldGoods s = new SoldGoods();
				s.setImei(rs.getString(1));
				s.setType(rs.getString(2));
				s.setModel(rs.getString(3));
				s.setSoldDate(rs.getString(4));
				s.setSoldPrice(rs.getString(5));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<SoldGoods> singlemonthsales(SoldGoods soldGoods) {
		// TODO Auto-generated method stub
		String sql = "SELECT  model,COUNT(*) FROM soldgoods_t  WHERE  MONTH(soldDate)= ? AND type=? GROUP BY model";
		List<SoldGoods> list = new ArrayList<SoldGoods>();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, soldGoods.getSoldDate());
			pstm.setString(2, soldGoods.getType());
			System.out.println(pstm.toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				SoldGoods s = new SoldGoods();
				s.setModel(rs.getString(1));
				s.setSoldNumber(rs.getInt(2));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<SoldGoods> viewmysales(SoldGoods soldGoods) {
		// TODO Auto-generated method stub
		String sql = "SELECT type,model,imei,soldPrice FROM soldgoods_t WHERE sellerID=? AND soldDate<=? and soldDate>=? ";
		List<SoldGoods> list = new ArrayList<SoldGoods>();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, soldGoods.getSellerID());
			pstm.setString(3, soldGoods.getTime1());
			pstm.setString(2, soldGoods.getTime2());
			System.out.println(pstm.toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				SoldGoods s = new SoldGoods();
				s.setType(rs.getString(1));
				s.setModel(rs.getString(2));
				s.setImei(rs.getString(3));
				s.setSoldPrice(rs.getString(4));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public boolean updateStock(SoldGoods soldGoods) {
		// TODO Auto-generated method stub
		String sql="update stock_t set quantity=quantity-1 WHERE model=? and "
		+ "ownerID=(select reporterID from employee_t  where ID=? ) and quantity>0 ";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, soldGoods.getModel());
			pstm.setString(2, soldGoods.getSellerID());
			
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
	public boolean updateSoldGoods(SoldGoods soldGoods) {
		// TODO Auto-generated method stub
		String sql="UPDATE soldgoods_t SET soldFlag=? WHERE imei=? AND sellerID=? AND type=? AND model=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setInt(1, soldGoods.getSoldFlag());
			pstm.setString(2, soldGoods.getImei());
			pstm.setString(3, soldGoods.getSellerID());
			pstm.setString(4, soldGoods.getType());
			pstm.setString(5, soldGoods.getModel());
			
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
	public boolean updateSoldGoodsInfo(SoldGoods soldGoods) {
		// TODO Auto-generated method stub
		String sql="UPDATE soldgoods_t set type=?,model=?,soldPrice=?,soldDate=? where  imei=? and sellerID=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, soldGoods.getType());
			pstm.setString(2, soldGoods.getModel());
			pstm.setString(3, soldGoods.getSoldPrice());
			pstm.setString(4, soldGoods.getSoldDate());
			pstm.setString(5, soldGoods.getImei());
			pstm.setString(6, soldGoods.getSellerID());
			System.out.println(pstm.toString());
			int a= pstm.executeUpdate();
			if(a>0){
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Map<String, String>> getsumofsales(SoldGoods soldGoods) {
		// TODO Auto-generated method stub
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		String sql="SELECT model  ,COUNT(model),SUM(soldPrice) FROM soldgoods_t WHERE sellerID=? and soldDate between ? and ? and soldFlag='1' GROUP BY model";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, soldGoods.getSellerID());
			pstm.setString(2, soldGoods.getTargetTime());
			pstm.setString(3, soldGoods.getTargetTime2());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Map<String, String> map=new HashMap<String,String>();
				map.put("model", rs.getString(1));
				map.put("tnum", rs.getString(2));
				map.put("tprice", rs.getString(3));
				System.out.println("*******"+map);
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
