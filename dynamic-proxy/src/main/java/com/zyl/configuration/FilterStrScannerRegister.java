package com.zyl.configuration;

import com.zyl.annotation.FilterStrScan;
import com.zyl.factory.FilterStrFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解扫描注册类
 *
 * @author zhangyanlong
 */
public class FilterStrScannerRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    /**
     * 资源扫描类
     */
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(FilterStrScan.class.getName()));
        FilterStrPathBeanDefinitionScanner scanner = new FilterStrPathBeanDefinitionScanner(registry);
        if (null != resourceLoader) {
            // 将资源扫描类添加进去
            scanner.setResourceLoader(resourceLoader);
        }
        Class<? extends Annotation> annotationClass = annotationAttributes.getClass("annotationClass");
        if (!Annotation.class.equals(annotationClass)) {
            scanner.setAnnotationClass(annotationClass);
        }
        Class<? extends BeanNameGenerator> generatorClass = annotationAttributes.getClass("nameGenerator");
        if (!BeanNameGenerator.class.equals(generatorClass)) {
            scanner.setBeanNameGenerator(BeanUtils.instantiateClass(generatorClass));
        }
        Class<? extends FilterStrFactoryBean> factoryBean = annotationAttributes.getClass("factoryBean");
        if (FilterStrFactoryBean.class.equals(factoryBean)) {
            FilterStrFactoryBean<?> filterStrFactoryBean = BeanUtils.instantiateClass(factoryBean);
            scanner.setFactoryBean(filterStrFactoryBean);
        }
        List<String> basePackages = new ArrayList<>();
        for (String pkg : annotationAttributes.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : annotationAttributes.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        scanner.registerFilters();
        scanner.doScan(basePackages);
    }


}
