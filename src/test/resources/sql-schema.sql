DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);




drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(20) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
SELECT * FROM customers;

CREATE TABLE IF NOT EXISTS `ims`.`items` (
`item_id` INT(20) NOT NULL AUTO_INCREMENT,
`item_name` VARCHAR(50) NOT NULL,
`price` DOUBLE NOT NULL,
PRIMARY KEY (`item_id`)
);

INSERT INTO items (item_name, price) VALUES ("Air Jordan 1", 99);
INSERT INTO items (item_name, price) VALUES ("Air Max 95", 119);
SELECT * FROM items WHERE item_id = 1;
SELECT * FROM items ORDER BY item_id DESC LIMIT 1;
UPDATE items SET item_name = "Air Jordan 3", price = 129 WHERE item_id = 1;
DELETE FROM items WHERE item_id = 1;
SELECT * FROM items;

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
`order_id` INT(20) NOT NULL AUTO_INCREMENT,
`fk_id` INT(20) NOT NULL,
`fk_item_id` INT(20) NOT NULL,
PRIMARY KEY (`order_id`),
FOREIGN KEY (`fk_id`) REFERENCES customers (`id`),
FOREIGN KEY (`fk_item_id`) REFERENCES items (`item_id`)
);

SELECT * FROM orders;
SELECT * FROM items ORDER BY item_id DESC LIMIT 1;
SELECT * FROM orders WHERE order_id = 1;
INSERT INTO orders (order_id, fk_id, fk_item_id) VALUES (1, 1, 1);
INSERT INTO orders (order_id, fk_id, fk_item_id) VALUES (2, 2, 2);
UPDATE orders SET fk_id = 3, fk_item_id = 4 WHERE order_id = 2;
DELETE FROM orders WHERE order_id = 1;

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
`order_items_id` INT(20) NOT NULL AUTO_INCREMENT,
`fk_item_id` INT(20) NOT NULL,
`fk_order_id` INT(20) NOT NULL,
`quantity` INT(20) NOT NULL,
`total_cost` DOUBLE NOT NULL,
PRIMARY KEY (`order_items_id`),
FOREIGN KEY (`fk_item_id`) REFERENCES items (`item_id`),
FOREIGN KEY (`fk_order_id`) REFERENCES orders (`order_id`)
);

INSERT INTO order_items (fk_item_id, fk_order_id, quantity, total_cost) VALUES (1, 2, 1, 15);
INSERT INTO order_items (fk_item_id, fk_order_id, quantity, total_cost) VALUES (2, 2, 1, 20);
INSERT INTO order_items (fk_item_id, fk_order_id, quantity, total_cost) VALUES (3, 2, 1, 13);
INSERT INTO order_items (fk_item_id, fk_order_id, quantity, total_cost) VALUES (4, 2, 1, 20);

Delete FROM order_items WHERE fk_item_id = 1 and fk_order_id = 2;

SELECT SUM(total_cost) FROM order_items WHERE fk_order_id = 2;

SELECT * FROM order_items;




