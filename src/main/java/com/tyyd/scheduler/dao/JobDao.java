package com.tyyd.scheduler.dao;

import java.util.List;

import com.tyyd.scheduler.model.JobInfo;

public interface JobDao {

    public List<JobInfo> getJobInfoList(JobInfo condition);

    public JobInfo getJobInfo(JobInfo condition);

    public Integer updateJobInfo(JobInfo condition);

    public Integer saveJobInfo(JobInfo condition);
}
