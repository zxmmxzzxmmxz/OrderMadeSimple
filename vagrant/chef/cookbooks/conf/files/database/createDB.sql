CREATE TABLE restaurant (
	restaurant_id SERIAL UNIQUE PRIMARY KEY,
	restaurant_name varchar(40) NOT NULL UNIQUE
);

CREATE TABLE dish (
	dish_id	SERIAL UNIQUE PRIMARY KEY,
	dish_name varchar(30) NOT NULL,
	description varchar(200) ,
	restaurant_name varchar(40) NOT NULL REFERENCES restaurant(restaurant_name),
	price real NOT NULL,
	menu_flag varchar(30)
);

CREATE TABLE order_order(
	order_id SERIAL UNIQUE PRIMARY KEY,
	restaurant_id SERIAL REFERENCES restaurant(restaurant_id),
	time timestamp,
	status varchar(30) NOT NULL
);

CREATE TABLE order_details(
	order_details_id SERIAL UNIQUE PRIMARY KEY,
	order_id SERIAL REFERENCES order_order(order_id),
	dish_id SERIAL REFERENCES dish(dish_id),
	dish_name varchar(30) NOT NULL,
	status varchar(30) NOT NULL
);

CREATE TABLE user_user(
	user_id SERIAL UNIQUE PRIMARY KEY,
	username varchar(30) NOT NULL,
	password varchar(150) NOT NULL,
	restaurant_id SERIAL NOT NULL
);

CREATE TABLE token(
	token_id SERIAL UNIQUE PRIMARY KEY,
	token varchar(150),
	username varchar(30) NOT NULL
);