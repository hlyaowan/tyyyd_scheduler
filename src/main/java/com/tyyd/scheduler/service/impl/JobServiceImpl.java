package com.tyyd.scheduler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyyd.scheduler.dao.JobDao;
import com.tyyd.scheduler.model.JobInfo;
import com.tyyd.scheduler.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    public List<JobInfo> getJobInfoList(JobInfo condition) {
        return jobDao.getJobInfoList(condition);
    }

    public JobInfo getJobInfo(JobInfo condition) {
        return jobDao.getJobInfo(condition);
    }

    public Integer updateJobInfo(JobInfo condition) {
        return jobDao.updateJobInfo(condition);
    }

    public Integer saveJobInfo(JobInfo condition) {
        return jobDao.saveJobInfo(condition);
    }
}
