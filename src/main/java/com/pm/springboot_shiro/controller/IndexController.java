package com.pm.springboot_shiro.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2019/7/12 10:02
 * @Created by Eaven
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView showIndex(){
        ModelAndView mv = new ModelAndView("back/index");
        return mv;
    }
}
