DROP SCHEMA `THETASPORTDB`;
CREATE SCHEMA `THETASPORTDB`;
DROP TABLE `CONTAINS`;
DROP TABLE `ORDER`;
DROP TABLE `USER`;
DROP TABLE `PRODUCT`;

CREATE TABLE `THETASPORTDB`.`USER` (
	`EMAIL` VARCHAR(60) NOT NULL,
	`NAME` VARCHAR(45) NOT NULL,
	`PASSWORD` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`EMAIL`));

CREATE TABLE `THETASPORTDB`.`PRODUCT` (
	`CODE` INT NOT NULL AUTO_INCREMENT,
	`NAME` VARCHAR(45) NOT NULL UNIQUE,
	`DESCRIPTION` TEXT NOT NULL,
	`STOCK` INT NOT NULL,
	`PRICE` FLOAT NOT NULL,
	`CATEGORY` VARCHAR(45) NOT NULL,
	`SUBCATEGORY` VARCHAR(45) NOT NULL,
	`IMAGE` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`CODE`));
  
CREATE TABLE `THETASPORTDB`.`ORDER` (
    `ORDERID` INT NOT NULL AUTO_INCREMENT,
    `ORDERDATE` DATE NOT NULL,
    `EMAIL` VARCHAR(60) NOT NULL,
    `GROSSPROFIT` FLOAT NOT NULL,
    PRIMARY KEY (`ORDERID`),
    INDEX `FK_ORDER` (`EMAIL` ASC) VISIBLE,
    CONSTRAINT `FK_ORDER_1` FOREIGN KEY (`EMAIL`) REFERENCES `THETASPORTDB`.`USER` (`EMAIL`)
        ON DELETE CASCADE ON UPDATE CASCADE);
        
CREATE TABLE `THETASPORTDB`.`CONTAINS` (
    `CODE` INT NOT NULL,
    `ORDERID_C` INT NOT NULL,
    `QUANTITY` INT NOT NULL,
    PRIMARY KEY (`CODE`,`ORDERID_C`),
    INDEX `FK_ORDER_CODE` (`CODE` ASC) VISIBLE,
        CONSTRAINT `FK_ORDER_CODE_1` FOREIGN KEY (`CODE`) REFERENCES `THETASPORTDB`.`PRODUCT` (`CODE`)
            ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX `FK_ORDER_2` (`ORDERID_C` ASC) VISIBLE,
        CONSTRAINT `FK_ORDER_22` FOREIGN KEY (`ORDERID_C`) REFERENCES `THETASPORTDB`.`ORDER` (`ORDERID`)
            ON DELETE CASCADE ON UPDATE CASCADE);

#--------------------------------------------------------------------
#------------------------- USERS AND ADMIN --------------------------
#--------------------------------------------------------------------
INSERT INTO `USER` (EMAIL, NAME, PASSWORD) VALUES ('admin','admin','admin');
INSERT INTO `USER` (EMAIL, NAME, PASSWORD) VALUES ('attilio@gmail.com','Attilio','password');
INSERT INTO `USER` (EMAIL, NAME, PASSWORD) VALUES ('mario@gmail.com','Mario','password');
INSERT INTO `USER` (EMAIL, NAME, PASSWORD) VALUES ('lorenzo@gmail.com','Lorenzo','password');
INSERT INTO `USER` (EMAIL, NAME, PASSWORD) VALUES ('carmine@gmail.com','Carmine','password');
INSERT INTO `USER` (EMAIL, NAME, PASSWORD) VALUES ('camilla@gmail.com','Camilla','password');
INSERT INTO `USER` (EMAIL, NAME, PASSWORD) VALUES ('vincenzo@gmail.com','Vincenzo','password');

#--------------------------------------------------------------------
#----------------- PRODUCT CATEGORY FOOTBALL SHOES ------------------
#--------------------------------------------------------------------
INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 1,'Adidas Predator Limited Shoes','Make a statement on the field with these unique
            and sleek limited edition football shoes from Adidas. Designed for the ultimate game-changer, they\'ll help
            you stand out from the crowd and dominate the competition.',100,99.99,'Football','Shoes','adidas-predator-limited-shoes.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 2,'Adidas F50 Shoes','These durable and stylish football shoes are designed for maximum
            performance on the pitch. With the perfect balance of comfort, speed, and agility, they\'ll help
            you take your game to the next level.',80,69.99,'Football','Shoes','adidas-f50-shoes.png');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 3,'Mizuno Morelia Shoes','With premium construction and materials, these football shoes
            from Mizuno offer optimal performance on the field. They\'re built to provide the ultimate in comfort,
            support, and traction, so you can play your best game every time.',30,34.99,'Football','Shoes','mizuno-morelia-shoes.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 4,'Nike Fluo Limited Shoes','These fluorescent limited edition football shoes from Nike
            offer bold style and excellent grip, so you can dominate on the pitch. With eye-catching design
            and superior performance features, they\'re the ultimate choice for serious players who want to stand
            out from the crowd.',160,110.99,'Football','Shoes','nike-fluo-limited-shoes.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 5,'Nike Mercurial Limited Shoes','Designed for the elite athlete, these limited edition
            football shoes from Mercurial combine speed, precision, and agility in one shoe. With advanced
            technology and superior materials, they\'re the ultimate choice for serious players.',66,84.99,'Football',
            'Shoes','nike-mercurial-limited-shoes.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 6,'Nike Mercurial Shoes','Sleek design and superior traction make these football
            shoes from Nike the perfect choice for modern players. With advanced technology and stylish
            design features, they\'ll help you take your game to the next level.',80,88.99,'Football','Shoes','nike-mercurial-shoes.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 7,'Nike Tiempo Shoes','Dynamic design and superior traction make these top-of-the-line
            football shoes from Nike the perfect choice for serious players. With advanced technology and
            innovative design features, they\'ll help you achieve peak performance on the pitch.',122,124.99,
            'Football','Shoes','nike-tiempo-shoes.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 8,'Puma Future Shoes','Lightweight, comfortable, and supportive, these high-quality
            football shoes from Puma are the perfect choice for serious players. They\'ll give you the
            edge you need to perform at your best and stay at the top of your game.',48,94.99,'Football','Shoes','puma-future-shoes.jpeg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 9,'Umbro Eternal Limited Shoes','These limited edition football shoes from Umbro offer
            premium craftsmanship and materials, making them the ultimate choice for the elite athlete.
            With advanced features and innovative design, they\'ll help you elevate your game and perform at your best.',
            100,34.99,'Football','Shoes','umbro-eternal-limited-shoes.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 10,'Umbro Velocita Shoes','Classic style meets modern performance in these premium
            football shoes from Umbro. Designed for the discerning player, they offer the best of both worlds,
            combining timeless style with advanced technology and superior materials.',80,44.99,'Football','Shoes','umbro-velocita-shoes.jpg');

#--------------------------------------------------------------------
#----------------- PRODUCT CATEGORY FOOTBALL TSHIRT -----------------
#--------------------------------------------------------------------
INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 11,'Haaland T-Shirt','The Haaland jersey is the perfect way for fans of the Norwegian soccer
            sensation to show their support on and off the field. Made from high-quality materials,
            this jersey features a sleek design in Manchester City\'s iconic colors.',100,124.99,'Football','Tshirt','haaland-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 12,'Ibrahimovic T-Shirt','Show your love for one of the greatest footballers of all time
            with this stylish t-shirt. Featuring Zlatan Ibrahimovic\'s name and number, it\'s the perfect
            choice for fans of the Swedish superstar',70,89.99,'Football','Tshirt','ibrahimovic-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 13,'Kvaratskhelia T-Shirt','Celebrate the talent of Georgian footballer Khvicha Kvaratskhelia
            with this trendy t-shirt. Perfect for fans of the up-and-coming star, it\'s a must\-have addition
            to any football fan\'s wardrobe.',90,79.99,'Football','Tshirt','kvaratskhelia-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 14,'Lautaro T-Shirt','Support the Argentine striker Lautaro Martinez with this cool football
            t-shirt. With his name and number on the back, it\'s the perfect way to show your allegiance to the
            Inter Milan forward.',50,59.99,'Football','Tshirt','lautaro-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 15,'Maradona T-Shirt','Pay homage to the legendary Argentine footballer Diego Maradona with this
            classic t-shirt. Featuring his iconic number 10, it\'s the perfect choice for fans of the football icon.',
            105,149.99,'Football','Tshirt','maradona-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 16,'Mbappe T-Shirt','Show your support for the French superstar Kylian Mbappe with this trendy
            football t-shirt. Featuring his name and number, it\'s the perfect way to show your allegiance to one
            of the game\'s most exciting young players.',150,39.99,'Football','Tshirt','mbappe-psg-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 17,'Messi T-Shirt','Celebrate the incredible talent of Lionel Messi with this stylish football t-shirt.
            Featuring his name and number, it\'s the perfect way to show your support for the Argentine superstar.',35,79.99,
            'Football','Tshirt','messi-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 18,'Osimhen T-Shirt','Cheer on the Nigerian forward Victor Oshimen with this cool football t-shirt.
            Featuring his name and number, it\'s the perfect choice for fans of the Napoli striker.',70,54.99,'Football',
            'Tshirt','osimhen-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 19,'Paris San Germain T-Shirt','Show your support for the Paris Saint Germain football club with this
            stylish t-shirt. Featuring the team\'s crest, it\'s the perfect way to show your allegiance to one of the
            top teams in Europe.',200,44.99,'Football','Tshirt','psg-tshirt.jpg' );

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 20,'Ronaldo T-Shirt','Support the Portuguese national team with this cool football t-shirt. Featuring
            Cristiano Ronaldo\'s name and number, it\'s the perfect way to show your allegiance to one of the greatest
            footballers of all time.',80,49.99,'Football','Tshirt','ronaldo-portogallo-tshirt.jpg' );

#--------------------------------------------------------------------
#----------------- PRODUCT CATEGORY TENNIS SHOES --------------------
#--------------------------------------------------------------------
INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 21,'Adidas Courtflash Speed Shoes',' These shoes from Adidas are designed with runners in mind,
            but they also work great for tennis players who demand a shoe that offers both comfort and support.
            With their superior cushioning and responsive design, they\'re a great choice for players of all levels.',
            50,153.99,'Tennis','Shoes','adidas-courtflash-speed.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 22,'Adidas Court Jam Shoes','These minimalist shoes from Adidas are designed to give players a
            barefoot-like feel on the court. With their lightweight design and flexible construction, they\'re a
            great choice for players who want to feel quick and agile on the court.',72,133.99,'Tennis','Shoes','adidas-courtflash-speed.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 23,'Asics Gel 7 Shoes','Make a statement on the court with these limited edition tennis shoes from Asics.
            With their unique design and superior performance, they\'re a must-have for any player who wants to stand
            out from the crowd.',92,174.99,'Tennis','Shoes','asics-gel-7.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 24,'Asics Limited Shoes','These shoes are designed for runners, but their lightweight design and
            supportive construction make them a great choice for tennis players who demand both comfort and performance on the court.
            With their advanced technology and stylish design, they\'re a great choice for players of all levels.',
            134,119.99,'Tennis','Shoes','asics-limited.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 25,'Asics Solution Swift Shoes','Designed with runners in mind, these Asics tennis shoes offer
            superior comfort and support for players who demand the best. With their lightweight design and advanced
            technology, they\'re a great choice for players who need to stay quick and nimble on the court.',138,184.99,
            'Tennis','Shoes','Asics-Solution-Swift.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 26,'Joma Ace Pro Clay Shoes','These limited edition running shoes are the perfect choice for
            players who want to make a statement on the court. With their unique design and superior performance,
            they\'re a must-have for any serious tennis player who wants to stand out from the crowd',52,136.99,'Tennis',
            'Shoes','joma-ace-pro-clay.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 27,'Lotto Limited Shoes','These shoes from Lotto are the perfect choice for players who want a shoe
            that\'s both comfortable and stylish. With their superior cushioning and support, they\'ll help you
            stay on top of your game all match long.',49,98.99,'Tennis','Shoes','lotto-limited.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 28,'Nike Court Lite 2 Shoes','The iconic design and superior performance of these Nike shoes make
            them a great choice for both on-court play and casual wear. With their durable construction and classic
            style, they\'re a must-have for any tennis player or sneakerhead.',76,182.99,'Tennis','Shoes','nike-court-lite-2.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 29,'Nike Hardcourt Vapor Pro Shoes','These shoes are more than just a fashion statement – they
            offer superior performance and support for tennis players of all levels. With their iconic design
            and unbeatable comfort, they\'re a must-have for any player who wants to take their game to the next level.',
            63,113.99,'Tennis','Shoes','nike-hardcourt-vapor-pro.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 30,'Nike Vapor X Shoes','These limited edition tennis shoes from Nike are a bold and stylish choice
            for players who want to stand out on the court. With their unique design and superior performance, they\'re
            a must-have for any serious tennis player.',153,112.99,'Tennis','Shoes','nike-vapor-x.jpg');

#--------------------------------------------------------------------
#----------------- PRODUCT CATEGORY TENNIS TSHIRT -------------------
#--------------------------------------------------------------------
INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 31,'Adidas London Tshirt Limited','This classic Adidas tennis t-shirt is a must-have for any player who
            wants to look and feel their best on the court. With its comfortable and breathable fabric, it\'s perfect
            for even the toughest matches.',220,34.99,'Tennis','Tshirt','adidas-london-tshirt-limited.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 32,'Asics Polo Tshirt','Designed with the needs of tennis players in mind, this Asics t-shirt offers
            superior comfort and performance on the court. With its advanced technology and stylish design, it\'s a great
            choice for players of all levels.',168,24.99,'Tennis','Tshirt','asics-polo-tshirt.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 33,'Diadora One Tshirt Limited','Make a statement on the court with this limited edition Diadora tennis t-shirt.
            With its unique design and superior performance, it\'s a must-have for any player who wants to stand out from the crowd.',
            200,14.99,'Tennis','Tshirt','diadora-one-tshirt-limited.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 34,'Limited 40 Tshirt','This limited edition tennis t-shirt is the perfect choice for players who want to make
            a statement on the court. With its bold design and superior performance, it\'s a must-have for any serious tennis player.',
            100,44.99,'Tennis','Tshirt','limited-40-tshirt.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 35,'Lotto Points Tshirt','This Lotto tennis t-shirt offers both style and comfort for players who demand the best.
            With its superior fabric and sleek design, it\'s a great choice for players who want to look their best on the court.',
            160,34.99,'Tennis','Tshirt','lotto-points-tshirt.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 36,'Lotto Black Tshirt Limited','This Lotto tennis t-shirt offers both style and comfort for players who demand the best.
            With its superior fabric and sleek design, it\'s a great choice for players who want to look their best on the court.',
            188,24.99,'Tennis','Tshirt','lotto-black-tshirt-limited.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 37,'Head Topspin Tshirt','Designed with the needs of serious tennis players in mind, this Head topspin t-shirt
            offers superior comfort and support on the court. With its advanced technology and stylish design, it\'s a great
            choice for players who demand the best.',220,64.99,'Tennis','Tshirt','head-topspin-tshirt.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 38,'Nike Tshirt','This classic Nike tennis t-shirt is a must-have for any player who wants to look and feel
            their best on the court. With its comfortable and breathable fabric, it\'s perfect for even the toughest matches.',
            220,24.99,'Tennis','Tshirt','nike-tshirt.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 39,'Nike Toro Tshirt Limited Orange','Make a statement on the court with this limited edition Nike tennis t-shirt.
            With its bold design and superior performance, it\'s a must-have for any player who wants to stand out from the crowd.',
            100,34.99,'Tennis','Tshirt','nike-toro-tshirt-limited-orange.jpg');

INSERT INTO PRODUCT (CODE, NAME, DESCRIPTION, STOCK, PRICE, CATEGORY, SUBCATEGORY, IMAGE)
    VALUES ( 40,'Wilson Tshirt','This Wilson tennis t-shirt offers both style and comfort for players who demand the best.
            With its superior fabric and sleek design, it\'s a great choice for players who want to look their best on the court.',
            88,14.99,'Tennis','Tshirt','wilson-tshirt.jpg');

#--------------------------------------------------------------------
#----------------------------- ORDER --------------------------------
#--------------------------------------------------------------------
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (1,'2022-01-07','attilio@gmail.com',289.97);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (2,'2022-02-16','attilio@gmail.com',469.95);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (3,'2022-03-26','attilio@gmail.com',189.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (4,'2022-04-24','attilio@gmail.com',364.97);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (5,'2023-04-06','attilio@gmail.com',264.95);

INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (6,'2022-06-28','mario@gmail.com',909.9);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (7,'2022-07-02','mario@gmail.com',409.96);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (8,'2022-08-24','mario@gmail.com',559.95);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (9,'2022-09-02','mario@gmail.com',329.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (10,'2023-04-04','mario@gmail.com',329.94);

INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (11,'2022-11-01','lorenzo@gmail.com',219.97);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (12,'2022-12-18','lorenzo@gmail.com',689.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (13,'2022-02-01','lorenzo@gmail.com',239.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (14,'2022-04-18','lorenzo@gmail.com',619.92);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (15,'2023-04-01','lorenzo@gmail.com',269.94);

INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (16,'2022-10-01','carmine@gmail.com',269.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (17,'2022-11-12','carmine@gmail.com',439.95);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (18,'2022-12-14','carmine@gmail.com',279.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (19,'2023-01-16','carmine@gmail.com',269.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (20,'2023-04-06','carmine@gmail.com',179.95);

INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (21,'2022-01-20','camilla@gmail.com',269.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (22,'2022-02-22','camilla@gmail.com',427.96);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (23,'2022-03-24','camilla@gmail.com',539.95);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (24,'2023-04-01','camilla@gmail.com',269.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (25,'2023-04-02','camilla@gmail.com',269.94);

INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (26,'2022-04-18','vincenzo@gmail.com',214.97);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (27,'2022-05-28','vincenzo@gmail.com',679.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (28,'2022-06-26','vincenzo@gmail.com',269.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (29,'2023-02-24','vincenzo@gmail.com',269.94);
INSERT INTO `ORDER` (ORDERID, ORDERDATE, EMAIL, GROSSPROFIT) VALUES (30,'2023-04-02','vincenzo@gmail.com',269.94);

#--------------------------------------------------------------------
#--------------------------- CONTAINS -------------------------------
#--------------------------------------------------------------------

-- ATTILIO
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (1,1,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (6,1,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (8,2,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (12,2,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (10,3,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (36,3,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (24,4,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (11,4,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,5,1);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (9,5,4);

-- MARIO
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (16,6,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (11,6,6);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (12,7,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (13,7,2);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,8,1);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (7,8,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (8,9,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (9,9,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (10,10,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,10,4);

-- LORENZO
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (13,11,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,11,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (7,12,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (8,12,2);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (9,13,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (20,13,2);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (24,14,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (39,14,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (40,15,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,15,4);

-- CARMINE
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (1,16,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (6,16,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (8,17,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,17,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (12,18,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (36,18,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (24,19,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (11,19,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (16,20,1);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (9,20,4);

-- CAMILLA
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (16,21,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (11,21,6);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (22,22,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (13,22,2);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (16,23,1);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (7,23,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (8,24,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (9,24,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (10,25,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,25,4);

-- VINCENZO
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (13,26,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (18,26,1);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (7,27,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (12,27,2);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (9,28,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (20,28,2);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (24,29,4);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (39,29,4);

INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (40,30,2);
INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) VALUES (14,30,4);

-- QUERY
-- NUMERO TOTALE DI ORDINI DI UN UTENTE
SELECT P.NAME,O.ORDERID,P.CODE FROM PRODUCT P JOIN CONTAINS C on P.CODE = C.CODE
    JOIN `ORDER` O on O.ORDERID = C.ORDERID_C
        JOIN USER U on O.EMAIL = U.EMAIL
            WHERE O.EMAIL = 'attilio@gmail.com';

-- PRODOTTI ACQUISTATI DA UN UTENTE SPECIFICO IN UN SINGOLO ORDINE
SELECT P.NAME,O.ORDERID,P.CODE FROM PRODUCT P JOIN CONTAINS C on P.CODE = C.CODE
    JOIN `ORDER` O on O.ORDERID = C.ORDERID_C
        JOIN USER U on O.EMAIL = U.EMAIL
            WHERE O.EMAIL = 'attilio@gmail.com' AND O.ORDERID = 1;

-- ORDERID EFFETTUATI DA UN SINGOLO UTENTE
SELECT ORDERID FROM `ORDER`
    WHERE EMAIL = 'attilio@gmail.com';
    
-- IN BASE AD UNA CATEGORIA VISUALIZZARE IL NUMERO DI PRODOTTI VENDUTI
SELECT P.CATEGORY,SUM(C.QUANTITY) FROM `ORDER` JOIN `CONTAINS` C on `ORDER`.ORDERID = C.ORDERID_C
    JOIN PRODUCT P on P.CODE = C.CODE
        WHERE P.CATEGORY = 'Football' AND P.SUBCATEGORY = 'Shoes';

-- TUTTI I PRODOTTI CON LE RISPETTIVE QUANTITÀ DI UNO SPECIFICO USER
SELECT P.NAME,C.QUANTITY FROM PRODUCT P JOIN CONTAINS C on P.CODE = C.CODE
    JOIN `ORDER` O on O.ORDERID = C.ORDERID_C
        JOIN USER U on O.EMAIL = U.EMAIL
            WHERE O.EMAIL = 'attilio@gmail.com';
         
-- TOTALE ORDINE EFFETTUATO IN UN DATO MESE ED IN UN DATO ANNO   
SELECT SUM(GROSSPROFIT) FROM `ORDER` 
	WHERE MONTH(ORDERDATE) = 3 AND YEAR(ORDERDATE) = 2022;

-- TOTALE ORDINI CON INFORMAZIONI UTENTE E SOMMA TOTALE DELLA SPESA
SELECT O.ORDERID,U.NAME,O.EMAIL,O.ORDERDATE,O.GROSSPROFIT AS TOTAL,SUM(QUANTITY) AS QUANTITY
    FROM `ORDER` O JOIN CONTAINS C on O.ORDERID = C.ORDERID_C
            JOIN PRODUCT P on P.CODE = C.CODE
               JOIN USER U on U.EMAIL = O.EMAIL
                    GROUP BY ORDERID;
