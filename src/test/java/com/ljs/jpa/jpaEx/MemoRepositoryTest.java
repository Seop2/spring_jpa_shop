package com.ljs.jpa.jpaEx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemoRepositoryTest {
    @Autowired
    MemoRepository memoRepository;
    @Test
    public void InsertDummies(){
        IntStream.rangeClosed(1,10).forEach(i->{
            Memo memo = Memo.builder()
                    .memoText("memo_db"+i)
                    .build();
            memoRepository.save(memo);
        });
    }

}