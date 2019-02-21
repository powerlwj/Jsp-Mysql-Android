package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.CompetionGoods;

public interface CompetionGoodsIF {

		//添加竞品
		public boolean add(CompetionGoods cgoods);
		//更新竞品信息
		public boolean update(CompetionGoods cgoods);
		//删除商品
		public boolean delete(CompetionGoods cgoods);
		//查询商品(返回值为CompetionGoods,输入值必须包含type&brand&&model，其他可选)
		public CompetionGoods query(CompetionGoods cgoods);
		//
		public List<CompetionGoods> querysimple(CompetionGoods cgoods);
}
