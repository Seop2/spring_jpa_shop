package com.ljs.jpa.jpaEx.domain;



import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//회원엔티티
@Entity
@Getter@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")//테이블은 관례상 테이블명+ id를 많이 사용
    private Long id;
    private String name;

    @Embedded//jpa 내장
    private Address address;

    @OneToMany(mappedBy = "member")//주인아님 조회만 할게
    private List<Order> orders = new ArrayList<>();//컬렉션은 필드에서 초기화하는 것이 안전하다
}
