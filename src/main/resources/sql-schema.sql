DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;


CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
`item_id` INT(20) NOT NULL AUTO_INCREMENT,
`item_name` VARCHAR(50) NOT NULL,
`price` DOUBLE NOT NULL,
PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
`order_id` INT(20) NOT NULL AUTO_INCREMENT,
`order_date` DATE NOT NULL,
`fk_id` INT(20) NOT NULL,
`fk_item_id` INT(20) NOT NULL,
PRIMARY KEY (`order_id`),
FOREIGN KEY (`fk_id`) REFERENCES customers (`id`),
FOREIGN KEY (`fk_item_id`) REFERENCES items (`item_id`)
);

CREATE TABLE IF NOT EXISTS `order_items` (
`order_items_id` INT(20) NOT NULL AUTO_INCREMENT,
`fk_item_id` INT(20) NOT NULL,
`fk_order_id` INT(20) NOT NULL,
`order_date` DATE NOT NULL,
`quantity` INT(20) NOT NULL,
`total_cost` DOUBLE NOT NULL,
PRIMARY KEY (`order_items_id`),
FOREIGN KEY (`fk_item_id`) REFERENCES items (`item_id`),
FOREIGN KEY (`fk_order_id`) REFERENCES orders (`order_id`)
);






