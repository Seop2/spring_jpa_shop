package com.ljs.jpa.jpaEx;

import org.springframework.data.jpa.repository.JpaRepository;

//Repository란?
//Entity애 의해 생성된 DB에 접근하는 메서드들을 사용하기 위한 인터페이스
public interface MemoRepository extends JpaRepository <Memo, Long>{
}
