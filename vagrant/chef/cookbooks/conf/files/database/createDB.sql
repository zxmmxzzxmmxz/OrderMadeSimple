CREATE TABLE dish (
	dish_id	SERIAL UNIQUE PRIMARY KEY,
	dish_name varchar(30) NOT NULL,
	description varchar(200),
	restaurant_id serial,
	price real NOT NULL,
	menu_flag varchar(30)
);

CREATE TABLE restaurant (
	restaurant_id SERIAL UNIQUE PRIMARY KEY,
	restaurant_name varchar(40) NOT NULL
);