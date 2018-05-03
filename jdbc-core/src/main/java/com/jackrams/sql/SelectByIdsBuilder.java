package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.excepts.BuilderException;
import javafx.util.Builder;

import java.util.List;

public class SelectByIdsBuilder<T> extends AbstractSelectBuilder<T> {

    private Object[] ids;
    public SelectByIdsBuilder(Class<T> tClass,Object ... ids) {
        super(tClass);
        this.ids=ids;
    }

    @Override
    protected void selectSql() {
        buildedSelectSql=true;
        sqlBuilder.append(WHERE);
        List<String> idFlied = entityClass.getIdFlied();
        if(idFlied.size()!=1) throw new BuilderException("");
        String idFliedName = idFlied.get(0);
        ColumnClass columnClass = entityClass.getFliedColumnMap().get(idFliedName);
        sqlBuilder.append(columnClass.getName()).append(_IN);
        sqlBuilder.append(_LEFT_SIGN);
        for (Object id : ids) {
            sqlBuilder.append(ARG_RE).append(_COMMA);
            sqlObjectArgs.add(id);
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length()-1).append(_RIGHT_SIGN);

    }


}
