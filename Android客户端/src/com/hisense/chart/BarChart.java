package com.hisense.chart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.hisense.asynctaskclasses.BarChartAsyncTask;
import com.hisense.asynctaskclasses.BarChartAsyncTask2;

public class BarChart implements AChartAbstract {

	Context context;
	String[] titles = new String[] { "A1", "A2" };
	String[] xLable = { "6", "6", "6", "6", "6", "6", "6", "6", "6", "6", "6",
			"6" };
	ArrayList<int[]> value = new ArrayList<int[]>();


	public BarChart(String[] titles) {
		super();
		this.titles = titles;
	}

	public BarChart(Context context, String[] titles, int[] a1, int[] a2) {
		super();
		this.context = context;
		this.titles = titles;
	}

	@Override
	public Intent getIntent(Context contect) {
		// TODO Auto-generated method stub
		Intent cintent = ChartFactory.getBarChartIntent(contect, getDataset(),
				getBarDemoRenderer(), Type.DEFAULT, "Sales comparison ");
		return cintent;
	}

	private XYMultipleSeriesDataset getDataset() {
		// TODO Auto-generated method stub
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		for (int i = 0; i < 12; i++) {
			System.out.println("models2 " + i + " :"
					+ BarChartAsyncTask.models2[i]);
			System.out.println("model3 " + i + " :"
					+ BarChartAsyncTask2.model3[i]);
		}
		value.add(BarChartAsyncTask.models2);
		 value.add(BarChartAsyncTask2.model3);
		for (int i = 0; i < 2; i++) {
			CategorySeries series = new CategorySeries(titles[i]);
			int[] yLable = value.get(i);
			for (int j = 0; j < 12; j++) {
				series.add(xLable[j], yLable[j]);
				System.out.println("&&&&&&&" + xLable[j]);
				System.out.println("*******" + yLable[j]);
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	public XYMultipleSeriesRenderer getBarDemoRenderer() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);
		r = new SimpleSeriesRenderer();
		r.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r);
		r = new SimpleSeriesRenderer();
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.GRAY);
		renderer.setMarginsColor(Color.BLACK);
		renderer.setZoomEnabled(false, false);
		renderer.setPanEnabled(false, false);
		renderer.setLabelsTextSize(24);
		renderer.setLabelsColor(Color.CYAN);
		setChartSettings(renderer);
		return renderer;
	}

	private void setChartSettings(XYMultipleSeriesRenderer renderer) {
		renderer.setChartTitle("Sales comparison analysis");
		renderer.setDisplayChartValues(true);
		renderer.setAxisTitleTextSize(32);
		renderer.setAxesColor(Color.BLUE);
		renderer.setLabelsColor(Color.RED);
		renderer.setLegendTextSize(40);
		renderer.setChartTitleTextSize(40);
		renderer.setMargins(new int[] { 50, 60, 40, 25 });
		renderer.setXTitle("Monthly");
		renderer.setYTitle("sales");
		renderer.setXAxisMin(0.5);
		renderer.setXAxisMax(12.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(100);
		renderer.setXLabels(12);
		renderer.setYLabels(20);
		renderer.setLegendHeight(40);
	}

}
