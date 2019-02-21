package classes_for_implement_of_interface;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;

import classes_for_JavaBean.CompetionGoods;
import classes_for_Tools.Db_Manager;
import classes_for_interface.CompetionGoodsIF;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 上午11:01:31
 * 类说明
 */
public class Competion_Goods implements CompetionGoodsIF {

	Db_Manager db;
	CompetionGoods competiongoods;
	Connection con=db.getConnection();
	
	public Competion_Goods() {
		// TODO Auto-generated constructor stub
		this.db=new Db_Manager();
	}
	@Override
	public boolean add(CompetionGoods cgoods) {
		// TODO Auto-generated method stub
		java.sql.Date current=new java.sql.Date(System.currentTimeMillis());
		String sql="insert into competinggoods_t(brand,model,type,price,priceDate,picPath,reporterID,feature) values (?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, cgoods.getBrand());
			pstm.setString(2, cgoods.getModel());
			pstm.setString(3, cgoods.getType());
			pstm.setString(4, cgoods.getPrice());
			pstm.setDate(5, current);
			pstm.setString(6, cgoods.getPicPath());
			pstm.setString(7, cgoods.getReporterID());
			pstm.setString(8, cgoods.getFeatures());
			
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
	public boolean update(CompetionGoods cgoods) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CompetionGoods cgoods) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompetionGoods query(CompetionGoods cgoods) {
		// TODO Auto-generated method stub
		competiongoods=new CompetionGoods();
		String sql="select price,priceDate,picPath,feature  from competinggoods_t where type=? and brand=? and model=?";
		
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, cgoods.getType());
			pstm.setString(2, cgoods.getBrand());
			pstm.setString(3, cgoods.getModel());
			
			ResultSet rs=pstm.executeQuery();
			if(rs.next())
			{
				competiongoods.setPrice(rs.getString(1));
				competiongoods.setPriceDate(rs.getDate(2));
				competiongoods.setPicPath(rs.getString(3));
				competiongoods.setFeatures(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return competiongoods;
	}
	@Override
	public List<CompetionGoods> querysimple(CompetionGoods cgoods) {
		// TODO Auto-generated method stub
		
		List<CompetionGoods> list=new ArrayList<CompetionGoods>();
		String sql="select brand,model from competinggoods_t where type=?  ";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			pstm.setString(1, cgoods.getType());
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
			{
				competiongoods=new CompetionGoods();
				competiongoods.setBrand(rs.getString(1));
				competiongoods.setModel(rs.getString(2));
				list.add(competiongoods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
