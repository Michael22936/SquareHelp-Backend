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
    dob DATE,
    phone_number VARCHAR(15),
    date_created DATE,
    last_login VARCHAR(255),
    gender VARCHAR(255),
    smoker_info_id INT UNSIGNED,
    profile_pic VARCHAR(255)
);

DROP TABLE IF EXISTS smoking_info;
CREATE TABLE smoking_info (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    day_quit_smoking DATE,
    day_relapse DATE,
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
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    originator_user_id INT,
    approver_name VARCHAR(255),
    day_created DATE,
    days_smoke_free INT(10),
    is_approved BOOLEAN
);

DROP TABLE IF EXISTS messages;
CREATE TABLE messages (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT(20) DEFAULT NULL,
    author_user_id BIGINT(20),
    recipient_user_id BIGINT(20),
    message TEXT,
    recipient_username VARCHAR(255),
    last_update Date
);

DROP TABLE IF EXISTS buddy_sys;
CREATE TABLE buddy_sys (
  originator_user_id BIGINT(20),
  recipient_user_id BIGINT(20)
);