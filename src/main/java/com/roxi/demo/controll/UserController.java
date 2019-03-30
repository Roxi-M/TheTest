package com.roxi.demo.controll;

import com.roxi.demo.Util.Intparse;
import com.roxi.demo.bean.Msg;
import com.roxi.demo.bean.User;
import com.roxi.demo.service.MsgService;
import com.roxi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
//@RequestMapping("/quanxian")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/registe")
    public String enroll(@RequestParam("account") String account,@RequestParam("password") String password){
        User user=new User(account,password);
        return String.valueOf(userService.insert(user));
    }
    @GetMapping("login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session){
        User user=new User(account,password);
        if(userService.select(user)){
            session.setAttribute("user",account);
            return "hello"+account;
        }else {
            return "登陆失败";
        }
    }
}
