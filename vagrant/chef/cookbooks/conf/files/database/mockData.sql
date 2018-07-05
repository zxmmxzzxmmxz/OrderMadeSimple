--restaurant
INSERT INTO restaurant VALUES (DEFAULT,'joojak');
INSERT INTO restaurant VALUES (DEFAULT,'szechuan restaurant');

--dishes
INSERT INTO dish (dish_name, dish_ver_id, description, price) 
	SELECT 'steamed fish',0,'',10.99;
INSERT INTO dish (dish_name, dish_ver_id, description, price) 
	SELECT 'pork',0,'just some pork',13.99;
INSERT INTO dish (dish_name, dish_ver_id, description, price) 
	SELECT 'chilly chicken',0,'super spicy',14.99;
INSERT INTO dish (dish_name, dish_ver_id, description, price) 
	SELECT 'rice',0,'just steamed rice',15.99;
INSERT INTO dish (dish_name, dish_ver_id, description, price) 
	SELECT 'noodle',0,'hand pulled',15.99;


-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'pork','',restaurant_name,13.99,'lunch special' from restaurant where restaurant_name = 'szechuan restaurant';
-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'chilly chicken','super spicy',restaurant_name,11.99,'all day' from restaurant where restaurant_name = 'szechuan restaurant';
-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'rice','just steamed rice',restaurant_name,10.99,'all day' from restaurant where restaurant_name = 'joojak';
-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'noodle','hand pulled',restaurant_name,5.99,'lunch special' from restaurant where restaurant_name = 'joojak';

--dish_ver
INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, dish_id,dish_name,description,'szechuan restaurant',price,'lunch special1' from dish where dish_id = 1;
INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, dish_id,dish_name,description,'szechuan restaurant',price,'lunch special2' from dish where dish_id = 2;
INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, dish_id,dish_name,description,'szechuan restaurant',price,'lunch special3' from dish where dish_id = 3;
INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, dish_id,dish_name,description,'szechuan restaurant',price,'lunch special4' from dish where dish_id = 4;
INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, dish_id,dish_name,description,'szechuan restaurant',price,'lunch special5' from dish where dish_id = 5;

UPDATE dish SET dish_ver_id = 1 WHERE dish_id = 1;
UPDATE dish SET dish_ver_id = 2 WHERE dish_id = 2;
UPDATE dish SET dish_ver_id = 3 WHERE dish_id = 3;
UPDATE dish SET dish_ver_id = 4 WHERE dish_id = 4;
UPDATE dish SET dish_ver_id = 5 WHERE dish_id = 5;

--orders
INSERT INTO order_order (time,restaurant_name,status) values(TIMESTAMP '2004-10-19 10:23:54+02','joojak','new');
INSERT INTO order_order (time,restaurant_name,status) values(TIMESTAMP '2018-11-12 10:23:54+02','joojak','new');

--order order_details
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,1,'steamed fish','new');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(2,2,'pork','done');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,4,'rice','done');
INSERT INTO order_details (order_id, dish_id, dish_name, status) values(1,3,'chilly chicken','in progress');

--user 
--default user password "password" SHA-1 hashed：5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8
INSERT INTO user_user (username,password,restaurant_id) values('joojak', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',1);
