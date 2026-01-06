# Birthday Reminder (JavaFX)

জন্মদিন রিমাইন্ডার — একটি সহজ ডেস্কটপ অ্যাপ যা আপনার সহপাঠীদের জন্মদিন সংরক্ষণ করে এবং আপনাকে আজকের জন্মদিন সম্পর্কে জানায়। UI-এর সব লেখাগুলো বাংলা।

## সেটআপ (MySQL)

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

