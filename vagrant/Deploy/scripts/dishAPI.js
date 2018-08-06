Dish = class {
    constructor(json){
        this.dish_id = json.dish_id;
        this.dish_ver_id = json.dish_ver_id;
        this.dish_name = json.dish_name;
        this.description = json.description;
        this.restaurant_name = json.restaurant_name;
        this.price = json.price;
        this.menu_flag = json.menu_flag;
    }

    toJson(){
        return JSON.stringify({
            "dish_id":this.dish_id,
            "dish_ver_id":this.dish_ver_id,
            "dish_name":this.dish_name,
            "description":this.description,
            "restaurant_name":this.restaurant_name,
            "price":this.price,
            "menu_flag":this.menu_flag
        });
    }
};

getDish = function(dishVerID, token, successCallback, errorCallback){
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/dish/getDish',
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        data:{'dishVerID':dishVerID},
        success: function(dish){
            successCallback(new Dish(dish));
        },
        error:function(jqXHR, textStatus, errorThrown){
            //errorCallback(jqXHR.responseText);
            //let dishes = JSON.parse('{"dish_id":4,"dish_ver_id":4,"dish_name":"beef","description":"description beef","restaurant_name":"joojak","price":5.0,"menu_flag":"lunch special4","_versionNumber":1}')
            errorCallback(jqXHR.responseText);
            //errorCallback(dishes);
        }
    });
};

getDishByID = function(dishID, token, successCallback, errorCallback){
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/dish/getDishByID',
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        data:{"dish_id":dishID},
        success: function(dish){
            successCallback(new Dish(dish));
        },
        error:function(jqXHR, textStatus, errorThrown){
            //errorCallback(jqXHR.responseText);
            let dishes = JSON.parse('{"dish_id":4,"dish_ver_id":4,"dish_name":"beef","description":"description beef","restaurant_name":"joojak","price":5.0,"menu_flag":"lunch special4","_versionNumber":1}')
            //errorCallback(jqXHR.responseText);
            errorCallback(dishes);
        }
    });
};

getDishes = function(dishVerIDs, token, successCallback, errorCallback){
    let dishIDParams = "";
    let prefix = "dish_ver_id=";
    for(let each of dishVerIDs){
        dishIDParams += prefix + each + "&";
    }
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/dish/getDishes/',
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        data:dishIDParams,
        success: function(dishes){
            //dishes = JSON.parse('[{"dish_id":4,"dish_ver_id":4,"dish_name":"beef","description":"description beef","restaurant_name":"joojak","price":5.0,"menu_flag":"lunch special4","_versionNumber":1}]')
            let result = [];
            for (let dish of dishes){
                result.push(new Dish(dish))
            }
            successCallback(result);
        },
        error:function(jqXHR, textStatus, errorThrown){
            //let dishes = JSON.parse('[{"dish_id":4,"dish_ver_id":4,"dish_name":"beef","description":"description beef","restaurant_name":"joojak","price":5.0,"menu_flag":"lunch special4","_versionNumber":1}]')
            errorCallback(jqXHR.responseText);
            //errorCallback(dishes);
        }
    });
};

getDishesByRestaurant = function(restaurant_name, token, successCallback, errorCallback){
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/dish/getDishesByRestaurant',
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        data:{
            "restaurant_name":restaurant_name
        },
        success: function(dishes){
            //dishes = JSON.parse('[{"dish_id":4,"dish_ver_id":4,"dish_name":"beef","description":"description beef","restaurant_name":"joojak","price":5.0,"menu_flag":"lunch special4","_versionNumber":1}]')
            let result = [];
            for (let dish of dishes){
                result.push(new Dish(dish))
            }
            successCallback(result);
        },
        error:function(jqXHR, textStatus, errorThrown){
            //let dishes = JSON.parse('[{"dish_id":4,"dish_ver_id":4,"dish_name":"beef","description":"description beef","restaurant_name":"joojak","price":5.0,"menu_flag":"lunch special4","_versionNumber":1}]')
            errorCallback(jqXHR.responseText);
            //errorCallback(dishes);
        }
    });
};

updateOrCreateDish = function(dish, mode, callback, errorCallBack){
    let url;
    if(mode === 'update'){
        url = NEED_PROJECT+'/rest/dish/updateDish'
    }
    else if (mode === 'create'){
        url = NEED_PROJECT+'/rest/dish/createDish'
    }
    else if (mode === 'delete'){
        url = NEED_PROJECT+'/rest/dish/deleteDish'
    }
    $.ajax({
        type: 'POST',
        url: url,
        dataType:'text',
        data:dish.toJson(),
        success: function(message){
            sessionStorage.message = message;
            callback(message);
        },
        error:function(jqXHR, textStatus, errorThrown){
            sessionStorage.message = jqXHR.responseText;
            errorCallBack(jqXHR.responseText);
        }
    });
};

