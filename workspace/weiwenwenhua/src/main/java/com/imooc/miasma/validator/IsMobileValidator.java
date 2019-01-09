package com.imooc.miasma.validator;
import javax.validation.*;

import org.apache.commons.lang3.StringUtils;
//import org.mockito.internal.util.StringUtil;

import com.imooc.miasma.util.ValidatorUtil;


public class IsMobileValidator implements ConstraintValidator<IsMobile,String>{
	private boolean require=false ;

	@Override
	public void initialize(IsMobile constraintAnnotation) {
		// TODO Auto-generated method stub
	
		require=constraintAnnotation.required();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(require){
			return ValidatorUtil.isMobile(value);
			
		}else{
			if(StringUtils.isEmpty(value)){
				return true;
			}else{
				return ValidatorUtil.isMobile(value);
			}
			
		}
	}

}
