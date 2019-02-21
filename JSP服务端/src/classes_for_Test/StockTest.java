package classes_for_Test;

import classes_for_JavaBean.Stock;
import classes_for_implement_of_interface.StockImplement;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年7月28日 下午5:00:27 类说明
 */
public class StockTest {
	Stock stock = new Stock();
	StockImplement skp = new StockImplement();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StockTest sd=new StockTest();
//		sd.add();
		sd.query();
	}

	public void add() {
		Stock stock = new Stock("messi", "P7", 2200, 40);
		boolean b = skp.add(stock);
		System.out.println("添加结果："+b);
	}

	public void query() {
		stock.setOwnerID("messi");
		stock.setModel("P8");
		Stock ss=new Stock();
		ss=skp.query(stock);
		System.out.println(ss.getQuantity()+"&&"+ss.getStockAlarm());
	}
}
