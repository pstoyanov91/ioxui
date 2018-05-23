/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.models;

import java.util.Date;

/**
 *
 * @author cpt2pes
 *
 * This class is used for describing a request/response data coming from the UI.
 * 
 * In particular this class is used when we want to send a task for execution
 *
 */
public class Task {

    private Long deviceId;
    private Date date;
    private String time;
    private int ioperation;

    public Task() {
    }

    //This constructor is used when executing a command in realtime
    public Task(Long deviceId, int ioperation) {
        this.deviceId = deviceId;
        this.ioperation = ioperation;
    }

    //Tins constructor is used for scheduling a task
    public Task(Long deviceId, Date date, String time, int ioperation) {
        this.deviceId = deviceId;
        this.date = date;
        this.time = time;
        this.ioperation = ioperation;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIoperation() {
        return ioperation;
    }

    public void setIoperation(int ioperation) {
        this.ioperation = ioperation;
    }

    @Override
    public String toString() {
        return "Device id: " + this.deviceId + " Operation: " + this.ioperation + " Timestamp: " + this.time; 
    }
    
    
}
