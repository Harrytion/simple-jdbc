package com.jackrams;

import com.jackrams.domain.Example;
import com.jackrams.domain.Page;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T,Id extends Object> {

    /**
     *
     * @param t insert Object With All Flied
     * @return
     *
     * 向数据库中插入所有字段
     */
    Integer insert(T t);

    /**
     *
     * @param t insert Object with Flied Only has Value
     * @return
     *
     * 直插入有值的字段
     *
     */

    Integer insertSelective(T t);

    /**
     *
     * @param t
     * @return
     * 更新所有字段
     */
    Integer update(T t);

    /**
     *
     * @param t
     * @return
     * 更新有值字段
     */
    Integer updateSelective(T t);

    /**
     *
     * @param newT the new Object Will update
     * @param example the Example
     * @return
     */
    Integer update(T newT, Example example);


    /**
     *
     * @param newT
     * @param
     * @return
     */
    Integer updateSelective(T newT,Example example);

    /**
     *
     * @param t will delete Object Id
     * @return
     */
    Integer delete(Id t);

    /**
     *
     * @param ids will delete Object Of Ids
     * @return
     */
    Integer deletes(Collection<Id> ids);

    /**
     *
     * @param ids
     * @return
     */
    Integer deletes(Id ... ids);

    /**
     * Query By Id
     * @param id
     * @return
     * 通过Id获取T实例
     */
    T selectById(Id id);

    /**
     *
     * @param ids
     * @return
     *
     *
     */
    List<T> selectByIds(Collection<Id> ids);

    /**
     *
     * @param ids
     * @return
     */

    List<T> selectByIds(Id ... ids);


    /**
     *
     * @param example 查询条件
     * @return
     */

    Page<T> selectPageByExample(Example example);

    /**
     *
     * @param example 查询条件
     * @return
     */

    List<T> selectListByExample(Example example);

    /**
     * 查询like 所有，以及查询逻辑为And
     * @param t
     * @return
     */

    List<T> selectListAllLikeAnd(T t);

    /**
     *
     * @param t
     * @param pageSize 一页显示的数量
     * @param page 第几页
     * @return
     */

    Page<T> selectPageAllLikeAnd(T t,int pageSize,int page);



    Connection getConnection();







}

