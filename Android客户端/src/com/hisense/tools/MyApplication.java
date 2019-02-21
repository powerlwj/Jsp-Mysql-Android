package com.hisense.tools;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	
	//当用户登陆成功后使用application类记住userid&&location用来更新首页信息
	public static String userid="Hisense";
	public static String location="laoshan1";
	//logstate用来记住用户的登录状态，true为登录，false为下线
	public static boolean logstate;
	//标记早上是否签到
	public static boolean Ismorning_check=false;
	//标记晚上是否签到
		public static boolean Isevenning_check=false;
	//特殊任务提醒
	public static boolean Is_special;
	//用户负责的产品Brand
	public static String own="";
	//用户类型：普通用户＆＆二级经理
	public static String ad_type;
	public static String password;
	// 用来获取全局的context
	public static Application mcontext;
	//月任务标记符
	public static int MONTH_INDEX=1;
	//特殊任务标记符
	public static int SPECIAL_INDEX=1;
	//特殊任务的起始时间
	public static String targetTime,targetTime2;
	//保存商品类型
	public static String[] models;
	//当前所选员工账号
	public static String MYSTUFF;
	//同来保存商品类型以及对应的型号的map
	public static Map<String, String[]> map=new HashMap<String,String[]>();
	//http://211.87.147.214:8080/Jumboo/addGoods.js
	//请求方式以及部署ip端口号工程名
	public static String ipaddress="211.87.147.164";
	public static String URL="http://"+ipaddress+":8080/hs/app/";
	// 用到的URL
	public static String add=URL+"addGoods.jsp";
	public static String login=URL+"userlogin.jsp";
//	public static String login=URL+"login.jsp";
	public static String changepass=URL+"changepassword.jsp";
	public static String viewcompinfo=URL+"querycompetionGoods.jsp";
	public static String attence=URL+"attence.jsp";
	public static String viewmessage=URL+"viewmessage.jsp";
	public static String monthTarget=URL+"monthTarget.jsp";
	public static String goodsType=URL+"goodsType.jsp";
	public static String salechart=URL+"salechart.jsp";
	public static String model=URL+"model.jsp";
	public static String model2=URL+"model2.jsp";
	public static String feedback=URL+"feedback.jsp";
	public static String specialPlan=URL+"specialPlan.jsp";
	public static String viewmissionprocess=URL+"viewmissionprocess.jsp";
	public static String addcompetionGoods=URL+"addcompetionGoods.jsp";
	public static String addStock=URL+"addStock.jsp";
	public static String cbrandmodel=URL+"cbrandmodel.jsp";
	public static String saleGoods=URL+"saleGoods.jsp";
	public static String saleReturn=URL+"saleReturn.jsp";
	public static String goodsType2=URL+"goodsType2.jsp";
	public static String stuff=URL+"stuff.jsp";
	public static String personalsalehistory=URL+"personalsalehistory.jsp";
	public static String missionalocate=URL+"missionalocate.jsp";
	public static String specialmissionalocate=URL+"specialmissionalocate.jsp";
	public static String piechart=URL+"pieChart.jsp";
	public static String viewmysales=URL+"viewmysales.jsp";
	public static String viewmystock=URL+"viewmystock.jsp";
	public static String addmessage=URL+"addmessage.jsp";
	public static String viewallocatedmissions=URL+"viewallocatedmissions.jsp";
	public static String returnsales=URL+"returnsales.jsp";
	public static String specialplanalert=URL+"specialplanalert.jsp";
	public static String updatesoldgoods=URL+"updatesoldgoods.jsp";
	public static String delsoldgoods=URL+"delsoldgoods.jsp";
	public static String sumofsales=URL+"sumofsales.jsp";
	public static String stokchgdetil=URL+"stokchgdetil.jsp";
	public static String updatestockdetail=URL+"updatestockdetail.jsp";
	public static String monthmissiondel=URL+"monthmissiondel.jsp";
	public static String specialmissiondel=URL+"specialmissiondel.jsp";
	public static String updatemonthmission=URL+"updatemonthmission.jsp";
	public static String updatespecialmission=URL+"updatespecialmission.jsp";
	public static String process_ms=URL+"process_ms.jsp";
	public static String process_special=URL+"process_special.jsp";
	public static String ischecked=URL+"ischecked.jsp";
	
	//常用的提示用语
	public static String unknown="sorry there is an error,try again";
	public static String ok="successfully";
	public static String no="Failure";
	public static String serverwrong="Server Wrong";
	public static String totalinfo="please input the information completely";
	public static String loading="loading successfully";
	
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		MyApplication.userid = userid;
	}

	
	public static String getAd_type() {
		return ad_type;
	}

	public static void setAd_type(String ad_type) {
		MyApplication.ad_type = ad_type;
	}

	public static boolean isLogstate() {
		return logstate;
	}

	public static void setLogstate(boolean logstate) {
		MyApplication.logstate = logstate;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		MyApplication.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		MyApplication.location = location;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mcontext=this;
	}
	
	public static Context getAppContext()
	{
		return mcontext;
	}
	public static String setUrl(String s)
	{
		System.out.println("URL<><><><>"+URL+s+".jsp");
		return URL+s+".jsp";
	}

}
