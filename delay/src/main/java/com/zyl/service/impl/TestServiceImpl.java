package com.zyl.service.impl;

import com.zyl.configuration.DelayEvent;
import com.zyl.configuration.DelayTrigger;
import com.zyl.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author zyl
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private ApplicationContext applicationContext;

    @DelayTrigger
    @Override
    public void test() {
        applicationContext.publishEvent(new DelayEvent(() -> {
            System.out.println("处理完成后的事件……");
        }));
        int a = 1 / 0;
        System.out.println("testing……");
    }
}
