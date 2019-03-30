package com.roxi.demo.aop;

import com.roxi.demo.Util.Intparse;
import com.roxi.demo.service.MsgService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class DeleteAop {

    @Autowired
    MsgService msgService;

    @Pointcut("@annotation(com.roxi.demo.aop.Cut)")
    public void cut(){
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] objects=pjp.getArgs();
        HttpSession session= (HttpSession) objects[0];
        String account= (String) session.getAttribute("user");
        int pid= Intparse.defalutInt(String.valueOf(objects[1]));
        if(msgService.isTrue(account,pid)) return pjp.proceed();
        else return "不是你的留言";
    }
}
