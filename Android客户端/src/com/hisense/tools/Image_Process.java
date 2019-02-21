package com.hisense.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

import com.example.hisense.MainActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.util.Base64;

public class Image_Process {
	//下面这句需要修改
	MainActivity main = new MainActivity();

	InputStream inputStream = null;

	// FileOutputStream outstream;
	// outstream=new FileOutputStream(realPath);
	// resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outstream);

	/**
	 * 读取照片信息中的旋转角度<br/>
	 * 
	 * @param path
	 *            照片路径
	 * @return角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	// 将压缩后的图片转化成字节流返回
	@SuppressLint("SdCardPath")
	public String getImgByte(File f) {
		// TODO Auto-generated method stub
		System.out.println(" 进入e世界清理垃圾哦亲");
		System.out.println("hhhhhhhhhhhhhhhhkkkkkkkkkkkkkkkkkkkkk");
		String str = "/mnt/sdcard/Tencent/QQ_Images/-34ed3ab2ff0ba74f.png";

		try {

			inputStream = new FileInputStream(f);
			System.out.println("----------------<<<<<" + f);
			// Toast.makeText(MainActivity.this,(int) f.length(), 1).show();
			int len = 0;
			byte b[] = new byte[1024];
			while ((len = inputStream.read()) != -1) {

				str += new String(b, 0, len);
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("ERROR");
		}
		// 保留代码
		// String tp = new String(BASE64Decoder.decodeToBytes(str));
		System.out.println(str.length());
		System.out.println(str);
		return str;
	}

	public Bitmap saveImage(String fileName, Bitmap bit) {
		File file = new File(fileName);
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bit.compress(CompressFormat.JPEG, 70, stream);
			// 70 是压缩率，表示压缩30%; 如果不压缩是100，表示压缩率为0
			FileOutputStream os = new FileOutputStream(file);
			os.write(stream.toByteArray());
			os.close();
			return bit;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将从Message中获取的，表示图片的字符串解析为Bitmap对象 保留方法
	 * 
	 * @param picStrInMsg
	 * @return
	 */
	public static Bitmap decodeImg(String picStrInMsg) {
		Bitmap bitmap = null;

		byte[] imgByte = null;
		InputStream input = null;
		try {
			imgByte = Base64.decode(picStrInMsg, Base64.DEFAULT);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 8;
			input = new ByteArrayInputStream(imgByte);
			SoftReference softRef = new SoftReference(
					BitmapFactory.decodeStream(input, null, options));
			bitmap = (Bitmap) softRef.get();
			;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (imgByte != null) {
				imgByte = null;
			}

			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return bitmap;
	}
}
