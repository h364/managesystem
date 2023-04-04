package com.hh.springboot.service;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.springboot.common.Result;
import com.hh.springboot.common.utils.JWTUtils;
import com.hh.springboot.entity.User;
import com.hh.springboot.mapper.UserMapper;
import com.hh.springboot.service.DTO.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    UserMapper userMapper;

    public Result pageQuery(int pageNum, int pageSize, String username, String email, String address) {
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
        IPage<User> pages = new Page<>(pageNum, pageSize);
        return new Result(Result.CODE_200, "", page(pages, wrapper));
    }

    public Result insertUser(User user) {
        boolean update = saveOrUpdate(user);
        if (update) {
            return new Result(Result.CODE_200, "保存成功", true);
        } else {
            return new Result(Result.CODE_500, "保存失败", false);
        }
    }

    public Result deleteById(User user) {
        boolean remove = removeById(user);
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

    public void handleExport(HttpServletResponse response) {
        List<User> list = list();
        String fileName = "";
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            fileName = URLEncoder.encode("用户信息", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    public void handleImp(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> list = reader.readAll(User.class);
    }

    public Result login(UserDTO userDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDTO.getUsername());
        wrapper.eq("password", userDTO.getPassword());
        User one = getOne(wrapper);
        if (one != null) {
            BeanUtils.copyProperties(one, userDTO);
            userDTO.setToken(JWTUtils.createToken(one.getId().toString(), one.getPassword()));
            return new Result(Result.CODE_200, "", userDTO);
        } else {
            return new Result(Result.CODE_500, "用户名或密码错误", null);
        }
    }

    public Result registry(UserDTO userDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDTO.getUsername());
        User one = getOne(wrapper);
        if (one != null) {
            return new Result(Result.CODE_500, "用户已存在", null);
        } else {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return insertUser(user);
        }
    }

    public Result getUser(UserDTO userDTO) {
        String userId = JWT.decode(userDTO.getToken()).getAudience().get(0);
        User user = getById(userId);
        if (user != null) {
            return new Result(Result.CODE_200, "", user);
        } else {
            return new Result(Result.CODE_500, "系统错误", null);
        }
    }
}
