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


    var airport = {
        name:airportName,
        city:airportCity
    };

    var airportString = JSON.stringify(airport);

    $.ajax({
        url: "http://localhost:8080/api/airport/airports/add",
        type: "post",
        data: airportString,
        contentType: "application/json",
        success: function(result) {

            updateModalText("Yes", "Added the airport.");
            // Show result
            $("#standardModal").modal("toggle");
            // Dismiss modal
            $("#addAirportModal").modal("hide");
            // Refresh dataTable
            getAirportData();
        }
    });
}