package com.hh.springboot.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.hh.springboot.common.Result;
import com.hh.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EchartsService {
    @Autowired
    UserService userService;

    public Result getData() {
        List<User> list = userService.list();
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for(User user : list) {
            Date create_time = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(create_time);
            switch (quarter) {
                case Q1: q1 += 1; break;
                case Q2: q2 += 1; break;
                case Q3: q3 += 1; break;
                case Q4: q4 += 1; break;
                default: break;
            }
        }
        return new Result(Result.CODE_200, "", CollUtil.newArrayList(q1, q2, q3, q4));
    }
}
