package com.jackrams.spring;

import com.jackrams.AbstractBaseDaoImpl;
import com.jackrams.scanner.AbstractScanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SpringScanner extends AbstractScanner implements  BeanFactoryPostProcessor {

    private transient Log log = LogFactory.getLog(SpringScanner.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Iterator<String> beanNamesIterator = configurableListableBeanFactory.getBeanNamesIterator();
        Set<String> domainPackage = new HashSet<>();
        while (beanNamesIterator.hasNext()){
            String beanName = beanNamesIterator.next();
            Object bean = configurableListableBeanFactory.getBean(beanName);
            Class<?> beanClass = bean.getClass();
            if(checkIsAbstractDaoSubClass(beanClass)){
                ParameterizedType genericSuperclass =(ParameterizedType) beanClass.getGenericSuperclass();
                Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
                final Class domainClass = (Class)actualTypeArguments[0];
                try {
                    setDomain(bean,domainClass);
                }catch (Exception e){
                    log.error("Set Domain Fail in "+ beanClass.getName());
                }
                domainPackage.add(domainClass.getPackage().getName());
            }
        }
        log.info("doScan for" +domainPackage.toString());
        doScan(domainPackage.toArray(new String[domainPackage.size()]));
        log.info("scan complete");
    }

    private boolean checkIsAbstractDaoSubClass(Class beanClass){

        return AbstractBaseDaoImpl.class.isAssignableFrom(beanClass);
    }

    @Override
    public void doScan(String... packages) {

       super.doScan(packages);
    }


    private void setDomain(Object bean,Class domianClass) throws Exception{
        Class<AbstractBaseDaoImpl> abstractBaseDaoClass = AbstractBaseDaoImpl.class;
        Field domainClass = abstractBaseDaoClass.getDeclaredField("domainClass");
        domainClass.setAccessible(true);
        domainClass.set(bean,domainClass);
    }
}
