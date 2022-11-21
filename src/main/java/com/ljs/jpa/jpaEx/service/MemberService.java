package com.ljs.jpa.jpaEx.service;

import com.ljs.jpa.jpaEx.domain.Member;
import com.ljs.jpa.jpaEx.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service//리포지토리에서 가져온 걸 로직처리하는 곳
@Transactional(readOnly = true)//조회만 변경x
@RequiredArgsConstructor//final만 생성자 만듬
public class MemberService {

    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    @Transactional//readOnly = false
    //회원 가입
    public Long join(Member member){
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    public void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");//중복처리 로직 from MemberRepository
            //실제 DB에서는 유니크제약조건으로 구분함
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
