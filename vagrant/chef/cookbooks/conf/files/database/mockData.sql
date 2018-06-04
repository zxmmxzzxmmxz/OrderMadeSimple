INSERT INTO restaurant VALUES (DEFAULT,'joojak');
INSERT INTO restaurant VALUES (DEFAULT,'szechuan restaurant');

INSERT INTO dish (dish_name, description, restaurant_id, price, menu_flag) 
	SELECT 'steamed fish','',restaurant_id,15.99,'lunch special' from restaurant where restaurant_name = 'joojak';
INSERT INTO dish (dish_name, description, restaurant_id, price, menu_flag) 
	SELECT 'pork','',restaurant_id,13.99,'lunch special' from restaurant where restaurant_name = 'szechuan restaurant';
INSERT INTO dish (dish_name, description, restaurant_id, price, menu_flag) 
	SELECT 'chilly chicken','super spicy',restaurant_id,11.99,'all day' from restaurant where restaurant_name = 'szechuan restaurant';
INSERT INTO dish (dish_name, description, restaurant_id, price, menu_flag) 
	SELECT 'rice','just steamed rice',restaurant_id,10.99,'all day' from restaurant where restaurant_name = 'joojak';
INSERT INTO dish (dish_name, description, restaurant_id, price, menu_flag) 
	SELECT 'noodle','hand pulled',restaurant_id,5.99,'lunch special' from restaurant where restaurant_name = 'joojak';