
$(function () {

    //alert(document.location);
    let searchParams = new URLSearchParams(document.location.search);
    if(searchParams.has("dish-id")){
        let id = searchParams.get("dish-id");
        $.ajax({
            type: 'GET',
            url: '/rest/dish/findDish/'+id,
            success: function(dish){
                $("#dish-id").attr("value", dish.dish_id);
                $("#dish-name").attr("value",dish.dish_name);
                $("#dish-description").attr("value",dish.description);
                $("#restaurant-name").attr("value",dish.restaurant_name);
                $("#price").attr("value",dish.price);
                $("#menu-flag").html(dish.menu_flag);
            },
            error:function(jqXHR, textStatus, errorThrown){
                sessionStorage.message = jqXHR.responseText;
                Window.location.href = "ModifyMenu.html";
            }
        });
    }
    else{
        sessionStorage.message = "Failed Loading Dish";
        Window.location.href = "ModifyMenu.html";
    }
});

$(function() {$('#dish-update').submit(function (e){
    e.preventDefault();
    $.ajax({
        type: 'POST',
        url: '/rest/dish/updateDish',
        dataType:'text',
        data:JSON.stringify({
            "dish_id":$("#dish-id").val(),
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


