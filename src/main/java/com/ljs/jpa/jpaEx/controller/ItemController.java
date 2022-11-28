package com.ljs.jpa.jpaEx.controller;

import com.ljs.jpa.jpaEx.domain.item.Book;
import com.ljs.jpa.jpaEx.domain.item.Item;
import com.ljs.jpa.jpaEx.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemsForm";
    }
    @PostMapping("/items/new")
    public String create(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        //BookForm에서 입력받을 값을 설계하고 타임리프에서 입력을 받은 값을 저장(post)해서 상품등록 로직을 통해 리포지토리로 저장
        itemService.saveItem(book);//상품 등록
        return "redirect:/";
    }
    @GetMapping("items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId")Long itemId, Model model){
        Book item = (Book)itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";

    }
    /*상품 수정*/
//    권장 코드
    @PostMapping("items/{itemId}/edit")//보안상 취약 id 접근이 쉬움
    public String updateItem(@ModelAttribute("form") BookForm form){
        //준영속 엔티티
//        @PathVariable String itemId(parameter)
//        Book book = new Book();
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
        itemService.updateItem(form.getId(), form.getName(), form.getPrice(), form.getStockQuantity());
//        itemService.saveItem(book);
        return "redirect:/items";
    }
}
