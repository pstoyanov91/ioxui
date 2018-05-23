/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.repository;

import com.ioxui.models.Measurement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cpt2pes
 */
@Repository
public class MeasurementRepositoryBean implements MeasurementRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Measurement> getMeasurementsForDevice(long deviceid) {
        String sql = "SELECT a.deviceid, to_char(a.itimestamp, 'YYYY-MM-DD HH24') AS itimestamp, avg(a.ivalue) AS value FROM measurements a "
                + "WHERE a.itimestamp > current_timestamp - interval '1440 minutes' AND a.deviceid =" + deviceid + " "
                + "GROUP BY a.deviceid, to_char(a.itimestamp, 'YYYY-MM-DD HH24') "
                + "ORDER BY to_char(a.itimestamp, 'YYYY-MM-DD HH24') ASC;";
        List<Measurement> measurements = jdbcTemplate.query(sql, new MeasurementMapper());
        return measurements;
    }

    @Override
    public Measurement getOneMeasurement(long deviceid) {
        String sql = "SELECT a.ivalue FROM measurements a WHERE a.deviceid = ? ORDER BY a.itimestamp DESC LIMIT 1;";
        return jdbcTemplate.queryForObject(sql, new Object[]{1212L}, new MeasurementMapper());
    }
    
    private static final class MeasurementMapper implements RowMapper<Measurement>{

        @Override
        public Measurement mapRow(ResultSet rs, int rowNum) throws SQLException {
           Measurement measurement = new Measurement();
           measurement.setId(rs.getLong("id"));
           measurement.setDeviceid(rs.getLong("deviceid"));
           measurement.setItimestamp(rs.getString("itimestamp"));
           measurement.setIvalue(rs.getInt("ivalue"));
           measurement.setItextvalue(rs.getString("itextvalue"));
           return measurement;
        }
    
    }
    
}
