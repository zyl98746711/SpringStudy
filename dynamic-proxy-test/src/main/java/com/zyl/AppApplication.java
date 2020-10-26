package com.zyl;

import com.zyl.annotation.FilterStrScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangyanlong
 */
@SpringBootApplication
@FilterStrScan(basePackages = {"com.zyl.testproxy.**"})
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
