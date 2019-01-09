package com.imooc.miasma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.miasma.dao.GoodsDao;
import com.imooc.miasma.domain.Goods;
import com.imooc.miasma.domain.MiaoshaGoods;
import com.imooc.miasma.vo.GoodsVo;

@Service
public class GoodsService {
	@Autowired
	GoodsDao goodsDao;
	
	public List<GoodsVo> listGoodsVo(){
		return  goodsDao.listGoodsVo();
	}

	public GoodsVo getByGoodsId(long goodsId) {
		// TODO Auto-generated method stub
		return goodsDao.findByGoodsId(goodsId);
	}

	public void reduceStock(GoodsVo goodsVo) {
		MiaoshaGoods goods=new MiaoshaGoods();
		goods.setGoodsId(goodsVo.getId());
		goods.setStockCount(goodsVo.getGoodsStock());
		long id=goodsVo.getId();
		goodsDao.reduceStock(goods);
		
	}
	

}
