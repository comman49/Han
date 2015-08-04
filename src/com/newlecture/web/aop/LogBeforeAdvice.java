package com.newlecture.web.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice{
	
	public void before(Method method, Object[] args, Object target)
			throws Throwable{
		
		System.out.println("[newlec] : " + method.getName() + " BEFORE");
		
		/*Log log = LogFactory.getLog(this.getClass());
		
		log.info("[newlec] : " + method.getName() + " BEFORE");*/
		
	}
	
}
