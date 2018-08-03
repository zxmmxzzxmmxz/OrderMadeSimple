ContainerFlow = class {
    constructor(colsEachRow, idPrefix, container){
        this.colsEachRow = colsEachRow;
        this.colIndex = 0;
        this.rowIndex = 0;
        this.idPrefix = idPrefix;
        this.container = container;
    }

    insert(element){
        if(this.colIndex === 0){
            this.currentRow = document.createElement("div");
            this.currentRow.classList.add("row");
            this.currentRow.setAttribute("id",this.idPrefix+"-row-"+this.rowIndex);
            this.container.append(this.currentRow);
            this.rowIndex += 1;
        }
        let col = document.createElement("div");
        col.classList.add("col-md-4");
        col.setAttribute("id",this.idPrefix+"-col-"+this.colIndex);
        col.append(element);
        this.currentRow.append(col);
        this.colIndex += 1;
        if(this.colIndex === this.colsEachRow){
            this.colIndex = 0;
        }
    }

    finish(){
        while(this.colIndex !== 0){
            this.insert("");
        }
    }
};

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