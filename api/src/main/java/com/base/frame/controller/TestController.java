package com.base.frame.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/index")
    public String index(){
        return "test index";
    }

    @RequestMapping("/index2")
    public String index2(){
        return "test index2";
    }

}