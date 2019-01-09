package com.imooc.miasma.result;

public class CodeMsg {
	private int code;
	private String msg;
	
	public static  CodeMsg SUCCESS=new CodeMsg(0,"success"); 
	public static CodeMsg SERVER_ERROR=new CodeMsg(500100,"服务端异常");
	
	public static CodeMsg BIND_ERROR=new CodeMsg(500101,"参数校验异常 : %s");
	
	   //登陆模块5002XX
	public static CodeMsg PASSWORK_EMPTY=new CodeMsg(500210,"密码不能为空");
	
	public static CodeMsg MOBILE_EMPTY=new CodeMsg(500212,"手机号 不能为空");
	
	public static CodeMsg MOBILE_ERROR=new CodeMsg(500213,"手机号码格式错误");
	
	public static CodeMsg MOBILE_NOT_EXIST=new CodeMsg(500214,"手机号码不存在");
	
	public static CodeMsg PASSWORD_ERROR=new CodeMsg(500215,"密码错误");
	
	//商品模块5003XX
	
	//秒杀模块5005x
	
	public static CodeMsg MIAOSHA_OVER=new CodeMsg(500500, "商品已经秒杀完毕");
	
	public static CodeMsg MIASHA_REPEAT=new CodeMsg(500501, "不能重复秒杀");
	
	private  CodeMsg(int code, String msg) {
		this.code=code;
		this.msg=msg;
	}

	public int getCode() {
		return code;
	}

	
	public CodeMsg fillArgs(Object...args){
		int code=this.code;
		String message=String.format(this.msg, args);
		return new CodeMsg(code, message);
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	

}
