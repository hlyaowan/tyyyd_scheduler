package com.tyyd.scheduler.model;


@SuppressWarnings("serial")
public class TriggerInfo  extends BaseDO  {

    private String triggerName;
    private String displayName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    private String description;
    private String   nextFireTime;
    private String   prevFireTime;
    private String priority;
    private String triggerState;
    private String triggerType;
    private String   startTime;
    private String   endTime;
    
    public String getTriggerName() {
        return triggerName;
    }
    
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }
    
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }
    
    public String getJobName() {
        return jobName;
    }
    
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    public String getJobGroup() {
        return jobGroup;
    }
    
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getNextFireTime() {
        return nextFireTime;
    }
    
    public void setNextFireTime(String nextFireTime) {
        this.nextFireTime = nextFireTime;
    }
    
    public String getPrevFireTime() {
        return prevFireTime;
    }
    
    public void setPrevFireTime(String prevFireTime) {
        this.prevFireTime = prevFireTime;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public String getTriggerState() {
        return triggerState;
    }
    
    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }
    
    public String getTriggerType() {
        return triggerType;
    }
    
    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
