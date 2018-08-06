--restaurant
INSERT INTO restaurant VALUES (DEFAULT,'joojak', 'sd3j4252k');

--dishes
INSERT INTO dish (dish_ver_id) 
	SELECT 0;
INSERT INTO dish (dish_ver_id) 
	SELECT 0;
INSERT INTO dish (dish_ver_id) 
	SELECT 0;
INSERT INTO dish (dish_ver_id) 
	SELECT 0;
INSERT INTO dish (dish_ver_id) 
	SELECT 0;


-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'pork','',restaurant_name,13.99,'lunch special' from restaurant where restaurant_name = 'szechuan restaurant';
-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'chilly chicken','super spicy',restaurant_name,11.99,'all day' from restaurant where restaurant_name = 'szechuan restaurant';
-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'rice','just steamed rice',restaurant_name,10.99,'all day' from restaurant where restaurant_name = 'joojak';
-- INSERT INTO dish (dish_name, dish_ver_id, description, restaurant_name, price, menu_flag) 
-- 	SELECT 'noodle','hand pulled',restaurant_name,5.99,'lunch special' from restaurant where restaurant_name = 'joojak';

--user 
--default user password 'password' SHA-1 hashed：5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8
INSERT INTO user_user (username,password,restaurant_id) values('joojak', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',1);

--dish_ver
INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, 1,'pork burger','Authentic Xi An flavoured pork burger','joojak',5.5,'all-day';

INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, 2,'lamb burger','cumin lamb burger with green pepper in it, a bit spicy','joojak',5.95,'all-day';

INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, 3,'lamb soup with pita bread','special lamb soup with shredded pita bread inside, with sides of sweet&sour garlic and pepper','joojak',12.99,'all-day';

INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, 4,'cold noodle','home-made cold chewy noodle, with gluten inside, best for summer','joojak',8.5,'all-day';

INSERT INTO dish_ver (version_number, dish_id, dish_name, description, restaurant_name, price, menu_flag) 
	SELECT 1, 5,'cold noodle with sesame paste','same cold noodle, but with sasame paste','joojak',8.95,'all-day';

UPDATE dish SET dish_ver_id = 1 WHERE dish_id = 1;
UPDATE dish SET dish_ver_id = 2 WHERE dish_id = 2;
UPDATE dish SET dish_ver_id = 3 WHERE dish_id = 3;
UPDATE dish SET dish_ver_id = 4 WHERE dish_id = 4;
UPDATE dish SET dish_ver_id = 5 WHERE dish_id = 5;

--orders
INSERT INTO order_order (time,restaurant_name,status,table_number) values(TIMESTAMP '2004-10-19 10:23:54+02','joojak','new','3');
INSERT INTO order_order (time,restaurant_name,status,table_number) values(TIMESTAMP '2018-11-12 10:23:54+02','joojak','new','1');

--order order_details
INSERT INTO order_details (order_id, dish_ver_id, order_detail_status, special_note) values(1,4,'new', 'no spicy');
INSERT INTO order_details (order_id, dish_ver_id, order_detail_status) values(2,2,'new');
INSERT INTO order_details (order_id, dish_ver_id, order_detail_status) values(1,4,'done');
INSERT INTO order_details (order_id, dish_ver_id, order_detail_status) values(1,3,'in progress');

--reserve mock data
INSERT INTO restaurant(restaurant_name, order_code) values('Uli''s Restaurant', 's33j32gf');

INSERT INTO restaurant_info(restaurant_id, phone, address) values(
	(SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), 
	'(604) 538-9373', 
	'15023 Marine Dr, White Rock, BC V4B 1C3'
);

-- INSERT INTO restaurant_hour(restaurant_id, open_day, open_hr, close_hr) values(
-- 	(select restaurant_id from restaurant where restaurant_name ='Uli''s Restaurant'), 
-- 	'Sunday', 
-- 	11,
-- 	21
-- );

INSERT INTO restaurant_info(restaurant_id, phone, address) values(
	(SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), 
	'(604) 538-0000', 
	'1245 Interesting Dr, Burnaby, BC V4C 1B3'
);

--mock available hours from Uli's Restaurant
INSERT INTO restaurant_hour(open_day, restaurant_id, open_hr, close_hr)
values
('Monday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), '11:30', '21:00'),
('Tuesday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), 'close', 'close'),
('Wednesday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), '11:30', '21:00'),
('Thursday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), '11:30', '21:00'),
('Friday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), '11:30', '23:00'),
('Saturday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), '10:30', '23:00'),
('Sunday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='Uli''s Restaurant'), '10:30', '22:00');

INSERT INTO restaurant_hour(open_day, restaurant_id, open_hr, close_hr)
values
('Monday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), 'clsoe', 'close'),
('Tuesday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), '17:00', '22:00'),
('Wednesday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), '12:00', '22:00'),
('Thursday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), '12:00', '22:00'),
('Friday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), '12:00', '22:00'),
('Saturday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), '12:00', '22:00'),
('Sunday', (SELECT restaurant_id FROM restaurant WHERE restaurant_name ='joojak'), '12:00', '22:00');
	
--mock existing tables from Uli's Restaurant
INSERT INTO dining_table(seats) VALUES
(4),
(4),
(4),
(4),
(6),
(6),
(6),
(12),
(12),
(15);	