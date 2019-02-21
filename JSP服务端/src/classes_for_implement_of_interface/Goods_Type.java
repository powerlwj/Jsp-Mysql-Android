package classes_for_implement_of_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.GoodsType;
import classes_for_Tools.Db_Manager;
import classes_for_interface.GoodsTypeIF;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月24日 上午8:46:39
 * 商品类型操作类
 */
public class Goods_Type implements GoodsTypeIF {

	Db_Manager db=null;
	Connection con=db.getConnection();
	GoodsType goodstype;
	boolean flag;
	
	public Goods_Type() {
		// TODO Auto-generated constructor stub
	this.db=new Db_Manager();
	}
	@Override
	public boolean add(GoodsType goodstype) {
		// TODO Auto-generated method stub
		String sql="insert into goodstype_t(id,type)values(?,?)";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			
			pstm.setString(1, goodstype.getId());
			pstm.setString(2, goodstype.getType());
			
			System.out.println("Goods_type调试：>>"+goodstype.getType());
			if(pstm.executeUpdate()>0)
			{
				flag=true;
			}else
			{
				flag=false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<GoodsType> queryAll() {
		// TODO Auto-generated method stub
		List<GoodsType> list=new ArrayList<GoodsType>();
//		String sqll="INSERT  goodstype_t(type) SELECT type from goods_t GROUP BY type";
		String sql="select type from goodstype_t";
		
		try {
//			Statement st = con.createStatement();
//			int row=st.executeUpdate(sqll);
//			if(row!=0) {
			PreparedStatement pstm=con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{   goodstype=new GoodsType();
				goodstype.setType(rs.getString(1));
				//System.out.println("商品类型有："+goodstype.getType());
				list.add(goodstype);
			}
//		}else
//		{
//			return null;
//		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean update(GoodsType goodstype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GoodsType querysimple(GoodsType goodstype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(GoodsType goodstype) {
		// TODO Auto-generated method stub
		return false;
	}

}
