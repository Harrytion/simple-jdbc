package com.jackrams.spring;

import com.jackrams.AbstractDao;
import com.jackrams.contants.Constants;
import com.jackrams.scanner.AbstractScanner;
import com.jackrams.utils.JdbcUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class SpringScanner extends AbstractScanner implements ApplicationContextAware {

    private transient Log log = LogFactory.getLog(SpringScanner.class);

    private boolean checkIsAbstractDaoSubClass(Class beanClass){

        return AbstractDao.class.isAssignableFrom(beanClass);
    }

    @Override
    public void doScan(String... packages) {

       super.doScan(packages);
    }


    private void setDomain(Object bean,Class domainClass) throws Exception{
        Class<AbstractDao> abstractBaseDaoClass = AbstractDao.class;
        Field domainClassField = abstractBaseDaoClass.getDeclaredField("domainClass");
        domainClassField.setAccessible(true);
        domainClassField.set(bean,domainClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Set<String> domainPackage = new HashSet<>();
        for (String beanName : beanDefinitionNames){
            Object bean = applicationContext.getBean(beanName);
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
            if(checkIsDataSource(beanClass)){
                JdbcUtils.setDataSource((DataSource)bean);
            }
        }

        log.info("doScan for" +domainPackage.toString());
        doScan(domainPackage.toArray(new String[domainPackage.size()]));
        log.info("scan complete");


    }

    private boolean checkIsDataSource(Class beanClass){

        return DataSource.class.isAssignableFrom(beanClass);
    }

    public void setShowSql(boolean showSql){
        Constants.showSql=showSql;
    }
}
