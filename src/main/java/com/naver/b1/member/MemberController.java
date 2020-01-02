package com.naver.b1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberJoin")
	public String memberJoin() throws Exception {
		return "member/memberJoin";
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(MemberVO memberVO) throws Exception {
		int result = memberService.memberJoin(memberVO);
		String msg = "가입실패";
		String path = "../";
		
		if (result > 0) {
			msg = "가입성공";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/result");
		mv.addObject("msg", msg);
		mv.addObject("path", path);
		
		return mv;
	}
	
}
