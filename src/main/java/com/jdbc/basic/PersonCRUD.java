package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.jdbc.basic.Connect.*;

public class PersonCRUD {

    // 내부 클래스 선언
    public static class Person{
        private String ssn;
        private String name;
        private int age;

        public Person() {
        }

        public Person(String ssn, String name, int age) {
            this.ssn = ssn;
            this.name = name;
            this.age = age;
        }

        public String getSsn() {
            return ssn;
        }

        public void setSsn(String ssn) {
            this.ssn = ssn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "ssn='" + ssn + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    // 메서드 선언

    // 사람객체를 생성해서 반환하는 팩토리 메서드 선언
    public static Person makePerson(String ssn, String name, int age){
        return new Person(ssn, name, age);
    }

    public static boolean save(Person p){
        // 1. SQL 구문 작성 - '?' 이거 문법
        String sql = "INSERT INTO person" +
                "(ssn, person_name, age)" + 
                "VALUES(?,?,?)";

        try(Connection conn = Connect.makeConnection()) {
            // 2. db에 접속

            // 3. sql 실행을 위한 객체 preparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);


            // 4. sql의 ? 값을 채우기
            pstmt.setString(1,p.getSsn());
            pstmt.setString(2,p.getName());
            pstmt.setInt(3,p.getAge());

            // 5. 실행명령 내리기
            // - INSERT, UPDATE, DELETE : executeUpdate()
            // -SELECT : executeQuery()

            //resultNumber는 삽입이 실패하면 0을 리턴
            int resultNumber = pstmt.executeUpdate();

            if (resultNumber == 0) return false;
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean remove(String ssn){
        // 1. SQL 구문 작성 - '?' 이거 문법
        String sql = "DELETE FROM person WHERE ssn=?";

        try(Connection conn = Connect.makeConnection()) {
            // 2. db에 접속

            // 3. sql 실행을 위한 객체 preparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);


            // 4. sql의 ? 값을 채우기
            pstmt.setString(1, ssn);

            // 5. 실행명령 내리기
            // - INSERT, UPDATE, DELETE : executeUpdate()
            // -SELECT : executeQuery()

            //resultNumber는 삭제가 실패하면 0을 리턴
            int resultNumber = pstmt.executeUpdate();

            if (resultNumber == 0) return false;
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // 이름 수정하기
    public static boolean update(String update_name, String ssn){
        // 1. SQL 구문 작성 - '?' 이거 문법
        String sql = "UPDATE person SET person_name = ? WHERE ssn = ? ";

        try(Connection conn = Connect.makeConnection()) {
            // 2. db에 접속

            // 3. sql 실행을 위한 객체 preparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);


            // 4. sql의 ? 값을 채우기
            pstmt.setString(1, update_name);
            pstmt.setString(2, ssn);

            // 5. 실행명령 내리기
            // - INSERT, UPDATE, DELETE : executeUpdate()
            // -SELECT : executeQuery()

            //resultNumber는 수정이 실패하면 0을 리턴
            int resultNumber = pstmt.executeUpdate();

            if (resultNumber == 0) return false;
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // SELECT ALL
    public static List<Person> findAll(){

        List<Person> personList = new ArrayList<>();

        // 1. sql 작성 - 모든행 조회
        String sql = "SELECT * FROM person";

        // 2. DB 연결
        try (Connection conn = Connect.makeConnection()) {

            // 3. sql 실행을 위한 pstmt 객체 생겅
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 값 채우기 -> 나중에

            //5. sql 실행 - SELECT 실행시 executeQuery

            // ResultSet : 조회결과 나타나는 2차원의 표
            ResultSet rs = pstmt.executeQuery();

            // resultset을 순회해서 소비 like stringtokener : 한줄씩 꺼내줌
            // a - next() 메서드를 통해 행들을 순서대로 지못
            // b - getter 메서드를 통해 열 데이터 추출

            // rs.next();


            while (rs.next()){

                String ssn = rs.getString("ssn");
                String name = rs.getString("person_name");
                int age = rs.getInt("age");

                Person p = makePerson(ssn, name, age);
                personList.add(p);

            }

            return personList;

        } catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList(); // 빈 리스트 반환
        }

    }


    //인서트 메서드

}
