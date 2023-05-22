package com.hh.springboot.controller;

import com.hh.springboot.common.Result;
import com.hh.springboot.entity.Menu;
import com.hh.springboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("/save")
    public Result save(@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,@RequestParam(defaultValue = "") String description) {
        return menuService.pageQuery(name, description);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Menu menu) {
        return menuService.deleteById(menu);
    }

    @PostMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return menuService.deleteBatch(ids);
    }
}
