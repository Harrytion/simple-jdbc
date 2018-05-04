package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.EntityClass;
import com.jackrams.domain.SQLObject;
import com.jackrams.utils.EntityUtils;
import static com.jackrams.utils.SQLUtils.checkDbName;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSelectBuilder<T> implements SQLBuilder {

    protected StringBuilder sqlBuilder = new StringBuilder();

    protected String tableName;

    protected String baseColumns ;

    protected List<Object> sqlObjectArgs = new ArrayList<>();

    protected List<ColumnClass> columnClasses;

    protected EntityClass entityClass;

    protected Class<T> tClass;

    protected boolean buildedSelectSql=false;
    @Override
    public SQLObject getSQLObject() throws Exception{
        if (!buildedSelectSql) selectSql();
        SQLObject sqlObject = new SQLObject();
        sqlObject.setSql(this.sqlBuilder.toString());
        sqlObject.setObjects(sqlObjectArgs);
        return sqlObject;
    }

    protected abstract void selectSql() throws Exception;




    public AbstractSelectBuilder(Class<T> tClass){
        this.entityClass = EntityUtils.getEntityClassMap().get(tClass);
        this.columnClasses = this.entityClass.getColumnClasses();
        this.tClass = tClass;
        String dbName = entityClass.getSchema();
        String tableName  =entityClass.getTableName();
        this.tableName = tableName;
        if(checkDbName(dbName)) {
            this.tableName = dbName+"."+tableName;
        }
        initBaseColumn();
        initSelectSql();
    }

    private void initSelectSql(){
        this.sqlBuilder.append(SELECT).append(this.baseColumns).append(_FROM)
                .append(tableName);
    }

    private void initBaseColumn(){
        StringBuilder baseColumnsBuilder =  new StringBuilder();
        for(ColumnClass columnClass : columnClasses){
            baseColumnsBuilder.append(columnClass.getName()).append(_COMMA);
        }
        baseColumnsBuilder = baseColumnsBuilder.deleteCharAt(baseColumnsBuilder.length() - 1);
        this.baseColumns=baseColumnsBuilder.toString();
    }


    public List<T> parseObject(ResultSet resultSet) throws Exception{
        List<T> tList =new ArrayList<>();
        while (resultSet.next()){
            T newInstance = tClass.newInstance();
            for(ColumnClass columnClass : columnClasses ){
                columnClass.getColumnField().set(newInstance,resultSet.getObject(columnClass.getName()));
            }
            tList.add(newInstance);
        }
        return tList;
    }

}
