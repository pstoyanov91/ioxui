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
        <script type="text/javascript" src="js/dashboard.js"></script>
        </head>
    <body>

        <#include "menu.html">
        <div class="container">
            <div class="row z-depth-1">
                <div class="col s12">
                    <h1>Dashboard</h1>
                    <table>
                        <thead>
                            <tr>
                                <th data-field="id">Name</th>
                                <th data-field="status">Status</th>
                                <th data-field="action">Action</th>
                                </tr>
                            </thead>
                        <tbody>
                    <#list devices as device>
                            <tr>
                                <td>${device.name}</td>
                                <td>
                            <#if 0 < device.status>
                                    ON
                            <#elseif device.status == 0>
                                    OFF
                            </#if>
                                    </td>
                                <td><#if device.type == 1 && device.status == 0>
                                      <!-- Switch -->
                                    <div class="switch" id="switch_${device.id}">
                                        <label>
                                            Off
                                            <input type="checkbox" class="device_switch" id="device_${device.id}">
                                            <span class="lever"></span>
                                            On
                                            </label>
                                        </div>
                                <#elseif device.type == 1 &&  device.status == 1>
                                    <div class="switch" id="switch_${device.id}">
                                        <label>
                                            Off
                                            <input type="checkbox" class="device_switch" id="device_${device.id}" checked>
                                            <span class="lever"></span>
                                            On
                                            </label>
                                        </div>
                            <#elseif device.type == 3>
                                    <form action="#">
                                        <input id="curr_status_${device.id}" type="hidden" value="${device.status}">
                                        <p class="range-field">
                                            <input class="device_scroller" type="range" value="${device.status}" id="device_${device.id}" min="0" max="5" />
                                            </p>
                                        </form>
                             </#if>
                                    </td>
                                </tr>
                    </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        <#include "footer.html">
        </body>
    </html>
