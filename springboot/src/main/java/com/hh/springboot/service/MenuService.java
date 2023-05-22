package com.hh.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.springboot.common.Result;
import com.hh.springboot.entity.Menu;
import com.hh.springboot.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {
    public Result saveMenu(Menu menu) {
        boolean update = saveOrUpdate(menu);
        if (update) {
            return new Result(Result.CODE_200, "保存成功", true);
        } else {
            return new Result(Result.CODE_500, "保存失败", false);
        }
    }

    public Result pageQuery(String name, String description) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(description)) {
            wrapper.like("description", description);
        }
        List<Menu> list = list(wrapper);
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for(Menu menu : parentNode) {
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return new Result(Result.CODE_200, "", parentNode);
    }

    public Result deleteById(Menu menu) {
        boolean remove = removeById(menu);
        if (remove) {
            return new Result(Result.CODE_200, "删除数据成功", true);
        } else {
            return new Result(Result.CODE_500, "删除数据失败", false);
        }
    }

    public Result deleteBatch(List<Integer> ids) {
        boolean batch = removeBatchByIds(ids);
        if (batch) {
            return new Result(Result.CODE_200, "删除数据成功", true);
        } else {
            return new Result(Result.CODE_500, "删除数据失败", false);
        }
    }
}
