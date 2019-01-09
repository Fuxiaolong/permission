package com.imooc.miasma.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.imooc.miasma.domain.User;

@Mapper
public interface UserMapper {
	@Select("SELECT id,usename FROM user WHERE id=#{id}")
	public User findUserById(@Param("id")int id);
	
	@Insert("INSERT INTO user(id,usename) VALUES(#{id},#{usename})")
	public int insertUser(User user);
}
