package com.example.hisense;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.hisense.myadapters.SetModelForSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.Image_Process;
import com.hisense.tools.MyApplication;
import com.hisense.tools.PhotoUpload;
import com.hisense.tools.UploadUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author powerliu 类说明：上传竞品信息
 */
public class UpdateCom extends Activity implements OnClickListener {

	private Spinner ty;
	private Button take, select, upload;
	private EditText pri, mod, bra, fea;
	Image_Process ima;
	private ImageView image;
	private String brand, model, price, type, features;
	private static final String tag = "数据库调试....";
	private static String picFileFullName;
	public static String realPath = "";
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	UploadUtil uploadpic;
	ProgressBar progress;
	DialogForMe df;
	SetModelForSpinner smfs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Upload Compition Goods ");
		setContentView(R.layout.updatecom);
		// 界面初始化
		init();
		smfs.TypeSet();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.take_photo:
			takePicture();
			break;
		case R.id.sel_photo:
			openAlbum();
			break;
		case R.id.upload:
			df.showdialog();
			brand = bra.getText().toString();
			model = mod.getText().toString();
			price = pri.getText().toString();
			features = fea.getText().toString();
			if (!(model.equals("") || price.equals("") || realPath.equals(""))) {
				/*
				 * System.out.println("图片的路仅为：》》》》》》》》"+realPath);||realPath.equals
				 * (null) if(realPath==null) { realPath = "file://assets//haaa";
				 * System.out.println(realPath); }
				 */
				AsyncHttpClient add = new AsyncHttpClient();
				System.out.println("Uploading......" + type + brand + model
						+ features);
				JSONObject params = new JSONObject();
				// 截取图片名称
				/*
				 * String upic=realPath.substring(realPath.lastIndexOf("/")+1,
				 * realPath.length()); File pic=new File(realPath);
				 * System.out.println(upic);
				 */
				String picdate;
				try {
					picdate = new PhotoUpload().Upload(realPath);
					params.put("type", smfs.TypeSet());
					params.put("reporterID", MyApplication.userid);
					params.put("model", model);
					params.put("brand", brand);
					params.put("price", price);
					params.put("features", features);
					params.put("picPath", picdate);
					StringEntity s = new StringEntity(params.toString());

					add.post(UpdateCom.this, MyApplication.setUrl("addcompetionGoods"),
							s, HTTP.UTF_8, new AsyncHttpResponseHandler() {

								@Override
								public void onSuccess(int arg0, Header[] arg1,
										byte[] arg2) {
									// TODO Auto-generated method stub
									System.out.println("服务器连接成功");
									if (arg0 == 200) {
										boolean b = Boolean.valueOf(new String(
												arg2).trim());
										if (b) {
											df.canceldialog();
											Toast.makeText(UpdateCom.this,
													MyApplication.ok,
													Toast.LENGTH_SHORT).show();
											bra.setText("");
											mod.setText("");
											pri.setText("");
											fea.setText("");
											image.setImageResource(R.drawable.haaa);
										} else {
											df.canceldialog();
											Toast.makeText(UpdateCom.this,
													MyApplication.no,
													Toast.LENGTH_SHORT).show();
										}
									}
								}

								@Override
								public void onFailure(int arg0, Header[] arg1,
										byte[] arg2, Throwable arg3) {
									// TODO Auto-generated method stub
									df.canceldialog();
									Toast.makeText(UpdateCom.this,
											MyApplication.serverwrong,
											Toast.LENGTH_SHORT).show();
								}
							});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Toast.makeText(UpdateCom.this, MyApplication.totalinfo, 1)
						.show();
			}
			break;
		default:
			break;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		df = new DialogForMe(UpdateCom.this);
		bra = (EditText) findViewById(R.id.brand);
		mod = (EditText) findViewById(R.id.model);
		ty = (Spinner) findViewById(R.id.type);
		pri = (EditText) findViewById(R.id.price);
		take = (Button) findViewById(R.id.take_photo);
		take.setOnClickListener(this);
		select = (Button) findViewById(R.id.sel_photo);
		select.setOnClickListener(this);
		upload = (Button) findViewById(R.id.upload);
		upload.setOnClickListener(this);
		image = (ImageView) findViewById(R.id.image);
		fea = (EditText) findViewById(R.id.features);

		smfs = new SetModelForSpinner(UpdateCom.this, ty);

	}

	// 拍照
	public String takePicture() {
		File outDir = null;
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			outDir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			if (!outDir.exists()) {
				outDir.mkdirs();
			}
			File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
			picFileFullName = outFile.getAbsolutePath();
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		} else {
			Log.e(tag, "请确认已经插入SD卡");
		}
		realPath = picFileFullName;
		return realPath;
	}

	// 打开相册
	public void openAlbum() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	// 获取图片路径
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Log.e(tag, "获取图片成功，path=" + picFileFullName);
				Toast.makeText(UpdateCom.this, "loading picture successfully", Toast.LENGTH_SHORT).show();
				// toast("获取图片成功，path="+picFileFullName);
				try {
					setImageView(picFileFullName);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (resultCode == RESULT_CANCELED) {
				// 用户取消了图像捕获
			} else {
				// 图像捕获失败，提示用户
				Log.e(tag, "拍照失败");
			}
		} else if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				if (uri != null) {
					realPath = getRealPathFromURI(uri);
					Log.e(tag, "获取图片成功，path=" + realPath);
					Toast.makeText(UpdateCom.this, "loading picture successfully", Toast.LENGTH_SHORT).show();
					try {
						setImageView(realPath);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Log.e(tag, "从相册获取图片失败");
				}
			}
		}
	}

	// 设置图片
	private void setImageView(String realPath) throws FileNotFoundException {

		OutputStream imge;
		imge = new ByteArrayOutputStream();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;//图片宽高都为原来的二分之一，即图片为原来的四分之一
		Bitmap bmp = BitmapFactory.decodeFile(realPath,options);
		@SuppressWarnings("static-access")
		int degree = ima.readPictureDegree(realPath);
		if (degree <= 0) {
			image.setImageBitmap(bmp);
		} else {
			Log.e(tag, "rotate:" + degree);
			// 创建操作图片是用的matrix对象
			Matrix matrix = new Matrix();
			// 旋转图片动作
			matrix.postRotate(degree);
			// 创建新图片
			Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
					bmp.getWidth(), bmp.getHeight(), matrix, true);
			// resizedBitmap.compress(Bitmap.CompressFormat.JPEG,50 imge);
			image.setImageBitmap(resizedBitmap);
		}
	}

	/**
	 * This method is used to get real path of file from from uri<br/>
	 * 
	 * @return String
	 */
	public String getRealPathFromURI(Uri contentUri) {
		try {
			String[] proj = { MediaStore.Images.Media.DATA };
			@SuppressWarnings("deprecation")
			Cursor cursor = this.managedQuery(contentUri, proj, null, null,
					null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} catch (Exception e) {
			return contentUri.getPath();
		}
	}
}