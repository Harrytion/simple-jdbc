package com.jackrams;

import com.jackrams.utils.SQLUtils;
import org.junit.Test;

import javax.persistence.Table;

public class TestSQLUtils {

    @Test
    public void testToUnderLine(){
        System.out.println(SQLUtils.toUnderLine("TestAdmin"));
    }
}
