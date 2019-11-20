DROP DATABASE IF EXISTS squareHelp;

CREATE DATABASE squareHelp;

USE squareHelp;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    state VARCHAR(2),
    city VARCHAR(255),
    dob INT(5),
    phone_number VARCHAR(15),
    date_created INT,
    last_login VARCHAR(255),
    gender VARCHAR(255)
);

DROP TABLE IF EXISTS smoking_info;
CREATE TABLE smoking_info (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    day_quit_smoking INT(5),
    day_relapse INT(5),
    total_days_smoke_free BIGINT(20),
    points INT(10),
    cost_of_cigs_saved INT(10)
);

DROP TABLE IF EXISTS notifications;
CREATE TABLE notifications (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    recipient_user_id BIGINT(20),
    originator_user_id BIGINT(20),
    notification VARCHAR(255),
    is_viewed BOOLEAN
);

DROP TABLE IF EXISTS verifications_req;
CREATE TABLE verifications_req (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    originator_user_id INT,
    approver_user_id BIGINT(20),
    day_created BIGINT(20),
    days_smoke_free INT(10),
    is_approved BOOLEAN
);

DROP TABLE IF EXISTS messages;
CREATE TABLE messages (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    author_user_id BIGINT(20),
    recipient_user_id BIGINT(20),
    message TEXT
);

DROP TABLE IF EXISTS buddy_sys;
CREATE TABLE buddy_sys (
  originator_user_id BIGINT(20),
  recipient_user_id BIGINT(20)
);