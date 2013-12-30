/*
 * 
 */
// Created on 2013-3-21

package com.tyyd.scheduler.analysis.mybatis;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tyyd.scheduler.model.BaseDO;



/**
 * MybatisWriteDAO
 * @author joe.chen
 */
@Repository
public final class MybatisWriteDAO {

    @Autowired
    @Qualifier("writeSqlSessionTemplate")
    private SqlSessionTemplate writeSqlSessionTemplate;

    /**
     * 保存
     * @param statement
     * @param data
     * @return
     */
    public int save(String statement, BaseDO data) {
        return writeSqlSessionTemplate.insert(statement, data);
    }

    /**
     * 批量保存
     * @param statement
     * @param list
     */
    public void saveBatch(String statement, List<Object> list) {
        SqlSession session = writeSqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            session.insert(statement, list);
            session.commit();
            session.clearCache();
        } finally {
            session.close();
        }

    }

    /**
     * 更新
     * @param statement
     * @param data
     * @return
     */
    public int update(String statement, BaseDO data) {
        return writeSqlSessionTemplate.update(statement, data);
    }

    /**
     * 批量更新
     * @param statement
     * @param list
     */
    public void updateBatch(String statement, List<Object> list) {
        SqlSession session = writeSqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            session.update(statement, list);
            session.commit();
            session.clearCache();
        } finally {
            session.close();
        }

    }

    /**
     * 删除
     * @param statement
     * @param data
     */
    public void delete(String statement, BaseDO data) {
        writeSqlSessionTemplate.delete(statement, data);
    }

    /**
     * 删除
     * @param statement
     * @param id
     */
    public void delete(String statement, Serializable id) {
        writeSqlSessionTemplate.delete(statement, id);
    }

    /**
     * 批量删除
     * @param statement
     * @param list
     */
    public void deleteBatch(String statement, List<Object> list) {
        SqlSession session = writeSqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            session.delete(statement, list);
            session.commit();
            session.clearCache();
        } finally {
            session.close();
        }
    }

}
