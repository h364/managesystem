package com.hh.springboot.controller;

import com.hh.springboot.common.Result;
import com.hh.springboot.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    EchartsService echartsService;

    @GetMapping("/get")
    public Result getData() {
        return echartsService.getData();
    }
}
