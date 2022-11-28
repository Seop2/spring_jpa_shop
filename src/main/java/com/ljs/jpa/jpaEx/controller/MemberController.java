package com.ljs.jpa.jpaEx.controller;

import com.ljs.jpa.jpaEx.domain.Address;
import com.ljs.jpa.jpaEx.domain.Member;
import com.ljs.jpa.jpaEx.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
    //form을 따로 만드는 이유: 실무에서는 이렇게 간단한 요구사항이 있는게 아니라
    // 좀 더 복잡하고 많은 것들이 있기 때문에 사용자가 원하는 요구사항, 실제 db접근할때의 객체랑 다를수밖에 없다
    @PostMapping("/members/new")//회원 추가
    public String create(@Valid MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }
    @GetMapping("/members")//회원목록조회
    public String list(Model model){
        List<Member> members = memberService.findMembers();//서비스에서 조회함수 가져와서 리스트로 저장
        model.addAttribute("members", members);//리스트로 저장된 데이터를 model객체(스프링 mvc 제공)에 추가
        return "members/memberList";//템플릿으로 반환
    }
}
