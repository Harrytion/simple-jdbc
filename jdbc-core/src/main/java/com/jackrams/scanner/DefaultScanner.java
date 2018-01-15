package com.jackrams.scanner;

import com.alibaba.druid.util.StringUtils;
import com.jackrams.domain.EntityClass;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class DefaultScanner implements Scanner {
    private List<Class> classList=new ArrayList<>();


    @Override
    public void doScan(String... packages) {
        if(null==packages||packages.length==0) throw new RuntimeException("Packages Is Null");
        for (String pack : packages){
            if(StringUtils.isEmpty(pack)) continue;
            List<Class> classes = ClassUtils.scanPackage(pack, new ClassFilter() {
                @Override
                public boolean accept(Class clazz) {
                    return clazz.isAnnotationPresent(Entity.class);
                }
            });
            classList.addAll(classes);





        }
    }

    public void doScanEntity(){
        EntityClass entityClass=new EntityClass();
        for (Class clazz :  classList){
            entityClass.setClassName(clazz.getName());
            if(clazz.isAnnotationPresent(Table.class)){
                entityClass.setClassName(clazz.getName());
                Table table =(Table) clazz.getAnnotation(Table.class);
                if(StringUtils.isEmpty(table.name())) {}
                entityClass.setTableName(table.name());
                entityClass.setCatalog(table.catalog());
                entityClass.setSchema(table.schema());

            }else{
            }

        }
    }

    public void doScanColumn(){

    }
}
