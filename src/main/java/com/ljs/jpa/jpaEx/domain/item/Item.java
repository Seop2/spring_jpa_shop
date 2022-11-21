package com.ljs.jpa.jpaEx.domain.item;

import com.ljs.jpa.jpaEx.domain.Category;
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
}
