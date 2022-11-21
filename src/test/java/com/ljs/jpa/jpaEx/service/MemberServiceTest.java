package com.ljs.jpa.jpaEx.service;

import com.ljs.jpa.jpaEx.domain.Member;
import com.ljs.jpa.jpaEx.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)//junit4
@SpringBootTest//스프링부트 테스트 선언
@Transactional// 자동 rollback
public class MemberServiceTest {
//    @Autowired
    @Autowired MemberService memberService;
//    @Autowired
    @Autowired MemberRepository memberRepository;
    @Test
    //insert 확인을 위해선 @Rollback(false)하면 된다! -> db에 값 들어갔는지 확인 할수 있게
    public void Member_join() throws Exception {//화원가입
        //given
        Member member = new Member();
        member.setName("Lee");
        //when
        Long savedId = memberService.join(member);
        //then
        assertEquals(member,memberRepository.findOne(savedId));
    }
    @Test(expected = IllegalStateException.class)
    public void DuplicateMemberException() throws Exception {//회원 중복 예외처리
        //given
        Member member1 = new Member();
        member1.setName("Lee");
        Member member2 = new Member();
        member2.setName("Lee");
        //when
        memberService.join(member1);
        memberService.join(member2);//예외가 발생해야 함!

        //then
        fail("예외가 발생해야 한다.");
    }
}