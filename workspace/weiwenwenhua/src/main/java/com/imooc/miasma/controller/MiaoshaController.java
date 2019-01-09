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
import com.imooc.miasma.domain.MiaoshaOrder;
import com.imooc.miasma.domain.MiaoshaUser;
import com.imooc.miasma.domain.OrderInfo;
import com.imooc.miasma.domain.User;
import com.imooc.miasma.redis.RedisService;
import com.imooc.miasma.redis.UserKey;
import com.imooc.miasma.result.CodeMsg;
import com.imooc.miasma.result.Result;
import com.imooc.miasma.service.GoodsService;
import com.imooc.miasma.service.MiaoshaService;
import com.imooc.miasma.service.MiaoshaUserServic;
import com.imooc.miasma.service.OrderService;
import com.imooc.miasma.service.UserService;
import com.imooc.miasma.util.ValidatorUtil;
import com.imooc.miasma.vo.GoodsVo;
import com.imooc.miasma.vo.LoginVo;

@Controller
@RequestMapping(value="/miaosha")
public class MiaoshaController {
	
	@Autowired
	UserService userService;
	@Autowired
	MiaoshaUserServic miaoshaUserServic;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
    MiaoshaService miaoshaService;
	
	
	@RequestMapping("/do_miaosha")
	public String doMiaosha(Model model,MiaoshaUser user,@Param("goodsId")Long goodsId ){
		model.addAttribute("user",user);
		
		if(user==null){
			return "login";
		}
		
		//判断库存
		
		GoodsVo goodsVo=goodsService.getByGoodsId(goodsId);
		int stock=goodsVo.getStockCount();
		
		if(stock<=0){
			model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER.getMsg());
			return "miaosha_fail";
		}
		
		// 判断是否已经秒杀到了
		MiaoshaOrder mishaoOrder=orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
		if(mishaoOrder!=null){
			model.addAttribute("errmsg", CodeMsg.MIASHA_REPEAT.getMsg());
			return "miaosha_fail";
		}
		//减库存  下订单 写入秒杀订单
		OrderInfo orderInfo=miaoshaService.miaoshao(user,goodsVo);
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("goods", goodsVo);
		
		return "order_detail";
	
	}
	

	
	

}
