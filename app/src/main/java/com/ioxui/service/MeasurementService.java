/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.service;

import com.ioxui.models.Measurement;
import java.util.List;

/**
 *
 * @author cpt2pes
 */
public interface MeasurementService {
    List<Measurement> getMeasurementsForDevice(long deviceid);
    Measurement getMeasurement(long deviceid);
}
