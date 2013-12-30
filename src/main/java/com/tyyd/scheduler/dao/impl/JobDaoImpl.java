package com.tyyd.scheduler.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tyyd.scheduler.analysis.mybatis.MybatisTemplate;
import com.tyyd.scheduler.dao.JobDao;
import com.tyyd.scheduler.model.JobInfo;

@Repository
public class JobDaoImpl extends MybatisTemplate implements JobDao{
    
    private static final String NAMESPACE = JobDao.class.getName().concat(".");
    
    public List<JobInfo> getJobInfoList(JobInfo condition) {
        return super.<JobInfo> getList(NAMESPACE.concat("getJobList"), condition);
    }

    public JobInfo getJobInfo(JobInfo condition) {
        return super.<JobInfo> get(NAMESPACE.concat("getJobInfo"), condition);
    }
    
    public Integer updateJobInfo(JobInfo condition) {
        return super.update(NAMESPACE.concat("updateJobInfo"), condition);
    }
    
    public Integer saveJobInfo(JobInfo condition) {
        return super.save(NAMESPACE.concat("saveJobInfo"), condition);
    }
}
