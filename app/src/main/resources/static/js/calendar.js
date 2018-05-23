/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cal_days_labels_en = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
var cal_months_labels_en = ['January', 'February', 'March', 'April',
    'May', 'June', 'July', 'August', 'September',
    'October', 'November', 'December'];

var cal_days_labels_bg = ['Неделя', 'Понеделник', 'Вторник', 'Сряда', 'Четвъртък', 'Петък', 'Събота'];
var cal_months_labels_bg = ['Януари', 'Февруари', 'Март', 'Април',
    'Май', 'Юни', 'Юли', 'Август', 'Септември',
    'Октомври', 'Ноември', 'Декември'];

var cal_days_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var cal_current_date = new Date();
var cal_days_labels = cal_days_labels_en;
var cal_months_labels = cal_months_labels_en;

function Calendar() {
    this.scheduledTasks;
    this.month = cal_current_date.getMonth();
    this.year = cal_current_date.getFullYear();
    this.calendarHtmlSkeleton = '';
    this.containerId = '';
}

Calendar.prototype.calendarInit = function (containerId, scheduledTasks) {
    //Set the tasks
    this.scheduledTasks = scheduledTasks;
    //Set the id
    this.containerId = containerId;
    //Append callendar skeleton 
    $("#" + this.containerId).append(this.generateHtmlSkeleton());
    //Populate calecalendar
    this.populateSkeleton();
    //Initialize the navigation functionality
    this.initNavigation();
};

Calendar.prototype.datepickerInit = function (containerId) {
    this.createScheduledTasksContainer(containerId);
    $("#datepicker_selected_date").text(cal_current_date.getDate() + "-" + (this.month + 1) + "-" + this.year);
};

Calendar.prototype.organizerInit = function (scheduledTasks) {
    //Set the tasks
    this.scheduledTasks = scheduledTasks;
};


Calendar.prototype.generateHtmlSkeleton = function () {
    var calendarHtmlSkeleton = '';
    calendarHtmlSkeleton += '<div id="pas_calendar_container" class="row">';
    calendarHtmlSkeleton += '<div id="pas_calendar_header" class="center-align col s12">';
    calendarHtmlSkeleton += '<h5 id="calendar_month_year"></h5>';
    calendarHtmlSkeleton += '<div id="pas_calendar_nav_container" class="col s12">';
    calendarHtmlSkeleton += '<a id="cal_nav_prev" class="left btn-floating btn-small waves-effect waves-light teal lighten-2"><i class="material-icons left">keyboard_arrow_left</i></a>';
    calendarHtmlSkeleton += '<a id="cal_nav_next" class="right btn-floating btn-small waves-effect waves-light teal lighten-2"><i class="material-icons left">keyboard_arrow_right</i></a>';
    calendarHtmlSkeleton += '</div>';
    calendarHtmlSkeleton += '</div>';
    calendarHtmlSkeleton += '<div id="pas_calendar_body" class="col s12"></div>';
    calendarHtmlSkeleton += '</div>';

    this.calendarHtmlSkeleton = calendarHtmlSkeleton;
    return this.calendarHtmlSkeleton;
};

Calendar.prototype.populateSkeleton = function () {

    //Clear previous calendar
    this.html = '';

    // get first day of month
    var firstDay = new Date(this.year, this.month, 1);

    //Fix the days of the week to start from monday (not sunday like it is by default functionality)
    var startingDay = firstDay.getDay();
    if (firstDay.getDay() === 0) {
        startingDay = 6;
    } else {
        startingDay -= 1;
    }

    // find number of days in month
    var monthLength = cal_days_in_month[this.month];

    // compensate for leap year
    if (this.month === 1) { // February only!
        if ((this.year % 4 === 0 && this.year % 100 !== 0) || this.year % 400 === 0) {
            monthLength = 29;
        }
    }

    // Turn off prev functionality if needed
    (this.month === cal_current_date.getMonth() && this.year === cal_current_date.getFullYear()) ? $("#cal_nav_prev").hide() : $("#cal_nav_prev").show();


    // do the header
    var monthName = cal_months_labels[this.month];

    var monthAndYear = monthName + " " + this.year;
    $("#calendar_month_year").text(monthAndYear);

    var html = '<table class="centered"><thead>';
    html += '<tr>';
    for (var i = 0; i <= 6; i++) {
        html += '<th class="calendar-header-day">';
        html += cal_days_labels[i];
        html += '</th>';
    }
    html += '</tr></thead>';
    html += '<tbody><tr>';

    //Calculate the number of needed rows to fill in the weeks.
    var weeksInTheMonth = Math.ceil((monthLength + startingDay) / 7);
    console.log(this.scheduledTasks[this.year]);
    // fill in the days
    var day = 1;
    // this loop is for the weeks (rows)
    for (var i = 0; i < weeksInTheMonth; i++) {
        // this loop is for weekdays (cells)
        for (var j = 0; j <= 6; j++) {

            //Check if there are tasks today
            thereAreTasksToday = this.areThereTasksToday(day);

            //These check's are made to deactivate all days that are passed;
            if (this.month === cal_current_date.getMonth() && this.year === cal_current_date.getFullYear() && day < cal_current_date.getDate()) {
                html += '<td class="calendar_day cal_disabled_day">';
            } else if (day <= monthLength && (i > 0 || j >= startingDay)) {
                if (thereAreTasksToday) {
                    html += '<td id="cal_day_' + day + '" class="calendar_day cal_active_day day_with_tasks">';
                } else {
                    html += '<td id="cal_day_' + day + '" class="calendar_day cal_active_day">';
                }
            } else {
                html += '<td class="calendar_day">';
            }
            /*
             * This roughly translates to: 
             * "fill the cell only if we haven't run out of days, and we're sure we're not in the first row, 
             * or this day is after the starting day for this month." Whew!
             * 
             */
            if (day <= monthLength && (i > 0 || j >= startingDay)) {
                html += day;
                if (thereAreTasksToday) {
                    html += '<div><div class="dot"></div></div>';
                }
                day++;
            }
            html += '</td>';
        }

        html += '</tr><tr>';

    }
    html += '</tr></tbody></table>';

    $("#pas_calendar_body").append(html);

    //Append the datepicker functionality to the newly created dates
    this.datepicker();

    //Mark the current day
    if (this.month === cal_current_date.getMonth() && this.year === cal_current_date.getFullYear()) {
        $("#cal_day_" + cal_current_date.getDate()).css("background-color", "#ccc");
    }

    //Show up all tasks today
    this.getAllTasksToday();

};

Calendar.prototype.initNavigation = function () {

    //Change to next month when clicking the corresponding button
    var calendar = this;

    $("#cal_nav_next").click(function () {
        calendar.nextMonth();
    });

    //Change to next month when clicking the corresponding button
    $("#cal_nav_prev").click(function () {
        calendar.prevMonth();
    });

};

Calendar.prototype.nextMonth = function () {

    if ((this.month + 1) > 11) {
        this.month = 0;
        this.year += 1;
    } else {
        this.month += 1;
    }

    this.changeMonth();
};

Calendar.prototype.prevMonth = function () {

    if ((this.month - 1) < 0) {
        this.month = 11;
        this.year -= 1;
    } else {
        this.month -= 1;
    }

    this.changeMonth();

};

Calendar.prototype.changeMonth = function () {

    $("#pas_calendar_body>table").remove();

    this.populateSkeleton();

};

Calendar.prototype.createScheduledTasksContainer = function (container_id) {
    var html = '<div class="row">';
    html += '<div id="datepicker_date_label" class="col s6 m6 l6">Date:</div><div id="datepicker_selected_date" class="col s6 m6 l6"></div>';
    html += '</div>';
    html += '<div class="row"><div class="datepicker_shcedule_content"></div></div>';
    $("#" + container_id).append(html);
};

Calendar.prototype.datepicker = function () {
    var calendar = this;
    $(".cal_active_day").click(function () {
        $(".selected").removeClass("selected");
        $(this).addClass("selected");
        var selected_day = $(this).text();
        $("#datepicker_selected_date").text(selected_day + "-" + (calendar.month + 1) + "-" + calendar.year);
    });
};

Calendar.prototype.getAllTasksToday = function () {
    var calendar = this;
    $(".cal_active_day").click(function () {
        $(".datepicker_shcedule_content").empty();
    });
    $(".day_with_tasks").click(function () {
        var day = $(this).text();
        var html = '';
        html += '<table>';
        html += '<thead><tr><th>id</th><th>time</th><th>operation</th></tr></thead><tbody>';
        console.log(calendar.year + "  " + calendar.month);
        var tasks = calendar.scheduledTasks[calendar.year][(calendar.month + 1)][day];
        console.log(tasks);
        $(".datepicker_shcedule_content").empty();
        for (var i = 0; i < tasks.length; ++i) {
            html += '<tr>';
            html += '<td>' + tasks[i]["id"] + '</td>';
            html += '<td>' + tasks[i]["timestamp"] + '</td>';
            html += '<td>' + tasks[i]["operation"] + '</td>';
            html += '</tr>';
        }
        html += '</tbody></table>';
        console.log(html);
        $(".datepicker_shcedule_content").append(html);
    });
};

Calendar.prototype.areThereTasksToday = function (day) {
    //Check if there are some tasks this year
    if (typeof this.scheduledTasks[this.year] !== 'undefined' && this.scheduledTasks[this.year] !== null) {
        //Check if there are some tasks this year
        if (typeof this.scheduledTasks[this.year][(this.month + 1)] !== 'undefined' && this.scheduledTasks[this.year][((this.month + 1))] !== null) {
            //Check if there are some tasks today
            if (typeof this.scheduledTasks[this.year][(this.month + 1)][day] !== 'undefined' && this.scheduledTasks[this.year][((this.month + 1))][day] !== null) {
                return true;
            }
        }
    }
    return false;
};


