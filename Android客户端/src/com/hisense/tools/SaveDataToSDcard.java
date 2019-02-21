package com.hisense.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Environment;


public class SaveDataToSDcard {
	//判断SD卡是否可用
	

	public static boolean isSDCardReable() 
	   { 
	       return Environment.getExternalStorageState().equals( 
	               Environment.MEDIA_MOUNTED) 
	         || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())?true:false ;
	   } 
	  //判断SD卡是否可以写入
	   public static boolean isSDCardWriteable() 
	   {
		   return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())?true:false;
	   }
	   
	  public String  savephoto(String filename,String phototbyte)
	  {
//			BASE64Decoder decoder=new BASE64Decoder();
		  if(isSDCardWriteable()&&isSDCardReable())
		  {
			  File SDdir=Environment.getExternalStorageDirectory();
			  File LWJ=new File(SDdir, "LWJ");
			  File Photourl=new File(LWJ, filename);
			  //如果LWJ文件夹不存在则创建[LWJ文件夹即为本应用的总文件夹]
			  if(!LWJ.exists())
			  {
				  LWJ.mkdir();
			  }
			  //如果LWJ文件夹下存放图片的Photo文件夹不存在,则创建
			  if(!Photourl.exists())
			  {
				  try {
					  Photourl.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  
			 try {
				FileOutputStream fos=new FileOutputStream(Photourl);
//				fos.write(decoder.decodeBuffer(phototbyte));
				fos.write(BASE64Decoder.decodeToBytes(phototbyte));
//				fos.write(Base64.decode(phototbyte, Base64.DEFAULT));
				System.out.println(">>>>"+Photourl.toString());
				return Photourl.toString();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("写入文件异常");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		  }
		  return null;
	  }
	  
	 
}
