/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.service;

import com.ioxui.models.Device;
import java.util.List;

/**
 *
 * @author cpt2pes
 */
public interface DeviceService {

    List<Device> getAllDevices();

    List<Device> getAllSensorDevices();

    List<Device> getAllRegularDevices();

    String updateDeviceStatus(long id, int status);
    
    Device getDevice(long id);
}
