package com.jackrams.helpper;

import java.util.concurrent.atomic.AtomicInteger;

public class CountHelper {

    private AtomicInteger countAtomic=new AtomicInteger();

    public void addCount(int num){

         countAtomic.addAndGet(num);

    }

    public Integer getCount(){

        return countAtomic.get();
    }

    public void addArrayCount(int [] batch){
        for (int num : batch) countAtomic.addAndGet(num);
    }
}
