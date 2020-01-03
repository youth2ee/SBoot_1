package com.naver.b1.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String memberJoin(Model model) throws Exception {
		model.addAttribute("memberVO", new MemberVO());
		return "member/memberJoin";
	}
	
	
	/* 위의 코드와 같다 .
	 * @GetMapping("memberJoin") 
	 * public String memberJoin(MemberVO memberVO) throws Exception { 
	 * return "member/memberJoin"; }
	 */
	
	
	/*
	 * @ModelAttribute 
	 * public MemberVO getMemberVO() throws Exception { 
	 * return new
	 * MemberVO(); }
	 */
	//이렇게 하면 이 안의 모든 메서드 들이 멤버VO를 가지고 간다.
	//이 경우에는 매개변수로 MemberVO를 안받아도 되고, 모델에 MemberVO를 안넣어줘도 된다.
	
	
	
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO, BindingResult bindingResult, MultipartFile files) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 검증 
		// MemberVO앞에 @Valid 써줘야하고, 반드시 내가 검증할 아이 바로 뒤에  BindingResult bindingResult를 넣어줘야 한다. -> 순서 주의
		
		
		if (memberService.memberJoinValidate(memberVO, bindingResult)) {
			mv.setViewName("member/memberJoin");
		}/*
		 * if (bindingResult.hasErrors()) { //에러가 발생하면
		 * mv.setViewName("member/memberJoin"); }
		 */ else {
			// 
			int result = memberService.memberJoin(memberVO, files);
			String msg = "가입실패";
			String path = "../";
			
			if (result > 0) {
				msg = "가입성공";
			}
			
			mv.setViewName("common/result");
			mv.addObject("msg", msg);
			mv.addObject("path", path);
		}
		
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
