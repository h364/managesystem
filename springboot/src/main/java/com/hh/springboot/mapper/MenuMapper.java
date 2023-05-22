package com.hh.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.springboot.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
