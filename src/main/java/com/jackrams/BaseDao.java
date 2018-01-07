package com.jackrams;

import com.jackrams.jdbc.Page;
import com.jackrams.jdbc.Qcriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T,Id extends Serializable> {

    /**
     *
     * @param t insert Object With All Flied
     * @return
     */
    Integer insert(T t);

    /**
     *
     * @param t insert Object with Flied Only has Value
     * @return
     */

    Integer insertSelective(T t);


    Integer update(T t);

    /**
     *
     * @param t
     * @return
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
     * Query By Id
     * @param id
     * @return
     */
    T selectById(Id id);

    /**
     *
     * @param ids
     * @return
     */
    List<T> selectByIds(Collection<Id> ids);


    List<T> selectByIds(Id ... ids);



    Page<T> selectPage(Qcriteria qcriteria);

    List<T> selectListByQcriteria(Qcriteria qcriteria);











}

