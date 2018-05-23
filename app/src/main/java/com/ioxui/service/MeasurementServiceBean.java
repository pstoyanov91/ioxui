/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.service;

import com.ioxui.models.Measurement;
import com.ioxui.repository.MeasurementRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cpt2pes
 */
@Service
public class MeasurementServiceBean implements MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    @Override
    public List<Measurement> getMeasurementsForDevice(long deviceid) {
        List<Measurement> measurements = measurementRepository.getMeasurementsForDevice(deviceid);
        return measurements;
    }

    @Override
    public Measurement getMeasurement(long deviceid) {
        return measurementRepository.getOneMeasurement(deviceid);
    }

}
