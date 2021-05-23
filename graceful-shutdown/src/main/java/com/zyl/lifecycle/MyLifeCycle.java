package com.zyl.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author zyl
 */
@Component
public class MyLifeCycle implements SmartLifecycle {

    private boolean isRunning = false;

    @Override
    public void start() {
        System.out.println("My Lifecycle starting……");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("My Lifecycle stop ……");
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        System.out.println("My Lifecycle is running ……");
        return isRunning;
    }
}
