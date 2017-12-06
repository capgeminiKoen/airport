function changeContent(url){
    $('#content').empty();
    $('#content').load(url);
}

function loadContentInto(id, url){
    $(id).empty();
    $(id).load(url);
}