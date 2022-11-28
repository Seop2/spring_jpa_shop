package com.ljs.jpa.jpaEx.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원이름은 필수입니다.")//유효성 검사
    private String name;

    private String city;
    private String zipcode;
    private String street;
}
/** 폼 객체 vs 엔티티 직접 사용
 * 요구사항이 정말 단순할 때는 폼 객체없이 엔티티룰 직접 등록과 수정화면에서 사용해도 된다. 하지만 요구사항이 복잡해지기 시작하면, 엔티티에 화면을 처리하기 위한 기능이
 * 점정 증가한다.
 * 결과적으로 엔티티는 점점 화면에 종속적으로 변하고, 이렇게 화면기능때문에 지저분해진 엔티티는 결국 유지보수하기 어려워진다.
 * 실무에서 엔티티는 핵심 비즈니스 로직만 가지고 있고, 화면을 위한 로직은 없어야 한다.
 * 화면이나 api에 맞는 폼 객체나 dto를 사용하자. 엔티티는 최대한 순수하게 유지하자
 */

