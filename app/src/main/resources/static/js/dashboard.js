$(document).ready(function () {

    //Execute operation from the switches
    $(".device_switch").click(function () {
        var deviceid = $(this).attr('id').split("_").pop();
        var operation = null;
        var prev_state = null;

        if ($(this).is(':checked')) {
            operation = 1;
            prev_state = 0;
        } else {
            operation = 0;
            prev_state = 1;
        }
        execute_operation(deviceid, operation, prev_state, "switch");
    });

    //Execute operation from the sliders
    $(".device_scroller").change(function () {
        var operation = $(this).val();
        var deviceid = $(this).attr('id').split("_").pop();
        var prev_state = $("#curr_status_" + deviceid).val();
        
        execute_operation(deviceid, operation, prev_state, "scroll");
    });
});


function execute_operation(deviceid, operation, prev_state, type) {
    //$('#switch_' + deviceid).hide();

    var task = {
        deviceId: deviceid,
        ioperation: operation
    };

    console.log(task);
    $.ajax({
        type: 'POST',
        url: '/api/execute/task',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(task),
        success: function (answer) {
            //$('#switch_' + deviceid).show();
            Materialize.toast('Successfully executed task', 4000);
            if(type === "scroll"){
                $("#curr_status_" + deviceid).val(operation);
            }
        },
        error: function () {
            Materialize.toast('Error. Could not execute operation', 4000);
            
            // Retrun the element to the previous state
            
            if (type === "switch") {
                if (operation === 1) {
                    $("#device_" + deviceid).prop('checked', false);
                } else if (operation === 0) {
                    $("#device_" + deviceid).prop('checked', true);
                }
            } else if (type === "scroll") {
                $("#device_" + deviceid).val(prev_state);
            }

            //$('#switch_' + deviceid).show();
        },
        cache: false
    });
}