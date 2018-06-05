--restaurant
INSERT INTO restaurant VALUES (DEFAULT,'joojak');
INSERT INTO restaurant VALUES (DEFAULT,'szechuan restaurant');

--dishes
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

--orders
INSERT INTO order_order (time,restaurant_id) values(TIMESTAMP '2004-10-19 10:23:54+02',1);
INSERT INTO order_order (time,restaurant_id) values(TIMESTAMP '2018-11-12 10:23:54+02',2);

--order details
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,1,'steamed fish','new');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(2,2,'pork','done');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,4,'rice','done');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,3,'chilly chicken','in progress');