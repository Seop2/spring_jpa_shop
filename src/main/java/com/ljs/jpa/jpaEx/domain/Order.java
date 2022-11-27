package com.ljs.jpa.jpaEx.domain;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

//주문 엔티티
@Entity
@Table(name = "orders")
@Getter@Setter
public class Order{
    @Id
    @GeneratedValue
    @Column(name="Order_id")//주문번호 id
    private Long id;

    @ManyToOne(fetch = LAZY)//지연로딩
    @JoinColumn(name="member_id")//fk로 회원번호(주문한)매핑
    //** 외래키가 있는곳을 연관관계의 주인으로 정할것!
    private Member member;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
//모든 엔티티는 기본적으로 영속성을 각각 따로 적용해야 하지만 cascade로 한번에 처리한다
    @OneToOne(cascade = CascadeType.ALL, fetch = LAZY)//일대일 관계
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;//주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태 (주문 , 취소)

//    연관관계 편의       메서드 - 하나의 메소드에서 양방향 관계 설정
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    //생성 메서드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;

    }
    //비즈니스 로직
    public void cancel(){
        if(delivery.getStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }
    //조회 로직
    /* 전체 주문 로직 조회*/
    public int getTotalPrice(){
        int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();//for each문을 람다식+스트림으로 간소화
//        for(OrderItem orderItem: orderItems){
//            totalPrice += orderItem.getTotalPrice();
//        }
        return totalPrice;
    }
}
