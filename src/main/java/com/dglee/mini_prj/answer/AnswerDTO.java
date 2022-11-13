package com.dglee.mini_prj.answer;
/*
 * Created by 이동기 on 2022-11-11
 */

import com.dglee.mini_prj.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Integer id;

    private String content;

    private LocalDateTime createDate;
    private Question question;

    public Answer toEntity(){
        return new Answer(content,createDate,question);
    }
}
