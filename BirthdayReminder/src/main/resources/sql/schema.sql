-- Schema for BirthdayReminder
CREATE DATABASE IF NOT EXISTS `birthday_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `birthday_db`;

CREATE TABLE IF NOT EXISTS `birthdays` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `birth_date` DATE NOT NULL,
  `note` VARCHAR(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample data (Bangla names)
INSERT INTO `birthdays` (`name`, `birth_date`, `note`) VALUES
('রফিকুল ইসলাম', '1998-01-06', 'শ্রেণীর বন্ধু'),
('সাবিনা রহমান', '2000-07-14', 'বাহিরে খেলা করে'),
('মাহমুদুল হাসান', '1999-12-20', 'টেক লিডার');
