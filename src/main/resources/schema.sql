-- 用户表
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 公告表
CREATE TABLE announcement (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    is_top TINYINT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES user(id)
);

-- 活动表
CREATE TABLE event (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(100),
    start_time DATETIME,
    end_time DATETIME,
    author_id BIGINT NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES user(id)
);

-- 评论表
CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    target_type VARCHAR(20) NOT NULL,
    target_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 活动报名表
CREATE TABLE event_registration (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    register_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES event(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 评论点赞表
CREATE TABLE IF NOT EXISTS comment_like (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    comment_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_like (comment_id, user_id)
);

-- 用户权限表
CREATE TABLE IF NOT EXISTS user_permission (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    permission VARCHAR(50) NOT NULL,
    UNIQUE KEY unique_user_permission (user_id, permission)
); 