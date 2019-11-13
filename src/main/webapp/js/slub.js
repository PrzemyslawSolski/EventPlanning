$(function () {

    $('#edit-form').css('display', 'none');
    $("#navbarName").text("Ślub@wanie");
    $("#menuTaskList").text("Przykładowa lista zadaaań");

    $('#addBtn').on('click',function () {
        // this.preventDefault();
        // console.log("ok")
        $('#edit-form').show();
    });

});