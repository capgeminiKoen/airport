function updateModalText(title, body){
    $("#standardModal .modal-title").html(title);
    $("#standardModal .modal-body p").html(body);
}

function showModal(title, body){
    updateModalText(title, body);
    $("#standardModal").modal("show");
}
