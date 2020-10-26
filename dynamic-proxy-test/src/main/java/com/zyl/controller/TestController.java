package com.zyl.controller;

import com.zyl.testproxy.TestProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyanlong
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestProxy testProxy;

    @GetMapping
    public String testProxy(@RequestParam("name") String name) {
        return testProxy.print(name);
    }

}
