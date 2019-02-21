package classes_for_Test;

import java.awt.List;
import java.util.ArrayList;

import classes_for_JavaBean.FeedBack;
import classes_for_implement_of_interface.FeedbackImplement;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年7月30日 下午9:27:44 类说明
 */
public class FeedbackTest {

	FeedbackImplement fi = new FeedbackImplement();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FeedbackTest ff = new FeedbackTest();
//		ff.add();
		ff.query();
	}

	public void add() {
		FeedBack f = new FeedBack("messi", "123456789000");
		boolean b = fi.add(f);
		System.out.println("添加结果" + b);
	}

	public void query() {
		java.util.List<FeedBack> list = new ArrayList<FeedBack>();
		list = fi.query();
		System.out.println(list.size());
	}
}
