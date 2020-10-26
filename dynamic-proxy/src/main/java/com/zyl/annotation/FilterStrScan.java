package com.zyl.annotation;

import com.zyl.configuration.FilterStrScannerRegister;
import com.zyl.factory.FilterStrFactoryBean;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhangyanlong
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FilterStrScannerRegister.class)
public @interface FilterStrScan {

    String[] value() default {};

    String[] basePackages() default {};

    Class<? extends Annotation> annotationClass() default Annotation.class;

    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

    Class<? extends FilterStrFactoryBean> factoryBean() default FilterStrFactoryBean.class;
}
