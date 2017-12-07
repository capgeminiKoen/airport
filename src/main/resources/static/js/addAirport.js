$(document).ready(function(){
    $('#addAirportForm').validator().on('submit', function (e) {
      if (e.isDefaultPrevented()) {
        // INVALID FORM, DO NOTHING
      } else {
        // Do something.
        addAirport();
        // Prevent default form action
        e.preventDefault();
      }
    });
});

function addAirport(){

    var airportName = $("#airportName").val();
    var airportCity = $("#airportCity").val();
    var xCoordinate = $("#airportLocationX").val();
    var yCoordinate = $("#airportLocationY").val();

    var airport = {
        name:airportName,
        city:airportCity,
        xCoordinate:xCoordinate,
        yCoordinate:yCoordinate
    };

    var airportString = JSON.stringify(airport);

    $.ajax({
        url: "http://localhost:8080/api/airport/airports/add",
        type: "post",
        data: airportString,
        contentType: "application/json",
        success: function(result) {

            updateModalText("Made it!", "We have added the airport.");
            // Show result
            $("#standardModal").modal("toggle");
            // Dismiss modal
            $("#addAirportModal").modal("hide");
            // Refresh dataTable
            getAirportData();
        }
    });
}