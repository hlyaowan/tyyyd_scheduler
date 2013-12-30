package com.tyyd.scheduler.model;

import java.sql.Timestamp;

@SuppressWarnings("serial")
public class JobInfo extends BaseDO {

    private int       id;
    private String    cpName;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Timestamp readTime;
    private int       flag;
    private String    timeExpress;
    private String    path;
    private String    extendsInfo;
    private String    channel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Timestamp getReadTime() {
        return readTime;
    }

    public void setReadTime(Timestamp readTime) {
        this.readTime = readTime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTimeExpress() {
        return timeExpress;
    }

    public void setTimeExpress(String timeExpress) {
        this.timeExpress = timeExpress;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtendsInfo() {
        return extendsInfo;
    }

    public void setExtendsInfo(String extendsInfo) {
        this.extendsInfo = extendsInfo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
