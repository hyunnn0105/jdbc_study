package com.jdbc.basic.score.controller;

import com.jdbc.basic.score.domain.Score;
import com.jdbc.basic.score.repository.ScoreOracleRepo;
import com.jdbc.basic.score.repository.ScoreRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 데이터 전처리 후처리하고 저장 관리하는 클래스
public class ScoreController {

    // 성적정보가 저장될 맵(K:학번, v : 성적정보)
    private static Map<Integer, Score> scoreMap;

    //ScoreRepository에 의존성 관계를 가진다 -> 의존하는건 주로 인터페이스

    private final ScoreRepository repository;

    public ScoreController() {
        this.repository = new ScoreOracleRepo();
    }

    static {
        scoreMap = new HashMap<>();
    }


    // 학생성적정보 입력기능
    public void insertStudent(Score score){
        // 메모리에 저장
        scoreMap.put(score.getStuNum(), score);
        // DB에 저장명령
        boolean result = repository.save(score);
    }

    public List<Score> findAllStudent(){
        Map<Integer, Score> students = repository.findAll();
        scoreMap = students;

        List<Score> scoreList = new ArrayList<>();
        for (Integer stuNum : scoreMap.keySet()) {
            scoreList.add(students.get(stuNum));
        }
        return scoreList;
    }

    public double calcClassAverage() {
        // case 1 : 앱 내부에서 구한다
        /*
        double avgSum = 0.0;
        for (Integer stuNum : scoreMap.keySet()) {
            avgSum += scoreMap.get(stuNum).getAverage();
        }
        return avgSum / scoreMap.size();

         */
        // case2: DB에서 전체 평균을 구해서 가져온다
        return repository.getClassAverage();
    }

    // 성적 개별조회
    public Score findOneStudent(int stuNum){
        return repository.findOne(stuNum);
    }

    // 성적 수정
    // 뷰에서 호출함 -> 성공/실패
    public Boolean updateStudent(int stuNum, int kor, int eng, int math){
        // 1. DB에서 해당 학생을 조회한다
        Score target = findOneStudent(stuNum);
        if (target != null){
            // 2. 수정 진행
            target.setKor(kor);
            target.setEng(eng);
            target.setMath(math);
            target.calc();

            // 3. DB에서 수정 반영
            return repository.modify(target);
        }
        return false;
    }

    // 성적정보 삭제 -> 성공실패 넘김
    public boolean deleteStudent(int stuNum){
        return repository.remove(stuNum);
    }

    // 학생존재유무를 리턴
    public boolean hasScore(int stuNum){
        return repository.findOne(stuNum) != null;
    }

}
