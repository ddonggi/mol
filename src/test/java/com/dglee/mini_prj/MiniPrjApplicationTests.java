package com.dglee.mini_prj;

import com.dglee.mini_prj.answer.Answer;
import com.dglee.mini_prj.answer.AnswerRepository;
import com.dglee.mini_prj.question.Question;
import com.dglee.mini_prj.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.awt.desktop.QuitEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MiniPrjApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    //데이터 입력
/*    @Test
//    @Transactional
    void inputTest() {
        Question question = Question.builder()
                .title("제목제목4")
                .content("내용내용4")
                .createDate(LocalDateTime.now()).build();
        this.questionRepository.save(question);

        Question question2 = Question.builder()
                .title("제목제목5")
                .content("내용내용5")
                .createDate(LocalDateTime.now()).build();
        questionRepository.save(question2);
    }*/

    //전체 질문 조회 및 비교
    @Test
    void selectAllAndCompareTest(){
        List<Question> questionList = this.questionRepository.findAll();
//        assertEquals(2,questionList.size());

        Question question = questionList.get(0);
        assertEquals("제목제목",question.getTitle());
        assertEquals("내용내용",question.getContent());
    }

    //단일 질문 조회 및 확인
    @Test
    void selectAndCompareTest(){
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()){
            Question question = oq.get();
            System.out.println("question.getTitle() = " + question.getTitle());
            System.out.println("question.getContent() = " + question.getContent());
            assertEquals("제목제목",question.getTitle());
        }
    }

    //제목으로 질문 조회
    @Test
    void findByTitleTest() {
        Optional<Question> oq = this.questionRepository.findByTitle("제목제목");
        if (oq.isPresent()) {
            Question question = oq.get();
            System.out.println("question.getId = " + question.getId());
        }
    }

    //아이디 및 제목으로 질문 조회
    @Test
    void findByIdAndTitleTest(){
        Optional<Question> oq2 = this.questionRepository.findByIdAndTitle(1, "제목제목");
        if (oq2.isPresent()) {
            Question question = oq2.get();
            System.out.println("question.toString() = " + question.toString());
        }
    }

    //글자가 포함되는 질문 찾기
    @Test
    void findLikeTest(){
        Optional<List<Question>> oqList = this.questionRepository.findByTitleLike("%목%");
        if (oqList.isPresent()) {
            List<Question> questionList = oqList.get();
            System.out.println("questionList.toString() = " + questionList.toString());
            Question q = questionList.get(0);
            System.out.println("q.toString() = " + q.toString());
        }
    }

    //데이터 수정하기
    @Test
    void updateTest(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setTitle("변경된 제목");
        q.setContent("변경된 내용!");
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }

    // 마지막 질문 데이터 삭제하기
/*    @Test
    void deleteTest(){
        System.out.println("questionRepository.count() = " + questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById((int) this.questionRepository.count());
        assertTrue(oq.isPresent());
        Question question = oq.get();
        this.questionRepository.delete(question);
        System.out.println("questionRepository.count() = " + questionRepository.count());
    }*/

    //답변 데이터 생성, 저장
/*    @Test
    void answerCreateAndSaveTest(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question question = oq.get();
        String content = "2번 질문에 대한 답변은 이러합니다.";
        Answer answer = Answer.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .question(question)//어떤 질문에 대한 답변인지 알기 위한 질문 객체
                .build();
        this.answerRepository.save(answer);
    }*/

    //답변 id로 답변 데이터 조회
    @Test
    void findAnswerTest(){
        Optional<Answer> optionalAnswer = this.answerRepository.findById(1);
        assertTrue(optionalAnswer.isPresent());
        Answer answer = optionalAnswer.get();
       assertEquals(2,answer.getQuestion().getId()); //답변의 질문 id 가 2인지 비교
    }
    
    //질문데이터에서 답변 조회
    @Transactional
    @Test
    void findAnswerFromQuestionTest(){
        Optional<Question> optionalQuestion = this.questionRepository.findById(2);
        assertTrue(optionalQuestion.isPresent());
        Question question = optionalQuestion.get();

        // Question 리포지터리가 findById를 호출하여 Question 객체를 조회하고 나면 DB세션이 끊어진다.
        // 그 이후에 실행되는 q.getAnswerList() 메서드는 세션이 종료되어 오류가 발생한다.
        // 답변 데이터 리스트는 q 객체를 조회할때 가져오지 않고 q.getAnswerList() 메서드를 호출하는 시점에 가져오기 때문이다.
        // 이렇게 필요한 시점에 데이터를 가져오는 방식을 Lazy 방식이라고 한다
        // @Transactional 애너테이션을 사용하면 메서드가 종료될 때 까지 DB 세션이 유지된다.
        List<Answer> answerList = question.getAnswerList();
        assertEquals(1,answerList.size());
        System.out.println("answerList.get(0).getContent() = " + answerList.get(0).getContent());
    }
}
