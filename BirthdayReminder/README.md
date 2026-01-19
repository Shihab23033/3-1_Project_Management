# Birthday Reminder (JavaFX)

জন্মদিন রিমাইন্ডার — একটি সহজ ডেস্কটপ অ্যাপ যা আপনার সহপাঠীদের জন্মদিন সংরক্ষণ করে এবং আপনাকে আজকের জন্মদিন সম্পর্কে জানায়। UI-এর সব লেখাগুলো বাংলা।

## সেটআপ (MySQL)

run this on mysql command line:
-- Create database (UTF8MB4 for Bengali)
CREATE DATABASE IF NOT EXISTS `birthday_db`
  DEFAULT CHARACTER SET = utf8mb4
  DEFAULT COLLATE = utf8mb4_unicode_ci;
USE `birthday_db`;

-- Create table to store birthdays
CREATE TABLE IF NOT EXISTS `birthdays` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `birth_date` DATE NOT NULL,
  `note` VARCHAR(255),
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Optional: add stored generated columns and index for fast month/day queries
ALTER TABLE `birthdays`
  ADD COLUMN `birth_month` TINYINT AS (MONTH(`birth_date`)) STORED,
  ADD COLUMN `birth_day` TINYINT AS (DAY(`birth_date`)) STORED,
  ADD INDEX `idx_month_day` (`birth_month`, `birth_day`);

-- Sample data (Bangla names)
INSERT INTO `birthdays` (`name`, `birth_date`, `note`) VALUES
  ('রফিকুল ইসলাম', '1998-01-06', 'শ্রেণীর বন্ধু'),
  ('সাবিনা রহমান', '2000-07-14', 'বাহিরে খেলা করে'),
  ('মাহমুদুল হাসান', '1999-12-20', 'টেক লিডার');

1. MySQL সার্ভার চালু করুন ও একটি ইউজার প্রস্তুত করুন (উদাহরণ: `root` এবং পাসওয়ার্ড)।
2. ডাটাবেস তৈরি ও নমুনা ডাটা ইম্পোর্ট করুন:

   mysql -u root -p < src/main/resources/sql/schema.sql

3. `src/main/resources/db.properties.example` থেকে `src/main/resources/db.properties` কপি করে therein আপনার ডাটাবেস কনফিগ লিখুন:

   db.host=localhost
   db.port=3306
   db.name=birthday_db
   db.user=root
   db.password=your_password

4. প্রকল্প build/run করুন (Maven প্রয়োজন):
   mvn clean javafx:run

---

UI লেবেল/অ্যাসার্ট/নোটিফিকেশন সব বাংলা ভাষায় প্রদর্শিত হবে।

