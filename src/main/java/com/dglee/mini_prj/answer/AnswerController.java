package com.dglee.mini_prj.answer;

import com.dglee.mini_prj.question.Question;
import com.dglee.mini_prj.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Created by 이동기 on 2022-11-11
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable("id") Integer id, @RequestParam String content){
        Question question = questionService.getQuestion(id);
        answerService.create(id,question,content);
        return String.format("redirect:/question/detail/%s", id);
    }
}
