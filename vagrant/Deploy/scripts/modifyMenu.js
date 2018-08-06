createDishes = function(dishes){
    let container = $("#dish-container");
    container.empty();
    let flow = new ContainerFlow(6, "menu", container, 2);
    for (let dish of dishes){
        console.log(dish);
        let card = createDishCard(dish);
        flow.insert(card);
    }
    flow.finish();
    $("#main").prepend(container);
    for (let dish of dishes){
        addModifyEventHandler("modify-dish-" + dish.dish_id);
    }
    $("#restaurant_name").val(sessionStorage.restaurant_name);
};


addModifyEventHandler = function(buttonID){
    $('#'+buttonID).on('click', function (event) {
        let updateButton = $("#update-dish");
        updateButton.html("Update Dish");
        updateButton.data("mode","update");
        let button = event.target;
        let dishVerId = button.dataset.dishVerId;

        $("#delete-dish").show();

        getDishes([dishVerId], sessionStorage.token, function(foundDishes){
            for (let dish of foundDishes){
                console.log(dish);
                $("#dish-id").val(dish.dish_id);
                $("#dish-name").val(dish.dish_name);
                $("#dish-description").val(dish.description);
                $("#restaurant-name").val(dish.restaurant_name);
                $("#price").val(dish.price);
                $("#menu-flag").val(dish.menu_flag);
            }
        });

    });
};

installModalConfirmButtonHandler = function(){
    $('#update-dish').on('click', function () {
        let dishJson = {
            "dish_name":$("#dish-name").val(),
            "description":$("#dish-description").val(),
            "restaurant_name":$("#restaurant_name").val(),
            "price":$("#price").val(),
            "menu_flag":$("#menu-flag").val()
        };
        let dishIDField = $("#dish-id");
        if(dishIDField.val() !== ''){
            let idObj = {"dish_id":dishIDField.val()};
            dishJson = $.extend(dishJson,idObj);
        }
        let dish = new Dish(dishJson);
        let mode = $("#update-dish").data("mode");
        updateOrCreateDish(dish,mode, function () {
            $("#main").empty();
            clearModal();
            $("#close-modal").trigger("click");
            loadDishes();
        }, function(){
            $("#main").empty();
            clearModal();
            $("#close-modal").trigger("click");
            loadDishes();
        });

    });
};

installModalDeleteButtonHandler = function() {
    $("#delete-dish").on('click', function(){
        let dishJson = {
            "dish_name":$("#dish-name").val(),
            "description":$("#dish-description").val(),
            "restaurant_name":$("#restaurant_name").val(),
            "price":$("#price").val(),
            "menu_flag":$("#menu-flag").val()
        };
        let dishIDField = $("#dish-id");
        if(dishIDField.val() !== ''){
            let idObj = {"dish_id":dishIDField.val()};
            dishJson = $.extend(dishJson,idObj);
        }
        let dish = new Dish(dishJson);
        let mode = 'delete';
        updateOrCreateDish(dish,mode, function () {
            $("#main").empty();
            clearModal();
            $("#close-modal").trigger("click");
            loadDishes();
        }, function(){
            $("#main").empty();
            clearModal();
            $("#close-modal").trigger("click");
            loadDishes();
        });
    })
}

loadDishes = function() {
    getDishesByRestaurant(sessionStorage.restaurant_name,sessionStorage.token,createDishes,function(msg){
        sessionStorage.message = msg;
    });
};

clearModal = function(){
    $("#dish-id").val("");
    $("#dish-name").val("");
    $("#dish-description").val("");
    $("#price").val("");
    $("#menu-flag").val("");
};

function addNewButton()
{
    let newDishButton = document.createElement("button");
    newDishButton.innerHTML = "New Dish";
    newDishButton.setAttribute("type","button");
    newDishButton.setAttribute("id","create-dish");
    newDishButton.setAttribute("data-toggle","modal");
    newDishButton.setAttribute("data-target","#dishModificationModal");
    newDishButton.classList.add("btn");
    newDishButton.classList.add("btn-primary");
    $('#new-dish-btn').html(newDishButton);

    $("#create-dish").on('click', function(){
        clearModal();
        $("#delete-dish").hide();
        let updateButton = $("#update-dish");
        updateButton.html("Add Dish");
        updateButton.data("mode","create");
    });
}

$(function () {
    addNewButton();
    loadDishes();
    installModalConfirmButtonHandler();
    installModalDeleteButtonHandler();
});
