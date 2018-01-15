package com.jackrams;

import com.jackrams.scanner.ClassFilter;
import com.jackrams.scanner.ClassUtils;
import org.junit.Test;

import javax.persistence.Entity;
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


}
