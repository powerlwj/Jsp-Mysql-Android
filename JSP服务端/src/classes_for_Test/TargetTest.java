package classes_for_Test;

import java.util.ArrayList;
import java.util.List;

import classes_for_JavaBean.Target;
import classes_for_implement_of_interface.TargetImplement;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年7月28日 下午10:30:01 类说明
 */
public class TargetTest {

	Target tar = new Target();
	Target tar2 = new Target();
	TargetImplement tarImplement = new TargetImplement();
	List<Target> list=new ArrayList<Target>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TargetTest test = new TargetTest();
		test.query();
	}
	public void add()
	{
		/*Target t=new Target("A2", 50, "2000", "1800", "messi", "2015", "12");
		boolean b=tarImplement.add(t);
		System.out.println("测试结果为："+b);*/
	}
	public void query()
	{
		tar.setTargetTime("2015.4");
		tar.setTargetType("Monthly");
		tar.setOwnerID("xiavi");
		list=tarImplement.queryall(tar);
		for(Target t:list)
		{
			System.out.println("查询到的数据为："+t.getModel()+"-"+t.getTarget());
		}
		
	}

}
