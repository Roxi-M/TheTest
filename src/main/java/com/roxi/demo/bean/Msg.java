package com.roxi.demo.bean;

import lombok.Data;

import java.util.List;

@Data
public class Msg {
    private String context;
    private String name;
    private int pid;
    private int father;
    private List<Msg> msgs;
    private boolean hide;
    public Msg(){

    }
    public Msg(String name,String context,int father,boolean hide){
        this.context=context;
        this.name=name;
        this.father=father;
        this.hide=hide;
        //这里明天写一个builder 代理试试
    }

}
