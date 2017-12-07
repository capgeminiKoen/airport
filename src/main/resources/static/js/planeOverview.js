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
            {"data": "maxGasLevel"},
            {"data": "speed"},
            {
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": '<button class="plane-delete btn btn-sm btn-danger">&times;</button>'
            }
        ]
    });


    // Delete a record
    $('#planeTable').on('click', 'button.plane-delete', function (e) {
        e.preventDefault();

        // Get row
        var row = $(this).closest('tr');
        var table = $("#planeTable").DataTable();
        var planeID = table.row(row).data().id;

        $.ajax({
            url: "http://localhost:8080/api/airport/planes/delete/" + planeID,
            type:"delete",
            success: function(response){
                updateModalText("Made it!", "We have removed the plane.");
                // Show result
                $("#standardModal").modal("toggle");
                row.remove();
            }
        });
    } );

    getPlaneData();

    // Load modal into the modal content
    loadContentInto("#addPlaneModalContent", "views/forms/addPlane.html");
});