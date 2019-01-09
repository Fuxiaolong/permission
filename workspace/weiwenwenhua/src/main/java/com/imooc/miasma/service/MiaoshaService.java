package com.imooc.miasma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.miasma.domain.MiaoshaUser;
import com.imooc.miasma.domain.OrderInfo;
import com.imooc.miasma.vo.GoodsVo;

@Service
public class MiaoshaService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Transactional
	public OrderInfo miaoshao(MiaoshaUser user, GoodsVo goodsVo) {
		//减库存  下订单 写入秒杀订单
		
		
		goodsService.reduceStock(goodsVo);
		
		//order_info miaosha_order
		OrderInfo orderInfo=orderService.createOrder(user,goodsVo);
		
		
		return orderInfo;
	}

}
