package com.naver.b1.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.naver.b1.util.Pager;

@Repository
@Mapper
public interface NoticeMapper {
	
	public List<NoticeVO> noticeList(Pager pager) throws Exception;
	public int noticeInsert(NoticeVO noticeVO) throws Exception;
	public int noticeCount() throws Exception;
	public NoticeVO noticeSelect(NoticeVO noticeVO) throws Exception;

}

