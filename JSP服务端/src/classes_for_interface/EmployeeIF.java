package classes_for_interface;

import java.util.List;

import classes_for_JavaBean.Employee;

public interface EmployeeIF {
	
	//根据用户输入的ID返回用户信息，用于登录验证时，验证返回密码与输入密码的一致性
	public Employee query(Employee employee);
	//根据管理员输入的ID删除某用户
	public boolean delete(Employee employee);
	//根据用户的输入信息更新密码{返回值：密码修改成功&&原密码输入错误&&服务器错误，请重试}
	public String update(Employee employee);
	//管理员增加用户
	public boolean add(Employee employee);
	//查询二级经理旗下的员工
	public List<String> queryperson(Employee employee);
	//查询单个用户（登录使用）
	public Employee queryuser(Employee employee);
	//密码修改
	public boolean updatepass(Employee employee); 
	//签到信息产生
	public boolean createcheck(Employee employee);
		

}