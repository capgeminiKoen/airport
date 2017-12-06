function getAirportData(){

    $.ajax({
        url: "http://localhost:8080/api/airport/airports/all",
        type:"get",
        success: function(response){

           $("#airportTable").DataTable().clear();
           $("#airportTable").DataTable().rows.add(response);
           $("#airportTable").DataTable().columns.adjust().draw();
        }
    });

}

$(document).ready(function (){
    $("#airportTable").DataTable({
        columns: [
            {"data": "name"},
            {"data": "city"}
        ]
    });

    getAirportData();

    // Load modal into the modal content
    loadContentInto("#addAirportModalContent", "views/forms/addAirport.html");
});