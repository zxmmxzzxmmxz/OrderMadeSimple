createButton = function(text,buttonType, clickHandler){
	switch(buttonType){
		case "primary":{
			return createPrimaryButton(text, clickHandler);
			break;
		}
		case "danger":{
			return createRedButton(text, clickHandler);
			break;
		}
	}
}

createPrimaryButton = function(text, clickHandler){
	//<button type="button" class="btn btn-secondary">Button</button>
	let button = document.createElement("button");
	button.classList.add("btn");
	button.classList.add("btn-primary");
	button.innerHTML = text;
	button.addEventListener("click", clickHandler);
	return button;
}

createRedButton = function(text, clickHandler){
	//<button type="button" class="btn btn-secondary">Button</button>
	let button = document.createElement("button");
	button.classList.add("btn");
	button.classList.add("btn-danger");
	button.innerHTML = text;
	button.addEventListener("click", clickHandler);
	return button;
}

createVerticalButtonBar = function(id){
	//<div class="btn-group-vertical" id="actionButtons">
   // <button type="button" class="btn btn-secondary">Button</button>
	//</div>
	let buttonList = document.createElement("div");
	buttonList.classList.add("btn-group-vertical");
	buttonList.setAttribute("id",id);
	return buttonList;
}