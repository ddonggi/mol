package com.dglee.mini_prj.question;
/*
 * Created by 이동기 on 2022-11-11
 */

import com.dglee.mini_prj.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

/*    public List<Question> getList(){
        return this.questionRepository.findAll();
    }*/
    // List -> Page
    public Page<Question> getList(int page){
        List<Sort.Order> sortedList = new ArrayList<>();
        sortedList.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,10,Sort.by(sortedList));
        return questionRepository.findAll(pageable);
        /*
        * 게시물을 역순으로 조회하기 위해서는 위와 같이 PageRequest.of 메서드의 세번째 파라미터로 Sort 객체를 전달해야 한다.
        *  Sort.Order 객체로 구성된 리스트에 Sort.Order 객체를 추가하고 Sort.by(소트리스트)로 소트 객체를 생성할 수 있다.
        * 작성일시(createDate)를 역순(Desc)으로 조회하려면 Sort.Order.desc("createDate") 같이 작성한다.
        * 만약 작성일시 외에 추가로 정렬조건이 필요할 경우에는 sorts 리스트에 추가하면 된다.
        * */
        // NOTE : page 는 optional 이 안되는가 //
    }

    public Question getQuestion(Integer id){
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent())
            return optionalQuestion.get();
        else
            throw new DataNotFoundException("question not found");
    }

    public void create(String title, String content){
//        QuestionDTO questionDTO = new QuestionDTO(title,content,LocalDateTime.now());
        QuestionDTO questionDTO = QuestionDTO.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .build();
        Question question = questionDTO.toEntity();
        questionRepository.save(question);
    }

    public void delete(Integer id) {
//        Question question = Question.builder().build();
//        questionRepository.delete(question);
    }
}
