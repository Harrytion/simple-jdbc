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
        List<Integer> list = Arrays.asList(1, 5,2 ,3 , 9,8);
        List<Integer> integers=new ArrayList<>(list);
        Collections.sort(integers);
        System.out.println(list);
        System.out.println(integers);
        Integer[] arr=new Integer[10];
        System.out.println((Object[] )arr);
    }


}
