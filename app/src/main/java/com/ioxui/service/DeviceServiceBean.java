/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.service;

import com.ioxui.models.Device;
import com.ioxui.repository.DeviceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cpt2pes
 */
@Service
public class DeviceServiceBean implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;
    
    @Override
    public List<Device> getAllDevices() {
        List<Device> devices = deviceRepository.getAllDevices();
        return devices;
    }

    @Override
    public List<Device> getAllSensorDevices() {
        List<Device> sensors = deviceRepository.getAllSensors();
        return sensors;
    }

    @Override
    public String updateDeviceStatus(long id, int status) {
        int rowsAffected = deviceRepository.updateStatus(id, status);
        String updateStatus = null;
        if(rowsAffected == 0){
            updateStatus = "Nothing was updated";
        }else{
            updateStatus = rowsAffected + " rows updated";
        }
        return updateStatus;
    }

    @Override
    public List<Device> getAllRegularDevices() {
        List<Device> devices = deviceRepository.getAllRegularDevices();
        return devices;
    }

    @Override
    public Device getDevice(long id) {
        return deviceRepository.getDevice(id);
    }
    
}
