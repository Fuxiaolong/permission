package com.imooc.miasma.redis;

public interface KeyPrefix {
	public  int expireSeconds();
	
	public String getPrefix();
	

}
