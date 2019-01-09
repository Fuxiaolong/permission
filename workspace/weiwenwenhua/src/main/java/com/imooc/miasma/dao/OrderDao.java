package com.imooc.miasma.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.imooc.miasma.domain.MiaoshaOrder;
import com.imooc.miasma.domain.OrderInfo;

@Mapper
public interface OrderDao {

	@Select("SELECT * FROM miaosha_order WHERE user_id=#{userId} AND goods_id=#{goodsId}")
	MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

	@Insert("INSERT INTO order_info(user_id,goods_id,goods_name,goods_count,goods_price,order_channel,order_status,create_date) VALUES"
			+ "(#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
	@SelectKey(keyColumn="id",keyProperty="id",resultType=long.class,before=false,statement="SELECT LAST_INSERT_ID()")
	long insert(OrderInfo orderInfo);

	@Insert("INSERT INTO miaosha_order(user_id,goods_id,order_id) VALUES(#{userId},#{goodsId},#{orderId})")
	public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

}
