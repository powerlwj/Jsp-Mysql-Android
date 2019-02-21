package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.Administrator;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月27日 下午9:41:25
 * 类说明
 */
public interface AdministratorIF {
	
	//添加用户
	public Administrator adduer(Administrator admin);
	//删除用户
	public boolean deluser(Administrator admin);
	//查询用户
	public Administrator queryuer(Administrator admin);
	// 修改用户
	public boolean updateuer(Administrator admin); 
	
}
