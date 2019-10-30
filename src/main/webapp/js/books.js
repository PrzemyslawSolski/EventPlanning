$(function () {

    $('#edit-form').css('display', 'none');
    $("#navbarName").text("Planowanie ślubu");
    $("#menuTaskList").text("Przykładowa lista zadań");
    $.ajax({
        url: "http://localhost:8282/books/",
        type: "GET",
        dataType: "json",
    }).done(function (result) {

        for (var i = 0; i < result.length; i++) {
            var book = result[i];
            var newTr = $('<tr>').appendTo($('#tBody'));
            $('<td>').text(book.id).attr('id', 'bookId_' + i).appendTo(newTr);
            $('<td>').text(book.author).appendTo(newTr);
            $('<td>').text(book.title).addClass('title').data('id', book.id).appendTo(newTr);
            $('<td><button class="btn btn-danger">Usuń</button></td>').attr('id', book.id).appendTo(newTr);
            $('<tr>').appendTo($('#tBody')).append($('<td colspan="3" class="details"></td>'));
        }


        $('.details').hide();
        $('#tBody').on('click', 'button', function () {

            // alert("button");
        });

        $('#tBody').on('click', '.title', function () {
            var id = $(this).data('id');
            var detail = $(this).parent().next().find('td');
            $.ajax({
                url: "http://localhost:8282/books/" + id,
                type: "GET",
                dataType: "json",
            }).done(function (result) {
                detail.text(result.author + ' - ' + result.title).toggle();
                // alert("id: " + id);
            })

        });


    });
    $('#addBtn').on('click',function () {
        // this.preventDefault();
        // console.log("ok")
        $('#edit-form').show();
    });

});