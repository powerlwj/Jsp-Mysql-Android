package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.StockChangeDetail;

public interface StockChangeDetailIF {
	
	//显示近一个月内的库存变动明细
	public List<StockChangeDetail> showdetail(String id);
	//更新库存变动明细（仅限comment字段）
	public boolean updatestockdetail(StockChangeDetail stockdetail);
}
