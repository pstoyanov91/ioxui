/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.service;

import com.ioxui.api.RestApiScheduleController;
import com.ioxui.models.Schedule;
import com.ioxui.repository.ScheduleRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author cpt2pes
 */
@Service
public class ScheduleServiceBean implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllScheduledTasks() {
        List<Schedule> tasks = scheduleRepository.getAllScheduledTasks();
        return tasks;
    }

    @Override
    public void deleteTask(long id) {
        int rowsAffected = scheduleRepository.deleteScheduledTask(id);
    }

    @Override
    public ResponseEntity<Map<String, String>> createScheduledTask(Schedule schedule) {

        Map<String, String> map = new HashMap<>();
        /*
            Validate the input
         */
        if (!isValidDate(schedule.getTimestamp())) {
            map.put("timestamp", new Date().toString());
            map.put("status", HttpStatus.BAD_REQUEST.name());
            map.put("error", "400");
            map.put("message", "The date format is wrong. Please use date format yyyy-MM-dd HH:mm");
            map.put("path", "/api/schedule/tasks");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        if (!validateOperation(schedule.getIoperation())) {
            map.put("timestamp", new Date().toString());
            map.put("status", HttpStatus.BAD_REQUEST.name());
            map.put("error", "400");
            map.put("message", "The operation is wrong. Please use integer value from 0 to 5");
            map.put("path", "/api/schedule/tasks");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        /*                                                          
            Send request to the server if everything went ok up there ^|^
         */
        int rowsAffected = scheduleRepository.createScheduledTask(schedule.getIoperation(), schedule.getDeviceid(), schedule.getTimestamp());

        /*
            If nothing was created... well this is bad you know.
         */
        if (rowsAffected != 1) {
            map.put("message", "The task was not created!");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*
            If everything was ok then we'll say it to the world and we'll send back a request.
         */
        map.put("message", "The task was created");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    private boolean isValidDate(String inDate) {

        if (inDate.length() != 16) {

            try {
                throw new Exception("Probably a wrong date format. It soud be yyyy-MM-dd HH:mm");
            } catch (Exception ex) {
                Logger.getLogger(RestApiScheduleController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            Logger.getLogger(ScheduleServiceBean.class.getName()).log(Level.SEVERE, null, pe);
            return false;
        }
        return true;
    }

    private boolean validateOperation(int ioperation) {
        if (ioperation < 0 || ioperation > 5) {
            return false;
        }
        return true;
    }

    //The return value is all schedules ordered like this:
    //Map<year, MonthSchedule>
    //MonthSchedule = Map<month, DaySchedule>
    //MonthSchedule = Map<day, List<Schdule>>
    //So if anyone needs a scheduled task he can access it like this:
    //For 17.01.2017 -> map = calendarOrderedTasks(); List<Schedule> tasksForDate = map.get(2017).get(1).get(17);
    @Override
    public Map<Integer, Map<Integer, Map<Integer, List<Schedule>>>> calendarOrderTasks() {
        List<Schedule> tasks = scheduleRepository.getAllScheduledTasks();
        Map<Integer, Map<Integer, Map<Integer, List<Schedule>>>> dateMap = new HashMap<>();
        
        for (Schedule task : tasks) {
            List<Schedule> daySchedule = new ArrayList<>();
            String[] timestamp = task.getTimestamp().split(" ");
            String date = timestamp[0];
            String[] dateArr = date.split("-");
            Integer year = Integer.valueOf(dateArr[0]);
            Integer month = Integer.valueOf(dateArr[1]);
            Integer day = Integer.valueOf(dateArr[2]);
            System.out.println("Date arr -> Year: " + year + " Month: " + month + " Day: " + day);
            
            if(dateMap.get(year) == null){
                Map<Integer, Map<Integer, List<Schedule>>> monthMap = new HashMap<>();
                dateMap.put(year, monthMap);
            } 
            if(dateMap.get(year).get(month) == null){
                Map<Integer, List<Schedule>> dayMap = new HashMap<>();
                dateMap.get(year).put(month, dayMap);
            } 
            if(dateMap.get(year).get(month).get(day) == null){
                List<Schedule> dayTasks = new ArrayList<>();
                dateMap.get(year).get(month).put(day, dayTasks);
            }
            
            dateMap.get(year).get(month).get(day).add(task);
        }
        return dateMap;
    }

}
