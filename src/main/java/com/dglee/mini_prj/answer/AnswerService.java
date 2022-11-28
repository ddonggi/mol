package com.dglee.mini_prj.answer;
/*
 * Created by 이동기 on 2022-11-11
 */

import com.dglee.mini_prj.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content){
//        AnswerDTO answerDTO = new AnswerDTO(id, content, LocalDateTime.now(),question);
//        AnswerDTO answerDTO = new AnswerDTO(id, content, LocalDateTime.now(),question);
        AnswerDTO answerDTO = AnswerDTO.builder()
                .content(content).createDate(LocalDateTime.now()).question(question).build();
        Answer answer = answerDTO.toEntity();
        answerRepository.save(answer);
    }
}
