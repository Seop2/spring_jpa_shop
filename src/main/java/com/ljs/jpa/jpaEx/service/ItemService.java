package com.ljs.jpa.jpaEx.service;

import com.ljs.jpa.jpaEx.domain.item.Item;
import com.ljs.jpa.jpaEx.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    @Transactional
    public void saveItem(Item item){//상품등록
        itemRepository.save(item);
    }
    public List<Item> findItems(){//상품 목록 전체 조회
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId){//상품 조회
        return itemRepository.findOne(itemId);
    }
    //영속성 컨텍스트가 자동변경
    @Transactional
    public void updateItem(Long id, String name, int price, int stockQuantity){//파라미터가 많을 경우 dto로 따로 빼기
        Item item = itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }
}
