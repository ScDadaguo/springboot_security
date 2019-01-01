package com.example.springboot_security.controller;

import com.example.springboot_security.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yangyibo on 17/1/18.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/admin")
    public String admin(){

        return "ROLE_ADMIN";
    }
    @RequestMapping("/user")
    public String user(){

        return "ROLE_USER";
    }


}
