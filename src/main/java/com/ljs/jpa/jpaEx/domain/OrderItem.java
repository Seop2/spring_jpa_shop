package com.ljs.jpa.jpaEx.domain;

import com.ljs.jpa.jpaEx.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "Order_item")
@Getter@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
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

    public static OrderItem createOrderItem(Item item, int orderPrice, int count){//쿠폰 할인 고려하여 세분화
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //비즈니스 로직
//    주문 취소
    public void cancel(){
        getItem().addStock(count);//주문 취소했을때 재고 추가
    }
//    주문상품 전체 가격 조회
    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }
}
