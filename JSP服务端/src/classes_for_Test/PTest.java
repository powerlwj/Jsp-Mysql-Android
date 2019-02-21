package classes_for_Test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import classes_for_JavaBean.SoldGoods;
import classes_for_implement_of_interface.Sold_Goods;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年8月10日 下午8:01:24 类说明
 */
public class PTest {

	SoldGoods soldGoods = new SoldGoods();
	Sold_Goods sold_Goods = new Sold_Goods();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PTest p = new PTest();
		p.haha();
	}

	public void haha() {
		soldGoods.setSellerID("messi");
		List<SoldGoods> list = new ArrayList<SoldGoods>();
		list = sold_Goods.querysimple(soldGoods);
		JSONArray jsArray = new JSONArray();
		for (SoldGoods s : list) {
			JSONObject jsObject = new JSONObject();
			jsObject.put("imei", s.getImei());
			jsObject.put("type", s.getType());
			jsObject.put("model", s.getModel());
			jsObject.put("soldDate", s.getSoldDate());
			jsArray.add(jsObject);
			System.out.println("s.toString():" + s.toString());
		}
	}

}
