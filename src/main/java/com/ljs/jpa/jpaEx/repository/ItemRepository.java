package com.ljs.jpa.jpaEx.repository;

import com.ljs.jpa.jpaEx.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        if(item.getId()==null) {//id가 없으면 새로 등록
            em.persist(item);
        }else{
            em.merge(item);//id가 있으면....
        }
    }
    public Item findOne(Long id){//상품 조회
        return em.find(Item.class, id);
    }
    public List<Item> findAll(){//상품 목록 조회
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
