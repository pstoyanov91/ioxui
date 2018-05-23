/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('.modal').modal({
        dismissible: false
    });

    $('.datepicker').pickadate();
   // $('.timepicker').pickatime();
   populate_timepicker();
    $('select').material_select();
});

function populate_timepicker(){
    for(var hours = 0; hours<=24; ++hours){
        var time = hours;
        for(var minutes = 0; minutes<60; ++minutes){
            if(minutes<10){
                time = time + ":0" + minutes;
            }else{
                time = time + ":" + minutes;
            }
            $("#time").append("<option value='"+time+"'>"+time+"</option>");
            time = hours;
        }
    }
}

function wizard_first_step(device_id, operation) {
    $("#shedule_device_id").val(device_id);
    $("#schedule_operation").val(operation);
    $("#first_step").animate({opacity: 0.3});
    $("#first_step_content").fadeOut(500, function () {
        $("#second_step").animate({opacity: 1});
        $("#second_step_content").fadeIn();
    });
    $("html, body").animate({scrollTop: 0}, "slow");
}

function wizard_back() {
    var empty = "";
    $("#shedule_device_id").val(empty);
    $("#schedule_operation").val(empty);
    $("#second_step").animate({opacity: 0.3});
    $("#second_step_content").fadeOut(500, function () {
        $("#first_step").animate({opacity: 1});
        $("#first_step_content").fadeIn();
    });
}

function confirm(task_id) {
    $("#confirm_btn").val(task_id);
    $("#confirmation").modal('open');
}

function remove_task() {
    task_id = $("#confirm_btn").val();
    if (task_id !== null) {

        $.ajax({
            type: 'DELETE',
            url: '/api/schedule/tasks/' + task_id,
            dataType: "json",
            contentType: "application/json",
            success: function (answer) {
                Materialize.toast('Successfully deleted task', 4000);
                $("#task_row_" + task_id).remove();
            },
            error: function () {
                Materialize.toast('Error. Could not remove the task', 4000);
            },
            cache: false
        });
    } else {
        console.log("Task id is not set: " + task_id);
    }
}

