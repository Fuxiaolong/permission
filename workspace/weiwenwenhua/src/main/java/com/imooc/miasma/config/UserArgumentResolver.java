package com.imooc.miasma.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.imooc.miasma.domain.MiaoshaUser;
import com.imooc.miasma.service.MiaoshaUserServic;
import com.imooc.miasma.service.UserService;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
	@Autowired
	MiaoshaUserServic miaoshaUserServic;

	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		HttpServletRequest request=arg2.getNativeRequest(HttpServletRequest.class);
	    HttpServletResponse response=arg2.getNativeResponse(HttpServletResponse.class);
	    
	    
	    String paramToken=request.getParameter(MiaoshaUserServic.COOKIE_NAME_TOKEN);
	    String cookieToken=getCookieValue(request,MiaoshaUserServic.COOKIE_NAME_TOKEN);
	    
	    
	    if(StringUtils.isEmpty(paramToken)&&StringUtils.isEmpty(cookieToken)){
				return null;
			}
			String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
			MiaoshaUser user=miaoshaUserServic.getByToken(response, token);
		return miaoshaUserServic.getByToken(response, token);
	}
	
	

	private String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies=request.getCookies();
		if(cookies==null||cookies.length<=0){
			return null;
		}
		for(Cookie cookie:cookies){
			if(cookie.getName().equals(cookieName)){
				return cookie.getValue(); 
				
			}
			
		}
		return null;
	}



	public boolean supportsParameter(MethodParameter arg0) {
		Class<?> clazz=arg0.getParameterType();
		return clazz==MiaoshaUser.class;
	}

}
