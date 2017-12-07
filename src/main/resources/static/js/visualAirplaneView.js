var airports = [];

$(document).ready(function(){
    getVisualAirports();
    loadContentInto("#airportPopupModalBody", "views/forms/airportPopup.html");
    loadContentInto("#airportVisualAddPopupModalBody", "views/forms/addVisualAirportPopup.html");

    $('#visualAirplaneView').click(visualAirportAddClick);
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
            airports[i].xCoordinate + 'px;top:' + airports[i].yCoordinate + 'px;" onclick="event.stopPropagation(); selectVisualAirport(' + i +
            ');"><span class="glyphicon glyphicon-plane"></span> ' + airports[i].name + '</button>');
    }
}

function selectVisualAirport(airportIndex){
    var airport = airports[airportIndex];
    fillAirportPopup(airport);
    $("#airportPopup").modal("show");
}

function fillAirportPopup(airport){
    $("#airportPopupPlanes").empty();
    for(i = 0; i < airport.planes.length; i++){
        $("#airportPopupPlanes").append('<option value="' + airport.planes[i].id + '">' + airport.planes[i].name + '</option>');
    }
    $("#airportPopupLocation").empty();
    for(i = 0; i < airports.length; i++){
        $("#airportPopupLocation").append('<option value="' + airports[i].id + '">' + airports[i].name + '</option>');
    }
}

function movePlaneFromAirport(){
    var selectedPlaneId = $("#airportPopupPlanes").val();
    var selectedAirportId = $("#airportPopupLocation").val();


    $.ajax({
        url: "http://localhost:8080/api/airport/airports/movePlane/" + selectedPlaneId + "/" + selectedAirportId,
        type:"put",
        success: function(response){
            updateModalText("Message", "We have updated the position of the plane.");
            // Show result
            $("#standardModal").modal("toggle");
            // Hide earlier modal
            $("#airportPopup").modal("hide");
            // Refresh dataTable
            getVisualAirports();
        },
        error: function(response){
            showModal("Error", "This airport is unreachable. Please fill the tanks or select another one.");
        }
    });
}

function visualAirportAddClick(e){//Offset mouse Position
    var posX = $(this).offset().left,
      posY = $(this).offset().top;

    var airportPositionX = Math.round(e.pageX - posX);
    var airportPositionY = Math.round(e.pageY - posY);
    showAddAirportModal(airportPositionX, airportPositionY);
}

function showAddAirportModal(x, y){
    $("#airportVisualAddPopup").modal("show");
    // Set x and y
    $("#visualAirportFormXPosition").val(x);
    $("#visualAirportFormYPosition").val(y);
}

function addVisualAirportFormSubmit(){

    // Get x and y
    var xCoordinate = $("#visualAirportFormXPosition").val();
    var yCoordinate = $("#visualAirportFormYPosition").val();
    var airportName = $("#airportNameVisual").val();
    var airportCity = $("#airportCityVisual").val();

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
            $("#airportVisualAddPopup").modal("hide");
            // Refresh dataTable
            getVisualAirports();
        }
    });
}