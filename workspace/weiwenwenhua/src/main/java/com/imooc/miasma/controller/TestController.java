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
@RequestMapping(value="/logSuccess")
public class TestController {
	
	@Autowired
	UserService userService;
	@Autowired
	MiaoshaUserServic miaoshaUserServic;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
	
	
	@RequestMapping("/index")
	public String toLogin(Model model,
//			@CookieValue(value=MiaoshaUserServic.COOKIE_NAME_TOKEN,required=false)String cookieToken,
//			@RequestParam(value=MiaoshaUserServic.COOKIE_NAME_TOKEN,required=false)String paramToken
			MiaoshaUser user){
		model.addAttribute("user",user);
		
//		if(StringUtils.isEmpty(paramToken)&&StringUtils.isEmpty(cookieToken)){
//			return "login";
//		}
//		String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
//		MiaoshaUser user=miaoshaUserServic.getByToken(response, token);
		//model.addAttribute("user",user);
		
		List<GoodsVo> listGoodsVo=goodsService.listGoodsVo();
		model.addAttribute("listGoodsVo", listGoodsVo); 
		return "index";
	}
	@RequestMapping("/to_detail/{goodsId}")
	public String detail(Model model,
			MiaoshaUser user,
			@PathVariable("goodsId")long goodsId){
		model.addAttribute("user",user); 
		//snowflake算法
		
		GoodsVo goods=goodsService.getByGoodsId( goodsId);
		
		model.addAttribute("goods",goods);
		long startAt=goods.getStartDate().getTime();
		long endAt= goods.getEndDate().getTime();
		long nowDate=System.currentTimeMillis();
		int miaoshaStatus=0;
		long remainSeconds=0;
		
		if(nowDate<startAt){//秒杀未开始
			  miaoshaStatus=0;
			  remainSeconds=(int)((startAt-nowDate)/1000);
			
		}else if(nowDate>endAt){//秒杀已经结束
			 miaoshaStatus=2;
			 
			 remainSeconds=-1;
			
		}else{//秒杀正在进行 
			 miaoshaStatus=1;
			 remainSeconds=0;
		}
		
		model.addAttribute("miaoshaStatus",miaoshaStatus);
		model.addAttribute("remainSeconds",remainSeconds);
		return "goods_detail";
	}
	

	
	

}
