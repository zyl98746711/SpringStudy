package com.zyl.configuration;

import com.zyl.annotation.FilterStr;
import com.zyl.factory.FilterStrFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 注解资源扫描
 *
 * @author zhangyanlong
 */
public class FilterStrPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private FilterStrFactoryBean<?> filterStrFactoryBean;
    private Class<? extends Annotation> annotationClass;
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterStrPathBeanDefinitionScanner.class);

    public FilterStrPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setFactoryBean(FilterStrFactoryBean<?> factoryBean) {
        this.filterStrFactoryBean = factoryBean != null ? factoryBean : new FilterStrFactoryBean<>();
    }

    public void registerFilters() {
        super.addIncludeFilter(new AnnotationTypeFilter(FilterStr.class));
    }

    public void doScan(List<String> backPackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(backPackages.toArray(new String[0]));
        if (CollectionUtils.isEmpty(beanDefinitionHolders)) {
            LOGGER.warn("There is No FilterStr Was Found");
        } else {
            processBeanDefinition(beanDefinitionHolders);
        }
    }

    /**
     * 将beanDefinition注入Factory信息
     *
     * @param beanDefinitionHolders
     */
    private void processBeanDefinition(Set<BeanDefinitionHolder> beanDefinitionHolders) {
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            String beanClassName = beanDefinition.getBeanClassName();
            if (Objects.nonNull(beanClassName)) {
                beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            }
            beanDefinition.setBeanClass(this.filterStrFactoryBean.getClass());
        }
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
