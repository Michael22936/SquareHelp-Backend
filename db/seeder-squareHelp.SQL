DROP DATABASE IF EXISTS squareHelp;

CREATE DATABASE squareHelp;

USE squareHelp;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    location VARCHAR(255),
    dob INT,
    phone VARCHAR(15),
    date_created INT,
    last_login INT,
    gender VARCHAR(6)
);

DROP TABLE IF EXISTS smoking_info;
CREATE TABLE smoking_info (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    day_quitSmoking INT,
    day_relapse VARCHAR(255),
    days_smokeFree INT,
    points BIGINT,
    costOfCigs INT,
    costOfCigsSaved BIGINT
);

DROP TABLE IF EXISTS notifications;
CREATE TABLE notifications (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    recipient_user_id INT,
    originator_user_id INT,
    content VARCHAR(255),
    isViewed BOOLEAN
);

DROP TABLE IF EXISTS verifications_req;
CREATE TABLE verifications_req (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    originator_user_id INT,
    recipient_user_id INT,
    day_created INT,
    daysSmokeFree INT,
    isApproved BOOLEAN
);
    
DROP TABLE IF EXISTS messages;
CREATE TABLE messages (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    originator_user_id INT,
    recipient_user_id INT,
    date_created INT,
    message TEXT
);