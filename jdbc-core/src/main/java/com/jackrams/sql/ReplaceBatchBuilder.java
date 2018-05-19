package com.jackrams.sql;

import java.util.List;

public class ReplaceBatchBuilder<T> extends AbstractBatchBuilder {
  public ReplaceBatchBuilder(List<T> objects, Class<T> objClazz) {
    super(objects, objClazz);
  }

  @Override
  protected void bulidSql() throws Exception{
    this.sqlBuilder.append(REPLACE);
    append();
    bulidObjects();
  }
}
