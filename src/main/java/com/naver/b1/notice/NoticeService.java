package com.naver.b1.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.naver.b1.util.FilePathGenerator;
import com.naver.b1.util.FileSaver;
import com.naver.b1.util.Pager;

@Service
public class NoticeService {
	
	//@Autowired
	@Resource(name = "noticeMapper")
	private NoticeMapper noticeMapper;
	
	@Autowired
	private NoticeFilesMapper noticeFilesMapper;
	
	@Autowired 
	private ServletContext servelContext;
	 
	@Autowired
	private FilePathGenerator filePathGenerator;
	
	@Autowired
	private FileSaver fileSaver;
	
	
	public List<NoticeVO> noticeList(Pager pager) throws Exception {
		
		pager.makeRow();
		pager.makePager(noticeMapper.noticeCount());
		
		
		return noticeMapper.noticeList(pager);
	}
	
	
	/*
	 * @Transactional(rollbackFor = Exception.class) public int
	 * noticeInsert(NoticeVO noticeVO, MultipartFile [] files) throws Exception {
	 * int result = noticeMapper.noticeInsert(noticeVO); int fresult = 0;
	 * 
	 * if(result > 0) { String filename = ""; File file =
	 * filePathGenerator.getUseClassPathResource("notice");
	 * 
	 * for(MultipartFile filed : files) {
	 * 
	 * if(filed.getOriginalFilename() != "") { NoticeFilesVO noticeFilesVO = new
	 * NoticeFilesVO(); filename = fileSaver.save(file,filed);
	 * noticeFilesVO.setNum(noticeVO.getNum()); noticeFilesVO.setFname(filename);
	 * noticeFilesVO.setOname(filed.getOriginalFilename());
	 * 
	 * fresult = noticeFilesMapper.noticeFilesInsert(noticeFilesVO); } } } return
	 * fresult; }
	 */

	
	//
	
	  @Transactional(rollbackFor = Exception.class) 
	  public int noticeInsert(NoticeVO noticeVO, MultipartFile [] files) throws Exception {
	  int result = noticeMapper.noticeInsert(noticeVO); 
	  int fresult = 0;
	  
	  if(result > 0) { 
		  String filename = ""; 
		  File file = filePathGenerator.getUseResourceLoader("upload");
	  
	  List<NoticeFilesVO> noticeFilesVOs = new ArrayList<>();
	  
	  
	  for(MultipartFile filed : files) { 
	  NoticeFilesVO noticeFilesVO = new
	  NoticeFilesVO(); filename = fileSaver.save(file,filed);
	  noticeFilesVO.setNum(noticeVO.getNum()); 
	  noticeFilesVO.setFname(filename);
	  noticeFilesVO.setOname(filed.getOriginalFilename());
	  
	  noticeFilesVOs.add(noticeFilesVO);
	  
	  }
	  
	  fresult = noticeFilesMapper.noticeFilesListInsert(noticeFilesVOs);
	  
	  
	  } return fresult; 
	  
	  }
	 
	
	
	//
	
	
	public boolean noticeWriteValidate(NoticeVO noticeVO, BindingResult bindingResult) throws Exception {
		boolean check = false;
		
		if (bindingResult.hasErrors()) {
			check = true;
		}
		
		return check;
		
	}
	
	
}
