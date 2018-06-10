--restaurant
INSERT INTO restaurant VALUES (DEFAULT,'joojak');
INSERT INTO restaurant VALUES (DEFAULT,'szechuan restaurant');

--dishes
INSERT INTO dish (dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 'steamed fish','',restaurant_name,15.99,'lunch special' from restaurant where restaurant_name = 'joojak';
INSERT INTO dish (dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 'pork','',restaurant_name,13.99,'lunch special' from restaurant where restaurant_name = 'szechuan restaurant';
INSERT INTO dish (dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 'chilly chicken','super spicy',restaurant_name,11.99,'all day' from restaurant where restaurant_name = 'szechuan restaurant';
INSERT INTO dish (dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 'rice','just steamed rice',restaurant_name,10.99,'all day' from restaurant where restaurant_name = 'joojak';
INSERT INTO dish (dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 'noodle','hand pulled',restaurant_name,5.99,'lunch special' from restaurant where restaurant_name = 'joojak';

--orders
INSERT INTO order_order (time,restaurant_id,status) values(TIMESTAMP '2004-10-19 10:23:54+02',1,'new');
INSERT INTO order_order (time,restaurant_id,status) values(TIMESTAMP '2018-11-12 10:23:54+02',2,'new');

--order order_details
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,1,'steamed fish','new');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(2,2,'pork','done');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,4,'rice','done');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,3,'chilly chicken','in progress');

--user 
--default user password "password" SHA-1 hashed：5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8
INSERT INTO user_user (username,password,restaurant_id) values('joojak', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',1);
