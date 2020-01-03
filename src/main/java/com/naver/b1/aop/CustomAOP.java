package com.naver.b1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAOP {

	//@Before("execution(* com.naver.b1.member.MemberService.memberLogin(..))")
	public void before() throws Exception {
		System.out.println("before member login");
	}
	
	//@AfterReturning("execution(* com.naver.b1.member.MemberService.memberLogin(..))")
	public void afterReturning() throws Exception {
		System.out.println("afterReturning member login");
	}
	
	//@AfterThrowing("execution(* com.naver.b1.member.MemberService.memberJoin(..))")
	public void afterThrowing() throws Exception {
		System.out.println("afterThrowing member join");
	}
	
	@Around("execution(* com.naver.b1.member.MemberService.memberLogin(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("실행 전");
		
		Object obj = joinPoint.proceed();
		
		System.out.println("실행 후");
		
		return obj;
	}
	
}
