package com.naver.b1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

import com.naver.b1.robot.LeftArm;
import com.naver.b1.robot.RightArm;

@Configuration
public class RobotConfig {
	
	@Bean("left") //지정하지 않으면 leftArm으로 자동지정
	//@Qualifier 이걸로 지정해도 됨
	public LeftArm getLeftArm() throws Exception {
		LeftArm leftArm = new LeftArm();
	
		return leftArm;
	}
	
	
	@Bean
	public RightArm getRightArm() throws Exception {
		return new RightArm();
	}

}
