package com.swpu.bms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ID {

	/**
	 * id�е�����
	 * @return
	 */
	String name();
	
	/**
	 * ��ʾ�����Ƿ�Ϊ����
	 * @return
	 */
	boolean isAutoIncrement() default false;
	
}
