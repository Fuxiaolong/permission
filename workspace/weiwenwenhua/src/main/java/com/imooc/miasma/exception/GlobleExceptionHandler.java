 package com.imooc.miasma.exception;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miasma.result.CodeMsg;
import com.imooc.miasma.result.Result;

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {
	
	
	@ExceptionHandler(value=Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
		e.printStackTrace();
		if(e instanceof GlobleException){
			 GlobleException ge=(GlobleException)e;
			 CodeMsg cm=ge.getCm();
			 return Result.error(cm);
			 
		}
		
		
		if(e instanceof BindException){
			BindException ex=(BindException)e;
			List<ObjectError> errors=ex.getAllErrors(); 
			
			ObjectError error=errors.get(0);
			String msg=error.getDefaultMessage();
			
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		}else{
			
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
}
