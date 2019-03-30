package com.roxi.demo.mapper;

import com.roxi.demo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
public interface UserMapper {

    @Insert("insert into user (id,account,password) values (#{id},#{account},#{password})")
    @Options(useGeneratedKeys = true ,keyColumn = "id",keyProperty = "id")
    public boolean insertUser(User user);

    @Select("select password from user where account=#{account}")
    public String selectUser(User user);
}
