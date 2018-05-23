/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.repository;

import com.ioxui.models.Schedule;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class ScheduleRepositoryBean implements ScheduleRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createScheduledTask(int ioperation, long deviceid, String timestamp) {
        String sql = "INSERT INTO schedule (deviceid, itimestamp, ioperation) VALUES (?, ?, ?)";
        Timestamp date = Timestamp.valueOf(timestamp + ":00");
        int rowsAffected = this.jdbcTemplate.update(sql, deviceid, date, ioperation);
        return rowsAffected;
    }

    @Override
    public List<Schedule> getAllScheduledTasks() {
        String sql = "SELECT * FROM schedule WHERE itimestamp >= now() ORDER BY id";
        return this.jdbcTemplate.query(sql, new ScheduleMapper());
    }

    @Override
    public int deleteScheduledTask(long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        int rowsAffected = this.jdbcTemplate.update(sql, id);
        return rowsAffected;
    }
    
    private static final class ScheduleMapper implements RowMapper<Schedule>{

        @Override
        public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("id"));
            schedule.setDeviceid(rs.getLong("deviceid"));
            schedule.setIoperation(rs.getInt("ioperation"));
            schedule.setTimestamp(rs.getString("itimestamp"));
            return schedule;
        }

    }
    
}
