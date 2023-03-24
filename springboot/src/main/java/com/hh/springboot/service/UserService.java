package com.hh.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.springboot.entity.User;
import com.hh.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    UserMapper userMapper;

    public IPage<User> pageQuery(int pageNum, int pageSize, String username, String email, String address) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            wrapper.like("username", username);
        }
        if (!"".equals(email)) {
            wrapper.like("email", email);
        }
        if (!"".equals(address)) {
            wrapper.like("address", address);
        }
        IPage<User> page = new Page<>(pageNum, pageSize);
        return page(page, wrapper);
    }

    public Boolean insertUser(User user) {
        boolean update = saveOrUpdate(user);
        return update;
    }

    public boolean deleteById(User user) {
        boolean remove = removeById(user);
        return remove;
    }

    public boolean deleteBatch(List<Integer> ids) {
        boolean batch = removeBatchByIds(ids);
        return batch;
    }
}
