package com.imooc.miasma.exception;

import com.imooc.miasma.result.CodeMsg;

public class GlobleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CodeMsg cm;
	
	public GlobleException(CodeMsg cm){
		this.cm=cm;
	}

	public CodeMsg getCm() {
		return cm;
	}

	public void setCm(CodeMsg cm) {
		this.cm = cm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
