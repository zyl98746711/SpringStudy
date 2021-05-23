package com.zyl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zyl
 */
@Component
public class DelayListener implements ApplicationListener<DelayEvent> {

    @Autowired
    private DelayProcessor delayProcessor;

    @Override
    public void onApplicationEvent(DelayEvent event) {
        Runnable delaySource = event.getDelaySource();
        delayProcessor.registerDelayEvent(delaySource);
    }
}
