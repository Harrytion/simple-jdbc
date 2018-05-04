package com.jackrams;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTest {
    @Test
    public void test(){
        boolean assignableFrom = AbstractBaseDaoImpl.class.isAssignableFrom(PersonDao.class);
        System.out.println(assignableFrom);
    }


}
