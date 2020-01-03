package com.naver.b1.member;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.naver.b1.util.FilePathGenerator;
import com.naver.b1.util.FileSaver;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberFilesMapper memberFilesMapper;
	
	@Autowired 
	private ServletContext servelContext;
	 
	@Autowired
	private FilePathGenerator filePathGenerator;
	
	@Autowired
	private FileSaver fileSaver;
	
	
	@Transactional(rollbackFor = Exception.class)
	public int memberJoin(MemberVO memberVO, MultipartFile files) throws Exception {
		/* 1.
		 * String filePath = servelContext.getRealPath("upload");
		 * System.out.println(filePath);
		 */
		
		/* 2.
		 * filePathGenerator.getUseResourceLoader(); */
		
		//3.
		//파일을 저장할 폴더
		File file = filePathGenerator.getUseClassPathResource("upload");
		String fileName = fileSaver.save(file, files);
		System.out.println(fileName);
		//
		
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setFname(fileName);
		memberFilesVO.setOname(files.getOriginalFilename());
		memberFilesVO.setId(memberVO.getId());
		
		int jresult = memberMapper.memberJoin(memberVO);
		int fresult = 0;
		
		if(jresult > 0) {
			fresult = memberFilesMapper.memberFilesInsert(memberFilesVO);
		}
				
		return fresult; 
	}
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberMapper.memberLogin(memberVO);
	}
	
	public MemberFilesVO memberFile(MemberVO memberVO) throws Exception {
		return memberFilesMapper.memberFile(memberVO);
	}
	
	public MemberFilesVO memberFilesSelect(MemberFilesVO memberFilesVO) throws Exception {
		return memberFilesMapper.memberFilesSelect(memberFilesVO);
	}
	
	//*********************************************************************************************************************************
	
	// 회원가입 유효성 검증
	public boolean memberJoinValidate(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check = false; //true라면 에러, false면 검증완료로 지정하겠다.
		
		//annotiation으로 검증
		if (bindingResult.hasErrors()) {
			check = true;
		}
		
		//pw가 일치하는지 검증 
		if(!memberVO.getPw().equals(memberVO.getPw2())) {
			check = true;
			bindingResult.rejectValue("pw2", "memberVO.pw.notEqual");
			//pw2 : form 내의 path명 
			//memberVO.pw.notEqual : properties의 key값 
		}
		
		//id 중복검사
		System.out.println(memberVO.getId());
		memberVO = memberMapper.idCheck(memberVO);
		if (memberVO != null) {
			check = true;
			bindingResult.rejectValue("id", "memberVO.id.notEqual"); 
		}
		
		
		return check;
	}
	
	
	
	
	
}
