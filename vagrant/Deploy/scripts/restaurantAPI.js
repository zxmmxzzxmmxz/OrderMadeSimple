Restaurant = class {
    constructor(fromJson) {
        this.orderDetails = [];
        if(fromJson !== undefined) {
            this.hours = fromJson.hours;
            this.contact = fromJson.contact;
        }
    }

    toJson(){
        return JSON.stringify({
            "hours":this.hours,
            "contact":this.contact
        })
    }
};

createHourAndContactList = function(item){
    var business = item.hours;
    var contact = item.contact;

    console.log(item);

    console.log('createHourAndContactList');
    var html = "<table class='table table-borderless table-light small'>";

    $.each(business, function(i, info){
        html += '<tr>' +
            '<td>' + info.Day + ':</td>' +
            '<td>' + info.OpenHr +' - ' + info.CloseHr +'</td>' +
            '<tr/>';
    });
    html += '</table>';

    html += '<p class="small">' + contact.Address + '</p>';
    html += '<p class="small">' + contact.Phone + '</p>';

    return html;
}

loadRestaurant = function(restaurant_id, token, successCallBack, errorCallBack){
    $.ajax({
        type: 'GET',
        url: NEED_PROJECT+'/rest/restaurant/info/'+restaurant_id,
        crossDomain: true,
        headers: {
            'Authorization':'cmpt470 '+ token
        },
        success: function(items){
            successCallBack(items);
        },
        error:function (jqXHR, textStatus, errorThrown) {
            errorCallBack(jqXHR.responseText)
        }
    })
};
