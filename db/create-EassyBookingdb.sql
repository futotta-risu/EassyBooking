DROP SCHEMA eassyBookingDB;
DROP USER 'eassyBooking_user1'@'%';

CREATE SCHEMA eassyBookingDB;
CREATE USER 'eassyBooking_user1'@'%' IDENTIFIED BY '12345';
GRANT ALL ON eassyBookingDB.* TO 'eassyBooking_user1'@'%';