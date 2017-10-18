package com.tianyue.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by FuWeiJin on 2017/10/17 16:48
 */

@Controller
@RequestMapping("/common")
public class CommonController {

    @ResponseBody
    @RequestMapping("/test")
    public String doTestWeb(){
        return "Hello Controller";
    }
}
