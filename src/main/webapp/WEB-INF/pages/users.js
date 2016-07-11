$(function () {
    var wrapper = $('.users');

    $.ajax({
        url: '/notebook/person/list',
        method: 'GET',
        success: function (data) {
            data.forEach(function (item) {
                $('<div>', {
                    class: 'user',
                    html: [
                        item.firstName,
                        item.middleName,
                        item.lastName,
                        ' - ',
                        item.birthday,
                        '<a href="edit.html?id=' + item.id + '">Edit</a>'
                    ].join(' ')
                }).appendTo(wrapper);
            });
        }
    });
});
