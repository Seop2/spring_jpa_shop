package com.ljs.jpa.jpaEx.repository;

import com.ljs.jpa.jpaEx.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor//final 생성자 feat.롬복
public class MemberRepository {

    private final EntityManager em;
    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    public List<Member> findAll(){//회원 조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();//jpql : 엔티티객체를 상대로 sql
    }
    public List<Member>findByName(String name){//회원 이름 조회
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
