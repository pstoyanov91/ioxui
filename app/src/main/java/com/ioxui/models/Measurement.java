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
 * This is a class describing the "measurements" database table.
 * 
 * It could be used as Entity class when using JPA
 */
public class Measurement {
    private long id;
    private long deviceid;
    private String itimestamp;
    private int ivalue;
    private String itextvalue;

    public Measurement() {
    }

    public Measurement(long id, long deviceid, String itimestamp, int ivalue, String itextvalue) {
        this.id = id;
        this.deviceid = deviceid;
        this.itimestamp = itimestamp;
        this.ivalue = ivalue;
        this.itextvalue = itextvalue;
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

    public String getItimestamp() {
        return itimestamp;
    }

    public void setItimestamp(String itimestamp) {
        this.itimestamp = itimestamp;
    }

    public int getIvalue() {
        return ivalue;
    }

    public void setIvalue(int ivalue) {
        this.ivalue = ivalue;
    }

    public String getItextvalue() {
        return itextvalue;
    }

    public void setItextvalue(String itextvalue) {
        this.itextvalue = itextvalue;
    }
    
    
    
    
}
