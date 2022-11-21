package com.ljs.jpa.jpaEx.domain;

import com.ljs.jpa.jpaEx.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter@Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name ="order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = LAZY)//일대다 매핑
    @JoinColumn(name="order_id")//외래키 조인(주문번호)
    private Order order;

    private int orderPrice;//주문가격
    private int count;//주문 수량
}
