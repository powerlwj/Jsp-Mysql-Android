package classes_for_interface;

import java.util.List;
import java.util.Map;

import classes_for_JavaBean.Employee;
import classes_for_JavaBean.Goods;
import classes_for_JavaBean.SoldGoods;

public interface SoldGoodsIF {
	
	//添加货物
	public boolean add(SoldGoods soldgoods);
	//查看货物
	public SoldGoods query(SoldGoods soldgoods);
	//全部查看
	public List<SoldGoods> queryAll();
	//查询所给type的型号
	public List<String> queryModel(SoldGoods soldgoods);
	//删除货物
	public boolean delete(SoldGoods soldgoods);
	//绘图
	public Map<Integer, Integer> chart(Goods goods);
	//查看某个人一月内的销量
	public List<SoldGoods> querysimple(SoldGoods soldGoods);
	//返回某个类型的商品一个月内的销量（制作饼状图）
	public List<SoldGoods> singlemonthsales(SoldGoods soldGoods);
	//个人查看单日消费状况
	public List<SoldGoods> viewmysales(SoldGoods soldGoods);
	//跟新库存
	public boolean updateStock(SoldGoods soldGoods);
	//更新商品标志位（soldFlag==0）
	public boolean updateSoldGoods(SoldGoods soldGoods);
	//更新商品信息
	public boolean updateSoldGoodsInfo(SoldGoods soldGoods);
	//返回售出商品的数字信息
	public List<Map<String, String>> getsumofsales(SoldGoods soldGoods);
	
	
}
