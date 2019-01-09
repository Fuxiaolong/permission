package com.imooc.miasma.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
//import org.mockito.internal.util.StringUtil;

public class ValidatorUtil {
	private static final Pattern mobile_pattern= Pattern.compile("1\\d{10}");
	
	public static boolean isMobile(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Matcher m=mobile_pattern.matcher(str);
		
		return m.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(isMobile("17826876656"));
	}
}
