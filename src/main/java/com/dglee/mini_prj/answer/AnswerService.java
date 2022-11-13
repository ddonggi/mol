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

    public void create(Integer id,Question question, String content){
        AnswerDTO answerDTO = new AnswerDTO(id, content, LocalDateTime.now(),question);
        Answer answer = answerDTO.toEntity();
        answerRepository.save(answer);
    }
}
