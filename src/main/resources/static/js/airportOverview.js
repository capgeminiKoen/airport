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
/* Formatting function for row details */
function format ( airport ) {
    // `d` is the original data object for the row
    var table = 'Planes overview:<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<th>Name</th>'+
            '<th>Gas level</th>'+
            '<th>Tank size</th>'+
        '</tr>';
    airport.planes.forEach(function(plane){
        table += '<tr>'+
            '<td>' + plane.name + '</td>'+
            '<td>'+plane.gasLevel+'</td>'+
            '<td>'+plane.maxGasLevel+'</td>'+
            '<td><button class="btn btn-sm btn-primary" onclick="addGasToPlane('+plane.id+');">Fill her up</button></td>'+
            '<td><button class="btn btn-sm btn-warning" onclick="openMovePlaneModal('+plane.id+');">Move plane</button></td>'+
        '</tr>';
    });
    table += '</table>';

    return table;
}

function openMovePlaneModal(id){
    // Show result
    $("#moveAirplaneModal").modal("toggle");
    $("#movePlaneId").val(id);
    // TODO: get airports.
}

function addGasToPlane(id){

    $.ajax({
        url: "http://localhost:8080/api/airport/planes/addGas/" + id,
        type:"put",
        success: function(response){
            updateModalText("Message", "We have added gas!");
            // Show result
            $("#standardModal").modal("toggle");
            // Refresh dataTable
            getAirportData();
        }
    });
}

$(document).ready(function (){
    // Setup datatable
    $("#airportTable").DataTable({
        columns: [
            {
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            {"data": "name"},
            {"data": "city"}
        ],
         order: [[1, 'asc']]
    });

    // Add event listener for opening and closing details
    $('#airportTable tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = $("#airportTable").DataTable().row( tr );

        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    } );

    getAirportData();

    // Load modal into the modal content
    loadContentInto("#addAirportModalContent", "views/forms/addAirport.html");
    // Load modal into the modal content
    loadContentInto("#moveAirplaneModal", "views/forms/movePlane.html");
});