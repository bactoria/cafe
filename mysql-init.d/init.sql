CREATE USER 'cafe'@'localhost' IDENTIFIED BY 'cafe';
CREATE USER 'cafe'@'%' IDENTIFIED BY 'cafe';

GRANT ALL PRIVILEGES ON *.* TO 'cafe'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'cafe'@'%';

ALTER DATABASE cafe CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `cafe` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) NOT NULL UNIQUE
);
