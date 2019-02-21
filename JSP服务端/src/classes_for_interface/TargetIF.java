package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.Target;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年7月28日 下午9:10:50 类说明
 */
public interface TargetIF {

	// 添加计划
	public boolean add(Target target);

	// 删除计划
	public boolean deletemonthmission(Target target);

	public boolean deletespecialmission(Target target);

	// 修改计划
	public boolean update(Target target);

	// 查看计划
	public Target query(Target target);

	// 查看所有
	public List<Target> queryall(Target target);

	// 特殊计划
	public Target queryspecial(Target target);

	// 查看二级经理分配给员工的任务
	public List<Target> viewallocatedmission(Target target);

	// app查看所有
	public List<Target> queryallforapp(Target target);

	// app查看特书任务进度
	public List<Target> smissionprocess(Target target);

	// app特殊任务
	public Target queryspecialforapp(Target target);

	// 加载特殊任务
	public List<Target> queryspe(Target target);

	// 查看所有特殊任务
	public List<Target> viewallspecialmission(Target target);

	// 更新月任务计划
	public boolean updatemonthtarget(Target target);

	// 更新特殊任务计划
	public boolean updatespecialtarget(Target target);

}
