package com.example.hisense;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.hisense.myadapters.SetModelForSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.Image_Process;
import com.hisense.tools.MyApplication;
import com.hisense.tools.PhotoUpload;
import com.hisense.tools.UpdateInfo;
import com.hisense.tools.UploadUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author powerliu 类说明：添加商品信息
 */
public class Add extends Activity implements OnClickListener {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	UploadUtil uploadpic;
	private Spinner ty;
	private Button takephoto, select, addgoods, s;
	private EditText mod, con, pri,inmodel;
	private TextView id, data, location;
	private static String picFileFullName;
	private String config, price, type, model, picdate,internalModel;
	public static String realPath = "";
	Image_Process ima;
	DialogForMe df;
	private static final String tag = "调试....";
	private ImageView image;
	SetModelForSpinner smfs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Add Goods");
		setContentView(R.layout.add);
		// 初始化
		init();
		s.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent spec = new Intent(Add.this,
						SpecialPlan.class);
				startActivity(spec);
			}
		});
		// 为Spinner（下拉框）设置数据源
		type = smfs.TypeSet();
	}

	// 为按钮添加点击响应事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 拍照
		case R.id.take_photo:
			takePicture();
			break;
		// 打开相册
		case R.id.sel_photo:
			openAlbum();
			break;
		// 商品信息上传
		case R.id.upload:
			model = mod.getText().toString();
			config = con.getText().toString().trim();
			price = pri.getText().toString();
			internalModel=inmodel.getText().toString().trim();
			System.out.println("测试是否为空" + type + model + price);
			if (!(model.equals("") || price.equals("") || realPath.equals(""))) {
				AsyncHttpClient add = new AsyncHttpClient();
				JSONObject params = new JSONObject();
				df.showdialog();
				try {
					picdate = new PhotoUpload().Upload(realPath);
					params.put("type", smfs.TypeSet());
					params.put("model", model);
					params.put("configInfo", config);
					params.put("price", price);
					params.put("internalModel", internalModel);
					params.put("picPath", picdate);
					System.out.println("上传数据为：》》》" + params.toString());
					StringEntity s = new StringEntity(params.toString());

					add.post(Add.this, MyApplication.setUrl("addGoods"), s, HTTP.UTF_8,
							new AsyncHttpResponseHandler() {

								@Override
								public void onSuccess(int arg0, Header[] arg1,
										byte[] arg2) {
									// TODO Auto-generated method stub
									if (arg0 == 200) {
										boolean b = Boolean.valueOf(new String(
												arg2).trim());
										if (b) {
											df.canceldialog();
											Toast.makeText(Add.this,
													"successfully",
													Toast.LENGTH_SHORT).show();
											mod.setText("");
											pri.setText("");
											con.setText("");
											inmodel.setText("");
											image.setImageResource(R.drawable.haaa);
										} else {
											df.canceldialog();
											Toast.makeText(Add.this, "Failure",
													Toast.LENGTH_SHORT).show();
										}
									}
								}

								@Override
								public void onFailure(int arg0, Header[] arg1,
										byte[] arg2, Throwable arg3) {
									// TODO Auto-generated method stub
									df.canceldialog();
									Toast.makeText(Add.this,
											"Unknown failure,try again",
											Toast.LENGTH_SHORT).show();
								}
							});
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Toast.makeText(Add.this,
						"please input the information completely ",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		df = new DialogForMe(Add.this);
		ty = (Spinner) findViewById(R.id.type);
		select = (Button) findViewById(R.id.sel_photo);
		select.setOnClickListener(this);
		addgoods = (Button) findViewById(R.id.upload);
		addgoods.setOnClickListener(this);
		inmodel=(EditText)findViewById(R.id.inmodel);
		takephoto = (Button) findViewById(R.id.take_photo);
		takephoto.setOnClickListener(this);
		pri = (EditText) findViewById(R.id.price);
		con = (EditText) findViewById(R.id.config);
		mod = (EditText) findViewById(R.id.model);
		image = (ImageView) findViewById(R.id.image);

		id = (TextView) findViewById(R.id.id);
		data = (TextView) findViewById(R.id.date);
		location = (TextView) findViewById(R.id.location);
		s = (Button) findViewById(R.id.specialTask);
		s.setVisibility(View.GONE);
		UpdateInfo updateinfo = new UpdateInfo();
		updateinfo.update(id, data, location, s);

		smfs = new SetModelForSpinner(Add.this, ty);
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

	public void openAlbum() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Log.e(tag, "获取图片成功，path=" + picFileFullName);
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

	// 加载图片
	private void setImageView(String realPath) throws FileNotFoundException {

		OutputStream imge;
		imge = new ByteArrayOutputStream();
		Bitmap bmp = BitmapFactory.decodeFile(realPath);
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

	// 获取图片的路径
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
