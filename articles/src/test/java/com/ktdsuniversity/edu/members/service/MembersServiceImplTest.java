package com.ktdsuniversity.edu.members.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.RegistMembersVO;
import com.ktdsuniversity.edu.members.vo.response.MembersVO;

@SpringBootTest // Spring이 생성하고 관리하는 Bean을 자동 주입 받기 위한 어노테이션
//@ExtendWith(SpringExtension.class) // J-Unit5를 사용하겠다는 명시 Spring의 버전이 올라가면서 SpringBootTest 어노테이션에 이미 선언되어있어 없어도됨
//@Import({MembersDao.class, MembersServiceImpl.class}) // MemberServiceImpl <-- 주입이 필요한 Bean
public class MembersServiceImplTest {

	// SpringBootTest와 Import가 준비한 bean을 주입받는다.
	@Autowired
	private MembersService memberService;
	
//	@Autowired
	@MockitoBean
	// 실제로 동작 흐름을 확인하려는 것이 아니기 때문에 
	// 가짜 Bean을 가져오는 MokitoBean사용
	private MembersDao membersDao;
	
	@Test
	@DisplayName("회원가입 성공 테스트")
	public void testCreateNewMember() {
		
		RegistMembersVO registMembersVO = new RegistMembersVO();
		registMembersVO.setEmail("test@gmail.com");
		registMembersVO.setName("TestUser");
		registMembersVO.setNickname("TestNickname");
		registMembersVO.setPassword("test1234!");
		
		//Test pattern => Given -> When -> Then 순으로 진행되야 한다.
		//Given - membersDao에게 역할 부여
		// membersDao.selectEmailCount에게 "test@gmail.com이 전달되면 0을 반환하도록 역할 부여
		BDDMockito.given(this.membersDao.selectEmailCount("test@gmail.com"))
				  .willReturn(0);
		
		BDDMockito.given(this.membersDao.selectNicknameCount("TestNickname"))
		  		  .willReturn(0);
		
		BDDMockito.given(this.membersDao.insertNewMember(registMembersVO))
		  		  .willReturn(1);
		
		MembersVO returnedMember = new MembersVO();
		
		BDDMockito.given(this.membersDao.selectMemberByEmail("test@gmail.com"))
		  		  .willReturn(returnedMember);
		
		
		// When - 실행
		MembersVO membersVO = this.memberService.createNewMember(registMembersVO);
		System.out.println("MembersVO => " +  membersVO);
		System.out.println("registMembersVO => " +  registMembersVO);
		
		// Then
		// 반환값 검증 (Given에서 주었던 값과 일치하는지)
		assertNotNull(membersVO);
		assertEquals(membersVO, returnedMember);
		// 회원가입의 경우 비밀번호가 올바르게 암호화 되었는지
		assertNotNull(registMembersVO.getSalt());
		assertNotEquals("test1234", registMembersVO.getPassword());
		
	}
	
}
