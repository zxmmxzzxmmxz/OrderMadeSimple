createDishCube = function(dish){
    let el = document.createElement("button");
    el.innerHTML = dish.dish_name+" ";
    el.classList.add("btn");
    el.classList.add("btn-primary");
    el.classList.add(".btn-block");
    el.setAttribute("id","dish_"+dish.dish_ver_id);
    el.setAttribute("data-toggle","modal");
    el.setAttribute("data-target","#newOrderDetailModal");
    let description = document.createElement("span");
    description.classList.add("badge");
    description.classList.add("badge-info");
    description.innerHTML = dish.description;
    el.appendChild(description);
    let price = document.createElement("span");
    price.classList.add("badge");
    price.classList.add("badge-secondary");
    price.innerHTML = "$ " + String(dish.price.toFixed(2));
    el.appendChild(price);

    el.addEventListener("click",function () {
        $("#dish-ver-id").val(dish.dish_ver_id);
    });
    return el;
};

loadDishesTable = function(dishes){
    sessionStorage.menu = JSON.stringify(dishes);
    let container = $("#dish-container");
    container.empty();
    let flow = new ContainerFlow(6,"dish-",container,2);
    for (let dish of dishes){
        let el = createDishCube(dish);
        flow.insert(el);
    }
    flow.finish();
};


loadAllDishes = function(){
    getDishesByRestaurant("joojak","", loadDishesTable,genericErrorCallBack);
};

installAddDishButton = function(){
    $("#add-dish").on("click",function(){
        let detailTag = 0;
        if(parseInt(sessionStorage.detailTag) === 0 || sessionStorage.detailTag === undefined){
            sessionStorage.detailTag = detailTag.toString();
        }
        else{
            detailTag = parseInt(sessionStorage.detailTag);
        }
        let newDetail = new OrderDetail();
        newDetail.dish_ver_id = $("#dish-ver-id").val();
        newDetail.status = "new";
        newDetail.special_note = $("#special-note").val();
        let orderJson = sessionStorage.order;
        let order = new Order(JSON.parse(orderJson));
        newDetail.tag = detailTag;
        sessionStorage.detailTag = (detailTag + 1).toString();
        order.orderDetails.push(newDetail);
        sessionStorage.order = order.toJson();
        $("#number-of-details").html("("+order.orderDetails.length+")");
        clearAddDishModalAndClose();
    })
};

clearAddDishModalAndClose = function(){
  $("#special-note").val("");
  $("#add-dish-close").trigger("click");
};

dumpOrderToModal = function(){
    var total_price = 0.0;
    let modal = $("#order-list");
    modal.empty();
    let order = new Order(JSON.parse(sessionStorage.order));
    for (let detail of order.orderDetails){
        let dishElement = document.createElement("div");
        dishElement.classList.add("list-group-item");
        let dish_ver_id_to_find = detail.dish_ver_id;
        let foundDish = Array.from(JSON.parse(sessionStorage.menu)).filter(each => (each.dish_ver_id.toString() === dish_ver_id_to_find))[0];
        let dish_name = document.createElement("h5");
        dish_name.innerHTML = foundDish.dish_name;
        let special_note = document.createElement("p");
        special_note.innerHTML = detail.special_note;
        let dish_price = document.createElement("p");
        dish_price.innerHTML = "$ " + foundDish.price;
        total_price += foundDish.price;
        let deleteButton = document.createElement("button");
        deleteButton.innerHTML = "Delete";
        deleteButton.classList.add("btn");
        deleteButton.classList.add("btn-danger");
        deleteButton.addEventListener("click",function () {
            deleteDetail(detail.tag);
            dumpOrderToModal();
        });

        dishElement.appendChild(dish_name);
        dishElement.appendChild(special_note);
        dishElement.appendChild(dish_price);
        dishElement.appendChild(deleteButton);
        modal.append(dishElement);
        modal.addClass("scrollable w-100");
    }
    let total_price_label = document.createElement("h5");
    total_price_label.innerHTML = "Total Price: $ " + String(total_price.toFixed(2));
    modal.append(total_price_label)
};

deleteDetail = function(detailTag) {
    let order = new Order(JSON.parse(sessionStorage.order));
    order.orderDetails = order.orderDetails.filter(detail => detail.tag !== detailTag);
    sessionStorage.order = order.toJson();
    $("#number-of-details").html("("+order.orderDetails.length+")");
};

installViewOrderButton = function(){
    $("#view-order").attr("data-toggle","modal");
    $("#view-order").attr("data-target","#currentOrderModal");
    $("#view-order").on("click",dumpOrderToModal);
};

createFreshOrder = function(){
    let order = new Order();
    order.restaurant_name = "joojak";
    order.order_status = "new";
    order.table_number = $("#table-number").val()[0];
    sessionStorage.order = order.toJson();
};

installSubmitOrderButton = function(){
    $("#submit-order").on("click", function () {
        let order = new Order(JSON.parse(sessionStorage.order));
        submitOrder(order, "",
            function (msg) {
                alert("Order Submitted!");
                createFreshOrder();
                $("#order-list").empty();
                $("#number-of-details").html("(0)");
                $("#submit-order-cancel").trigger("click");
            },
            function (msg) {
                alert(msg);
            });
    });
};

loadTableSelectionButton = function(){
    $("#table-number-submit").on("click", function () {

        if($("#table-number").val().length != 1){
            // You cannot choose more than 1 table
            alert("You cannot choose more than 1 table")
        }
        else{
            if(confirm("Are you sure")) {
                createFreshOrder();
                $("#order-list").empty();
                $("#number-of-details").html("(0)");
                $("#table-number-card").hide();
                $("#table-label").html("<h5>Table #" + $("#table-number").val() + "</h5>").show();
                $("#view-order-button-container").show();

                loadAllDishes();
                installAddDishButton();
                installViewOrderButton();
                installSubmitOrderButton();
            }
        }

    });
}


$(function(){
    $("#table-label").hide();
    $("#view-order-button-container").hide();
   loadTableSelectionButton();

});