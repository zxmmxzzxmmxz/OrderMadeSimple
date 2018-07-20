
$(function() {$('#dish-add').submit(function (e){
    e.preventDefault();
    $.ajax({
        type: 'POST',
        url: '/rest/dish/addDish',
        dataType:'text',
        data:JSON.stringify({
            "dish_name":$("#dish-name").val(),
            "description":$("#dish-description").val(),
            "restaurant_name":$("#restaurant-name").val(),
            "price":$("#price").val(),
            "menu_flag":$("#menu-flag").val()
        }),
        success: function(message){
            sessionStorage.message = message;
            window.location.href = "ModifyMenu.html";
        },
        error:function(jqXHR, textStatus, errorThrown){
            sessionStorage.message = jqXHR.responseText;
            window.location.href = "ModifyMenu.html";
        }
    });
})});

$(function(){
    $('#restaurant_name').html(sessionStorage.restaurant_name);
});