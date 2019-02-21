package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import classes_for_JavaBean.Administrator;
import classes_for_JavaBean.GoodsType;
import classes_for_Tools.Db_Manager;
import classes_for_interface.AdministratorIF;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年7月27日 下午9:49:34 类说明
 */
public class AdministratorImplement implements AdministratorIF {

	Db_Manager db = null;
	Connection con = db.getConnection();
	Administrator adm;
	boolean flag;

	public AdministratorImplement() {
		// TODO Auto-generated constructor stub
		this.db = new Db_Manager();
	}

	@Override
	public Administrator adduer(Administrator admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deluser(Administrator admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Administrator queryuer(Administrator admin) {
		// TODO Auto-generated method stub
		String sql = "select password,cx,region from administrator_t where ID=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, admin.getId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				adm = new Administrator();
				String pass = rs.getString(1);
				String cx = rs.getString(2);
				String region = rs.getString(3);
				adm.setPassword(pass);
				adm.setAdmin_type(cx);
				adm.setRegion(region);;
			}
			return adm;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return adm;
	}

	@Override
	public boolean updateuer(Administrator admin) {
		// TODO Auto-generated method stub
		String sql = "update administrator_t set password=? where id=? ";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, admin.getPassword());
			pstm.setString(2, admin.getId());

			if (pstm.executeUpdate() != 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
