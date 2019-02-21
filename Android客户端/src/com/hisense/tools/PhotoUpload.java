package com.hisense.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PhotoUpload {
	
	String uploadBuffer="";

	public String Upload(String srcUrl) throws IOException {

		if(!"null".equals(srcUrl))
		{
		FileInputStream fis = new FileInputStream(srcUrl);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = fis.read(buffer)) >= 0) {
			baos.write(buffer, 0, count);
		}
		 uploadBuffer = new String(Base64Encoder.encode(baos
				.toByteArray())); // 进行Base64编码
		System.out.println("图像的字节码大小为："+uploadBuffer.length());
		baos.close();
		fis.close();
		return uploadBuffer;
	}else
	{
		uploadBuffer="";
	}
		
		return uploadBuffer;
	}

}
