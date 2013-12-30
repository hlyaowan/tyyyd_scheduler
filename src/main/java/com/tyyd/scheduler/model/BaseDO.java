/*
 * 
 */
// Created on 2013-3-21


package com.tyyd.scheduler.model;

import java.io.Serializable;


/**
 * @author joe.chen
 *
 */
@SuppressWarnings("serial")
public class BaseDO implements Serializable {

    public int start;
    public int count;
    
    
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
