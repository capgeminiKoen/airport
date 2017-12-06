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
});

function addPlane(){

    var name = $("#planeName").val();
    var gasLevel = $("#gasLevel").val();
    var maxGasLevel = $("#maxGasLevel").val();


    var plane = {
        name:name,
        gasLevel:gasLevel,
        maxGasLevel:maxGasLevel
    };

    var planeString = JSON.stringify(plane);

    $.ajax({
        url: "http://localhost:8080/api/airport/planes/add",
        type: "post",
        data: planeString,
        contentType: "application/json",
        success: function(result) {
//        TODO: fill modal with correct information.
            // Show result
            $("#standardModal").modal("toggle");
            // Toggle modal
            $("#addPlaneModal").modal("hide");
            // Refresh dataTable
            getPlaneData();
        }
    });
}