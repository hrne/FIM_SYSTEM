-- 建立新的帳號，主機是localhost
CREATE USER 'fimuser'@'localhost' IDENTIFIED BY 'fimuser';
-- 給予新帳號權限讀寫新資料庫 fim_system
GRANT ALL PRIVILEGES ON fim_system.* TO 'fimuser'@'localhost';