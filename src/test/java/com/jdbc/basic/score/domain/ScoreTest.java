package com.jdbc.basic.score.domain;

import com.jdbc.basic.score.repository.ScoreOracleRepo;
import com.jdbc.basic.score.repository.ScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void lombokTest(){
        Score s = new Score(1, "김철수", 99,88,11,270,54.3);
        // 파일 설정-> 어노테이션 검색
        s.setTotal(222);

        System.out.println(s.getStuName());
        assertEquals(s.getStuName(), "김철수");


    }

    @Test
    void lombokBuilderTest(){
        // 생성자에 원하는 파라미터만 넣어서 만들 수 있음
        Score park = new Score.ScoreBuilder()
                .stuNum(2)
                .stuName("박영희")
                .math(92)
                .eng(100)
                .total(192)
                .average(96).build();

        System.out.println(park);
    }

}