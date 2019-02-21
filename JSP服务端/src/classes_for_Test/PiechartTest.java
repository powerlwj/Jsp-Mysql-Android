package classes_for_Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import classes_for_JavaBean.SoldGoods;
import classes_for_implement_of_interface.Sold_Goods;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年8月11日 下午6:32:48
 * 类说明
 */
public class PiechartTest {
	SoldGoods soldGoods=new SoldGoods();
	Sold_Goods sold_Goods=new Sold_Goods();
	List<SoldGoods> list=new ArrayList<SoldGoods>();
	public static void main(String[] args) {
		PiechartTest pst=new PiechartTest();
		pst.pie();
	}
	public void pie()
	{
		soldGoods.setSoldDate("7");
		soldGoods.setType("Mobile");
		list=sold_Goods.singlemonthsales(soldGoods);
		for (SoldGoods s :list) {
			System.out.println(">>>>>>"+s.getModel()+s.getSoldNumber());
		}
	}

}
