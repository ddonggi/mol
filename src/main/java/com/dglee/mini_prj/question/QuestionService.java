package com.dglee.mini_prj.question;
/*
 * Created by 이동기 on 2022-11-11
 */

import com.dglee.mini_prj.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Pageable pageable = PageRequest.of(page,10);
        return questionRepository.findAll(pageable);
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
