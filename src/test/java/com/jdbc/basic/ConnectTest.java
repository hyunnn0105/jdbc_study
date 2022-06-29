package com.jdbc.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectTest {
    // 테스트 하고싶을때마다 메서드 만들기
    @Test
    // Assertion 단언하다
    @DisplayName("데이터베이스 연결이 정상 수행되어야 한다")
    void connectTest(){
        Connection conn = Connect.makeConnection();

        if (conn != null){
            System.out.println("연결 성공!");
        } else {
            System.out.println("연결 실패");
        }

        // 이것만 있어도 오케이
        assertNotNull(conn); // 나는 conn이 null이 아닌 것을 단언한다

    }
}