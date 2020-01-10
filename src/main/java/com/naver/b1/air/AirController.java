package com.naver.b1.air;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/airline/**")
public class AirController {

	@GetMapping("airSelect")
	public void airSelect() {
		
	}
	
}
