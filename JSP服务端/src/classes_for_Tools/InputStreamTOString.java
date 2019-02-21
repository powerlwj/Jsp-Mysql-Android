package classes_for_Tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

/**
 * @author powerliu 将InputStream转化为String
 */
public class InputStreamTOString {

	public String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		String ret=baos.toString();
		return ret;
	}

	public String getparms(String json) {
		JSONTokener par = new JSONTokener(json);
		JSONObject js = (JSONObject) par.nextValue();
		String model = js.getString("model");
		String id=js.getString("id");
		System.out.println("解析得到的参数为："+model);
		return model;
	}
	

}
