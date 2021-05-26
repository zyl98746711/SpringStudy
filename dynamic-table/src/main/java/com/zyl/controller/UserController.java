package com.zyl.controller;

import com.zyl.domain.User;
import com.zyl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyl
 * @create 2020/12/13 1:05 下午
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 保存用户
     */
    @GetMapping
    public void saveUser() {
        User user = new User();
        user.setAge(11);
        user.setUserName("abc");
        userMapper.save(user);
    }

}
