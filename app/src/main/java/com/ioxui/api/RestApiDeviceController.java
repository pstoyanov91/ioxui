/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.api;

import com.ioxui.models.Device;
import com.ioxui.models.Task;
import com.ioxui.service.DeviceService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cpt2pes
 */
@RestController
public class RestApiDeviceController {
    
    @Autowired
    DeviceService deviceService;
    
    @RequestMapping(value = "/api/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Device>> getAllDevices(){
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/devices/sensors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Device>> getAllSensorDevices(){
        List<Device> devices = deviceService.getAllSensorDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    
     @RequestMapping(value = "/api/devices/regular", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Device>> getAllRegularDevices(){
        List<Device> devices = deviceService.getAllRegularDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    
    @RequestMapping(
            value = "/api/devices/{id}", 
            method = RequestMethod.PUT, 
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> updateDeviceStatus(@PathVariable("id") Long id, @RequestBody Device device){
        
        String updateStatus = deviceService.updateDeviceStatus(id, device.getStatus());
        if(updateStatus == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping("/api/execute/task")
    public ResponseEntity<Map<String, String>> executeTask(@RequestBody Task task){

        /*
            To do execute the on/off command
        */
        deviceService.updateDeviceStatus(task.getDeviceId(), task.getIoperation());
        
        Map<String, String> map = new HashMap<>();
        map.put("status", "ok");
        System.out.println("Operation: " + task.getIoperation() + " Device: " + task.getDeviceId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}
