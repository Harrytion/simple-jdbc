package com.jackrams;

import com.jackrams.utils.DataSourceLoader;
import com.jackrams.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.util.ServiceLoader;
import java.util.Iterator;
public class DataSourceTest {
 @Test
 public void testDriver(){
     Driver Driver=null;
     ServiceLoader<Driver> Drivers=ServiceLoader.load(Driver.class);
     Iterator<Driver> iterator = Drivers.iterator();
     if (iterator.hasNext()){
          Driver = iterator.next();
     }
     System.out.print(Driver);

 }

 @Test
 public void testDataSourceLoader(){
     DataSourceLoader dataSourceLoader=null;
     ServiceLoader<DataSourceLoader> dataSourceLoaders=ServiceLoader.load(DataSourceLoader.class);
     Iterator<DataSourceLoader> iterator = dataSourceLoaders.iterator();
     if(iterator.hasNext()){
        dataSourceLoader= iterator.next();

     }

     System.out.println(dataSourceLoader);


 }

 @Test
 public void testDataSource()throws Exception{

     Connection connection = JdbcUtils.getConnection();
     System.out.println(connection);

 }



}
