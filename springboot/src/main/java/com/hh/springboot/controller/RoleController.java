package com.hh.springboot.controller;

import com.hh.springboot.common.Result;
import com.hh.springboot.entity.Role;
import com.hh.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/save")
    public Result save(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam int pageNum,
                            @RequestParam int pageSize,
                            @RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "") String description) {
        return roleService.pageQuery(pageNum, pageSize, name, description);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Role role) {
        return roleService.deleteById(role);
    }

    @PostMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return roleService.deleteBatch(ids);
    }
}
