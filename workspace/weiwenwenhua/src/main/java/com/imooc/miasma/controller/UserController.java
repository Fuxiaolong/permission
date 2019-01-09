package com.imooc.miasma.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miasma.domain.Goods;
import com.imooc.miasma.domain.MiaoshaUser;
import com.imooc.miasma.domain.User;
import com.imooc.miasma.redis.RedisService;
import com.imooc.miasma.redis.UserKey;
import com.imooc.miasma.result.CodeMsg;
import com.imooc.miasma.result.Result;
import com.imooc.miasma.service.GoodsService;
import com.imooc.miasma.service.MiaoshaUserServic;
import com.imooc.miasma.service.UserService;
import com.imooc.miasma.util.ValidatorUtil;
import com.imooc.miasma.vo.GoodsVo;
import com.imooc.miasma.vo.LoginVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	MiaoshaUserServic miaoshaUserServic;
	
	@Autowired
	RedisService redisService;
	
	
	
	@RequestMapping("/info")
	@ResponseBody
	public Result<MiaoshaUser> info(Model model,MiaoshaUser user){
		return Result.success(user);
	}
	
		
	

	
	

}
