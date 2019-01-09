package com.imooc.miasma.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	public static String md5(String src){
		return DigestUtils.md5Hex(src);
	} 
	
	private static  final String  salt="1a2b3c4d";
	
	public static  String inputPassFormPass(String  inputPass){
		String str=""+salt.charAt(0)+salt.charAt(2)+salt.charAt(5)+salt.charAt(4);
		
		return md5(str);
	}
	
	public static  String formPasswordToDBPassword(String  formPass,String salt){
		String str=""+salt.charAt(0)+salt.charAt(2)+salt.charAt(5)+salt.charAt(4);
		
		return md5(str);
	}
	
	public static String inputPassToDbPass(String input,String saltDB){
		
		String formPass=inputPassFormPass( input);
		String dbPass=formPasswordToDBPassword(formPass,saltDB);
		return dbPass;
	}
	
	public static void main(String[] args) {
		System.out.println(inputPassFormPass("123456"));
		//System.out.println(inputPassFormPass("123456"));
	}
}
