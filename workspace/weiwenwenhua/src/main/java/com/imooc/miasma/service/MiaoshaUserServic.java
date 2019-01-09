package com.imooc.miasma.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.miasma.dao.MiaoshaUserDao;
import com.imooc.miasma.domain.MiaoshaUser;
import com.imooc.miasma.exception.GlobleException;
import com.imooc.miasma.redis.MiaoshaUserKey;
import com.imooc.miasma.redis.RedisService;
import com.imooc.miasma.result.CodeMsg;
import com.imooc.miasma.util.MD5Util;
import com.imooc.miasma.util.UUIDUtil;
import com.imooc.miasma.vo.LoginVo;

@Service
public class MiaoshaUserServic {
	public static final String COOKIE_NAME_TOKEN="token";
	@Autowired
	private MiaoshaUserDao miaoshaUserDao;
	
	@Autowired
	private RedisService redisService;
	
	public MiaoshaUser getById(long id){
		return miaoshaUserDao.getById(id);
	}

	public boolean login(HttpServletResponse response,LoginVo loginVo) {
		if(loginVo==null){
			//return CodeMsg.SERVER_ERROR;
			throw new  GlobleException(CodeMsg.BIND_ERROR );
		}
		
		String mobile=loginVo.getMobile();
		String password=loginVo.getPassword();
		//判断手机号是否存在
		MiaoshaUser user=getById(Long.parseLong(mobile));
		if(user==null){
			//return CodeMsg.MOBILE_NOT_EXIST;
			throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
		}
		
		// 验证密码
		
		String dbPass=user.getPassword();
		String dbsalt=user.getSalt();
		 String calcPass=MD5Util.formPasswordToDBPassword(dbPass, dbsalt);
		 if(!dbPass.equals(calcPass)){
			//return CodeMsg.PASSWORD_ERROR ;
			 throw new GlobleException(CodeMsg.PASSWORD_ERROR);
			 
		 }
		//return CodeMsg.SUCCESS;
		 
		 String token=UUIDUtil.uuid();
		 addCookie(response,token, user);
		return true;
		
	}
	private void addCookie(HttpServletResponse response,String token,MiaoshaUser user){
		
		 redisService.set(MiaoshaUserKey.token, token, user);
		 Cookie cookie=new Cookie(COOKIE_NAME_TOKEN, token);
		 cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
		 cookie.setPath("/");  
		 response.addCookie(cookie);
	}

	public MiaoshaUser getByToken(HttpServletResponse response,String token) {
		if(StringUtils.isEmpty(token)){
			return null;
		}
		MiaoshaUser user=redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class); 
		if(user !=null){
			addCookie(response,token, user);
		}
		
		return user;
	}
}
