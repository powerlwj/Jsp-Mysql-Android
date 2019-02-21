package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.Goods;
import classes_for_JavaBean.GoodsType;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月24日 上午8:40:59
 * 商品类型接口
 */
public interface GoodsTypeIF {
	
	//添加商品类型
	public boolean add(GoodsType goodstype);
	//查询全部商品类型
	public List<GoodsType> queryAll();
	//修改商品类型
	public boolean update(GoodsType goodstype);
	//查询单个商品类型
	public GoodsType querysimple(GoodsType goodstype);
	//删除商品类型
	public boolean delete(GoodsType goodstype);
	

}
