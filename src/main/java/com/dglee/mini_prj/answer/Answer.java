package com.dglee.mini_prj.answer;

import com.dglee.mini_prj.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    public Answer(String content,LocalDateTime createDate,Question question){
        this.content = content;
        this.createDate = createDate;
        this.question = question;
    }
    @ManyToOne
    private Question question;
}