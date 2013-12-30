/*
 * 
 */
// Created on 2013-3-21

package com.tyyd.scheduler.analysis.mybatis;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tyyd.scheduler.model.BaseDO;



/**
 * MybatisTemplate
 * 
 * @author joe.chen
 */
public abstract class MybatisTemplate {

    @Autowired
    private MybatisReadDAO readDao;

    @Autowired
    private MybatisWriteDAO writeDao;


    /**
     * 按Id查询数据
     * 
     * @param statement
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T get(String statement, Serializable id) {
        return (T) readDao.get(statement, id);
    }


    /**
     * 按给定条件查询数据
     * 
     * @param statement
     * @param data
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T get(String statement, BaseDO data) {
        return (T) readDao.get(statement, data);
    }


    /**
     * 按给定条件查询数据列表
     * 
     * @param statement
     * @param data
     * @return
     */
    protected <E> List<E> getList(String statement, BaseDO data) {
        return readDao.getList(statement, data);
    }


    /**
     * 按给定条件查询数据总数
     * 
     * @param statement
     * @param data
     * @return
     */
    protected Integer getTotalCount(String statement, BaseDO data) {
        return readDao.getTotalCount(statement, data);
    }


    /**
     * 查询所有数据
     * 
     * @param statement
     * @return
     */
    protected <E> List<E> getAll(String statement) {
        return readDao.getAll(statement);
    }


    /**
     * 保存数据
     * 
     * @param statement
     * @param data
     * @return
     */
    protected int save(String statement, BaseDO data) {
        return writeDao.save(statement, data);
    }


    /**
     * 批量保存
     * 
     * @param statement
     * @param list
     */
    protected void saveBatch(String statement, List<Object> list) {
        writeDao.saveBatch(statement, list);
    }


    /**
     * 更新数据
     * 
     * @param statement
     * @param data
     * @return
     */
    protected int update(String statement, BaseDO data) {
        return writeDao.update(statement, data);
    }


    /**
     * 批量更新
     * 
     * @param statement
     * @param list
     */
    protected void updateBatch(String statement, List<Object> list) {
        writeDao.updateBatch(statement, list);
    }


    /**
     * 删除数据
     * 
     * @param statement
     * @param data
     */
    protected void delete(String statement, BaseDO data) {
        writeDao.delete(statement, data);
    }


    /**
     * 按Id删除数据
     * 
     * @param statement
     * @param id
     */
    protected void delete(String statement, Serializable id) {
        writeDao.delete(statement, id);
    }


    /**
     * 批量删除
     * 
     * @param statement
     * @param list
     */
    protected void deleteBatch(String statement, List<Object> list) {
        writeDao.deleteBatch(statement, list);
    }

}
