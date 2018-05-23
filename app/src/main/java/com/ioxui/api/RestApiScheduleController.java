/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.api;

import com.ioxui.models.Schedule;
import com.ioxui.service.ScheduleService;
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
public class RestApiScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/api/schedule/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Schedule>> getAllTasks() {
        List<Schedule> tasks = scheduleService.getAllScheduledTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/schedule/tasks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createScheduledTask(@RequestBody Schedule schedule) {
        return scheduleService.createScheduledTask(schedule);
    }

    @RequestMapping(value = "/api/schedule/tasks/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteTask(@PathVariable("id") long id) {
        scheduleService.deleteTask(id);
        return new ResponseEntity<>("Deleted!",HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/api/schedule/tasks/calendar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> calendarOrderTasks() {
        scheduleService.calendarOrderTasks();
        return new ResponseEntity<>("Deleted!",HttpStatus.NO_CONTENT);
    }

}
