package com.jackrams;

import com.jackrams.helpper.CountHelper;
import com.jackrams.utils.JdbcUtils;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PrepareStatmentTest {

    public PreparedStatement init(String sql) throws Exception{
        PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(sql);
        return preparedStatement;
    }

    @Test
    public void testInsertBatch() throws Exception{
        int i=0;

        PreparedStatement preparedStatement = init("insert into test(t_id,t_name,p_des) values(?,?,?)");
        while(true){
            if(i==1000000) break;
            for (int j =1;j<=3;j++){
                preparedStatement.setObject(j,"dhadfjasdfjkdf"+i+j);
            }
            preparedStatement.addBatch();
            i++;
        }
        int[] ints = preparedStatement.executeBatch();
        CountHelper countHelper =new CountHelper();
        countHelper.addArrayCount(ints);
        Integer count = countHelper.getCount();
        System.out.print(count);

    }

    @Test
    public void testInsertBatchSql() throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("");


    }


    Connection getConnection() throws Exception{
        return JdbcUtils.getConnection();
    }
}
