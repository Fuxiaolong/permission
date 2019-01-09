package com.imooc.miasma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.miasma.domain.Goods;
import com.imooc.miasma.domain.MiaoshaGoods;
import com.imooc.miasma.vo.GoodsVo;

@Mapper
public interface GoodsDao {

	@Select("SELECT g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaoshao_price FROM miaosha_goods mg LEFT JOIN goods g ON mg.goods_id=g.id")
	public List<GoodsVo> listGoodsVo ();

	@Select("SELECT g.*,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price FROM miaosha_goods mg LEFT JOIN goods g ON mg.goods_id=g.id WHERE g.id=#{goodsId}")
	public GoodsVo findByGoodsId(@Param("goodsId")long goodsId);

	@Update("UPDATE miaosha_goods SET stock_count=stock_count-1 WHERE goods_id=#{goodsId}")
	public int reduceStock(MiaoshaGoods mg);
}
