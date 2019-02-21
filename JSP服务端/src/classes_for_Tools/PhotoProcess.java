package classes_for_Tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author powerliu 
 * @Email:569546435@qq.com
 * @version 
 * @创建时间：2015年7月28日 下午1:14:14
 * 类说明:图片处理（编码解码，读取保存）
 */
public class PhotoProcess {
	

	@SuppressWarnings("resource")
	public boolean SavePhoto(String picname,String picString)
	{
		//File file=new File("//usr//local//ImageFromClient//"+picname);
		File file=new File("E:\\Apache Software Foundation\\Tomcat 6.0\\webapps\\hs\\upload\\"+picname);
		if (!file.exists()) 
		{
			try 
			{
				System.out.println("文件不存在,正在创建.路径为" + file);
				file.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		OutputStream fos1;
		try {
			fos1 = new FileOutputStream(file);
			try {
				fos1.write(new sun.misc.BASE64Decoder().decodeBuffer(picString));
//				fos1.write(BASE64Decoder.decodeToBytes(picString));
				if(fos1.toString().length()>0)
				{
					return true;
				}else
				{
					return false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	@SuppressWarnings("resource")
	public String readPhoto(File file)
	{
		String picString="";
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = fis.read(buffer)) >= 0) {
				baos.write(buffer, 0, count);
			}
			// 进行Base64编码
		   picString = new String(Base64Encoder.encode(baos.toByteArray()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return picString;
	}
}
