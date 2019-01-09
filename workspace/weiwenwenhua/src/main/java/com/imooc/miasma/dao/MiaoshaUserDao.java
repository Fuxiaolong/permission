package com.imooc.miasma.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.imooc.miasma.domain.MiaoshaUser;

@Mapper
public interface MiaoshaUserDao {
	@Select("SELECT * FROM misha_user WHERE id=#{id}")
	public MiaoshaUser getById(@Param("id")long id);
}
