package com.hisense.chart;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import com.hisense.asynctaskclasses.PieChartAsyncTask;
import com.hisense.myadapters.SetTy_MoforSpinner;

public class PieChart implements AChartAbstract {

	double[] values = { 412, 542, 486, 900 };
	int[] colors = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED ,Color.BLACK,Color.WHITE,Color.BLUE,Color.GREEN,
			Color.YELLOW,Color.RED ,Color.BLACK,Color.RED ,Color.WHITE,Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED ,Color.BLACK,Color.WHITE};
	List<integer> list=new ArrayList<integer>();
	int size;
	
	public PieChart() {
		super();
	}
	

	@Override
	public Intent getIntent(Context contect) {
		// TODO Auto-generated method stub
		Intent pIntent = ChartFactory.getPieChartIntent(contect, getDataset(),
				getRender(), "Sales comparison ");
		System.out.println("<<<<<<<"+getRender().toString()+">>>>"+getDataset().toString());
		return pIntent;
	}

	private CategorySeries getDataset() {
		// TODO Auto-generated method stub
		CategorySeries series = new CategorySeries("Sales comparison ");
		for (int i = 0; i < PieChartAsyncTask.models.length; i++) {
			series.add(PieChartAsyncTask.models[i], PieChartAsyncTask.numbers[i]);
		}
		return series;
	}

	private DefaultRenderer getRender() {
		// TODO Auto-generated method stub
		size=PieChartAsyncTask.models.length;
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLegendTextSize(42);// 设置左下角表注的文字大小
		renderer.setBackgroundColor(Color.GRAY);
		renderer.setZoomEnabled(false);// 设置不允许放大缩小.
		renderer.setChartTitleTextSize(40);// 设置图表标题的文字大小
		renderer.setChartTitle("This Month"+SetTy_MoforSpinner.type+"Sales comparison ");// 设置图表的标题 默认是居中顶部显示
		renderer.setLabelsTextSize(40);// 饼图上标记文字的字体大小
		 renderer.setLabelsColor(Color.BLACK);//饼图上标记文字的颜色
		renderer.setPanEnabled(false);// 设置是否可以平移
		 renderer.setDisplayValues(true);//是否显示值
		renderer.setClickEnabled(true);// 设置是否可以被点击
		renderer.setMargins(new int[] { 20, 30, 15, 0 });
		// top, left, bottom, right
		for(int i=0;i<size;i++)
		{
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>size:"+size);
		return renderer;
	}

}
