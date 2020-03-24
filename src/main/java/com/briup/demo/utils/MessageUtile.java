package com.briup.demo.utils;

import java.util.Date;

public class MessageUtile {
	/*
	 * 成功，并且返回数据
	 */
	public static <E>Message<E> success(E obj){
		return new Message<E>(200,"success",obj,new Date().getTime());
		
	}

	/*
	 * 成功，无并且返回数据
	 */
	public static <E>Message<E> success(){
		return new Message<E>(200,"success",null,new Date().getTime());
		
	}
	
	/*
	 * 失败,将自定义异常返回
	 */
	public static <E>Message<E> error(Integer code,String msq){
		return new Message<E>(code, msq, null, new Date().getTime());
	
	}
}