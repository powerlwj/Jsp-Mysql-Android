package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes_for_JavaBean.Goods;
import classes_for_Tools.Db_Manager;
import classes_for_interface.GoodsIF;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年8月1日 上午10:08:42 类说明
 */
public class GoodsImplement implements GoodsIF {

	Db_Manager db;
	Connection con = db.getConnection();
	Goods good;

	public GoodsImplement() {
		// TODO Auto-generated constructor stub
		db = new Db_Manager();
	}

	@Override
	public boolean add(Goods goods) {
		// TODO Auto-generated method stub
		String sql = "insert into goods_t(internalModel,model,type,price,configInfo,region,picPath)"
				+ "values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, goods.getInternalModel());
			pstm.setString(2, goods.getModel());
			pstm.setString(3, goods.getType());
			pstm.setString(4, goods.getPrice());
			pstm.setString(5, goods.getConfigInfo());
			pstm.setString(6, goods.getRegion());
			pstm.setString(7, goods.getPicPath());

			if (pstm.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Goods goods) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String internalModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Goods query(Goods goods) {
		// TODO Auto-generated method stub
		good = new Goods();
		String sql = "select price,configInfo,picPath from goods_t where type=? and model=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, goods.getType());
			pstm.setString(2, goods.getModel());

			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				good.setPrice(rs.getString(1));
				good.setConfigInfo(rs.getString(2));
				good.setPicPath(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return good;
	}

}
