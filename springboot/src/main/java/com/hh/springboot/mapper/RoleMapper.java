package com.hh.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.springboot.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
