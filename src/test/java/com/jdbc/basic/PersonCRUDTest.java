package com.jdbc.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.jdbc.basic.PersonCRUD.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonCRUDTest {

    @Test
    @DisplayName("사람정보가 데이터베이스에 저장되어야한다.")
    void saveTest(){

        // 사람객체 생성
//        Person p = makePerson("850201-1233499", "고길동", 30);
        Person p = makePerson("001201-1233411", "뽀로로", 23);

        // 사람저장명령
        boolean result = save(p);

        // 단언
        assertTrue(result);


    }

    @Test
    @DisplayName("주어진 주민번호에 맞는 사람 정보를 데이터베이스에서 삭제해야한다.")
    void removeTest(){

        boolean flag = remove("850201-1233499");

        assertTrue(flag);

    }

    @Test
    @DisplayName("사람 이름을 수정해야한다")
    void updateTest(){

        boolean updateResult = update("크로옹", "001201-1233411");

        assertTrue(updateResult);

    }

    @Test
    void bulkInsertTest(){

        String[] firstName = {"한", "이", "박", "최", "송", "라"};

        for (int i = 0; i < 10; i++) {
            String f = firstName[(int) (Math.random() * firstName.length)];
            save(makePerson("991112-123456"+i, f+"철수", 24));
        }
    }

    @Test
    @DisplayName("전체 사람데이터를 조회해야한다")
    void findAllTest(){
        List<Person> people = findAll();

        for (Person p : people) {
            System.out.println(p);
        }
    }
}