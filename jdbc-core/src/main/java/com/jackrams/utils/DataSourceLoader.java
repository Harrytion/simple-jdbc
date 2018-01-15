package com.jackrams.utils;


import javax.sql.DataSource;

public interface DataSourceLoader {

  void setDataSource();

  void setDataSource(DataSource dataSource);

  DataSource getDataSource();


}
