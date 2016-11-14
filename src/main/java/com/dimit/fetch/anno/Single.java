package com.dimit.fetch.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段描述注解
 * 
 * @author dimit
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FiledDec {
	/** 行*/
	int row() default 1;
	
	/** 列*/
	int col() default 1;
	
	/** 行模式*/
	boolean rowModel() default true;
	
	/** 行头内容*/
	String rowHead() default "";
}
