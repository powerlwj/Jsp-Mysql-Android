package classes_for_Test;

import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.Employee;
import classes_for_implement_of_interface.EmployeeImplement;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年8月10日 下午8:19:36
 * 类说明
 */
public class StuffTest {

	Employee em = new Employee();
	EmployeeImplement empl = new EmployeeImplement();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuffTest sf=new StuffTest();
		sf.haha();
	}
	public void haha()
	{
		em.setReporterID("xiavi");
		List<String> list=new ArrayList<String>();
		list=empl.queryperson(em);
		System.out.println(list.toString());
	}

}
