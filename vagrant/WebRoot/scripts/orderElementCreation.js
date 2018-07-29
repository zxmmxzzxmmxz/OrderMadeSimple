getOrderList = function(order, dishes){
    let list = document.createElement("ul");
    list.classList.add("list-group");
    list.setAttribute("id","order-id-"+order.order_id);

    for (let detail of order.orderDetails){
        let detailEl = createDetailItem(detail);
        list.appendChild(detailEl);
        let foundDish = dishes.filter(dish => dish.dish_ver_id === detail.dish_ver_id)[0];
        if(foundDish)
        {
            detailEl.appendChild(createDishName(foundDish));
            detailEl.appendChild(createDishDescription(foundDish));
        }
    }

    return list;
};

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
    if(dish == undefined)
        return "";
    let item = document.createElement("h5");
    if(dish && typeof dish.dish_name == "undefined")
    {
        item.innerHTML = "untitled dish";
    }
    else{
        item.innerHTML = dish.dish_name;
    }
    item.classList.add("mb-1");
    return item;
};


createDishDescription = function(dish){
    let item = document.createElement("p");
    item.innerHTML = dish.description;
    item.classList.add("mb-1");
    return item;
};

createOrderMetaDataPanel = function(order){
    let panel = document.createElement("div");
    let tableNumber = document.createElement("h5");
    tableNumber.innerHTML = "Table Number: " + order.table_number;
    panel.append(tableNumber);

    let createdTime = document.createElement("p");
    createdTime.classList.add("mb-1");
    createdTime.innerHTML = order.time;
    panel.append(createdTime);

    let included_in_eod_report = document.createElement("p");
    included_in_eod_report.classList.add("mb-1");
    if(order.included_in_eod_report){
        included_in_eod_report.innerHTML = "This Order Will Be Used to Calculate End Of Day Report";
    }
    else{
        included_in_eod_report.innerHTML = "This Order Is Excluded In the End Of Day Report";
    }
    panel.append(included_in_eod_report);


    let orderStatus = document.createElement("p");
    orderStatus.classList.add("mb-1");
    orderStatus.innerHTML = "Order Status: " + order.order_status;
    panel.append(orderStatus);

    return panel;
}
