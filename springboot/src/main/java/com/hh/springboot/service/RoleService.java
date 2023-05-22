package com.hh.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.springboot.common.Result;
import com.hh.springboot.entity.Role;
import com.hh.springboot.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    public Result saveRole(Role role) {
        boolean update = saveOrUpdate(role);
        if (update) {
            return new Result(Result.CODE_200, "保存成功", true);
        } else {
            return new Result(Result.CODE_500, "保存失败", false);
        }
    }

    public Result pageQuery(int pageNum, int pageSize, String name, String description) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(description)) {
            wrapper.like("description", description);
        }
        IPage<Role> pages = new Page<>(pageNum, pageSize);
        return new Result(Result.CODE_200, "", page(pages, wrapper));
    }

    public Result deleteById(Role role) {
        boolean remove = removeById(role);
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
