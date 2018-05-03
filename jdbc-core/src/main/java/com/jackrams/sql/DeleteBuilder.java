package com.jackrams.sql;

import com.jackrams.excepts.BuilderException;

import java.util.List;

public class DeleteBuilder<T> extends AbstractBuilder {
    private Object [] objects;


    @Override
    protected void buildSql() throws Exception{
        build=true;
        sqlBuilder.append(DELETE).append(_FROM)
                .append(tableName).append(WHERE);
        List<String> idFlied = entityClass.getIdFlied();
        sqlBuilder.append(entityClass.getFliedColumnMap().get(idFlied.get(0)).getName()).append(_IN).append(_LEFT_SIGN);
        for (int i=0;i<objects.length;i++) {
          sqlObjectList.add(objects[i]);
            if(i==objects.length-1) {
              sqlBuilder.append(ARG_RE).append(_RIGHT_SIGN);
              break;
          }
          sqlBuilder.append(ARG_RE).append(_COMMA);
        }
    }

    public  DeleteBuilder( Class<T> objClazz,Object ... objects) throws Exception{
        super(null,objClazz);
        this.objects=objects;
        if(this.entityClass.getIdFlied().size()!=1) throw new BuilderException("id Must one size");
        buildSql();

    }
}
