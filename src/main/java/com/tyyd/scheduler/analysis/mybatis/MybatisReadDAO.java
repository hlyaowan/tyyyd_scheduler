/*
 * 
 */
// Created on 2013-3-21

package com.tyyd.scheduler.analysis.mybatis;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tyyd.scheduler.model.BaseDO;



/**
 * MybatisReadDAO
 * @author joe.chen
 */
@Repository
public final class MybatisReadDAO {

    @Autowired
    @Qualifier("readSqlSessionTemplate")
    private SqlSessionTemplate readSqlSessionTemplate;

    /**
     * 查询
     * @param statement
     * @param id
     * @return
     */
    public <T> T get(String statement, Serializable id) {
        return readSqlSessionTemplate.<T> selectOne(statement, id);
    }

    /**
     * 查询
     * @param statement
     * @param data
     * @return
     */
    public <T> T get(String statement, BaseDO data) {
        return readSqlSessionTemplate.<T> selectOne(statement, data);
    }

    /**
     * 查询
     * @param statement
     * @param data
     * @return
     */
    public <E> List<E> getList(String statement, BaseDO data) {
        return readSqlSessionTemplate.<E> selectList(statement, data);
    }

    /**
     * 查询总数
     * @param statement
     * @param data
     * @return
     */
    public Integer getTotalCount(String statement, BaseDO data) {
        return readSqlSessionTemplate.<Integer> selectOne(statement, data);
    }

    /**
     * 获取所有数据
     * @param statement
     * @return
     */
    public <E> List<E> getAll(String statement) {
        return readSqlSessionTemplate.<E> selectList(statement);
    }

}
