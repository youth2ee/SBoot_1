package com.b1.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.naver.b1.notice.NoticeMapper;
import com.naver.b1.notice.NoticeVO;

@SpringBootTest
class NoticeWriteTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	
	@Test
	void test() throws Exception {
		
		for(int i=0;i<30;i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setTitle("title"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO.setContents("contents"+i);
			noticeMapper.noticeInsert(noticeVO);
		}
		
		
	}

}
