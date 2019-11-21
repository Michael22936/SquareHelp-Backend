
USE squareHelp;
# DROP TABLE IF EXISTS users;

insert into messages (author_user_id, recipient_user_id, message) values (1, 1, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.');

insert into notifications (recipient_user_id, originator_user_id, notification, is_viewed) values (1, 1, 'In est risus, auctor sed, tristique in, tempus sit amet, sem.', false);

insert into smoking_info (user_id, day_quit_smoking, day_relapse, total_days_smoke_free, points,  cost_of_cigs_saved) values (1, '1995-10-05', '1991-07-23', 5, 10, 458);
insert into smoking_info (user_id, day_quit_smoking, day_relapse, total_days_smoke_free, points,  cost_of_cigs_saved) values (2, '1991-11-26', '2005-03-13', 20, 20, 662);
insert into smoking_info (user_id, day_quit_smoking, day_relapse, total_days_smoke_free, points,  cost_of_cigs_saved) values (3, '2001-01-01', '2015-03-10', 15, 30, 551);

insert into users (username, password, email, state, city, dob, phone_number, date_created, last_login, gender) values ('rroman0', 'PRvUKfYl', 'mwoolfall0@harvard.edu', 'TX', 'San Antonio', '1991-11-26', '15266697881', '2001-08-12', '19321', 'M');
insert into users (username, password, email, state, city, dob, phone_number, date_created, last_login, gender) values ('tom', 'PRvUKfYl', 'mwooolfall0@harvard.edu', 'TX', 'San Antonio', '1955-08-12', '15266697882', '2015-03-21', '19321', 'M');
insert into users (username, password, email, state, city, dob, phone_number, date_created, last_login, gender) values ('admin', 'password', 'mweelfall0@harvard.edu', 'TX', 'San Antonio', '1965-05-20', '15266697883', '2005-05-05', '19321', 'F');

insert into verifications_req (originator_user_id, approver_user_id, day_created, days_smoke_free, is_approved) values (1, 1, 19321, 1, false);

insert into buddy_sys(originator_user_id, recipient_user_id) VALUE (1,1)


