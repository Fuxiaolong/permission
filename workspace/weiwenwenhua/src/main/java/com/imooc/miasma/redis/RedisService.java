package com.imooc.miasma.redis;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.imooc.miasma.result.Result;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {
	@Autowired
	JedisPool jp;
	
	
	public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
		Jedis jedis=null;
		
		try{
			 jedis=jp.getResource();
			 //生成真正的Key
			String realKey=prefix.getPrefix()+key;
			 String str=jedis.get(realKey);
			 
			 T t=stringToBean(str,clazz );
			 return t;
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private <T> T stringToBean(String str,Class<T> clazz) {
		if(str==null||str.length()<=0||clazz==null){
			return null;
		}
		if(clazz==int.class || clazz==Integer.class){
			return (T)Integer.valueOf(str);
		}else if(clazz==String.class){
			return (T)str;
		}else if(clazz==long.class||clazz==Long.class){
			return (T)Long.valueOf(str);
		}else{
			return JSON.toJavaObject(JSON.parseObject(str),clazz);
		}
		
	}
	
	public <T> boolean set(KeyPrefix prefix,String key,T value){
		Jedis jedis=null;
		
		try{
			 jedis=jp.getResource();
			 
			 String str=beanToString(value);
			 if(str==null || str.length()<=0){
				 return false;
			 }
			 String realKey=prefix.getPrefix()+key;
			 int seconds=prefix.expireSeconds();
			 
			 if(seconds<=0){
				 jedis.set(realKey, str);
			 }else{
				 jedis.setex(realKey, seconds, str);
			 }
			 
			 return true;
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
		
	}
	
	//判断key是否存在
	public <T> boolean exists(KeyPrefix prefix,String key){
		Jedis jedis=null;
		
		try{
			 jedis=jp.getResource();
			 
			 
			 String realKey=prefix.getPrefix()+key;
			 return jedis.exists(realKey);
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
		
	}
	
	
	public <T> Long incr(KeyPrefix prefix,String key){
		Jedis jedis=null;
		
		try{
			 jedis=jp.getResource();
			 
			 
			 String realKey=prefix.getPrefix()+key;
			 return jedis.incr(realKey);
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
		
	}
	
	
	public <T> Long decr(KeyPrefix prefix,String key){
		Jedis jedis=null;
		
		try{
			 jedis=jp.getResource();
			 
			 
			 String realKey=prefix.getPrefix()+key;
			 return jedis.decr(realKey);
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
		
	}

	

	private <T> String beanToString(T value) {
		if(value==null){
			return null;
		}
		Class<?> clazz=value.getClass();
		if(clazz==int.class || clazz==Integer.class){
			return ""+value;
		}else if(clazz==String.class){
			return (String)value;
		}else if(clazz==long.class||clazz==Long.class){
			return ""+value;
		}
		return JSON.toJSONString(value);
	}


	

}
