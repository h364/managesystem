package com.hh.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.springboot.common.Result;
import com.hh.springboot.entity.RoleMenu;
import com.hh.springboot.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {

    public Result setRoleMenu(Integer role_id, List<Integer> menu_id) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", role_id);
        remove(queryWrapper);
        for (Integer menuId : menu_id) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role_id);
            roleMenu.setMenuId(menuId);
            save(roleMenu);
        }
        return new Result(Result.CODE_200, "保存成功", null);
    }

    public Result getData(Integer id) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        List<RoleMenu> list = list(queryWrapper);
        List<Integer> collect = list.stream().map(roleMenu -> roleMenu.getMenuId()).collect(Collectors.toList());
        return new Result(Result.CODE_200, "", collect);
    }
}
