package com.zyl.configuration;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author zyl
 */
@Configuration
@MapperScan("com.zyl.mapper.**")
public class MyBatisConfiguration {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void configSqlSessionFactory() {
        sqlSessionFactory.getConfiguration().addInterceptor(new MybatisTableInterceptor());
    }


}
