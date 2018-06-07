CREATE TABLE restaurant (
	restaurant_id SERIAL UNIQUE PRIMARY KEY,
	restaurant_name varchar(40) NOT NULL
);

CREATE TABLE dish (
	dish_id	SERIAL UNIQUE PRIMARY KEY,
	dish_name varchar(30) NOT NULL,
	description varchar(200) ,
	restaurant_id serial REFERENCES restaurant(restaurant_id),
	price real NOT NULL,
	menu_flag varchar(30)
);

CREATE TABLE order_order(
	order_id SERIAL UNIQUE PRIMARY KEY,
	restaurant_id SERIAL REFERENCES restaurant(restaurant_id),
	time timestamp
);

CREATE TABLE order_details(
	order_details_id SERIAL UNIQUE PRIMARY KEY,
	order_id SERIAL REFERENCES order_order(order_id),
	dish_id SERIAL REFERENCES dish(dish_id),
	dish_name varchar(30) NOT NULL,
	status varchar(30) NOT NULL
);