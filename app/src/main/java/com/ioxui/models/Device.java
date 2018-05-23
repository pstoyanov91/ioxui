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
 * This is a class describing the "devices" database table.
 *
 * It could be used as Entity class when using JPA
 *
 */
public class Device {

    private long id;
    private long userid;
    private int type;
    private String sysid;
    private String name;
    private String commandOn;
    private String commandOff;
    private boolean measurement;
    private String measurementUnit;
    private String formula;
    private int status;
    private int executed;

    public Device() {
    }

    public Device(long id, long userid, int type, String sysid, String name, String commandOn, String commandOff, boolean measurement, String measurementUnit, String formula, int status, int executed) {
        this.id = id;
        this.userid = userid;
        this.type = type;
        this.sysid = sysid;
        this.name = name;
        this.commandOn = commandOn;
        this.commandOff = commandOff;
        this.measurement = measurement;
        this.measurementUnit = measurementUnit;
        this.formula = formula;
        this.status = status;
        this.executed = executed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommandOn() {
        return commandOn;
    }

    public void setCommandOn(String commandOn) {
        this.commandOn = commandOn;
    }

    public String getCommandOff() {
        return commandOff;
    }

    public void setCommandOff(String commandOff) {
        this.commandOff = commandOff;
    }

    public boolean isMeasurement() {
        return measurement;
    }

    public void setMeasurement(boolean measurement) {
        this.measurement = measurement;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getExecuted() {
        return executed;
    }

    public void setExecuted(int executed) {
        this.executed = executed;
    }    

    @Override
    public String toString() {
        
        return "ID: " + this.id
                + " User id: " + this.userid
                + " Type: " + this.type
                + " Sys id: " + this.name
                + " Name: " + this.name
                + " Command on: " + this.commandOn
                + " Command off: " + this.commandOff
                + " Measurement unit: " + this.measurementUnit
                + " Status: " + this.status;
                
    }
    
    
}
