package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.FeedBack;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月30日 下午8:48:54
 * 类说明
 */
public interface FeedbackIF {
	
	public boolean add(FeedBack feedback);
	public boolean delete(FeedBack feedback);
	public List<FeedBack> query();

}
