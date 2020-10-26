package com.zyl.testproxy;


import com.zyl.annotation.FilterStr;

/**
 * @author zhangyanlong
 */
@FilterStr(value = "test")
public interface TestProxy {

    String print(String name);
}
