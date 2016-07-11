$(function() {
    
    var form = $('#ajax-person-add');

    form.find('button').on('click', function() {
        $.ajax({
            url: '/notebook/person/add',
            method: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                first_name: $('#firstName').val(),
                last_name: $('#lastName').val(),
                middle_name: $('#middleName').val(),
                birthday: $('#datepicker').val()
            }),
            success: function(data) {
                window.location = 'index.html';
            },
            error: function(error) {
                var errArray = $.parseJSON(error.responseText);
                alert(errArray.join("\n"));
            }
        });

        return false;
    });
});
