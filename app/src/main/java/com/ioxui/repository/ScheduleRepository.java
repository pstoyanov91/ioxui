/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.repository;

import com.ioxui.models.Schedule;
import java.util.List;

/**
 *
 * @author cpt2pes
 */
public interface ScheduleRepository {

    public int createScheduledTask(int ioperation, long diveceid, String timestamp);

    public List<Schedule> getAllScheduledTasks();

    public int deleteScheduledTask(long id);
}
