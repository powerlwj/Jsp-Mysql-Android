package classes_for_Test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import classes_for_JavaBean.Goods;
import classes_for_implement_of_interface.Sold_Goods;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年8月5日 下午8:18:40
 * 类说明
 */
public class ChartTest {

	Sold_Goods sg=new Sold_Goods();
	Map<Integer, Integer> map=new HashMap<Integer, Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChartTest te=new ChartTest();
		te.query();
	}
	
	public void query()
	{
		Goods g=new Goods();
		g.setModel("A1");
		map=sg.chart(g);
		JSONObject jsObject=JSONObject.fromObject(map);
		System.out.println("jsObject"+jsObject);
	}

}
