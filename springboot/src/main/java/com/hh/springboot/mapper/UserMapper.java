package com.hh.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from user")
//    List<User> selectAll();
//
//    @Insert("insert into user(username, password, nickname, email, phone, address) values(#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
//    int insert(User user);
//
//    int update();
//
//    @Delete("delete from user where id = #{id}")
//    int deleteById(int id);
//
//    @Select("select * from user limit #{pageNum},#{pageSize}")
//    List<User> selectPage(int pageNum, int pageSize);
//
//    @Select("select count(*) from user")
//    int userTotal();
//
//    @Select("select * from user where username like concat('%', #{str}, '%')")
//    List<User> searchByName(@Param("str") String str);
}
