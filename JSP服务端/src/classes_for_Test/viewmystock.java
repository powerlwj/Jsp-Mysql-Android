package classes_for_Test;

import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.Stock;
import classes_for_implement_of_interface.StockImplement;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年8月20日 下午1:07:22 类说明
 */
public class viewmystock {
	StockImplement ss = new StockImplement();
	List<Stock> list = new ArrayList<Stock>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		viewmystock v = new viewmystock();
		v.qu();
	}

	private void qu() {
		// TODO Auto-generated method stub
		Stock s = new Stock();
		s.setOwnerID("xiavi");
		list = ss.viewmystock(s);
		for (Stock sss : list) {
			System.out.println(sss.getModel() + sss.getQuantity());
		}

	}
}
