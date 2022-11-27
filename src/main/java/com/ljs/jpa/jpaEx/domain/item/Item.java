package com.ljs.jpa.jpaEx.domain.item;

import com.ljs.jpa.jpaEx.domain.Category;
import com.ljs.jpa.jpaEx.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//전체 아이템들의 추상클래스(클래스들의 조상)

@Entity
@DiscriminatorColumn(name="dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//상속타입
@Getter@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categoryList = new ArrayList<>();

    //비즈니스 로직
//    stock 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }
    //stock 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity -quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
