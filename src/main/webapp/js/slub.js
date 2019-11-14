$(function () {

    $('#edit-form').css('display', 'none');
    $(".details").toggle();
    // $("#navbarName").text("Ślub@wanie");
    // $("#menuTaskList").text("Przykładowa lista zadaaań");
    // $(".taskGroup").parent().hide();
    // $('.details').css('display', 'none');
    // $('.details').toggle();

    $('#addBtn').on('click',function () {
        // this.preventDefault();
        // console.log("ok")
        $('#edit-form').show();
    });

    $('.taskGroup').on('click',function () {
        // this.preventDefault();
        var id = $(this).data('idg');
        $(".details[data-id="+id+']').toggle();
        // console.log(id);
    });

});