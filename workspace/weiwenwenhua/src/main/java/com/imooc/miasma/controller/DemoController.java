package com.imooc.miasma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miasma.domain.User;
import com.imooc.miasma.redis.RedisService;
import com.imooc.miasma.redis.UserKey;
import com.imooc.miasma.result.CodeMsg;
import com.imooc.miasma.result.Result;
import com.imooc.miasma.service.UserService;

@Controller
public class DemoController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public Result<String> home(){
		
		return Result.success("hello SUCCESS!");
	}
	
	@RequestMapping("/helloError")
	@ResponseBody
	public Result<CodeMsg> helloError(){
		
		return Result.error(CodeMsg.SERVER_ERROR);
	}
	
	@RequestMapping("/templates")
	public String helloTemplates(Model model){
		String name="TEMPLETSBYF";
		model.addAttribute("name", name);
		
		return "hello";
	}
	
	@RequestMapping("/db/get")
	@ResponseBody
	public Result<User> dbGet(){
		
		User user=userService.getUserById(1);
		return Result.success(user);
	}

	
	@RequestMapping("/db/add")
	@ResponseBody
	public  Result<Boolean> dbAdd(){
		userService.inserUser();
		return Result.success(true);
	}
//rest api json输出 2页面输出
	
	//redis测试
	@RequestMapping("/redis/set")
	@ResponseBody
	public  Result<Boolean> redisSet(){
		//boolean va=redisService.set("key222","womeiyouliyou我要加油");
		User user=new User();
		user.setId(9);
		user.setUsename("99999999");
		
		boolean va=redisService.set(UserKey.getById,""+1,user);
		return Result.success(va);
	}
	
	@RequestMapping("/db/getU")
	@ResponseBody
	public  Result<User> getUser(){
		User user=redisService.get(UserKey.getById,""+1, User.class);
		return Result.success(user);
	}

}
