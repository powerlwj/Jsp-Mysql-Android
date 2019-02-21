package classes_for_Test;

import java.io.InputStream;

import net.sf.json.JSONObject;
import classes_for_JavaBean.Administrator;
import classes_for_Tools.InputStreamTOString;
import classes_for_implement_of_interface.AdministratorImplement;

import com.google.gson.Gson;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 上午7:54:10
 * 类说明
 */
public class Logintest {

	Administrator admin = new Administrator();
	AdministratorImplement adminip = new AdministratorImplement();
	public static void main(String[] args) {
		Logintest l=new Logintest();
		l.log();
		
	}

	
	public void log()
	{
		admin.setId("messi");
		admin.setPassword("messi");
		Administrator admin2 = new Administrator();
		boolean b=adminip.updateuer(admin);
		System.out.println("密码修改："+b);
		//admin2 = adminip.queryuer(admin);
		/*JSONObject jsObject=new JSONObject();
		jsObject.put("ad_type", admin2.getAdmin_type());
		jsObject.put("region", admin2.getRegion());
		System.out.println("生成json测试："+jsObject);*/
	}
	
	
}
