
USE squareHelp;
# DROP TABLE IF EXISTS users;

insert into messages (author_user_id, recipient_user_id, message) values (1, 1, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.');

insert into notifications (recipient_user_id, originator_user_id, notification, is_viewed) values (1, 1, 'In est risus, auctor sed, tristique in, tempus sit amet, sem.', false);

insert into smoking_info (user_id, day_quit_smoking, day_relapse, total_days_smoke_free, points,  cost_of_cigs_saved) values (1, 24978, 19321, 5, 10, 551);

insert into users (username, password, email, state, city, dob, phone_number, date_created, last_login, gender) values ('rroman0', 'PRvUKfYl', 'mwoolfall0@harvard.edu', 'TX', 'San Antonio', 19321, '15266697881', 19321, '19321', 'M');
insert into users (username, password, email, state, city, dob, phone_number, date_created, last_login, gender) values ('tom', 'PRvUKfYl', 'mwooolfall0@harvard.edu', 'TX', 'San Antonio', 19321, '15266697882', 19321, '19321', 'M');
insert into users (username, password, email, state, city, dob, phone_number, date_created, last_login, gender) values ('admin', 'password', 'mweelfall0@harvard.edu', 'TX', 'San Antonio', 19321, '15266697883', 19321, '19321', 'F');

insert into verifications_req (originator_user_id, approver_user_id, day_created, days_smoke_free, is_approved) values (1, 1, 19321, 1, false);

insert into buddy_sys(originator_user_id, recipient_user_id) VALUE (1,1)


