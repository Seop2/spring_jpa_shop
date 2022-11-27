package com.ljs.jpa.jpaEx.service;

import com.ljs.jpa.jpaEx.domain.Address;
import com.ljs.jpa.jpaEx.domain.Member;
import com.ljs.jpa.jpaEx.domain.Order;
import com.ljs.jpa.jpaEx.domain.OrderStatus;
import com.ljs.jpa.jpaEx.domain.item.Book;
import com.ljs.jpa.jpaEx.domain.item.Item;
import com.ljs.jpa.jpaEx.exception.NotEnoughStockException;
import com.ljs.jpa.jpaEx.repository.OrderRepository;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(readOnly = true)
public class OrderServiceTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = CreateMember();
        Book book = CreateBook("김영한의 jpa", 10000, 10);

        //when
        int orderCount = 2;//주문한 갯수

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());

        assertEquals("주문 가격은 가격 * 수량이다.", 10000* orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    private Book CreateBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member CreateMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강남대로", "123-123"));
        em.persist(member);
        return member;
    }

    @Test
    public void 주문취소() throws Exception {//주문을 취소하려면 먼저 주문을 해야 한다.
        //given
        Member member = CreateMember();
        Item item = CreateBook("감영한의 jpa", 10000, 10);
        int orderCount = 2;//주문 2개!

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);//취소했을 때
        //then
        Order getOrder = orderRepository.findOne(orderId);//주문 취소 상태인지 취소한 만큼 재고가 증가했는 지 검증
    }
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = CreateMember();
        Item item = CreateBook("김영한의 jpa", 10000, 10);//이름, 가격 , 재고
        int orderCount = 11;//재고보다 많은 수량
        //when
        orderService.order(member.getId(),item.getId(), orderCount);
        //then
        fail("재고 수량 예외가 발생해야 함!!");
    }
    
}