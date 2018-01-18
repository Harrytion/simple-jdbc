package com.jackrams.scanner;

import com.jackrams.contants.Constants;
import com.jackrams.domain.ColumnClass;
import com.jackrams.utils.SQLUtils;
import org.apache.commons.lang3.StringUtils;
import com.jackrams.domain.EntityClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */

public class DefaultScanner implements Scanner {
    protected transient Log log= LogFactory.getLog(getClass());

    private List<Class> classList=new ArrayList<>();

    private List<EntityClass> entityClassList=new ArrayList<>();

    @Override
    public void doScan(String... packages) {
        log.debug("Start doScan");
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

        log.debug("doScan End scan Entity size "+Constants.entityClassMap.keySet().size());
    }



    public void doScanEntity(){
        for (Class clazz :  classList){
            EntityClass entityClass=new EntityClass();
            entityClass.setClassName(clazz.getName());
            if(clazz.isAnnotationPresent(Table.class)){
                entityClass.setClassName(clazz.getName());
                Table table =(Table) clazz.getAnnotation(Table.class);
                if(StringUtils.isEmpty(table.name())) {
                    String simpleName = clazz.getSimpleName();
                    final String tableName = SQLUtils.camelhumpToUnderline(simpleName);
                    entityClass.setSchema(table.schema());
                    entityClass.setCatalog(table.catalog());
                }
                entityClass.setTableName(table.name());
                entityClass.setCatalog(table.catalog());
                entityClass.setSchema(table.schema());

            } else {
                entityClass.setTableName(SQLUtils.camelhumpToUnderline(clazz.getSimpleName()));

            }
            entityClassList.add(entityClass);
            Constants.entityClassMap.putIfAbsent(clazz,entityClass);
        }
    }

    public void doScanColumn(){
        if(Constants.entityClassMap.isEmpty()) return;
        Set<Class<?>> classes = Constants.entityClassMap.keySet();
        for(Class clazz :classes){
            EntityClass entityClass = Constants.entityClassMap.get(clazz);
            //List of Field
            Field[] declaredFields = clazz.getDeclaredFields();
            //List of Method
            Method[] declaredMethods = clazz.getDeclaredMethods();


        }
    }


    public List<ColumnClass> methodAnnotantion(Method[] methods){

        return null;

    }


    public List<ColumnClass> fieldAnnotantion(Field[] fields){

        return null;
    }

    public List<ColumnClass> excludFieldAndMethodAnnotantion(Field [] fields,List<ColumnClass> methodAnnos,List<ColumnClass> fieldAnnos){
        List<ColumnClass> columnClasses=new ArrayList<>();
        for (Field field : fields){
            String fieldName = field.getName();
            if(!(checkField(fieldName,fieldAnnos)||checkField(fieldName,methodAnnos))) {
                ColumnClass columnClass=new ColumnClass();
                columnClass.setName(SQLUtils.camelhumpToUnderline(fieldName));
                columnClass.setFieldName(fieldName);
                columnClasses.add(columnClass);
            }

        }



        return columnClasses;
    }

    private boolean checkField(String field,List<ColumnClass> columnClasses){
        for (ColumnClass columnClass : columnClasses){
            if(field.equalsIgnoreCase(columnClass.getFieldName())) return true;
        }
        return  false;

    }
}
