package com.pm.springboot_shiro.controller;
import com.pm.springboot_shiro.dao.common.Message;
import com.pm.springboot_shiro.pojo.Manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname LoginController
 * @Description TODO
 * @Date 2019/7/12 9:22
 * @Created by Eaven
 */
@Controller
@RequestMapping("/back")
public class LoginController {

    /**
     * 登陆校验
     * @param manager
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Message login(Manager manager){
        Message msg = new Message();
        if(manager == null){
            msg.setCode(400);
            msg.setMsg("账号密码为空！");
        }
            String username = manager.getUsername();
            String password = manager.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,false);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            msg.setCode(200);
            msg.setMsg("登陆成功！");
        }catch (Exception e){
            msg.setCode(400);
            msg.setMsg("账号或者密码错误！");
        }
        return msg;
    }

    /**
     * 跳转到主页
     * @return
     */
    @GetMapping("/home")
    public String home(){
        return "back/main";
    }
}
