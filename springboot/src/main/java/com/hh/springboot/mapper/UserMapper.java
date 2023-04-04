package com.hh.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.springboot.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
