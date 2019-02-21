package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.Stock;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 下午3:57:03
 * 类说明
 */
public interface StockIF {
	
	//添加库存
	public boolean add(Stock stock);
	//删除库存
	public boolean delet(Stock stock);
	//修改库存
	public boolean update(Stock stock);
	//跟新库存
	public Stock query(Stock stock);
	//经理查看分配给自己的库存
	public List<Stock> viewmystock(Stock stock);

}
