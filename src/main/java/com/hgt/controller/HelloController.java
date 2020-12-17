package com.hgt.controller;

import com.hgt.pojo.Person;
import com.hgt.pojo.ResponseResult;
import com.hgt.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
/*第二步：使用自定义包装类注解*/
@ResponseResult
public class HelloController {


    @RequestMapping("hello")
    public String hello() {
        String s = null;
        return s;
    }

    @RequestMapping(path = "result")
    public Result testResult() {
        // 无数据的返回值
//        return Result.success();
        // 有数据的返回值
        return Result.success("字符串代替数据");
    }

    @RequestMapping(path = "login")
    public String login(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {
        Person p = null;
        System.out.println(p.getName());
        return username + password;
    }

    /*测试拦截器*/
    @RequestMapping(path = "testInterceptor")
    public String testInterceptor() {
        return "testInterceptor";
    }
}
