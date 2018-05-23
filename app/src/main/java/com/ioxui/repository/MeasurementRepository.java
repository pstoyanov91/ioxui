/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.repository;

import com.ioxui.models.Measurement;
import java.util.List;

/**
 *
 * @author cpt2pes
 */
public interface MeasurementRepository {

    /**
     *
     * @param deviceid
     * @return
     */
    public List<Measurement> getMeasurementsForDevice(long deviceid);
    public Measurement getOneMeasurement(long deviceid);
}
