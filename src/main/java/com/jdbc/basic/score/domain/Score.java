package com.jdbc.basic.score.domain;

import lombok.*;
// @data - 한번에 만들어 주지만 사용X
@Setter @Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor
@ToString
@Builder // 객체 생성시 생성자 역할을 대신 - inner static class 생성해서 사용?

// database score table의 행데이터를 저장할 객체
// sql은 _ 스네이크 케이스 사용해서 _ 제외하고 바꾸기 or 새로운 이름
public class Score {

    private int stuNum;
    private String stuName;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;

    // 전체가 아니라 부분적으로 생성자 만들고 싶다면 직접 만들기

    public void calc(){
        this.total = kor + eng + math;
        this.average = Math.round((total / 3.0) * 100) / 100.0;
    }

    public int getStuNum() {
        return stuNum;
    }
}
