package com.hisense.tools;

import android.location.Location;
import android.os.Bundle;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;


public class GetLocation  implements AMapLocationListener {

	
	private LocationManagerProxy mLocationManagerProxy;
	private String city,zone,loc;
	double lat,lon;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public  GetLocation()
	{
//		mLocationManagerProxy=LocationManagerProxy.getInstance(ActivityForOrdinary.getAppContext());
		mLocationManagerProxy=LocationManagerProxy.getInstance(MyApplication.getAppContext());
		mLocationManagerProxy.setGpsEnable(false);
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);
	}
	public String getloc()
	{
		return getLon()+"&"+getLat();
		
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		// TODO Auto-generated method stub
		if (amapLocation != null
				&& amapLocation.getAMapException().getErrorCode() == 0) {
			// 定位成功回调信息，设置相关消息
			lat=amapLocation.getLatitude();
			lon=amapLocation.getLongitude();
			zone=amapLocation.getAddress();
			city=amapLocation.getCity()+amapLocation.getDistrict();
			System.out.println("定位信息为："+lat+lon+city+zone);
			setCity(city);
			setLat(lat);
			setLon(lon);
			setZone(zone);;
			System.out.println("错误为："+amapLocation.getAMapException().getErrorCode());
		}
	}

}