package classes_for_Test;

import classes_for_JavaBean.CompetionGoods;
import classes_for_implement_of_interface.Competion_Goods;


/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 上午11:38:54
 * 类说明
 */
public class CompetionGoodsTest {

	CompetionGoods comGoods;
	Competion_Goods comGoods2=new Competion_Goods();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompetionGoodsTest test=new CompetionGoodsTest();
//		test.add();
		test.query();
	}
	public void add()
	{
		comGoods=new CompetionGoods();
		java.sql.Date cu=new java.sql.Date(System.currentTimeMillis());
		comGoods.setBrand("HuaWei");
		comGoods.setType("Mobile");
		comGoods.setModel("P7");
		comGoods.setPrice("2000");
		comGoods.setPriceDate(cu);
		comGoods.setPicPath("/dfs/sdfs/sfs.jpg");
		
		boolean b=comGoods2.add(comGoods);
		
		System.out.println("货物添加返回值"+b);
	}
	public void query()
	{
		comGoods=new CompetionGoods();
		comGoods.setType("Mobile");
		comGoods.setBrand("HuaWei");
		comGoods.setModel("P7");
		
		CompetionGoods c=new CompetionGoods();
		c=comGoods2.query(comGoods);
		System.out.println(c.getPriceDate()+c.getPrice()+c.getPicPath());
	}

}
