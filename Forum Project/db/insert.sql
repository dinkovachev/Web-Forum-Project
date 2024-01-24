use forum;

INSERT INTO users (first_name, last_name, email, username, password, is_blocked, is_deleted, is_admin)
VALUES ('John', 'Doe', 'john.doe@example.com', 'john_doe', 'pass1', false, false, true),
       ('Jane', 'Smith', 'jane.smith@example.com', 'jane_smith', 'pass2', false, false, false),
       ('Alice', 'Johnson', 'alice.johnson@example.com', 'alice_johnson', 'pass3', true, false, false);

INSERT INTO admins(user_id, phone_number)
VALUES (10,'0877123456');

INSERT INTO posts (title, content, time_stamp, is_deleted, author_id)
VALUES ('Post 1 Title', 'Content of post 1...', '2024-01-24 11:45:52', false, 11),
       ('Post 2 Title', 'Content of post 2...', '2024-01-23 10:46:52', false, 11),
       ('Post 3 Title', 'Content of post 3...', '2024-01-22 15:47:52', false, 10),
       ('Post 4 Title', 'Content of post 4...', '2024-01-21 16:48:52', false, 11),
       ('Post 5 Title', 'Content of post 5...', '2024-01-20 17:49:52', false, 11),
       ('Post 6 Title', 'Content of post 6...', '2024-01-27 19:41:52', false, 10);

INSERT INTO comments(content, time_stamp, is_deleted, author_id, post_id)
VALUES ('Comment 1 for post 1...', '2024-01-24 11:45:52', false, 11, 7),
       ('Comment 2 for post 1...', '2024-01-24 11:45:52', false, 10, 8),
       ('Comment 1 for post 2...', '2024-01-24 11:45:52', false, 11, 9),
       ('Comment 2 for post 2...', '2024-01-24 11:45:52', false, 10, 10),
       ('Comment 1 for post 3...', '2024-01-24 11:45:52', false, 11, 11),
       ('Comment 2 for post 3...', '2024-01-24 11:45:52', false, 10, 12),
       ('Comment 1 for post 4...', '2024-01-24 11:45:52', false, 11, 7),
       ('Comment 2 for post 4...', '2024-01-24 11:45:52', false, 10, 8),
       ('Comment 1 for post 5...', '2024-01-24 11:45:52', false, 11, 9),
       ('Comment 2 for post 5...', '2024-01-24 11:45:52', false, 10, 10),
       ('Comment 1 for post 6...', '2024-01-24 11:45:52', false, 11, 11),
       ('Comment 2 for post 6...', '2024-01-24 11:45:52', false, 10, 12);
