$(function () {

    var form = $('#ajax-person-edit');

    var userId = location.search.split('id=')[1];

    $.ajax({
        url: '/notebook/person/user',
        method: 'GET',
        data: {
            id: userId
        },
        success: function (data) {
            $('#firstName').val(data.firstName);
            $('#lastName').val(data.lastName);
            $('#middleName').val(data.middleName);
            $('#datepicker').val(data.birthday);
        }
    });

    form.find('button').on('click', function () {
        $.ajax({
            url: '/notebook/person/update',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                id: userId,
                first_name: $('#firstName').val(),
                last_name: $('#lastName').val(),
                middle_name: $('#middleName').val(),
                birthday: $('#datepicker').val()
            }),
            success: function (data) {
                window.location = 'index.html';
            },
            error: function (error) {
                var errArray = $.parseJSON(error.responseText);
                alert(errArray.join("\n"));
            }
        });

        return false;
    });
});
