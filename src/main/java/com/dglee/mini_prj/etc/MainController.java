package com.dglee.mini_prj.etc;
/*
 * Created by 이동기 on 2022-11-10
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("signup")
    public String signUp(){ return "signup";}
}
