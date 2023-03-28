package com.hh.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    public static final String CODE_200 = "200";  //成功
    public static final String CODE_500 = "500";  //系统错误
    public static final String CODE_401 = "401";  //权限不足


    private String code;
    private String msg;
    private Object data;
}
