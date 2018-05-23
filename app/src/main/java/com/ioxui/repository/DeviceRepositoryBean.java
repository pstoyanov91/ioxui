/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.repository;

import com.ioxui.models.Device;
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
public class DeviceRepositoryBean implements DeviceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Device> getAllRegularDevices() {
        String sql = "SELECT * FROM devices WHERE type IN (1,3) ORDER BY id;";
        return jdbcTemplate.query(sql, new DeviceMapper());
    }

    @Override
    public List<Device> getAllSensors() {
        String sql = "SELECT * FROM devices WHERE type=2 ORDER BY id";
        return jdbcTemplate.query(sql, new DeviceMapper());
    }

    @Override
    public int updateStatus(long id, int status) {
        String sql = "UPDATE devices SET status = ? WHERE id= ? ";
        int rowsAffected = jdbcTemplate.update(sql, status, id);
        return rowsAffected;
    }

    @Override
    public List<Device> getAllDevices() {
        String sql = "SELECT * FROM devices ORDER BY id;";
        return jdbcTemplate.query(sql, new DeviceMapper());
    }

    @Override
    public Device getDevice(long id) {
        String sql = "SELECT * FROM devices WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DeviceMapper());
    }

    @Override
    public int createDevice(Device device) {
        String sql = "INSERT INTO devices "
                + "(userid, type, sysid, name, command_on, command_off, measurement_unit, formula, status)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int rowsAffected = this.jdbcTemplate.update(sql, device.getUserid(), device.getSysid(), device.getName(), device.getCommandOn(),
                device.getCommandOff(), device.getMeasurementUnit(), device.getFormula(), device.getStatus());
        return rowsAffected;
    }

    // A static nested class, for mapping the results.
    private static final class DeviceMapper implements RowMapper<Device> {

        @Override
        public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
            Device device = new Device();
            device.setId(rs.getLong("id"));
            device.setUserid(rs.getLong("userid"));
            device.setType(rs.getInt("type"));
            device.setName(rs.getString("name"));
            device.setStatus(rs.getInt("status"));
            device.setSysid(rs.getString("sysid"));
            device.setCommandOn(rs.getString("command_on"));
            device.setCommandOff(rs.getString("command_off"));
            device.setMeasurementUnit(rs.getString("measurement_unit"));
            return device;
        }

    }

}
