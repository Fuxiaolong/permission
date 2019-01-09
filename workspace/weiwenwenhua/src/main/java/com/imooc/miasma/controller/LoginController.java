package com.imooc.miasma.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miasma.domain.User;
import com.imooc.miasma.redis.RedisService;
import com.imooc.miasma.redis.UserKey;
import com.imooc.miasma.result.CodeMsg;
import com.imooc.miasma.result.Result;
import com.imooc.miasma.service.MiaoshaUserServic;
import com.imooc.miasma.service.UserService;
import com.imooc.miasma.util.ValidatorUtil;
import com.imooc.miasma.vo.LoginVo;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	
	private MiaoshaUserServic miaoshaUserServic;
	
	private static Logger log=LoggerFactory.getLogger(LoginController.class);
	
	
	
	@RequestMapping("/to_login")
	public String toLogin(){
		
		return "login";
	}
	
	

	@RequestMapping("/do_login")
	@ResponseBody
	public Result<Boolean> doLogin( HttpServletResponse response,@Valid LoginVo loginVo){
		log.info(loginVo.toString());
		/**
		String password=loginVo.getPassword();
		String mobile=loginVo.getPassword();
		
		if(StringUtils.isEmpty(password)){
			return Result.(CodeMsg.PASSWORK_EMPTY);
		}
		if(StringUtils.isEmpty(mobile)){
			return Result.error(CodeMsg.MOBILE_EMPTY);
		}
		if(ValidatorUtil.isMobile(mobile)){
			return Result.error(CodeMsg.MOBILE_ERROR);
		}**/
		
		//登陆
		
		boolean bool=miaoshaUserServic.login(response,loginVo);
//		if(cm.getCode()==0){
//			return Result.success(true);
//		}else{
		//	return Result.error(cm);
//		}
		
		return Result.success(true);
	}
	

}
