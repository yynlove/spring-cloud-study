package com.hello.demo.controller;

import com.hello.demo.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeloPostController {


    @RequestMapping(value = "/insertOne")
    public String insertOne(@RequestParam User user){
        return "新增一条记录成功。"+user.toString();
    }

}
