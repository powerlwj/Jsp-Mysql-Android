package com.hisense.chart;

import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.hisense.asynctaskclasses.ChartAsyncTask;
import com.hisense.asynctaskclasses.MyAsyncTask;


public class SaleChart implements AChartAbstract{
	
	String model;
	Map<Integer, Integer> datamap;
	
	public SaleChart() {
		super();
	}

	public SaleChart(String model, Map<Integer, Integer> datamap) {
		super();
		this.model = model;
		this.datamap = datamap;
	}

	@Override
	public Intent getIntent(Context contect) {
		// TODO Auto-generated method stub
		Intent mIntent=ChartFactory.getLineChartIntent(contect, getDataset(), getRender(),"销售情况");
		return mIntent;
	}

	private XYMultipleSeriesDataset getDataset() {
		// TODO Auto-generated method stub
		XYMultipleSeriesDataset xyDataset=new XYMultipleSeriesDataset();
		XYSeries s1=new XYSeries("Sales trend Chart");
		for (int i = 1; i < 13; i++) {
			if(ChartAsyncTask.map.get(i)==null)
			{
				s1.add(i, 0);
			}else
			{
				s1.add(i, ChartAsyncTask.map.get(i));
			}
			System.out.println("------"+ChartAsyncTask.map.get(i));
		}
		
		xyDataset.addSeries(s1);
		return xyDataset;
	}
	
	private XYMultipleSeriesRenderer getRender() {
		// TODO Auto-generated method stub
		XYMultipleSeriesRenderer renderer=new XYMultipleSeriesRenderer();
		renderer.setXTitle(MyAsyncTask.salemodel);  
        renderer.setYTitle("单位／个"); 
        renderer.setAxesColor(Color.BLUE);  
        renderer.setLabelsColor(Color.RED);  
        renderer.setXAxisMin(0.5);  
        renderer.setXAxisMax(12.5);  
        renderer.setYAxisMin(0);  
        renderer.setYAxisMax(100);  
        renderer.setPanEnabled(false, false);
        renderer.setLabelsTextSize(24);
        renderer.setAxisTitleTextSize(30);
        renderer.setZoomButtonsVisible(true);  
        renderer.setZoomEnabled(false);  
        renderer.setAntialiasing(true);  
        renderer.setApplyBackgroundColor(true);  
        renderer.setBackgroundColor(Color.BLACK); 
        renderer.setXLabels(12);
        renderer.setYLabels(20);
        renderer.setShowGrid(true);
    	renderer.setLegendHeight(40);
    	renderer.setMargins(new int[] {20,40,40,10});//上，左，下，右
        renderer.setMarginsColor(Color.argb(0, 0XF3, 0XF3, 0XF3));
        XYSeriesRenderer sr1=new XYSeriesRenderer();
    	sr1.setColor(Color.GREEN);
    	sr1.setPointStyle(PointStyle.CIRCLE);
    	sr1.setChartValuesTextSize(28);
    	sr1.setChartValuesSpacing(10);
    	sr1.setDisplayChartValues(true);
    	sr1.setLineWidth(3);
    	sr1.setFillPoints(true);
    	
    	renderer.addSeriesRenderer(sr1);
        
		return renderer;
	}

}
