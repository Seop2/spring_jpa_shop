package com.ljs.jpa.jpaEx.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)// ORDINAL : enum 순서를 데이터베이스에 저장 나중에 상태 추가시 꼬임
    //EnumType.STRING // enum 이름을 데이터베이스에 저장
    //거의 무조건 STRING씀
    private DeliveryStatus status; //READY, COMP
}
