getOrderList = function(order, dishes, token, detailUpdateSuccessCallback, detailUpdateFailCallback){
    let list = document.createElement("div");
    list.classList.add("list-group");
    list.setAttribute("id","order-id-"+order.order_id);
    list.appendChild(createOrderHeader(order));
    for (let detail of order.orderDetails){
        let detailEl = createDetailItem(detail, token, detailUpdateSuccessCallback, detailUpdateFailCallback);
        list.appendChild(detailEl);
        let foundDish = dishes.filter(dish => dish.dish_ver_id === detail.dish_ver_id)[0];
        detailEl.appendChild(createDishName(foundDish));
        detailEl.appendChild(createDishDescription(foundDish));
        let special_note = document.createElement("p");
        special_note.classList.add("text-danger");
        special_note.innerHTML = detail.special_note;
        detailEl.appendChild(special_note);
    }

    return list;
};

createOrderHeader = function(order){
  let header = document.createElement("div");
  if(order.table_number !== undefined && order.table_number !== ""){
      let table_number = document.createElement("h4");
      table_number.innerHTML = "Table #"+order.table_number;
      let createdTime = document.createElement("h5");
      createdTime.innerHTML = order.time;
      header.appendChild(table_number);
      header.appendChild(createdTime);
  }
  header.classList.add('list-group-item');
  return header;
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

createDetailItem = function(detail, token, detailUpdateSuccessCallback, detailUpdateFailCallback){
    let item = document.createElement("a");
    item.setAttribute("id", "detail-id-"+detail.order_detail_id);
    item.classList.add('list-group-item');
    if(detail.status === 'new' || detail.status === 'in progress'){
        item.classList.add('list-group-item-success');
        item.classList.add("list-group-item-action");
        item.addEventListener("click",function(){
            detail.status="done";
            updateOrderDetail(detail, token, detailUpdateSuccessCallback, detailUpdateFailCallback);
        });
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
