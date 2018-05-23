<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

<head>
    <title>IOXUI Dashboad</title>
    <#include "headers.html">
        <script type="text/javascript" src="js/schedule.js"></script>
        <script type="text/javascript" src="js/calendar.js"></script>
        <link rel="stylesheet" href="css/calendar.css">
</head>

<body>
    <#include "menu.html">
        <div class="container">
            <div class="row card-panel">
                <div class="col s12">
                    <input id="shedule_device_id" type="hidden" value="" />
                    <input id="schedule_operation" type="hidden" value="" />
                    <div class="row card-panel" id="wizard_steps_container">
                        <div class="col s12">
                            <div class="left wizard_step" id="first_step">
                                1. Choose a date
                            </div>
                            <div class="left" id="wizard_arrow"></div>
                            <div class="left wizard_step" id="second_step">
                                2. Choose a device to turn ON/OFF
                            </div>
                        </div>
                    </div> 
                    <div class="row" id="wizard_content">
                        <div class="col s12">
                            <div class="row" id="first_step_content">
                                <div class="row">
                                     <div id="calendar_container" class="col s12 m8 l6"></div>
                                     <div id="day_tasks_container" class="col s12 m4 l6"></div>
                                </div>
                            </div>
                            <div class="row" id="second_step_content">
                                <table class="">
                                    <thead>
                                        <tr>
                                            <th class="" data-field="id">#</th>
                                            <th class="" data-field="device">Device</th>
                                            <th class="" data-field="operation">Operation (ON/OFF)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <#list devices as device>
                                            <tr>
                                                <td>${device?index + 1}</td>
                                                <td>${device.name}</td>
                                                <td class="schedule_options">
                                                    <#if device.type == 1>
                                                        <#if device.status == 0>
                                                            <button class="waves-effect waves-light btn" onclick="wizard_first_step(${device.id}, 0)">OFF</buton>
                                                            <button class="blue-grey lighten-5 waves-effect waves-light btn" onclick="wizard_first_step(${device.id}, 1)">ON</buton>
                                                        <#else>
                                                            <button class="blue-grey lighten-5 waves-effect waves-light btn" onclick="wizard_first_step(${device.id}, 0)">OFF</buton>
                                                            <button class="waves-effect waves-light btn" onclick="wizard_first_step(${device.id}, 1)">ON</buton>
                                                        </#if>
                                                    <#elseif device.type == 3>

                                                            <#list [0, 1, 2, 3, 4, 5] as i>
                                                            
                                                            <#if i == device.status>
                                                                <button class="m-t-5 waves-effect waves-light btn" onclick="wizard_first_step(${device.id}, ${i})">
                                                                    <#if i == 0>OFF<#else>${i}</#if>
                                                                </buton>
                                                                <#else>
                                                                <button class="m-t-5 blue-grey lighten-5 waves-effect waves-light btn" onclick="wizard_first_step(${device.id}, ${i})">
                                                                    <#if i == 0>OFF<#else>${i}</#if>
                                                                </buton>
                                                            </#if>
                                                           
                                                           </#list>
                                                    </#if>
                                                </td>
                                            </tr>  
                                        </#list>
                                     </tbody> 
                                </table>
                                <div class="col s12">
                                    <div class="row">
                                        <button class="left waves-effect waves-light btn" onclick="wizard_back()">
                                            <i class="material-icons left">keyboard_arrow_left</i>Back
                                        </button>
                                        <button class="right waves-effect waves-light btn" onclick="wizard_finish()">
                                            Submit<i class="material-icons right">send</i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row z-depth-1">
                <div class="col s12">
                    <h2>Scheduled tasks</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Timestamp</th>
                                <th>Operation (ON/OFF)</th>
                                <th>Delete task</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list tasks as task>
                                <tr id="task_row_${task.id}">
                                    <td>${task?index + 1}</td>
                                    <td>${task.timestamp}</td>
                                    <td>${task.ioperation}</td>
                                    <td><button class="waves-effect waves-light btn" onclick="confirm(${task.id})">Delete</buton></td>
                                </tr>  
                            </#list>
                            </tbody> 
                        </table>
                    </div>
                </div>
            </div>
        <#include "footer.html">
            <!-- Modal Structure -->
            <div id="confirmation" class="modal">
                <div class="modal-content">
                    <h4>Сигурен ли си, че искаш да изтриеш тази задача?</h4>
                    <p>Натиснете Да, ако искате да изтриете задачата и Не ако искате да се откажете.</p>
                </div>
                <div class="modal-footer">
                    <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat ">Не</a>
                    <button onclick="remove_task()" id="confirm_btn" class="modal-action modal-close waves-effect waves-green btn-flat">Да</button>
                </div>
            </div>
    <script type="text/javascript">
        //Map all the values comming from the backend to the calendar shcedule list.
        var scheduledTasks = [];
        <#list calendarTasks as year, yearMap>
                    //year?c means we get the value of year in the normal format e.g. -> 2017
                    // if we use only year we get -> 2,016, which breaks the logig afterwords
                    scheduledTasks["${year?c}"] =  [];
            <#list yearMap as month, monthMap>
                    scheduledTasks["${year?c}"]["${month}"] = [];
                <#list monthMap as day, tasks>
                    scheduledTasks["${year?c}"]["${month}"]["${day}"] = [];
                    <#list tasks as task>
                        var currTask = [];
                        currTask["id"] = "${task.id}";
                        currTask["timestamp"] = "${task.timestamp}";
                        currTask["operation"] = "${task.ioperation}";
                        scheduledTasks["${year?c}"]["${month}"]["${day}"].push(currTask);
                    </#list>
                </#list>
            </#list>   
        </#list>
         
        //console.log(scheduledTasks[0]);
        var calendar = new Calendar();
        //Create calnedar only.
        calendar.calendarInit("calendar_container", scheduledTasks);
        //Initiate datepicker functionality
        calendar.datepickerInit("day_tasks_container");
        //Initiate organizer functionality
        //calendar.organizerInit(scheduledTasks);
    </script>
</body>

</html>