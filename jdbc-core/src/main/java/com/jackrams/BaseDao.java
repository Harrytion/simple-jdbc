package com.jackrams;

import com.jackrams.domain.Example;
import com.jackrams.domain.Page;
import com.jackrams.domain.SelectExample;

import java.util.Collection;
import java.util.List;

public interface BaseDao<T> {

    /**
     *
     * @param t insert Object With All Flied
     * @return
     *
     * 向数据库中插入所有字段
     */
    Integer insert(T t)throws Exception;

    /**
     *
     * @param t insert Object with Flied Only has Value
     * @return
     *
     * 只插入有值的字段
     *
     */

    Integer insertSelective(T t)throws Exception;

    /**
     * 同时插入多条数据
     * @param ts
     * @return
     */
    Integer insertList(Collection<T> ts)throws Exception;

    /**
     *
     * @param t
     * @return
     * 更新所有字段
     */
    Integer update(T t)throws Exception;

    /**
     *
     * @param t
     * @return
     * 更新有值字段
     */
    Integer updateSelective(T t)throws Exception;

    /**
     *
     * @param newT the new Object Will update
     * @param example the Example
     * @return
     */
    Integer update(T newT, Example example)throws Exception;


    /**
     *
     * @param newT
     * @param
     * @return
     */
    Integer updateSelective(T newT,Example example)throws Exception;

    /**
     *
     * @param t will delete Object Id
     * @return
     */
    Integer delete(Object t)throws Exception;

    /**
     *
     * @param ids will delete Object Of Ids
     * @return
     */
    Integer deletes(Collection<Object> ids)throws Exception;

    /**
     *
     * @param ids
     * @return
     */
    Integer deletes(Object ... ids)throws Exception;

    /**
     * Query By Id
     * @param id
     * @return
     * 通过Id获取T实例
     */
    T selectById(Object id)throws Exception;

    /**
     *
     * @param ids
     * @return
     *
     *
     */
    List<T> selectByIds(Collection<Object> ids)throws Exception;

    /**
     *
     * @param ids
     * @return
     */

    List<T> selectByIds(Object ... ids)throws Exception;


    /**
     *
     * @param example 查询条件
     * @return
     */

    Page<T> selectPageByExample(SelectExample  example) throws Exception;

    /**
     *
     * @param example 查询条件
     * @return
     */

    List<T> selectListByExample(SelectExample example) throws Exception;

    /**
     * 查询like 所有，以及查询逻辑为And
     * @param t
     * @return
     */

    List<T> selectListAllLikeAnd(T t)throws Exception;

    /**
     *
     * @param t
     * @param pageSize 一页显示的数量
     * @param page 第几页
     * @return
     */

    Page<T> selectPageAllLikeAnd(T t,int pageSize,int page)throws Exception;


    Class<T> getDomainClass();
}

