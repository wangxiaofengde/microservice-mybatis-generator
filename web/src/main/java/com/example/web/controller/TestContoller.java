package com.example.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("测试")
@RestController
@RequestMapping("test")
public class TestContoller {
    @ApiOperation("添加")
    @PostMapping("/add")
    public void add(int a ,String name){
        System.out.println("add success");
    }
}
