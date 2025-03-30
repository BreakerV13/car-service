CREATE DATABASE car_shop_db;

USE car_shop_db;

CREATE TABLE car (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(100),
                     brand VARCHAR(100),
                     model VARCHAR(100),
                     price DOUBLE,
                     year INT,
                     description VARCHAR(1000),
                     picture BLOB,
                     sold TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(100) UNIQUE,
                      password VARCHAR(255),
                      role VARCHAR(255)
);
