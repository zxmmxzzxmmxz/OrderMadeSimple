
createDishCard = function(dish){
    this.prefix = "modify-dish-";
    let card = document.createElement("div");
    card.classList.add("card");
    card.setAttribute("style","width: 18rem;");
    let cardBody = document.createElement("div");
    cardBody.classList.add("card-body");

    let dishName = document.createElement("h5");
    dishName.classList.add("card-title");
    dishName.innerHTML = dish.dish_name;

    let description = document.createElement("p");
    description.classList.add("card-text");
    description.innerHTML = dish.description;

    let price = document.createElement("p");
    price.classList.add("card-text");
    price.innerHTML = dish.price;

    let menuFlag = document.createElement("p");
    menuFlag.classList.add("card-text");
    menuFlag.innerHTML = dish.menu_flag;

    let modifyButton = document.createElement("button");
    modifyButton.innerHTML = "Modify";
    modifyButton.setAttribute("type","button");
    modifyButton.setAttribute("id",this.prefix+dish.dish_id);
    modifyButton.setAttribute("data-toggle","modal");
    modifyButton.setAttribute("data-target","#dishModificationModal");
    modifyButton.setAttribute("data-dish-id",dish.dish_id);
    modifyButton.setAttribute("data-dish-ver-id",dish.dish_ver_id);
    modifyButton.classList.add("btn");
    modifyButton.classList.add("btn-primary");

    cardBody.append(dishName);
    cardBody.append(description);
    cardBody.append(price);
    cardBody.append(menuFlag);
    cardBody.append(modifyButton);
    card.append(cardBody);
    return card;
};