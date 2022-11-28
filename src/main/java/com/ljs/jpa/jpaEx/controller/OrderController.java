package com.ljs.jpa.jpaEx.controller;

import com.ljs.jpa.jpaEx.domain.Member;
import com.ljs.jpa.jpaEx.domain.Order;
import com.ljs.jpa.jpaEx.domain.item.Item;
import com.ljs.jpa.jpaEx.repository.OrderSearch;
import com.ljs.jpa.jpaEx.service.ItemService;
import com.ljs.jpa.jpaEx.service.MemberService;
import com.ljs.jpa.jpaEx.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")//상품 주문
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item>items = itemService.findItems();

        model.addAttribute("members", members );
        model.addAttribute("items", items);

        return "order/orderForm";
    }
    @PostMapping("/order")
    public String order(@RequestParam("memberId")Long memberId,
                        @RequestParam("itemId")Long itemId,
                        @RequestParam("count")int count){
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
//주문목록 조회
    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }
    //주문취소
    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
