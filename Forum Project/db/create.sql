drop database if exists forum;

create database forum;

use forum;
create table users
(
    user_id    int auto_increment
        primary key,
    first_name varchar(32) not null,
    last_name  varchar(32) not null,
    email      varchar(50) not null,
    username   varchar(20) not null,
    password   varchar(200) not null,
    is_blocked tinyint(1)  not null default 0,
    is_deleted tinyint(1)  not null,
    is_admin   tinyint(1)  not null default 0
);

create table admins
(
    admin_id     int auto_increment
        primary key,
    user_id      int         not null,
    phone_number varchar(20) null,
    constraint user_id
        foreign key (user_id) references users (user_id)
);

create table posts
(
    post_id    int auto_increment
        primary key,
    title      varchar(64)   not null,
    content    varchar(8192) not null,
    time_stamp datetime      not null,
    is_deleted tinyint(1)    not null,
    author_id  int           not null,
    constraint author_id
        foreign key (author_id) references users (user_id)
);

create table comments
(
    comment_id int auto_increment
        primary key,
    content    varchar(8192) not null,
    time_stamp datetime      not null,
    is_deleted tinyint(1)    not null,
    author_id  int           not null,
    post_id    int           not null,
    constraint comment_author_id
        foreign key (author_id) references users (user_id),
    constraint post_id
        foreign key (post_id) references posts (post_id)
);

create table likes
(
    like_id       int auto_increment
        primary key,
    liked_post_id int not null,
    author_id     int not null,
    constraint liked_post_id
        foreign key (liked_post_id) references posts (post_id),
    constraint user_liked_id
        foreign key (author_id) references users (user_id)
);

