/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioxui.api;

import com.ioxui.models.Device;
import com.ioxui.models.Schedule;
import com.ioxui.service.DeviceService;
import com.ioxui.service.ScheduleService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cpt2pes
 */
@Controller
public class AppController {
    
    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private ScheduleService scheduleService;
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model){
        List<Device> devices = deviceService.getAllRegularDevices();
        model.addAttribute("devices", devices);
        return "dashboard";
    }
    
    @RequestMapping("/schedule")
    public String schedule(Model model){
       List<Schedule> scheduledTasks = scheduleService.getAllScheduledTasks();
       List<Device> devices = deviceService.getAllRegularDevices();
       Map<Integer, Map<Integer, Map<Integer, List<Schedule>>>> calendarTasks = scheduleService.calendarOrderTasks();
       model.addAttribute("calendarTasks", calendarTasks);
       model.addAttribute("tasks", scheduledTasks);
       model.addAttribute("devices", devices);
       return "schedule";
    }
    
}
