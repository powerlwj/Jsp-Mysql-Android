package com.hisense.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.Toast;

public class NumORChars {

	Context context;
	int res;
	boolean is;
	public int judge(String text) {
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(text);
		if (m.matches()) {
			res = 0;
//			Toast.makeText(context, "输入的是数字", Toast.LENGTH_SHORT).show();
		}
		p = Pattern.compile("[a-zA-Z]");
		m = p.matcher(text);
		if (m.matches()) {
			res = 1;
//			Toast.makeText(context, "输入的是字母", Toast.LENGTH_SHORT).show();
		}
		p = Pattern.compile("[\u4e00-\u9fa5]");
		m = p.matcher(text);
		if (m.matches()) {
			res = 2;
//			Toast.makeText(context, "输入的是汉字", Toast.LENGTH_SHORT).show();
		}
		return res;
	}
	
	public boolean judgenull(String text)
	{
		if(text.equalsIgnoreCase(""))
		{
			is=true;
		}else
		{
			is=false;
		}
		System.out.println("isisisisisi"+is);
		return is;
		
	}
}
