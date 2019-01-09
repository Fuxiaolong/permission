package com.imooc.miasma.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.miasma.dao.OrderDao;
import com.imooc.miasma.domain.MiaoshaOrder;
import com.imooc.miasma.domain.MiaoshaUser;
import com.imooc.miasma.domain.OrderInfo;
import com.imooc.miasma.vo.GoodsVo;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;
	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
		// TODO Auto-generated method stub
		return orderDao.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);
	}
	
	@Transactional
	public OrderInfo createOrder(MiaoshaUser user, GoodsVo goodsVo) {
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goodsVo.getId());
		orderInfo.setGoodsName(goodsVo.getGoodsName());
		orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		long orderId=orderDao.insert(orderInfo);
		
		MiaoshaOrder miaoshaOrder=new MiaoshaOrder();
		miaoshaOrder.setGoodsId(goodsVo.getId());
		miaoshaOrder.setOrderId(orderId);
		miaoshaOrder.setUserId(user.getId());
		
		orderDao.insertMiaoshaOrder(miaoshaOrder);
		
		
		return orderInfo;
	}
	
	

}
