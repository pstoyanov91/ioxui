/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.service;

import com.ioxui.models.Schedule;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author cpt2pes
 */
public interface ScheduleService {

    List<Schedule> getAllScheduledTasks();

    //The return value is all schedules ordered like this:
    //Map<year, MonthSchedule>
    //MonthSchedule = Map<month, DaySchedule>
    //MonthSchedule = Map<day, List<Schdule>>
    //So if anyone needs a scheduled task he can access it like this:
    //For 17.01.2017 -> map = calendarOrderedTasks(); List<Schedule> tasksForDate = map.get(2017).get(1).get(17);
    Map<Integer, Map<Integer, Map<Integer, List<Schedule>>>> calendarOrderTasks();

    ResponseEntity<Map<String, String>> createScheduledTask(Schedule schedule);

    void deleteTask(long id);
}
