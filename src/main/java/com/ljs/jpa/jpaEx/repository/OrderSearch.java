package com.ljs.jpa.jpaEx.repository;

import com.ljs.jpa.jpaEx.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderSearch {
    private String memberName;//회원 이름
    private OrderStatus orderStatus;//주문 상태(주문했는지, 취소했는지)
}
