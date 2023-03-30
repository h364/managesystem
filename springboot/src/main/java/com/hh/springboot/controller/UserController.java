package com.hh.springboot.controller;

import com.hh.springboot.common.Result;
import com.hh.springboot.entity.User;
import com.hh.springboot.service.DTO.UserDTO;
import com.hh.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @PostMapping("/registry")
    public Result registry(@RequestBody UserDTO userDTO) {
        return userService.registry(userDTO);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam int pageNum,
                           @RequestParam int pageSize,
                           @RequestParam(defaultValue = "") String username,
                           @RequestParam(defaultValue = "") String email,
                           @RequestParam(defaultValue = "") String address) {
        return userService.pageQuery(pageNum, pageSize, username, email, address);
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody User user) {
        return userService.deleteById(user);
    }

    @PostMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return userService.deleteBatch(ids);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        userService.handleExport(response);
    }

    @PostMapping("/import")
    public void imp(MultipartFile file) {
        userService.handleImp(file);
    }
}
