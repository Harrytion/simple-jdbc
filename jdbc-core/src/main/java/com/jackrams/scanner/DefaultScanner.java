package com.jackrams.scanner;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.EntityClass;
import com.jackrams.helpper.PropertyHelpper;
import com.jackrams.utils.EntityUtils;
import com.jackrams.utils.SQLUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */

public class DefaultScanner implements Scanner {
    private transient Log log = LogFactory.getLog(getClass());


    private List<Class> classList = new ArrayList<>();


    private List<EntityClass> entityClassList = new ArrayList<>();

    @Override
    public void doScan(String... packages) {
        log.debug("Start doScan");
        if (null == packages || packages.length == 0) throw new RuntimeException("Packages Is Null");
        for (String pack : packages) {
            if (StringUtils.isEmpty(pack)) continue;
            List<Class> classes = ClassUtils.scanPackage(pack, new ClassFilter() {
                @Override
                public boolean accept(Class clazz) {
                    return clazz.isAnnotationPresent(Entity.class);
                }
            });
            classList.addAll(classes);
        }

        //scan entity
        doScanEntity();
        //scan column
        doScanColumn();


        log.debug("doScan End scan Entity size " + EntityUtils.getEntityClassMap().keySet().size());
    }


    public synchronized void doScanEntity() {
        for (Class clazz : classList) {
            EntityClass entityClass = new EntityClass();
            entityClass.setClassName(clazz.getName());
            if (clazz.isAnnotationPresent(Table.class)) {
                entityClass.setClassName(clazz.getName());
                Table table = (Table) clazz.getAnnotation(Table.class);
                if (StringUtils.isEmpty(table.name())) {
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
            EntityUtils.putIfAbsent(clazz, entityClass);
        }
    }

    public synchronized void doScanColumn() {
        if (EntityUtils.entityClassMapEmpty()) return;
        ConcurrentMap<Class<?>, EntityClass> entityClassMap = EntityUtils.getEntityClassMap();
        Set<Class<?>> classes = entityClassMap.keySet();
        for (Class clazz : classes) {
            EntityClass entityClass = EntityUtils.getEntityClassMap().get(clazz);
            //List of Field
            Field[] declaredFields = clazz.getDeclaredFields();
            List<String> fieldNames = new ArrayList<>(declaredFields.length);
            Map<String,ColumnClass> fieldAnnos=new HashMap<>();
            for (Field field : declaredFields) {
                fieldNames.add(field.getName());
                ColumnClass value = fieldAnnotantion(field);
                if(null!=value){
                    fieldAnnos.put(field.getName().toUpperCase(), value);
                }

            }
            //List of Method
            Method[] declaredMethods = clazz.getDeclaredMethods();
            Map<String, ColumnClass> methodAnnos = methodAnnotantion(declaredMethods, fieldNames);
            List<ColumnClass> merge = mergeColumnMap(fieldAnnos, methodAnnos);
            merge.addAll(excludFieldAndMethodAnnotantion(declaredFields,merge));
            entityClass.setColumnClasses(merge);
            entityClass.setFliedColumnMap(getColumnClassMap(merge));
            entityClass.setIdFlied(getIdField(merge));
            entityClassMap.putIfAbsent(clazz,entityClass);

        }

        EntityUtils.putAll(entityClassMap);
    }


    public Map<String,ColumnClass> methodAnnotantion(Method[] methods, List<String> fieldNames) {
        Map<String,ColumnClass> columnClassList = new HashMap<>();
        int mLength = methods.length;
        for (int i = 0; i < mLength; i++) {
            Method method = methods[i];
            if (!(method.isAnnotationPresent(Column.class) || method.isAnnotationPresent(Id.class)
                    ||method.isAnnotationPresent(Temporal.class))) continue;
            ColumnClass columnClass = new ColumnClass();
            if (method.isAnnotationPresent(Column.class)) {
                Column column = method.getAnnotation(Column.class);
                columnClass=setColumnValue(columnClass,column);
            }
            columnClass.setFieldName(PropertyHelpper.getFieldNameByMethod(method.getName(), fieldNames));

            if (method.isAnnotationPresent(Id.class)) {
                columnClass.setIdable(true);
            }

            if(method.isAnnotationPresent(Temporal.class)){
                Temporal annotation = method.getAnnotation(Temporal.class);
                columnClass.setTemporalType(annotation.value());
            }
           columnClassList.put(columnClass.getFieldName().toUpperCase(),columnClass);
        }

        return columnClassList;

    }


    public ColumnClass fieldAnnotantion(Field field) {
        if (!(field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)||
                field.isAnnotationPresent(Temporal.class))) return null;

        ColumnClass columnClass = new ColumnClass();
        if(field.isAnnotationPresent(Id.class)){
            columnClass.setIdable(true);
        }
        if(field.isAnnotationPresent(Temporal.class)){
            Temporal temporal = field.getAnnotation(Temporal.class);
            columnClass.setTemporalType(temporal.value());
        }
        if(field.isAnnotationPresent(Column.class)){
            Column column = field.getAnnotation(Column.class);
            columnClass=setColumnValue(columnClass,column);

        }

        return columnClass;
    }

    public List<ColumnClass> excludFieldAndMethodAnnotantion(Field[] fields, List<ColumnClass> AllAnnos) {
        List<ColumnClass> columnClasses = new ArrayList<>();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!(checkField(fieldName, AllAnnos))) {
                ColumnClass columnClass = new ColumnClass();
                columnClass.setName(SQLUtils.camelhumpToUnderline(fieldName));
                columnClass.setFieldName(fieldName);
                columnClasses.add(columnClass);
            }

        }


        return columnClasses;
    }

    private boolean checkField(String field, List<ColumnClass> columnClasses) {
        for (ColumnClass columnClass : columnClasses) {
            if (field.equalsIgnoreCase(columnClass.getFieldName())) return true;
        }
        return false;

    }



    private ColumnClass setColumnValue(ColumnClass columnClass,Column column){
        columnClass.setColumnDefinition(column.columnDefinition());
        columnClass.setInsertable(column.insertable());
        columnClass.setLength(column.length());
        columnClass.setName(column.name());
        columnClass.setNullable(column.nullable());
        columnClass.setPrecision(column.precision());
        columnClass.setScale(column.scale());
        columnClass.setTable(column.table());
        columnClass.setUnique(column.unique());
        columnClass.setUpdatable(column.updatable());
        return columnClass;
    }

    private List<ColumnClass> mergeColumnMap(Map<String,ColumnClass> fieldsAnnos,Map<String,ColumnClass> methodAnnos ){
        List<ColumnClass> columnClassList=new ArrayList<>();
        Set<String> fieldKeys = fieldsAnnos.keySet();
        for (String fieldKey :fieldKeys){
            ColumnClass fieldColumn = fieldsAnnos.get(fieldKey);
            if(methodAnnos.containsKey(fieldKey)) {
                ColumnClass methodColumn = methodAnnos.get(fieldKey);
                if (fieldColumn.getName().equalsIgnoreCase("")) {
                    if (methodColumn.getName().equals("")) {
                        if(methodColumn.getTemporalType()!=null){
                            fieldColumn.setTemporalType(methodColumn.getTemporalType());
                        }
                        if(methodColumn.isIdable()){
                            fieldColumn.setIdable(true);
                        }
                    } else {
                        fieldColumn.setName(methodColumn.getName());
                        fieldColumn.setTable(methodColumn.getTable());
                        fieldColumn.setScale(methodColumn.getScale());
                        fieldColumn.setPrecision(methodColumn.getPrecision());
                        fieldColumn.setNullable(methodColumn.isNullable());
                        fieldColumn.setInsertable(methodColumn.getInsertable());
                        fieldColumn.setColumnDefinition(methodColumn.getColumnDefinition());
                        fieldColumn.setUnique(methodColumn.isUnique());
                        fieldColumn.setUpdatable(methodColumn.isUpdatable());
                    }
                    columnClassList.add(fieldColumn);

                }
            }
            methodAnnos.remove(fieldKey);
        }

        //Only Method
        for(String key : methodAnnos.keySet()){
            columnClassList.add(methodAnnos.get(key));
        }

        //check Column Name

        for (ColumnClass columnClass:columnClassList){
            if(columnClass.getName().equalsIgnoreCase("")){
                columnClass.setName(SQLUtils.camelhumpToUnderline(columnClass.getFieldName()));
            }
        }
        return columnClassList;
    }

    private Map<String,ColumnClass> getColumnClassMap(List<ColumnClass> columnClasses){
        Map<String,ColumnClass> columnClassMap=new HashMap<>(columnClasses.size());
        for (ColumnClass columnClass :columnClasses){
            String fieldName = columnClass.getFieldName();
            columnClassMap.put(fieldName,columnClass);
        }

        return columnClassMap;
    }

    private List<String> getIdField(List<ColumnClass> columnClasses){
        List<String> idFields=new ArrayList<>();
        for (ColumnClass columnClass : columnClasses){
            if(columnClass.isIdable()){
                idFields.add(columnClass.getFieldName());
            }
        }
        return idFields;
    }
}
