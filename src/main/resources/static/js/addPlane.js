$(document).ready(function(){
    $('#addPlaneForm').validator().on('submit', function (e) {
      if (e.isDefaultPrevented()) {
        // INVALID FORM, DO NOTHING
      } else {
        // Do something.
        addPlane();
        // Prevent default form action
        e.preventDefault();
      }
    });

    $.ajax({
        url: "http://localhost:8080/api/airport/airports/all",
        type: "get",
        contentType: "application/json",
        success: function(result){
            appendAirfields(result);
        }
    });
});

/* Add rooms to the roomSelect object */
function appendAirfields(result){
    $("#airportLocation").empty();
    for(i=0;i<result.length;i++) {
            $("#airportLocation").append('<option value='+result[i].id +'>'+result[i].name+'</option>');
    }
}

function addPlane(){

    var name = $("#planeName").val();
    var gasLevel = $("#gasLevel").val();
    var maxGasLevel = $("#maxGasLevel").val();
    var planeSpeed = $("#planeSpeed").val();
    var airportId = $("#airportLocation").val();

    var plane = {
        name:name,
        gasLevel:gasLevel,
        maxGasLevel:maxGasLevel,
        speed:planeSpeed
    };

    var airport = {
        id:airportId
    }

    var planeString = JSON.stringify(plane);

    $.ajax({
        url: "http://localhost:8080/api/airport/planes/add",
        type: "post",
        data: planeString,
        contentType: "application/json",
        success: function(result) {
        // Use ID of newplane to indicate the location.
            var newPlane = {
                id:result.id
            };
            var airportObject = {
                airport:airport,
                plane:newPlane
            };
            var airportString = JSON.stringify(airportObject);
            $.ajax({
                url: "http://localhost:8080/api/airport/airports/addPlane",
                type: "post",
                data: airportString,
                contentType: "application/json",
                success: function(result) {
                    updateModalText("Made it!", "We have added the plane.");
                    // Show result
                    $("#standardModal").modal("toggle");
                    // Toggle modal
                    $("#addPlaneModal").modal("hide");
                    // Refresh dataTable
                    getPlaneData();
                }
            });

        }
    });
}