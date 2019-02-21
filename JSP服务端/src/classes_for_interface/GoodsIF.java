/**
 * 
 */
package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.Goods;

/**
 * @author powerliu
 *
 */
public interface GoodsIF {
	
	//添加商品
	public boolean add(Goods goods);
	//更新商品信息
	public boolean update(Goods goods);
	//删除商品
	public boolean delete(String internalModel);
	//查询商品(返回值为Goods,输入值必须包含internalModel，其他可选)
	public Goods query(Goods goods);
	

}
