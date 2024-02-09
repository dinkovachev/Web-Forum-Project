use forum;

INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (1, 'John', 'Doe', 'john.doe@example.com', 'john_doe', 'pass1', 0, 0, 1);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (2, 'Jane', 'Smith', 'jane.smith@example.com', 'jane_smith', 'pass2', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (3, 'Alice', 'Johnson', 'alice.johnson@example.com', 'alice_johnson', 'pass3', 1, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (4, 'Geori', 'Tomov', 'georgi.tomov@example.com', 'georgi_tomov', 'pass4', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (5, 'Berta', 'Morar', 'berta.morar@example.com', 'berta_morar', 'pass5', 1, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (6, 'Serena', 'Hayes', 'serena.hayes@example.com', 'serena_hayes', 'pass6', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (7, 'Zella', 'Kertzman', 'zella.kertzman@example.com', 'zella_kertzman', 'pass7', 1, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (8, 'Petq', 'Todorova', 'petq.todorova@example.com', 'petq_todorova', 'pass8', 0, 0, 1);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (9, 'Mark', 'Zuckerberg', 'marko123@example.com', 'mark_zuckerberg', 'pass9', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (10, 'Petar', 'Stoyanov', 'petar.stoyanov@example.com', 'petar_stoyanov', 'pass10', 0, 0, 0);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (11, 'Lionel', 'Messi', 'lionel.messi@example.com', 'lionel_messi', 'pass11', 0, 0, 1);
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin) VALUES (12, 'Georgi', 'Asparuhov', 'georgi.asparuhov@example', 'georgi_asparuhov', 'pass12', 0, 0, 0);


INSERT INTO forum.admins (admin_id, user_id, phone_number) VALUES (1, 1, '0877123456');
INSERT INTO forum.admins (admin_id, user_id, phone_number) VALUES (2, 8, null);
INSERT INTO forum.admins (admin_id, user_id, phone_number) VALUES (3, 11, '0887649598');


INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (1, 'Post 1 Title', 'Content of post 1...', '2024-01-24 11:45:52', 0, 8);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (2, 'Post 2 Title', 'Content of post 2...', '2024-01-23 10:46:52', 0, 11);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (3, 'Post 3 Title', 'Content of post 3...', '2024-01-22 15:47:52', 0, 10);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (4, 'Post 4 Title', 'Content of post 4...', '2024-01-21 16:48:52', 0, 6);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (5, 'Post 5 Title', 'Content of post 5...', '2024-01-20 17:49:52', 0, 11);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (6, 'Post 6 Title', 'Content of post 6...', '2024-01-27 19:41:52', 0, 12);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (7, 'Post 7 Title', 'Content of post 7...', '2024-01-27 14:03:40', 1, 2);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (8, 'Post 8 Title', 'Content of post 8...', '2024-01-05 16:05:01', 0, 3);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (9, 'Post 9 Title', 'Content of post 9...', '2024-01-11 10:05:12', 0, 7);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (10, 'Post 10 Title', 'Content of post 10...', '2023-12-29 08:05:37', 1, 4);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (11, 'Post 11 Title', 'Content of post 11...', '2024-01-25 12:06:05', 0, 1);
INSERT INTO forum.posts (post_id, title, content, time_stamp, is_deleted, author_id) VALUES (12, 'Post 12 Title', 'Content of post 11...', '2023-02-07 11:06:34', 0, 5);


INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (1, 'Comment 1 for post 1...', '2024-01-24 11:45:52', 0, 11, 7);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (2, 'Comment 2 for post 1...', '2024-01-24 11:45:52', 0, 3, 8);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (3, 'Comment 1 for post 2...', '2024-01-24 11:45:52', 0, 12, 9);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (4, 'Comment 2 for post 2...', '2024-01-24 11:45:52', 0, 1, 10);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (5, 'Comment 1 for post 3...', '2024-01-24 11:45:52', 0, 11, 11);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (6, 'Comment 2 for post 3...', '2024-01-24 11:45:52', 0, 10, 12);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (7, 'Comment 1 for post 4...', '2024-01-24 11:45:52', 0, 4, 7);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (8, 'Comment 2 for post 4...', '2024-01-24 11:45:52', 0, 6, 8);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (9, 'Comment 1 for post 5...', '2024-01-24 11:45:52', 0, 5, 9);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (10, 'Comment 2 for post 5...', '2024-01-24 11:45:52', 0, 7, 10);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (11, 'Comment 1 for post 6...', '2024-01-24 11:45:52', 0, 9, 1);
INSERT INTO forum.comments (comment_id, content, time_stamp, is_deleted, author_id, post_id) VALUES (12, 'Comment 2 for post 6...', '2024-01-24 11:45:52', 0, 10, 1);

INSERT INTO forum.users_posts(user_id, post_id) VALUES(1,11);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(2,7);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(3,8);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(4,10);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(5,12);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(6,4);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(7,9);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(8,1);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(10,3);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(11,2);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(11,5);
INSERT INTO forum.users_posts(user_id, post_id) VALUES(12,6);