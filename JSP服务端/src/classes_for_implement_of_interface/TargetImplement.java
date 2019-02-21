package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import classes_for_JavaBean.SoldGoods;
import classes_for_JavaBean.Target;
import classes_for_Tools.Db_Manager;
import classes_for_interface.TargetIF;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年7月28日 下午9:23:21 类说明
 */
public class TargetImplement implements TargetIF {

	Db_Manager db;
	Target tar;
	static int a = 0;
	Connection con = db.getConnection();

	public TargetImplement() {
		// TODO Auto-generated constructor stub
		this.db = new Db_Manager();
	}

	@Override
	public boolean add(Target target) {
		// TODO Auto-generated method stub

		int index = target.getIndex();
		String sql = "";
		PreparedStatement pstm;
		//实际上，index=1和2时执行的代码逻辑相同！
		if (index == 1) {
			sql = "insert into target_t(model,target,targetAmount,ownerID,targetType,targetTime,targetTime2,type)"
					+ "values (?,?,?,?,?,?,?,?)";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getModel());
				pstm.setInt(2, target.getTarget());
				pstm.setInt(3, target.getTargetAmount());
				pstm.setString(4, target.getOwnerID());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getTargetTime());
				pstm.setString(7, target.getTargetTime2());
				pstm.setString(8, target.getType());
				System.out.println("SQL语句为：" + pstm.toString());
				if (pstm.executeUpdate() != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (index == 2) {
			sql = "insert into target_t(target,targetAmount,ownerID,targetType,targetTime,targetTime2,type,model)"
					+ "values (?,?,?,?,?,?,?,?)";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, target.getTarget());
				pstm.setInt(2, target.getTargetAmount());
				pstm.setString(3, target.getOwnerID());
				pstm.setString(4, target.getTargetType());
				pstm.setString(5, target.getTargetTime());
				pstm.setString(6, target.getTargetTime2());
				pstm.setString(7, target.getType());
				pstm.setString(8, target.getType());//Type Target, type=model
				System.out.println("SQL语句为：" + pstm.toString());
				if (pstm.executeUpdate() != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean deletemonthmission(Target target) {
		// TODO Auto-generated method stub
		int index = target.getIndex();
		String sql = "";
		PreparedStatement pstm;
		if (index == 1) {
			sql = " delete from  target_t where model=? AND type=? AND target=? AND targetAmount=? AND targetType=? and  ownerID=? ";

			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getModel());
				pstm.setString(2, target.getType());
				pstm.setInt(3, target.getTarget());
				pstm.setInt(4, target.getTargetAmount());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getOwnerID());
				System.out.println("SQL语句为：" + pstm.toString());
				int a = pstm.executeUpdate();
				if (a > 0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (index == 2) {
			sql = "  delete from  target_t where model=? AND type=? AND targetAmount=? AND target=? AND targetType=? and ownerID=?  ";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getModel());
				pstm.setString(2, target.getType());
				pstm.setInt(3, target.getTargetAmount());
				pstm.setInt(4, target.getTarget());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getOwnerID());
				System.out.println("SQL语句为：" + pstm.toString());
				int a = pstm.executeUpdate();
				if (a > 0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean deletespecialmission(Target target) {
		// TODO Auto-generated method stub
		int index = target.getIndex();
		System.out.println("INDEXXXXXXXXXXXX:" + index);
		String sql = "";
		PreparedStatement pstm;
		if (index == 1) {
			sql = " delete from  target_t where model=? AND type=? AND target=? AND targetAmount=? AND targetType=? and  targetTime=? and targetTime2=?  and  ownerID=?";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getModel());
				pstm.setString(2, target.getType());
				pstm.setInt(3, target.getTarget());
				pstm.setInt(4, target.getTargetAmount());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getTargetTime());
				pstm.setString(7, target.getTargetTime2());
				pstm.setString(8, target.getOwnerID());
				System.out.println("SQL语句为：" + pstm.toString());
				int a = pstm.executeUpdate();
				if (a > 0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (index == 2) {
			sql = " delete from  target_t where model=? AND type=? AND targetAmount=? AND target=? AND targetType=? and  targetTime=? and targetTime2=?  and  ownerID=? ";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getModel());
				pstm.setString(2, target.getType());
				pstm.setInt(3, target.getTargetAmount());
				pstm.setInt(4, target.getTarget());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getTargetTime());
				pstm.setString(7, target.getTargetTime2());
				pstm.setString(8, target.getOwnerID());
				System.out.println("SQL语句为：" + pstm.toString());
				int a = pstm.executeUpdate();
				if (a > 0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean update(Target target) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public Target query(Target target) {
		// TODO Auto-generated method stub
		String sql = "select model,target,price,targetAmount from target_t where targetType=? and targetTime=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, target.getTargetType());
			pstm.setString(2, target.getTargetTime());

			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				tar = new Target();
				tar.setModel(rs.getString(1));
				tar.setTarget(rs.getInt(2));
				tar.setPrice(rs.getString(3));
				tar.setTargetAmount(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tar;
	}

	@Override
	public List<Target> queryall(Target target) {
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();
		/*
		 * String sql="SELECT MONTHNAME(t1.soldDate),COUNT(*),t1.model," +
		 * "t2.target,COUNT(*) - target AS remain FROM soldgoods_t t1,target_t t2 "
		 * + "WHERE YEAR (t1.soldDate) = ? AND t2.type=? AND t2.targetType=? " +
		 * "AND t2.ownerID=? AND t1.model = t2.model GROUP BY MONTHNAME(t1.soldDate),t1.model"
		 * ; try { PreparedStatement pstm=con.prepareStatement(sql);
		 * pstm.setString(1, target.getTargetTime()); pstm.setString(2,
		 * target.getType()); pstm.setString(3, target.getTargetType());
		 * pstm.setString(4, target.getOwnerID());
		 * System.out.println(pstm.toString()); ResultSet
		 * rs=pstm.executeQuery(); while(rs.next()) { tar=new Target();
		 * tar.setMonth(rs.getString(1)); tar.setAchieved(rs.getInt(2));
		 * tar.setModel(rs.getString(3)); tar.setTarget(rs.getInt(4));
		 * tar.setRemain(rs.getString(5)); list.add(tar); } } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		try {
			String sql3 = "select ID from employee_t where reporterID=?";
			PreparedStatement pstm3 = con.prepareStatement(sql3);
			pstm3.setString(1, target.getOwnerID());
			System.out.println(pstm3.toString());
			ResultSet rs3 = pstm3.executeQuery();
			// 二级销售经理
			if (rs3.isBeforeFirst()) {

				String strTargetTime = target.getTargetTime();
				String strMonth = strTargetTime.substring(5, 7);
				String strModel = null;

				switch (Integer.parseInt(strMonth)) {
				case 1:
					strMonth = "January";
					break;
				case 2:
					strMonth = "February";
					break;
				case 3:
					strMonth = "March";
					break;
				case 4:
					strMonth = "April";
					break;
				case 5:
					strMonth = "May";
					break;
				case 6:
					strMonth = "June";
					break;
				case 7:
					strMonth = "July";
					break;
				case 8:
					strMonth = "August";
					break;
				case 9:
					strMonth = "September";
					break;
				case 10:
					strMonth = "October";
					break;
				case 11:
					strMonth = "November";
					break;
				case 12:
					strMonth = "December";
					break;
				default:
					break;
				}

				String sql = "select ownerID,targetTime,target,model from target_t where type=? AND targetType=? AND targetTime=? AND ownerID=?";
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getType());
				pstm.setString(2, target.getTargetType());
				pstm.setString(3, target.getTargetTime());
				pstm.setString(4, target.getOwnerID());
				System.out.println(pstm.toString());
				ResultSet rs = pstm.executeQuery();

				// 针对该二级销售经理的每一有销售任务的型号
				while (rs.next()) {
					int iAchieved = 0;
					int iTarget = 0;

					String strTargetTime2 = "%" + rs.getString("targetTime") + "%";
					iTarget = rs.getInt("target");
					strModel = rs.getString("model");
					ResultSet rs2 = null;

					// 针对该二级销售经理的所有销售人员的该型号的销售记录
					while (rs3.next()) {
						String strId = rs3.getString("ID");

						String sql2 = "select MONTHNAME(soldDate),count(*),model from soldgoods_t where sellerID=? and model=? and soldFlag=1 and soldDate like ?";
						PreparedStatement pstm2 = con.prepareStatement(sql2);

						// pstm2.setInt(1, rs.getInt("target"));
						pstm2.setString(1, strId);
						pstm2.setString(2, strModel);
						pstm2.setString(3, strTargetTime2);
						System.out.println(pstm2.toString());
						rs2 = pstm2.executeQuery();
						rs2.next();

						iAchieved = iAchieved + rs2.getInt(2);
					}
					tar = new Target();
					tar.setMonth(strMonth);
					tar.setAchieved(iAchieved);
					tar.setModel(strModel);
					tar.setTarget(iTarget);
					tar.setRemain(Integer.toString(iTarget - iAchieved));
					list.add(tar);
				}

			}
			// 销售人员
			else {
				String strTargetTime = target.getTargetTime();
				String strMonth = strTargetTime.substring(5, 7);
				switch (Integer.parseInt(strMonth)) {
				case 1:
					strMonth = "January";
					break;
				case 2:
					strMonth = "February";
					break;
				case 3:
					strMonth = "March";
					break;
				case 4:
					strMonth = "April";
					break;
				case 5:
					strMonth = "May";
					break;
				case 6:
					strMonth = "June";
					break;
				case 7:
					strMonth = "July";
					break;
				case 8:
					strMonth = "August";
					break;
				case 9:
					strMonth = "September";
					break;
				case 10:
					strMonth = "October";
					break;
				case 11:
					strMonth = "November";
					break;
				case 12:
					strMonth = "December";
					break;
				default:
					break;
				}

				String sql = "select ownerID,targetTime,target,model from target_t where type=? AND targetType=? AND targetTime=? AND ownerID=?";
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getType());
				pstm.setString(2, target.getTargetType());
				pstm.setString(3, target.getTargetTime());
				pstm.setString(4, target.getOwnerID());
				System.out.println(pstm.toString());
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					String strTargetTime2 = "%" + rs.getString("targetTime") + "%";
					int iTarget = rs.getInt("target");
					String strModel = rs.getString("model");

					String sql2 = "select MONTHNAME(soldDate),count(*),model,?-COUNT(*) AS remain from soldgoods_t where sellerID=? and model=? and soldFlag=1 and soldDate like ?";
					PreparedStatement pstm2 = con.prepareStatement(sql2);

					pstm2.setInt(1, rs.getInt("target"));
					pstm2.setString(2, rs.getString("ownerID"));
					pstm2.setString(3, rs.getString("model"));
					pstm2.setString(4, strTargetTime2);
					System.out.println(pstm2.toString());
					ResultSet rs2 = pstm2.executeQuery();
					rs2.next();

					if (rs2.getInt(2) > 0) {
						tar = new Target();
						tar.setMonth(strMonth);
						tar.setAchieved(rs2.getInt(2));
						tar.setModel(strModel);
						tar.setTarget(iTarget);
						tar.setRemain(rs2.getString(4));
						list.add(tar);
					} else {
						tar = new Target();
						tar.setMonth(strMonth);
						tar.setAchieved(0);
						tar.setModel(strModel);
						tar.setTarget(iTarget);
						tar.setRemain(Integer.toString(iTarget));
						list.add(tar);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	@Override
	public Target queryspecial(Target target) {
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();
		Target tar = new Target();
		/*
		 * Target t=new Target(); String sql=
		 * "SELECT t1.model,COUNT(t1.model),target,COUNT(*)-target AS remain " +
		 * "FROM soldgoods_t t1,target_t t2 where t1.model=t2.model and t2.model=? "
		 * +
		 * "AND t2.targetType=? AND t1.soldDate BETWEEN ? and ? AND t2.type=?  "
		 * + "AND t1.soldflag=1 " + "AND t2.ownerID=? GROUP BY t1.model";
		 * 
		 * 
		 * try { PreparedStatement pstm=con.prepareStatement(sql);
		 * pstm.setString(1, target.getModel()); pstm.setString(2,
		 * target.getTargetType()); pstm.setString(3, target.getTargetTime());
		 * pstm.setString(4, target.getTargetTime2()); pstm.setString(5,
		 * target.getType()); pstm.setString(6, target.getOwnerID());
		 * System.out.println("pstm>>>>>"+pstm); ResultSet
		 * rs=pstm.executeQuery(); if(rs.next()) { t.setModel(rs.getString(1));
		 * t.setAchieved(rs.getInt(2)); t.setTarget(rs.getInt(3));
		 * t.setRemain(rs.getString(4)); } } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } return t;
		 */
		try {
			String sql3 = "select ID from employee_t where reporterID=?";
			PreparedStatement pstm3 = con.prepareStatement(sql3);
			pstm3.setString(1, target.getOwnerID());
			System.out.println(pstm3.toString());
			ResultSet rs3 = pstm3.executeQuery();

			String sql = "select ownerID,targetTime,targetTime2,target,model from target_t where type=? AND targetType=? AND targetTime=? AND targetTime2=? AND ownerID=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, target.getType());
			pstm.setString(2, target.getTargetType());
			pstm.setString(3, target.getTargetTime());
			pstm.setString(4, target.getTargetTime2());
			pstm.setString(5, target.getOwnerID());
			System.out.println(pstm.toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				// String strTargetTime2 = "%" + rs.getString("targetTime") +
				// "%";
				int iTarget = rs.getInt("target");
				String strModel = rs.getString("model");

				String sql2 = "select count(*),model,?-COUNT(*) AS remain from soldgoods_t where sellerID=? and model=? and soldFlag=1 and soldDate BETWEEN ? AND ?";
				PreparedStatement pstm2 = con.prepareStatement(sql2);

				pstm2.setInt(1, rs.getInt("target"));
				pstm2.setString(2, rs.getString("ownerID"));
				pstm2.setString(3, rs.getString("model"));
				pstm2.setString(4, target.getTargetTime());
				pstm2.setString(5, target.getTargetTime2());
				System.out.println(pstm2.toString());
				ResultSet rs2 = pstm2.executeQuery();
				rs2.next();

				if (rs2.getInt(1) > 0) {
					tar = new Target();
					tar.setAchieved(rs2.getInt(1));
					tar.setModel(strModel);
					tar.setTarget(iTarget);
					tar.setRemain(rs2.getString(3));
					list.add(tar);
				} else {
					tar = new Target();
					tar.setAchieved(0);
					tar.setModel(strModel);
					tar.setTarget(iTarget);
					tar.setRemain(Integer.toString(iTarget));
					list.add(tar);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tar;
	}
	
	@Override
	public Target queryspecialforapp(Target target) {
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();
		Target tar = new Target();
		Target t = new Target();
		String sql = "SELECT t1.model,COUNT(t1.model),target,COUNT(*)-target AS remain "
				+ "FROM soldgoods_t t1,target_t t2 where t1.model=t2.model and t2.model=? "
				+ "AND t2.targetType=? AND t1.soldDate BETWEEN ? and ? AND t2.type=?  " + "AND t1.soldflag=1 "
				+ "AND t2.ownerID=? GROUP BY t1.model";

		System.out.println("target.toString()>>>>>>" + target.toString());

		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, target.getModel());
			pstm.setString(2, target.getTargetType());
			pstm.setString(3, target.getTargetTime());
			pstm.setString(4, target.getTargetTime2());
			pstm.setString(5, target.getType());
			pstm.setString(6, target.getOwnerID());
			System.out.println("pstm>>>>>" + pstm);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				t.setModel(rs.getString(1));
				t.setAchieved(rs.getInt(2));
				t.setTarget(rs.getInt(3));
				t.setRemain(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	/* 销售人员：查看上级分配给自己的ING中的特殊任务 */
	@Override
	public List<Target> queryspe(Target target) 
	{
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();
		String sql = "select * from target_t where targetTime <= DATE(NOW()) AND targetTime2 >= DATE(NOW()) and targetType='Special' and ownerID='"+target.getOwnerID()+"'";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			System.out.println("pstm>>>>>" + pstm);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Target t = new Target();
				t.setModel(rs.getString("model"));
				t.setTarget(rs.getInt("target"));
				t.setTargetAmount(rs.getInt("targetAmount"));
				t.setPrice(rs.getString("price"));
				t.setTargetTime(rs.getString("targetTime"));
				t.setTargetTime2(rs.getString("targetTime2"));
				t.setType(rs.getString("type"));
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* 二级经理：查询自己分配给下级的月度销售任务 */
	@Override
	public List<Target> viewallocatedmission(Target target) 
	{
		// TODO Auto-generated method stub
		int index = target.getIndex();
		//String sql1 = "SELECT type,model,target,targetAmount FROM target_t WHERE ownerID=? AND targetTime=? and targetAmount != '0' and target='0' and targetTime2 is null";
		//lyw: 刘维江分成了index1,index2,对应2种Target（Model Target和 Type Target）。实际上，除了SQL语句，下列index=1或2时执行的代码完全一样！
		//可能查出多个任务
		//For Model Target:
		String sql1 = "SELECT type,model,target,targetAmount FROM target_t WHERE ownerID=? AND targetTime=? and targetType='Monthly' and type!=model";
		//For Type Target：
		String sql2 = "SELECT type,model,target,targetAmount FROM target_t WHERE ownerID=? AND targetTime=? and targetType='Monthly' and type=model";
		List<Target> list = new ArrayList<Target>();

		if (index == 1) 
		{
			try {
				PreparedStatement pstm = con.prepareStatement(sql1);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getTargetTime());
				System.out.println(pstm.toString());
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) 
				{
					Target t = new Target();
					t.setType(rs.getString(1));
					t.setModel(rs.getString(2));
					t.setTarget(rs.getInt(3));
					t.setTargetAmount(rs.getInt(4));
					list.add(t);
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else if (index == 2) 
		{
			try 
			{
				PreparedStatement pstm = con.prepareStatement(sql2);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getTargetTime());
				System.out.println(pstm.toString());
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) 
				{
					Target t = new Target();
					t.setType(rs.getString(1));
					t.setModel(rs.getString(2));
					t.setTarget(rs.getInt(3));
					t.setTargetAmount(rs.getInt(4));
					list.add(t);
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}	
	
	/* 二级经理：查询自己分配给下级的特殊销售任务 */
	@Override
	public List<Target> viewallspecialmission(Target target) 
	{
		// TODO Auto-generated method stub
		int index = target.getIndex();
		//lyw: 刘维江分成了index1,index2,对应2种Target（Model Target和 Type Target）。实际上，除了SQL语句，下列index=1或2时执行的代码完全一样！
		//可能查出多个任务
		//For Model Target:
		String sql1 = "SELECT type,model,target,targetAmount,targetTime,targetTime2 FROM target_t WHERE ownerID=? and type=? and model=?"
				+ "AND ? <= targetTime and ? >= targetTime2 and targetType='Special' and targetAmount !='0' and target !='0' and type!=model";
		//For Type Target：
		String sql2 = "SELECT type,model,target,targetAmount,targetTime,targetTime2 FROM target_t WHERE ownerID=? and type=? and ? <= targetTime and ? >="
				+ " targetTime2 and targetType='Special' AND target !='0' and targetAmount !='0' and type=model";
		List<Target> list = new ArrayList<Target>();

		if (index == 1) 
		{
			try 
			{
				PreparedStatement pstm = con.prepareStatement(sql1);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getType());
				pstm.setString(3, target.getModel());
				pstm.setString(4, target.getTargetTime());
				pstm.setString(5, target.getTargetTime2());
				System.out.println(pstm.toString());
				
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) 
				{
					Target t = new Target();
					t.setType(rs.getString(1));
					t.setModel(rs.getString(2));
					t.setTarget(rs.getInt(3));
					t.setTargetAmount(rs.getInt(4));
					t.setTargetTime(rs.getString(5));
					t.setTargetTime2(rs.getString(6));
					list.add(t);
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else if (index == 2) 
		{
			try 
			{
				PreparedStatement pstm = con.prepareStatement(sql2);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getType());
				pstm.setString(3, target.getTargetTime());
				pstm.setString(4, target.getTargetTime2());
				System.out.println(pstm.toString());
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) 
				{
					Target t = new Target();
					t.setType(rs.getString(1));
					t.setModel(rs.getString(2));
					t.setTarget(rs.getInt(3));
					t.setTargetAmount(rs.getInt(4));
					t.setTargetTime(rs.getString(5));
					t.setTargetTime2(rs.getString(6));
					list.add(t);
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	/* 销售人员或二级经理：查询上级分配给自己的月度销售任务 */
	@Override
	public List<Target> queryallforapp(Target target) 
	{
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int index = target.getIndex();
		String sql, sql1, sql2 = null;
		
		//最多只能查出1个任务
		if (index == 1) // Model Target
		{
			sql = "SELECT target,targetAmount FROM target_t WHERE ownerID=? AND type=? AND model=? AND targetType='Monthly' AND targetTime=? AND type!=model";
			try
			{
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getType());
				pstm.setString(3, target.getModel());
				pstm.setString(4, target.getTargetTime());
				System.out.println(pstm.toString()+"\n");
				rs = pstm.executeQuery();	
				
				while (rs.next()) //是否有这样的任务？
				{
					tar = new Target();
					tar.setTarget(rs.getInt("target"));
					tar.setAmountprices(rs.getInt("targetAmount"));		
					tar.setAchieved(0);			
					tar.setAmountpricesachieve(0);
					
					sql1 = "SELECT level FROM employee_t WHERE ID='" + target.getOwnerID() + "'";
					pstm = con.prepareStatement(sql1);
					System.out.println(pstm.toString()+"\n");
					rs = pstm.executeQuery();	
					if (rs.next())
					{
						Integer level = rs.getInt("level");
						if (level==5) //销售人员
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(soldPrice) AS amountachieved FROM soldgoods_t WHERE type=? AND model=?"
								        + " AND soldDate LIKE ? AND sellerID=? AND soldFlag='1' GROUP BY model";				
						}
						else if (level==4) //二级经理
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(t1.soldPrice) AS amountachieved FROM soldgoods_t t1, employee_t t2 WHERE t1.type=? AND t1.model=?"
										+ " AND t1.soldDate LIKE ? AND t1.sellerID=t2.ID AND t2.reporterID=? AND t1.soldFlag='1' GROUP BY t1.model";
						}	
					}
									
					pstm = con.prepareStatement(sql2);
					pstm.setString(1, target.getType());
					pstm.setString(2, target.getModel());
					pstm.setString(3, "%"+target.getTargetTime()+"%");
					pstm.setString(4, target.getOwnerID());
					System.out.println(pstm.toString()+"\n");
					rs = pstm.executeQuery();
					if (rs.next()) 
					{
						tar.setAchieved(rs.getInt("quantityachieved"));			
						tar.setAmountpricesachieve(rs.getInt("amountachieved"));
					}
					list.add(tar);
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (index == 2) // Type Target
		{
			sql = "SELECT target,targetAmount FROM target_t WHERE ownerID=? AND type=? AND targetType='Monthly' AND targetTime=? AND type=model";
			try
			{
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getType());
				pstm.setString(3, target.getTargetTime());
				System.out.println(pstm.toString()+"\n");
				rs = pstm.executeQuery();	
				
				while (rs.next()) //是否有这样的任务？
				{	
					tar = new Target();
					tar.setTarget(rs.getInt("target"));
					tar.setAmountprices(rs.getInt("targetAmount"));		
					tar.setAchieved(0);			
					tar.setAmountpricesachieve(0);
					
					sql1 = "SELECT level FROM employee_t WHERE ID='" + target.getOwnerID() + "'";
					pstm = con.prepareStatement(sql1);
					System.out.println(pstm.toString()+"\n");
					rs = pstm.executeQuery();	
					if (rs.next())
					{
						Integer level = rs.getInt("level");
						if (level==5) //销售人员
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(soldPrice) AS amountachieved FROM soldgoods_t WHERE type=?"
										+ " AND soldDate LIKE ? AND sellerID=? AND soldFlag='1' GROUP BY type";		
						}
						else if (level==4) //二级经理
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(t1.soldPrice) AS amountachieved FROM soldgoods_t t1, employee_t t2 WHERE t1.type=?"
										+ " AND t1.soldDate LIKE ? AND t1.sellerID=t2.ID AND t2.reporterID=? AND t1.soldFlag='1' GROUP BY t1.type";
						}	
					}
			
					pstm = con.prepareStatement(sql2);
					pstm.setString(1, target.getType());
					pstm.setString(2, "%"+target.getTargetTime()+"%");
					pstm.setString(3, target.getOwnerID());
					System.out.println(pstm.toString()+"\n");
					rs = pstm.executeQuery();
					if (rs.next()) 
					{
						tar.setAchieved(rs.getInt("quantityachieved"));			
						tar.setAmountpricesachieve(rs.getInt("amountachieved"));
					}
					list.add(tar);
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return list;
		/*String sql1 = "SELECT  COUNT(*)  AS achieved,t2.target ,t2.targetAmount AS amountprices,sum(t1.soldPrice) "
				+ "AS amountpricesachieve FROM soldgoods_t t1,target_t t2  WHERE t2.targetType='Monthly' and t2.type=? and t2.model=?  "
				+ " and MONTH(t1.soldDate)= MONTH(?) AND t2.ownerID=? AND t1.model = t2.model AND t1.soldFlag='1' GROUP BY t1.model";
		String sql2 = "SELECT  COUNT(*)  AS achieved,t2.target ,t2.targetAmount AS amountprices,sum(t1.soldPrice) "
				+ "AS amountpricesachieve FROM soldgoods_t t1,target_t t2  WHERE t2.targetType='Monthly' and t2.type=? "
				+ " and MONTH(t1.soldDate)= MONTH(?) AND t2.ownerID=? AND t1.model = t2.model AND t1.soldFlag='1' GROUP BY t1.type";*/
	}
	
	/* 销售人员或二级经理：查询上级分配给自己的特殊销售任务 */
	@Override
	public List<Target> smissionprocess(Target target) 
	{
		// TODO Auto-generated method stub
		List<Target> list = new ArrayList<Target>();	
		PreparedStatement pstm = null;
		ResultSet rs,rs2,rs3 = null;
		int index = target.getIndex();
		String sql,sql1,sql2 = null;
		String targetTime_db,targetTime2_db = null;

		//可能查出多个任务
		if (index == 1) // Model Target
		{
			sql = "SELECT target,targetAmount,targetTime,targetTime2 FROM target_t WHERE ownerID=? AND type=? AND model=? AND targetType='Special' AND ?<=targetTime AND targetTime2<=? AND type!=model";
			try
			{
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getType());
				pstm.setString(3, target.getModel());
				pstm.setString(4, target.getTargetTime());
				pstm.setString(5, target.getTargetTime2());
				System.out.println(pstm.toString()+"\n");
				rs = pstm.executeQuery();	
				
				while (rs.next()) //是否有这样的任务？可能有不止一个特殊任务落在该时间区间
				{
					tar = new Target();
					tar.setTarget(rs.getInt("target"));
					tar.setAmountprices(rs.getInt("targetAmount"));		
					tar.setAchieved(0);			
					tar.setAmountpricesachieve(0);			
					targetTime_db = rs.getString("targetTime"); //获取数据库中的targetTime字段值
					targetTime2_db = rs.getString("targetTime2"); //获取数据库中的targetTime2字段值
					tar.setTargetTime(targetTime_db);
					tar.setTargetTime2(targetTime2_db);
					
					sql1 = "SELECT level FROM employee_t WHERE ID='" + target.getOwnerID() + "'";
					pstm = con.prepareStatement(sql1);
					System.out.println(pstm.toString()+"\n");
					rs2 = pstm.executeQuery();	
					if (rs2.next())
					{
						Integer level = rs2.getInt("level");
						if (level==5) //销售人员
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(soldPrice) AS amountachieved FROM soldgoods_t WHERE type=? AND model=?"
								 + " AND soldDate>=? AND soldDate<=? AND sellerID=? AND soldFlag='1' GROUP BY model";			
						}
						else if (level==4) //二级经理
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(soldPrice) AS amountachieved FROM soldgoods_t t1, employee_t t2 WHERE t1.type=? AND t1.model=?"
								 + " AND t1.soldDate>=? AND t1.soldDate<=? AND t1.sellerID=t2.ID AND t2.reporterID=? AND t1.soldFlag='1' GROUP BY t1.model";
						}	
					}
									
					pstm = con.prepareStatement(sql2);
					pstm.setString(1, target.getType());
					pstm.setString(2, target.getModel());
					pstm.setString(3, targetTime_db);
					pstm.setString(4, targetTime2_db);
					pstm.setString(5, target.getOwnerID());
					System.out.println(pstm.toString()+"\n");
					rs3 = pstm.executeQuery();
					if (rs3.next()) 
					{
						tar.setAchieved(rs3.getInt("quantityachieved"));			
						tar.setAmountpricesachieve(rs3.getInt("amountachieved"));
					}
					list.add(tar);
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (index == 2) // Type Target
		{
			sql = "SELECT target,targetAmount,targetTime,targetTime2 FROM target_t WHERE ownerID=? AND type=? AND targetType='Special' AND ?<=targetTime AND targetTime2<=? AND type=model";
			try
			{
				pstm = con.prepareStatement(sql);
				pstm.setString(1, target.getOwnerID());
				pstm.setString(2, target.getType());
				pstm.setString(3, target.getTargetTime());
				pstm.setString(4, target.getTargetTime2());
				System.out.println(pstm.toString()+"\n");
				rs = pstm.executeQuery();	
				
				while (rs.next()) //是否有这样的任务？可能有不止一个特殊任务落在该时间区间
				{	
					tar = new Target();
					tar.setTarget(rs.getInt("target"));
					tar.setAmountprices(rs.getInt("targetAmount"));		
					tar.setAchieved(0);			
					tar.setAmountpricesachieve(0);								
					targetTime_db = rs.getString("targetTime"); //获取数据库中的targetTime字段值
					targetTime2_db = rs.getString("targetTime2"); //获取数据库中的targetTime2字段值
					tar.setTargetTime(targetTime_db);
					tar.setTargetTime2(targetTime2_db);
					
					sql1 = "SELECT level FROM employee_t WHERE ID='" + target.getOwnerID() + "'";
					pstm = con.prepareStatement(sql1);
					System.out.println(pstm.toString()+"\n");
					rs2 = pstm.executeQuery();	
					if (rs2.next())
					{
						Integer level = rs2.getInt("level");
						if (level==5) //销售人员
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(soldPrice) AS amountachieved FROM soldgoods_t WHERE type=?"
								 + " AND soldDate>=? AND soldDate<=? AND sellerID=? AND soldFlag='1' GROUP BY type";			
						}
						else if (level==4) //二级经理
						{
							sql2 = "SELECT COUNT(*) AS quantityachieved,SUM(soldPrice) AS amountachieved FROM soldgoods_t t1, employee_t t2 WHERE t1.type=?"
								 + " AND t1.soldDate>=? AND t1.soldDate<=? AND t1.sellerID=t2.ID AND t2.reporterID=? AND t1.soldFlag='1' GROUP BY t1.type";
						}	
					}
					
					//sql1 = "SELECT COUNT(*) AS quantityachieved,SUM(soldPrice) AS amountachieved FROM soldgoods_t WHERE type=?"
					//	+ " AND soldDate>=? AND soldDate<=? AND sellerID=? AND soldFlag='1' GROUP BY type";	
			
					pstm = con.prepareStatement(sql2);
					pstm.setString(1, target.getType());
					pstm.setString(2, targetTime_db);
					pstm.setString(3, targetTime2_db);
					pstm.setString(4, target.getOwnerID());
					System.out.println(pstm.toString()+"\n");
					rs3 = pstm.executeQuery();
					if (rs3.next()) 
					{
						tar.setAchieved(rs3.getInt("quantityachieved"));			
						tar.setAmountpricesachieve(rs3.getInt("amountachieved"));
					}
					list.add(tar);
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return list;
	}

	@Override
	public boolean updatemonthtarget(Target target) {
		// TODO Auto-generated method stub
		int index = target.getIndex();
		String sql = "";
		PreparedStatement pstm;
		if (index == 1) {
			sql = " update target_t set target=?,targetAmount=?   where  model=? and  type=? and targetType=? and ownerID=? and targetTime=? ";

			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, target.getTarget());
				pstm.setInt(2, target.getTargetAmount());
				pstm.setString(3, target.getModel());
				pstm.setString(4, target.getType());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getOwnerID());
				pstm.setString(7, target.getTargetTime());
				System.out.println("SQL语句为：" + pstm.toString());
				if (pstm.executeUpdate() != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (index == 2) {
			sql = " update target_t set target=?,targetAmount=? where  model=? and  type=? and targetType=? and ownerID=? and targetTime=?";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, target.getTarget());
				pstm.setInt(2, target.getTargetAmount());
				pstm.setString(3, target.getModel());
				pstm.setString(4, target.getType());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getOwnerID());
				pstm.setString(7, target.getTargetTime());
				System.out.println("SQL语句为：" + pstm.toString());
				if (pstm.executeUpdate() != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean updatespecialtarget(Target target) {
		// TODO Auto-generated method stub
		int index = target.getIndex();
		String sql = "";
		PreparedStatement pstm;
		if (index == 1) {
			sql = " update target_t set target=?,targetAmount=?   where  model=? and  type=? and targetType=?"
					+ " and ownerID=? AND targetTime=?  AND targetTime2=? ";

			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, target.getTarget());
				pstm.setInt(2, target.getTargetAmount());
				pstm.setString(3, target.getModel());
				pstm.setString(4, target.getType());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getOwnerID());
				pstm.setString(7, target.getTargetTime());
				pstm.setString(8, target.getTargetTime2());
				System.out.println("SQL语句为：" + pstm.toString());
				if (pstm.executeUpdate() != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (index == 2) {
			sql = " update target_t set target=?,targetAmount=?   where  model=? and  type=? and targetType=? "
					+ "and ownerID=? AND targetTime=?  AND targetTime2=?";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, target.getTarget());
				pstm.setInt(2, target.getTargetAmount());
				pstm.setString(3, target.getModel());
				pstm.setString(4, target.getType());
				pstm.setString(5, target.getTargetType());
				pstm.setString(6, target.getOwnerID());
				pstm.setString(7, target.getTargetTime());
				pstm.setString(8, target.getTargetTime2());
				System.out.println("SQL语句为：" + pstm.toString());
				if (pstm.executeUpdate() != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

}
