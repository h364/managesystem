package com.hh.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hh.springboot.entity.User;
import com.hh.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam int pageNum,
                                @RequestParam int pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
        return userService.pageQuery(pageNum, pageSize, username, email, address);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody User user) {
        return userService.deleteById(user);
    }

    @PostMapping("/delete/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.deleteBatch(ids);
    }
}
