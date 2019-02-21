package com.example.hisense;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.hisense.asynctaskclasses.BarChartAsyncTask;
import com.hisense.asynctaskclasses.BarChartAsyncTask2;
import com.hisense.asynctaskclasses.ChartAsyncTask;
import com.hisense.asynctaskclasses.MyAsyncTask;
import com.hisense.asynctaskclasses.PieChartAsyncTask;
import com.hisense.chart.BarChart;
import com.hisense.chart.PieChart;
import com.hisense.chart.SaleChart;
import com.hisense.myadapters.SetTy_MoforSpinner;
import com.hisense.tools.DialogForMe;
import com.hisense.tools.MyApplication;


/**
 * @author powerliu
 *类说明：销量趋势分析
 */
public class SaleAnalysis extends Activity {

	private Spinner ty, mod;
	private Spinner mod2,mod3;
	private Button create,pie,bar;
	private String type, model;
	DialogForMe df;
	MyAsyncTask mytask;
	ChartAsyncTask chTask;
	SetTy_MoforSpinner stms;
	PieChartAsyncTask pctask;
	BarChartAsyncTask bctask; 
	BarChartAsyncTask2 bctask2;
	TextView click;
	AsyncTask<String, Integer, String[]> modtask;
	public String salemodel2,salemodel3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Sales Analysis View");
		setContentView(R.layout.sale_analysis);
		// 界面初始化
		init();
		stms.TypeSet();
		
		// 生成图表
		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chTask = new ChartAsyncTask(SaleAnalysis.this,
						SetTy_MoforSpinner.type, MyAsyncTask.salemodel);
				chTask.execute(MyApplication.setUrl("salechart"));
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						SaleAnalysis.this);
				dialog.setTitle("Sales query");
				dialog.setMessage("Type:" + SetTy_MoforSpinner.type + "\n" + "Model："
						+ MyAsyncTask.salemodel);
				dialog.setPositiveButton("ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent line = new SaleChart()
										.getIntent(SaleAnalysis.this);
								startActivity(line);
							}
						});
				dialog.setNegativeButton("cancle", null);
				dialog.create().show();
			}
		});
		//饼状图
		pie.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONObject js=new JSONObject();
				try {
					js.put("type", SetTy_MoforSpinner.type);
					pctask=new PieChartAsyncTask(SaleAnalysis.this, js);
					pctask.execute(MyApplication.setUrl("pieChart"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						SaleAnalysis.this);
				dialog.setTitle("Sales query");
				dialog.setMessage("Type:" + SetTy_MoforSpinner.type + "\n" + "Model："
						+ MyAsyncTask.salemodel);
				dialog.setPositiveButton("ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent pie = new PieChart()
								.getIntent(SaleAnalysis.this);
								startActivity(pie);
							}
						});
				dialog.setNegativeButton("cancle", null);
				dialog.create().show();
			}
		});
		// 柱状图描绘
		click.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mod2.setVisibility(View.VISIBLE);
				mod3.setVisibility(View.VISIBLE);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(SaleAnalysis.this,
						android.R.layout.select_dialog_item, MyApplication.models);
				mod2.setAdapter(adapter);
				mod3.setAdapter(adapter);
				mod2.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						salemodel2 = parent.getItemAtPosition(position).toString();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
				});
				mod3.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						salemodel3 = parent.getItemAtPosition(position).toString();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		bar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String model2=salemodel2;
				String model3=salemodel3;
				final String[] titles={model2,model3};
				JSONObject js1=new JSONObject();
				JSONObject js2=new JSONObject();
				try {
					js1.put("type", SetTy_MoforSpinner.type);
					js1.put("model", model2);
					js2.put("type", SetTy_MoforSpinner.type);
					js2.put("model", model3);
					//调用两次异步类，分别加载要比较的两个型号
					bctask=new BarChartAsyncTask(SaleAnalysis.this, js1, model2);
					bctask.execute(MyApplication.setUrl("salechart"));
					bctask2=new BarChartAsyncTask2(SaleAnalysis.this, js2, model3);
					bctask2.execute(MyApplication.setUrl("salechart"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						SaleAnalysis.this);
				dialog.setTitle("Sales query");
				dialog.setMessage("Type:" + SetTy_MoforSpinner.type + "\n" + "Model："
						+ model2+" VS "+model3);
				dialog.setPositiveButton("ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent pie = new BarChart(titles)
								.getIntent(SaleAnalysis.this);
								startActivity(pie);
							}
						});
				dialog.setNegativeButton("cancle", null);
				dialog.create().show();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		ty = (Spinner) findViewById(R.id.stype);
		mod = (Spinner) findViewById(R.id.smodel);
		mod2 = (Spinner) findViewById(R.id.smodel2);
		mod3 = (Spinner) findViewById(R.id.smodel3);
		mod2.setVisibility(View.INVISIBLE);
		mod3.setVisibility(View.INVISIBLE);
		create = (Button) findViewById(R.id.chart);
		pie=(Button)findViewById(R.id.piechart);
		df = new DialogForMe(SaleAnalysis.this);
		bar=(Button)findViewById(R.id.barchart);
		click=(TextView)findViewById(R.id.click);
		stms = new SetTy_MoforSpinner(SaleAnalysis.this, ty, mod);
//		stms2 = new SetTy_MoforSpinner2(SaleAnalysis.this, ty, mod2);		
		
	}
}