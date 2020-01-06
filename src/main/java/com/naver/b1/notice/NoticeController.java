package com.naver.b1.notice;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.b1.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	

	@GetMapping("noticeList")
	public void noticeList(Model model, Pager pager) throws Exception {
		List<NoticeVO> noticeList =  noticeService.noticeList(pager);
		model.addAttribute("list", noticeList);
		model.addAttribute("pager", pager);
		
	}
	
	@GetMapping("noticeWrite")
	public NoticeVO noticeInsert(NoticeVO noticeVO) throws Exception {
		return noticeVO;
	}
	
	/*
	 * @GetMapping("noticeWrite") public NoticeVO noticeInsert(@ModelAttribute(name
	 * = "boardVO") NoticeVO noticeVO) throws Exception { return noticeVO; }
	 */
	
	
	/*
	 * @ModelAttribute(name="boardVO") public NoticeVO getNoticeVO() { return new
	 * NoticeVO(); }
	 *///무조건 이 컨트롤러에 들어오면 이 메서드가 바로 실행되고 나머지 메서드가 실행된다.
	
	
	
	@PostMapping("noticeWrite") 
	public ModelAndView noticeInsert(@Valid NoticeVO noticeVO, BindingResult bindingResult, MultipartFile [] files, HttpSession session) throws Exception {

		ModelAndView mv = new ModelAndView();

		if(noticeService.noticeWriteValidate(noticeVO, bindingResult)) {
			mv.setViewName("notice/noticeWrite"); 
		}else { 
			int result = noticeService.noticeInsert(noticeVO, files); 
			String msg = "작성실패"; 
			String path = "../";

			if(result > 0) {
				msg = "작성성공"; 
			}

			mv.setViewName("common/result"); 
			mv.addObject("msg", msg);
			mv.addObject("path", path);

		}
		return mv;
	}
	
	
	@GetMapping("noticeSelect")
	public void noticeSelect(NoticeVO noticeVO) {
	
		
	
	}


	

}
