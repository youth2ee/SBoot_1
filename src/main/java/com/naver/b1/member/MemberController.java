package com.naver.b1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
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
	public ModelAndView memberJoin(MemberVO memberVO, MultipartFile files) throws Exception {
		int result = memberService.memberJoin(memberVO, files);
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
	
	@GetMapping("memberLogin")
	public String memberLogin() {
		return "member/memberLogin";
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession session) throws Exception {
		memberVO = memberService.memberLogin(memberVO);	
		
		String msg = "로그인 실패";
		String path = "../";
		
		if(memberVO != null) {
			msg = "로그인 성공";
			MemberFilesVO memberFilesVO = memberService.memberFile(memberVO);
			session.setAttribute("member", memberVO);
			session.setAttribute("file", memberFilesVO);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/result");
		mv.addObject("msg", msg);
		mv.addObject("path", path);
		
		return mv;
		
	}
	
	@GetMapping("memberPage")
	public void memberPage() {
	}
	
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) {
		session.invalidate();
		return "redirect:../";
	}
	
	@GetMapping("memberFileDown")
	public ModelAndView memberFileDown(MemberFilesVO memberFilesVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		memberFilesVO = memberService.memberFilesSelect(memberFilesVO);
		
		if(memberFilesVO != null) {
			mv.addObject("memberFiles", memberFilesVO);
			mv.addObject("path", "upload");
			
			//중요 : 객체이름을 지정하지 않으면 그 클래스명의 첫글자를 소문자로 만든것이 객체이름이 된다.
			mv.setViewName("fileDown");
			
		} else {
			mv.addObject("msg", "사진이 없습니다.");
			mv.addObject("path", "./memberPage");
			mv.setViewName("common/result");
		}
		
		return mv;
	}
}
