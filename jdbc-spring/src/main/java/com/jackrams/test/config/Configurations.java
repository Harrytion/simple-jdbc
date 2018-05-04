package com.jackrams.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.jackrams.scanner.Scanner;
import com.jackrams.spring.SpringScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.jackrams.test.dao"})
public class Configurations {
    @Bean
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://base:3306/test?useSSL=false&rewriteBatchedStatements=true");
        dataSource.setUsername("root");
        dataSource.setPassword("Harrytion");
        return dataSource;
    }

    @Bean
    public Scanner getScanner(){
        SpringScanner springScanner = new SpringScanner();
        springScanner.setShowSql(true);
        return springScanner;

    }



}
