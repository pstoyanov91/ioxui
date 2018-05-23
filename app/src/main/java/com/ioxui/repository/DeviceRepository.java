/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.repository;

import com.ioxui.models.Device;
import java.util.List;

/**
 *
 * @author cpt2pes
 */
public interface DeviceRepository {

    public List<Device> getAllDevices();

    public List<Device> getAllRegularDevices();

    public List<Device> getAllSensors();

    public int updateStatus(long id, int status);
    
    public Device getDevice(long id);
    
    public int createDevice(Device device);
}
