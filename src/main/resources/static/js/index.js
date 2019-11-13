document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
});

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.autocomplete');
    var instances = M.Autocomplete.init(elems);
});

index = {
    save : function () {
        var hero = {
            name: $('#createName').val(),
            age: $('#createAge').val(),
            note: $('#createNote').val()
        };

        $.ajax({
            url: '/save',
            type: 'post',
            data: JSON.stringify(hero),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            location.href="/?page=1";
        }).fail(function () {
            alert('Please try again');
        });
    },
    findById : function (id) {
        $.ajax({
            url: '/find',
            type: 'post',
            data: JSON.stringify(id),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (hero) {
            $('#updateModal').data('id', id);
            $('#updateName').val(hero.name);
            $('#updateAge').val(hero.age);
            $('#updateNote').val(hero.note);
        }).fail(function () {
            alert('Please try again');
        })
    },
    update : function (page) {
        var hero = {
            id: $('#updateModal').data('id'),
            name: $('#updateName').val(),
            age: $('#updateAge').val(),
            note: $('#updateNote').val()
        };

        $.ajax({
            url: '/update',
            type: 'put',
            data: JSON.stringify(hero),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            location.href="/?page=" + page;
        }).fail(function () {
            alert('Please try again');
        });
    },
    deleteById : function (id, totalElements, size, page) {
        $.ajax({
            url: '/delete',
            type: 'delete',
            data: JSON.stringify(id),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            if(totalElements % size === 1) {
                location.href="/?page=" + page;
            }else {
                page++;
                location.href="/?page=" + page;
            }
        }).fail(function () {
            alert('Please try again');
        })
    }
};

function save() {
    index.save();
}

function findById(id) {
    index.findById(id);
}

function update(page) {
    index.update(page);
}

function deleteById(id, totalElements, size, page) {
    index.deleteById(id, totalElements, size, page);
}