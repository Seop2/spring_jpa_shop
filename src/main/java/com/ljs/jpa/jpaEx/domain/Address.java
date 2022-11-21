package com.ljs.jpa.jpaEx.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable//밸류 클래스: 다른 엔티티에 포함될수 있다.
//public 보다는 protected
@Getter
public class Address {
    private String city;//도시
    private String street;//도로명
    private String zipcode;//몇동몇호
    protected Address(){//함부로 값변경x(setter x)

    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
