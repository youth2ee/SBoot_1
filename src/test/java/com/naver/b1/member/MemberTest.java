package com.naver.b1.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.naver.b1.notice.NoticeVO;

@SpringBootTest
@Transactional
@Rollback
class MemberTest {

	/*
	 * @Autowired 
	 * private MemberMapper memberMapper;
	 */
	
	/*@Test
	 * public void test() throws Exception { 
	 * MemberVO memberVO = new MemberVO();
	 * memberVO.setId("yooza"); 
	 * memberVO.setPw("1234");
	 * memberVO.setName("yoozamom"); 
	 * memberVO.setEmail("y@y.com"); 
	 * int result = memberMapper.memberJoin(memberVO); 
	 * assertEquals(1, result); 
	 * }
	 */
	
	/*
	 * @Test 
	 * public void test2() throws Exception { 
	 * MemberVO memberVO = new MemberVO();
	 * memberVO.setId("yooza"); 
	 * memberVO.setPw("1234"); 
	 * memberVO = memberMapper.memberLogin(memberVO); 
	 * assertEquals("yooza", memberVO.getId());
	 * }
	 */
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void memberJoinTest() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("admin");
		memberVO.setPw("admin");
		memberVO.setName("admin");
		memberVO.setEmail("admin@admin.com");
		
		int result = memberMapper.memberJoin(memberVO);
		
		assertEquals(1, result);
	}
	
	/*
	 * @Test public void memberWrite() throws Exception { for(int i=0;i<30;i++) {
	 * MemberVO memberVO = new MemberVO(); memberVO.setId("id"+i);
	 * memberVO.setPw("aaa"); memberVO.setName("name"+i);
	 * memberVO.setEmail("email"+i+"@com");
	 * 
	 * memberMapper.memberJoin(memberVO); }
	 * 
	 * }
	 */
}
