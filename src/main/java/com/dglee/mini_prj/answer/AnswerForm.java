package com.dglee.mini_prj.answer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/*
 * Create by dglee on 2022/11/23
 */
@Setter
@Getter
public class AnswerForm {
    @NotEmpty(message = "내용은 필수 항목 입니다")
    private String content;
}
