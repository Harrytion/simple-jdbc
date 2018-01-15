package com.jackrams.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DefaultDataSourceLoader implements DataSourceLoader {

    private DataSource dataSource;

    @Override
    public void setDataSource()  {
        if(getDataSource()==null) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dataSource.properties");
            DruidDataSource dataSource = new DruidDataSource();
            Properties properties = new Properties();
            try {
                properties.load(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
            String driver = properties.getProperty("jdbc.driver");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            this.dataSource=dataSource;
            this.setDataSource(dataSource);
        }else{
            setDataSource(getDataSource());
        }
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        JdbcUtils.setDataSource(dataSource);
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }
}
