package classes_for_Test;

import java.sql.Date;

import classes_for_JavaBean.Employee;
import classes_for_implement_of_interface.EmployeeImplement;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月30日 上午9:55:30
 * 类说明
 */
public class EmployeeTest {

	Employee em=new Employee();
	EmployeeImplement emp=new EmployeeImplement();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeTest te=new EmployeeTest();
//		te.add();
		te.update();
	}
	
	public void add()
	{
		Employee e=new Employee("pique", "pique", "Gerard", 0, null, "ID", "120120120120", "110", "110@110.com", 5, "xiavi", "terry", "liu", "pps", "laoshan", null);
		boolean b=emp.add(e);
		System.out.println("添加结果"+b);
	}
	public void query()
	{
		Employee s=new Employee();
		s.setID("messi");
		em=emp.queryuser(s);
		System.out.println(em.getLevel()+em.getRegion()+em.getPassword());
	}
	public void update()
	{
		Employee s=new Employee();
		s.setID("messi");
		s.setPassword("tiago");
		boolean b=emp.updatepass(s);
		System.out.println("???????"+b);
	}

}
