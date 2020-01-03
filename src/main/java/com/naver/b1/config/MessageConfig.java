package com.naver.b1.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

	@Bean
	public LocaleResolver getLocaleResolver() {
		//1. 세션 이용법 : 저장위치-서버데이터를 꺼내쓴다.
		/*
		 * SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		 * sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);
		 */
		
		//2. 쿠키 이용법 :저장위치-클라이언트데이터를 꺼내쓴다. 주로 쿠키사용
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN); //defalut값을 지정한다. 
		cookieLocaleResolver.setCookieName("lang"); //파라미터로 넘길 name의 값을 지정한다.
		
		return cookieLocaleResolver;
	}
	
	
	@Bean
	public LocaleChangeInterceptor getLocale() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		return localeChangeInterceptor;
	}
	
	

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getLocale()).addPathPatterns("/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	
	
	
	
	
}
