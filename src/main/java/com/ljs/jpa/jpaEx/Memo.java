package com.ljs.jpa.jpaEx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="testdb_memo")
@Entity
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//mysql의 auto_increment를 사용
    private Long id;

    @Column(length = 200,  nullable = false)
    private String memoText;
}
