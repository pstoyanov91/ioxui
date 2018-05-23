/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.api;

import com.ioxui.models.Measurement;
import com.ioxui.service.MeasurementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cpt2pes
 */
@RestController
public class RestApiMeasurementController {

    @Autowired
    MeasurementService measurementService;
    
    @RequestMapping(value = "/api/measurements/{deviceid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Measurement>> getMeasurementsForDevice(@PathVariable("deviceid") long deviceid){
        List<Measurement> measurements = measurementService.getMeasurementsForDevice(deviceid);
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/measurement/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Measurement> getMeasurement(@PathVariable("id") long id){
        Measurement measurement = measurementService.getMeasurement(id);
        if(measurement == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }
}
