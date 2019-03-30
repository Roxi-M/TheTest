package com.roxi.demo.mapper;

import com.roxi.demo.bean.Dz;
import com.roxi.demo.bean.Msg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MsgMapper {

    @Insert("insert into msg (name,context,pid,father,hide) values(#{name},#{context},#{pid},#{father},#{hide})")
    @Options(keyColumn = "pid", useGeneratedKeys = true)
    boolean insertMsg(Msg msg);

    @Select("select * from msg where pid=#{pid}")
     Msg selectMsg(Msg msg);

    @Select("select pid from msg where father=#{pid}")
    List<Integer> selectChild(Msg msg);

    @Select("select * from dz where id=#{id} and pid=#{pid}")
    Dz select(Dz dz);

    @Insert("insert into dz(id,pid) values(#{id},#{pid})")
     boolean insertDz(Dz dz);

    @Delete("delete from dz where id=#{id} and pid=#{pid}")
     boolean deleteDz(Dz dz);

    @Delete("delete from msg where pid=#{pid}")
     void deleteMsg(Msg msg);

    @Select("select context from msg where name=#{account} and pid=#{pid}")
     Msg selectTrue(String account,int pid);
}
