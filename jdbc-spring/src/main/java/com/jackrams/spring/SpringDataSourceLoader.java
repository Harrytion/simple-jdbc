package com.jackrams.spring;

import com.jackrams.utils.DataSourceLoader;
import com.jackrams.utils.JdbcUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.sql.DataSource;

public class SpringDataSourceLoader implements DataSourceLoader,BeanPostProcessor {

    private DataSource dataSource ;
    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        JdbcUtils.setDataSource(dataSource);
    }

    @Override
    public void setDataSource() {
        JdbcUtils.setDataSource(dataSource);
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        if(checkIsDataSource(bean)) this.setDataSource((DataSource)bean);
        return bean;
    }

    private boolean checkIsDataSource(Object bean){

        return DataSource.class.isAssignableFrom(bean.getClass());
    }


}
