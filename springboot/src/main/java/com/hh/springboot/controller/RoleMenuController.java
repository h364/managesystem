package com.hh.springboot.controller;

import com.hh.springboot.common.Result;
import com.hh.springboot.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rolemenu")
public class RoleMenuController {

    @Autowired
    RoleMenuService roleMenuService;

    @PostMapping("/save/{roleId}")
    public Result setRoleMenu(@PathVariable("roleId") Integer role_id, @RequestBody List<Integer> menu_id) {
        return roleMenuService.setRoleMenu(role_id, menu_id);
    }

    @GetMapping("/get")
    public Result getSelectData(@RequestParam Integer id) {
        return roleMenuService.getData(id);
    }
}
