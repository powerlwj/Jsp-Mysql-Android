package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.Message;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月31日 上午10:42:21
 * 类说明
 */
public interface MessageIF {
	
	public boolean add(Message message);
	//普通员工
	public List<Message> ordquery();
	//二级经理查询
	public List<Message> sedquery();
	public boolean delete();

}
