package com.roxi.demo.service;

import com.roxi.demo.Util.Intparse;
import com.roxi.demo.bean.Dz;
import com.roxi.demo.bean.Msg;
import com.roxi.demo.mapper.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MsgService {
    @Autowired
    private MsgMapper msgMapper;
    public boolean insertMsg(Msg msg){
        msg.setContext(Intparse.defalutString(msg.getContext()));
        msgMapper.insertMsg(msg);
        return true;
    }
    public Msg selectMsg(int pid){
        Msg msg= new Msg();
        msg.setPid(pid);
        msg=msgMapper.selectMsg(msg);
        List<Msg> msgList=new ArrayList<Msg>();
        List<Integer> list=msgMapper.selectChild(msg);
        for (int pid1 : list) {
            Msg msg1 = new Msg();
            msg1.setPid(pid1);
            msgList.add(selectChild(msg1));
        }
        msg.setMsgs(msgList);
        return msg;
    }

    private Msg selectChild(Msg msg){
        msg=msgMapper.selectMsg(msg);
        List<Integer> list=msgMapper.selectChild(msg);
        List<Msg> msgList=new ArrayList<Msg>();
        for (int pid : list) {
            Msg msg1 = new Msg();
            msg1.setPid(pid);
            msgList.add(selectChild(msg1));
        }
        msg.setMsgs(msgList);
        return msg;
    }

    public boolean isDz(Dz dz){
        Dz dz1=msgMapper.select(dz);
       if(dz1==null){
           msgMapper.insertDz(dz);
           return true;
       } else {
           msgMapper.deleteDz(dz);
           return  false;
       }
    }

    public boolean delete(int pid){
        Msg msg=new Msg();
        msg.setPid(pid);
        List<Integer> list=msgMapper.selectChild(msg);
        for (int pid1 : list) {
            Msg msg1 = new Msg();
            msg1.setPid(pid1);
            deleteChild(msg1);
        }
        msgMapper.deleteMsg(msg);
        return true;
    }

    private void deleteChild(Msg msg){
        List<Integer> list=msgMapper.selectChild(msg);
        for(int pid:list){
            Msg msg1=new Msg();
            msg1.setPid(pid);
            deleteChild(msg1);
        }
        msgMapper.deleteMsg(msg);
    }

    public boolean isTrue(String account,int pid){
        Msg msg = msgMapper.selectTrue(account,pid);
        return msg != null;
    }
}
