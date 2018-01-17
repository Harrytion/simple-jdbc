package com.jackrams;

import com.jackrams.scanner.ClassFilter;
import com.jackrams.scanner.ClassUtils;
import org.junit.Test;

import javax.persistence.Entity;
import java.util.Iterator;
import java.util.List;

public class ScannerTest {

@Test
public void testScanner(){
    List<Class> classes = ClassUtils.scanPackage("", new ClassFilter() {
        @Override
        public boolean accept(Class clazz) {
            return clazz.isAnnotationPresent(Entity.class);
        }
    });
    for(Class clazz : classes){
        System.out.print(clazz.getName());
    }
}

@Test
public void testItrable(){
    int index=0;
    StringBuilder sb=new StringBuilder("hello worldddd");
    // index = sb.length()-1;
    index=sb.lastIndexOf("d");
    sb.deleteCharAt(index);
    System.out.println(sb);


}


public void testItrables(Iterable iterable){
    Iterator iterator = iterable.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }
}
}
