package com.imooc.miasma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.miasma.dao.UserMapper;
import com.imooc.miasma.domain.User;

@Service
public class UserService {
	@Autowired
	UserMapper usermapper;
	public User getUserById(int id){
		return usermapper.findUserById(id);
		
	}
	public   Boolean inserUser(){
		User user2=new User();
		user2.setId(5);
		user2.setUsename("longxiaofu");
		int count=usermapper.insertUser(user2);
		
		User user1=new User();
		user1.setId(1);
		user1.setUsename("ooooooo");
		int c=usermapper.insertUser(user1);
		return true;
	}
	

}
