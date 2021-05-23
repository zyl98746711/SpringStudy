package com.zyl.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zyl
 */
@Configuration
public class DelayConfiguration {


    @Bean
    public DelayProcessor delayProcessor() {
        return new DelayProcessor();
    }

    @Aspect
    @Component
    public static class DelayAspect {

        @Autowired
        private DelayProcessor delayProcessor;

        @Pointcut("@annotation(DelayTrigger)")
        public void delayAspect() {
        }

        @Before("delayAspect()")
        public void before() {
            System.out.println("before……");
            delayProcessor.initDelay();
        }

        @AfterReturning("delayAspect()")
        public void after(JoinPoint joinPoint) {
            System.out.println("returned……");
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 自定义
            //DelayTrigger annotation = method.getAnnotation(DelayTrigger.class);
            delayProcessor.execute();
        }

        @AfterThrowing("delayAspect()")
        public void throwing(JoinPoint joinPoint) {
            delayProcessor.clear();
        }
    }
}
