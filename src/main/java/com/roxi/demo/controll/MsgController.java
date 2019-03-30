package com.roxi.demo.controll;


import com.roxi.demo.Util.Intparse;
import com.roxi.demo.aop.Cut;
import com.roxi.demo.bean.Dz;
import com.roxi.demo.bean.Msg;
import com.roxi.demo.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MsgController {
    @Autowired
    private MsgService msgService;
    @GetMapping("/write")
    public String write(HttpSession session, String context, String father, @RequestParam("isHide") String isHide){
        String account= (String) session.getAttribute("user");
        int node = Intparse.defalutInt(father);
        boolean hide=Intparse.defalutBoolean(isHide);
        Msg msg=new Msg(account,context,node,hide);
        if(msgService.insertMsg(msg)){
            return "留言成功";
        }else {
            return "false";
        }
    }

    @GetMapping("do")
    public Msg can(@RequestParam("pid") String pid){
        int pdd=Intparse.defalutInt(pid);
        return msgService.selectMsg(pdd);
    }
    @GetMapping("dz")
    public String dz(Dz dz){
        // 应该是传入参数 为 session 和 pid 这里只是暂时这样 点赞就是实现好了
        if(msgService.isDz(dz))
        return "成功👍";
        else return "取消👍";
    }

    /*@GetMapping("isyours")
    public String isyours(HttpSession session){
        String account= (String) session.getAttribute("user");
       //判断是否这是不是你发的留言，或者是不是你的评论，如果是会提供删除按钮
    }*/

    @Cut
    @GetMapping("delete")
    public String delete(HttpSession session,@RequestParam("pid") String pid){
        msgService.delete(Intparse.defalutInt(pid));
        return "删除留言成功";
    }
}
