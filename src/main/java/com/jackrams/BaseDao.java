package com.jackrams;

import com.jackrams.domain.Page;
import com.jackrams.jdbc.Qcriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T,Id extends Serializable> {

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
     * @param oldT the old Object
     * @return
     */
    Integer update(T newT,T oldT);

    /**
     *
     * @param newT
     * @param id Id Flied of t
     * @return
     */
    Integer update(T newT,Id id);

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
     * @param qcriterias 查询条件
     * @param pageSize 一页显示数量
     * @param page  页码
     * @return
     *
     *
     */

    Page<T> selectPage(List<Qcriteria> qcriterias,int pageSize,int page);

    /**
     *
     * @param qcriterias 查询条件
     * @return
     */

    List<T> selectListByQcriteria(List<Qcriteria> qcriterias);

    /**
     * 查询like 所有，以及查询逻辑为And
     * @param t
     * @return
     */

    List<T> selectListAllLikeAnd(T t );

    /**
     *
     * @param t
     * @param pageSize 一页显示的数量
     * @param page 第几页
     * @return
     */

    Page<T> selectPageAllLikeAnd(T t,int pageSize,int page);












}

