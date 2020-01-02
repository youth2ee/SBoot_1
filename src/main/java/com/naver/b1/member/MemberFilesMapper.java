package com.naver.b1.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberFilesMapper {
	public int memberFilesInsert(MemberFilesVO memberFilesVO) throws Exception;

}

