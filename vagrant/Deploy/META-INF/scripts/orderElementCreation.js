createOrderListCol = function(orderID){
    let el = document.createElement("div");
    el.classList.add('col');
    el.setAttribute("id","order-list-col-"+orderID);
    return el;
};

createOrderListRow = function(index){
    let el = document.createElement("div");
    el.classList.add('row');
    el.classList.add('row-margin');
    el.setAttribute("id", "order-list-row-"+index);
    return el;
};

createOrderList = function(order){
    let list = document.createElement("ul");
    list.classList.add("list-group");
    list.setAttribute("id", "order-id-"+order.order_id);
    return list;
};

createDetailItem = function(detail){
    let item = document.createElement("li");
    item.setAttribute("id", "detail-id-"+detail.order_detail_id);
    item.classList.add('list-group-item');
    if(detail.status === 'new' || detail.status === 'in progress'){
        item.classList.add('list-group-item-success');
    }
    else if(detail.status === 'done'){
        item.classList.add('list-group-item-dark');
    }
    return item;
};

createDishName = function(dish){
    let item = document.createElement("h5");
    item.innerHTML = dish.dish_name;
    item.classList.add("mb-1");
    return item;
};


createDishDescription = function(dish){
    let item = document.createElement("p");
    item.innerHTML = dish.description;
    item.classList.add("mb-1");
    return item;
};
