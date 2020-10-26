package com.zyl.factory;

import com.zyl.proxy.FilterProxy;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangyanlong
 */
public class FilterStrFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private Class<T> clazz;

    public FilterStrFactoryBean() {
        super();
    }

    public FilterStrFactoryBean(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new FilterInvocationHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return this.clazz;
    }

    public static class FilterInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Object自带的方法不做任何代理动作
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (Exception t) {
                    throw ExceptionUtils.getRootCause(t);
                }
            }
            return new FilterProxy().print(args[0].toString());
        }
    }
}
