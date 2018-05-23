/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.models;

/**
 *
 * @author cpt2pes
 * 
 * This is a class describing the "schedule" database table.
 * 
 * It could be used as Entity class when using JPA
 * 
 */
public class Schedule {
    private long id;
    private long deviceid;
    private String timestamp;
    private int ioperation;

    public Schedule() {
    }

    public Schedule(long id, long deviceid, String timestamp, int ioperation) {
        this.id = id;
        this.deviceid = deviceid;
        this.timestamp = timestamp;
        this.ioperation = ioperation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(long deviceid) {
        this.deviceid = deviceid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getIoperation() {
        return ioperation;
    }

    public void setIoperation(int ioperation) {
        this.ioperation = ioperation;
    }
    
    
}
