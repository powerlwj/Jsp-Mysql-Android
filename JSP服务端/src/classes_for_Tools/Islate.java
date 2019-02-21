package classes_for_Tools;

/**
 * @author powerliu
 * @Email:569546435@qq.com
 * @version
 * @创建时间：2015年8月1日 上午9:21:16 类说明
 */
public class Islate {

	public boolean judge(int hour, int min, int sec) {
		if (hour < 8) {
			return true;
		} else {
			return false;
		}

	}

	public int MorningOREvening(int hour) {
		int a=0;
		System.out.println(hour);
		if (hour < 8) {
			a=1;
		} else {
			a=2;
		}
		return a;
	}

}
