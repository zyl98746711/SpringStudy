package com.zyl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zyl
 */
@Configuration
public class ExecutorConfiguration {

    @Bean(DelayProcessor.EXECUTOR_NAME)
    public Executor executor() {
        return new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new LinkedBlockingDeque());
    }
}
