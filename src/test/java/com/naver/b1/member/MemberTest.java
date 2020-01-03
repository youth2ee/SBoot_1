package com.naver.b1.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
		memberVO.setId("n21");
		memberVO.setPw("n21");
		memberVO.setName("n21");
		memberVO.setEmail("n2@n.com");
		
		int result = memberMapper.memberJoin(memberVO);
		
		assertEquals(1, result);
	}
}
