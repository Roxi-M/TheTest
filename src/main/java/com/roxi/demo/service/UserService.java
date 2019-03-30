package com.roxi.demo.service;

import com.roxi.demo.bean.User;
import com.roxi.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
   private UserMapper userMapper;

    public boolean insert(User user){
        return userMapper.insertUser(user);
    }

    public boolean select(User user){
        String password=  userMapper.selectUser(user);
        return user.getPassword().equals(password);
    }


}
