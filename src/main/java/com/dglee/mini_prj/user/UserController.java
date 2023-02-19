package com.dglee.mini_prj.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * PackageName : com.dglee.mini_prj.user
 * FileName : UserController
 * Author : dglee
 * Create : 2023/02/19 2:13 PM
 * Description : 유저 컨트롤러
 **/

@Controller
@RequiredArgsConstructor //final 이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할을 한다
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "signup_form"; // 회원가입을 위한 템플릿 렌더링
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "signup_form";
        if(!userCreateForm.getPassword().equals(userCreateForm.getPassword2())){ // 비밀번호 2개가 동일하지 않을 경우
            // NOTE : 대형 프로젝트에서는 번역과 관리를 위해 오류코드를 잘 정의하여 사용해야 한다.
            bindingResult.rejectValue("password2","passwordIncorrect","2개의 패스워드가 일치하지 않습니다"); // rejectValue 오류 발생 시킴
            return "signup_form";
        }
        userService.create(userCreateForm.getUsername(),userCreateForm.getPassword(),userCreateForm.getEmail());
        return "redirect:/";
    }
}
