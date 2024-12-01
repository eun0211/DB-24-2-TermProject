-- 데이터베이스 생성 및 사용
CREATE DATABASE IF NOT EXISTS twitter;
USE twitter;

-- user 테이블
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` INT NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- posts 테이블
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `post_id` INT NOT NULL,
  `text` TEXT,
  `writter_id` INT DEFAULT NULL,
  `num_of_likes` INT DEFAULT '0',
  PRIMARY KEY (`post_id`),
  KEY `writter_id` (`writter_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`writter_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- comment 테이블
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` INT NOT NULL,
  `text` TEXT,
  `user_id` INT DEFAULT NULL,
  `post_id` INT DEFAULT NULL,
  `num_of_likes` INT DEFAULT '0',
  PRIMARY KEY (`comment_id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- child_comment 테이블
DROP TABLE IF EXISTS `child_comment`;
CREATE TABLE `child_comment` (
  `cmt_id` INT NOT NULL,
  `text` TEXT,
  `user_id` INT DEFAULT NULL,
  `parent_cmt_id` INT DEFAULT NULL,
  PRIMARY KEY (`cmt_id`),
  KEY `user_id` (`user_id`),
  KEY `parent_cmt_id` (`parent_cmt_id`),
  CONSTRAINT `child_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `child_comment_ibfk_2` FOREIGN KEY (`parent_cmt_id`) REFERENCES `comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- comment_like 테이블
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like` (
  `l_id` INT NOT NULL,
  `user_id` INT DEFAULT NULL,
  `cmt_id` INT DEFAULT NULL,
  PRIMARY KEY (`l_id`),
  KEY `user_id` (`user_id`),
  KEY `cmt_id` (`cmt_id`),
  CONSTRAINT `comment_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `comment_like_ibfk_2` FOREIGN KEY (`cmt_id`) REFERENCES `comment` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- follower 테이블
DROP TABLE IF EXISTS `follower`;
CREATE TABLE `follower` (
  `f_id` INT NOT NULL,
  `follwer_id` INT DEFAULT NULL,
  `user_id` INT DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `follwer_id` (`follwer_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `follower_ibfk_1` FOREIGN KEY (`follwer_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `follower_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- following 테이블
DROP TABLE IF EXISTS `following`;
CREATE TABLE `following` (
  `f_id` INT NOT NULL,
  `following_id` INT DEFAULT NULL,
  `user_id` INT DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `following_id` (`following_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `following_ibfk_1` FOREIGN KEY (`following_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `following_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- post_like 테이블
DROP TABLE IF EXISTS `post_like`;
CREATE TABLE `post_like` (
  `l_id` INT NOT NULL,
  `user_id` INT DEFAULT NULL,
  `post_id` INT DEFAULT NULL,
  `text` TEXT,
  PRIMARY KEY (`l_id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `post_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 데이터 삽입
INSERT INTO `user` (`user_id`, `password`) VALUES
(1, 'password123'),
(2, 'securepass456'),
(3, 'p3'),
(4, 'p4'),
(5, 'p5'),
(6, 'p6'),
(7, 'p7'),
(8, 'p8'),
(9, 'p9'),
(10, 'p10');

INSERT INTO `posts` (`post_id`, `text`, `writter_id`, `num_of_likes`) VALUES
(1, 'This is the first post!', 1, 0),
(2, 'Hello from user 2!', 2, 0),
(3, 'Post by user 3', 3, 0),
(4, 'Post by user 4', 4, 0),
(5, 'Post by user 5', 5, 0);

INSERT INTO `comment` (`comment_id`, `text`, `user_id`, `post_id`, `num_of_likes`) VALUES
(1, 'Great post!', 1, 1, 0),
(2, 'Thank you!', 2, 1, 0);

INSERT INTO `child_comment` (`cmt_id`, `text`, `user_id`, `parent_cmt_id`) VALUES
(2, 'You’re welcome!', 1, 1);
