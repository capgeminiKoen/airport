var airports = [];

$(document).ready(function(){
    getVisualAirports();
    loadContentInto("#airportPopupModalBody", "views/forms/airportPopup.html");
});

function getVisualAirports(){

    $.ajax({
        url: "http://localhost:8080/api/airport/airports/all",
        type: "get",
        success: function(result) {
            airports = result;
            fillVisualAirport(result);
        }
    });
}

function fillVisualAirport(airports){
// First empty the container
    $("#visualAirplaneView").empty();
    for(i = 0; i < airports.length; i++){
        // Add all buttons
        $("#visualAirplaneView").append('<button class="btn btn-sm btn-primary airportItem" style="left:' +
            airports[i].xCoordinate + 'px;top:' + airports[i].yCoordinate + 'px;" onclick="selectVisualAirport(' + i +
            ');"><span class="glyphicon glyphicon-plane"></span> ' + airports[i].name + '</button>');
    }
}

function selectVisualAirport(airportIndex){
    var airport = airports[airportIndex];
    fillAirportPopup(airport);
    $("#airportPopup").modal("show");
}

function fillAirportPopup(airport){
    for(i = 0; i < airport.planes.length; i++){
        $("#airportPopupPlanes").append('<option value="' + airport.planes[i].id + '">' + airport.planes[i].name + '</option>');
    }
    for(i = 0; i < airports.length; i++){
        $("#airportPopupLocation").append('<option value="' + airports[i].id + '">' + airports[i].name + '</option>');
    }

}