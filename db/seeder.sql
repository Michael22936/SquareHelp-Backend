
USE squareHelp;
# DROP TABLE IF EXISTS users;

insert into messages (originator_user_id, recipient_user_id, date_created, message) values (1, 1, '30', 'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (2, 2, '0440', 'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (3, 3, '10431', 'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (4, 4, '2743', 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (5, 5, '76503', 'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (6, 6, '755', 'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (7, 7, '646', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (8, 8, '21500', 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (9, 9, '390', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.');
insert into messages (originator_user_id, recipient_user_id, date_created, message) values (10, 10, '8', 'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.');


insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (1, 1, 'In est risus, auctor sed, tristique in, tempus sit amet, sem.', false);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (2, 2, 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', false);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (3, 3, 'Praesent id massa id nisl venenatis lacinia.', true);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (4, 4, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.', false);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (5, 5, 'Nulla suscipit ligula in lacus.', false);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (6, 6, 'Cras non velit nec nisi vulputate nonummy.', true);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (7, 7, 'Ut at dolor quis odio consequat varius.', true);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (8, 8, 'Praesent blandit lacinia erat.', true);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (9, 9, 'Donec ut dolor.', false);
insert into notifications (recipient_user_id, originator_user_id, content, isViewed) values (10, 10, 'Vestibulum rutrum rutrum neque.', true);


insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (1, '24978', '824 Morningstar Terrace', '89', 1, '5.51', '4.65');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (2, '9131', '00 Kipling Crossing', '8', 2, '5.31', '5.73');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (3, '56947', '5683 Acker Park', '4', 3, '6.74', '6.28');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (4, '194', '54 Vidon Avenue', '52', 4, '4.46', '6.18');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (5, '35', '0 Anhalt Pass', '012', 5, '4.84', '4.77');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (6, '4', '40891 Pawling Crossing', '22225', 6, '4.14', '6.23');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (7, '11170', '2 Reinke Parkway', '0', 7, '4.56', '5.94');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (8, '5', '0121 Packers Pass', '21755', 8, '6.12', '6.44');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (9, '160', '91161 Northland Place', '50335', 9, '5.84', '6.38');
insert into smoking_info (user_id, day_quitSmoking, day_relapse, days_smokeFree, points, costOfCigs, costOfCigsSaved) values (10, '63', '94 Rowland Point', '63106', 10, '6.98', '5.86');


insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('rroman0', 'PRvUKfYl', 'mwoolfall0@harvard.edu', '04 Loftsgordon Terrace', 2010, '526-669-7881', '915', '988', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('jzimek1', 'cxBkmiWtER', 'efantone1@foxnews.com', '295 Grayhawk Parkway', 1996, '497-260-9291', '9552', '96', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('kiacapucci2', '8Ru36RC', 'cdeyes2@blog.com', '8 Monica Crossing', 2000, '713-693-3143', '74', '409', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('jcoursor3', 'KFvvoOW', 'rantonopoulos3@clickbank.net', '09 Heffernan Street', 1992, '243-703-7765', '6047', '4', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('cdorkins4', 'qQj78PKoJJS', 'bcottu4@amazon.de', '2 New Castle Junction', 1994, '413-392-6502', '98', '35', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('thellmer5', 'j0kzIO', 'hbeasley5@statcounter.com', '16 Dawn Center', 2001, '407-324-8044', '1160', '449', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('gwhetnall6', 'aumfnC', 'ffiridolfi6@about.com', '3 Fairview Road', 2012, '659-203-5283', '0789', '35858', 'F');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('bmullins7', 'fyG4s8VYFQB', 'kattrill7@sbwire.com', '79 Saint Paul Lane', 2005, '903-751-6175', '1', '97', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('kscamadine8', 'hMVGl5DVuR', 'awitherington8@google.co.jp', '14405 Eastwood Park', 1987, '951-395-4264', '62142', '0525', 'M');
insert into users (username, password, email, location, dob, phone, date_created, last_login, gender) values ('cterne9', '3LtZETJZ69iz', 'hgarstan9@com.com', '1465 Emmet Point', 2007, '448-218-4025', '5', '2763', 'F');



insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (1, 1, '236', '98401', false);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (2, 2, '5543', '277', true);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (3, 3, '223', '7686', true);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (4, 4, '7249', '4', false);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (5, 5, '94', '04', false);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (6, 6, '068', '2', false);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (7, 7, '046', '3', true);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (8, 8, '91', '70670', false);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (9, 9, '4510', '8519', false);
insert into verifications_req (originator_user_id, recipient_user_id, day_created, daysSmokeFree, isApproved) values (10, 10, '25795', '969', false);
