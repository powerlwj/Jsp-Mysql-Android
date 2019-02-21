package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.Employee;
import classes_for_Tools.DateUtils;
import classes_for_Tools.Db_Manager;
import classes_for_interface.EmployeeIF;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月30日 上午9:19:28
 * 类说明
 */
public class EmployeeImplement implements EmployeeIF {

	Db_Manager db;
	Connection con=db.getConnection();
	Employee em;
	public EmployeeImplement() {
		// TODO Auto-generated constructor stub
		this.db=new Db_Manager();
	}
	@Override
	public Employee query(Employee employee) {
		// TODO Auto-generated method stub
		em=new Employee();
		String sql="select password,realname,sex,birthday,paperType, paperNo,tel,email,level,reporterID, secondMgrID,firstMgrID, directorID, region,addtime from employee_t where ID=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, employee.getID());
			
			ResultSet rs=pstm.executeQuery();
			if(rs.next())
			{
				em.setPassword(rs.getString(1));
				em.setRealname(rs.getString(2));
				em.setSex(rs.getInt(3));
				em.setBirthday(rs.getDate(4));
				em.setPaperType(rs.getString(5));
				em.setPaperNo(rs.getString(6));
				em.setTel(rs.getString(7));
				em.setEmail(rs.getString(8));
				em.setLevel(rs.getInt(9));
				em.setReporterID(rs.getString(10));
				em.setSecondMgrID(rs.getString(11));
				em.setFirstMgrID(rs.getString(12));
				em.setDirectorID(rs.getString(13));
				em.setRegion(rs.getString(14));
				em.setAddtime(rs.getDate(15));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return em;
	}

	public boolean delete(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String update(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean add(Employee employee) {
		// TODO Auto-generated method stub
		String sql="insert into employee_t(ID,password,realname,sex,birthday,paperType, paperNo,tel,email,level,reporterID, secondMgrID,firstMgrID, directorID, region,addtime)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, employee.getID());
			pstm.setString(2, employee.getPassword());
			pstm.setString(3, employee.getRealname());
			pstm.setInt(4, employee.getSex());
			pstm.setDate(5, employee.getBirthday());
			pstm.setString(6, employee.getPaperType());
			pstm.setString(7, employee.getPaperNo());
			pstm.setString(8, employee.getTel());
			pstm.setString(9, employee.getEmail());
			pstm.setInt(10, employee.getLevel());
			pstm.setString(11, employee.getReporterID());
			pstm.setString(12,employee.getSecondMgrID());
			pstm.setString(13, employee.getFirstMgrID());
			pstm.setString(14, employee.getDirectorID());
			pstm.setString(15, employee.getRegion());
			pstm.setDate(16, employee.getAddtime());
			
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
	public List<String> queryperson(Employee employee) {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		String sql="SELECT ID FROM employee_t WHERE reporterID=?;";
		
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, employee.getReporterID());
			System.out.println("SQL语句测试："+pstm);
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}
	@Override
	public Employee queryuser(Employee employee) {
		// TODO Auto-generated method stub
		String is_special="SELECT * FROM target_t WHERE targetType='special' AND ownerID=? AND DATE(NOW()) BETWEEN targetTime AND targetTime2";
		String sql = "SELECT password ,level, region,ownedGoodsType  FROM employee_t WHERE ID=?";
		em=new Employee();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, employee.getID());
			System.out.println("pstm:>>>>"+pstm);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				
				String pass = rs.getString(1);
				int level = rs.getInt(2);
				String region = rs.getString(3);
				em.setPassword(pass);
				em.setLevel(level);
				em.setRegion(region);
				em.setOwn(rs.getString(4));
				em.setIs_Special(false);
			}
			if(em.getLevel()==5)
			{
				PreparedStatement pstmm=con.prepareStatement(is_special);
				pstmm.setString(1, employee.getID());
				System.out.println("pstm:>>>>"+pstmm);
				ResultSet rss=pstmm.executeQuery();
				if(rss.next())
				{
					em.setIs_Special(true);
				}
			}
			
			return em;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return em;
	}
	@Override
	public boolean updatepass(Employee employee) {
		String sql = "update employee_t set password=? where ID=? ";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, employee.getPassword());
			pstm.setString(2, employee.getID());

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
	public boolean createcheck(Employee employee) {
		// TODO Auto-generated method stub
		String sql="insert ignore  into attence_t(ID,date) values(?,?)";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, employee.getID());
			pstm.setString(2, DateUtils.getNowTime(DateUtils.DATE_SMALL_STR).toString());
			
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

}
