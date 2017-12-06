function getPlaneData(){

    $.ajax({
        url: "http://localhost:8080/api/airport/planes/all",
        type:"get",
        success: function(response){

           $("#planeTable").DataTable().clear();
           $("#planeTable").DataTable().rows.add(response);
           $("#planeTable").DataTable().columns.adjust().draw();
        }
    });

}

$(document).ready(function (){
    $("#planeTable").DataTable({
        columns: [
            {"data": "name"},
            {"data": "gasLevel"},
            {"data": "maxGasLevel"}
        ]
    });

    getPlaneData();
});