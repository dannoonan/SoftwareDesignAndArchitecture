SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


--
-- Database: `gradeace`
--

CREATE DATABASE IF NOT EXISTS `BikeRental` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `BikeRental`;

--
-- Stored Procedures
--

DELIMITER $$


DROP PROCEDURE IF EXISTS `addUserType`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addUserType`(IN `UserTypeName`  VARCHAR(128))
    READS SQL DATA
BEGIN
INSERT INTO `UserType`(UserTypeName) VALUES (UserTypeName);
END$$

-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `addStudentCard`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addStudentCard`(IN `Balance`  INT(10))
    READS SQL DATA
BEGIN
INSERT INTO `StudentCard`(Balance) VALUES (Balance);
END$$

-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `addUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser`(IN `StudentCardId` INT(10), IN `UserTypeId` INT(10), IN `Username` VARCHAR(128), IN `Email` VARCHAR(128), IN `Password` VARCHAR(128))
    READS SQL DATA
BEGIN
INSERT INTO `Users`(StudentCardId, UserTypeId, Username, Email, Password) VALUES (StudentCardId, UserTypeId, Username, Email, Password );
END$$


-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `addNode`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNode`(IN `Position` VARCHAR(128))
    READS SQL DATA
BEGIN
INSERT INTO `Nodes`(Position) VALUES (Position);
END$$

-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `getUsersByType`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUser`( IN `UserType` VARCHAR(128))
    READS SQL DATA
BEGIN

	select  u.UserId, u.UserType, u.Username
		from  Users u
		where(u.UserType = UserType);

END$$


-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `getBikesByNode`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBikesByNode`( IN `NodeId` VARCHAR(128))
    READS SQL DATA
BEGIN

	select  b.BikeId, b.BikeType
		from  Bikes b
		where(b.NodeId = NodeId)
			and(b.Status = 0);

END$$

-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `claimBike`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `claimBike`(IN `rBikeId` INT(10), IN `rLastUserId` INT(10))
    READS SQL DATA
BEGIN
UPDATE `Bikes` SET Status=1 WHERE BikeId= rBikeId;
UPDATE `Bikes` SET LastUserId=rLastUserId WHERE BikeId = rBikeId;
END$$

-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `unclaimBike`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `unclaimBike`(IN `rBikeId` INT(10))
    READS SQL DATA
BEGIN
UPDATE `Bikes` SET Status=0 WHERE BikeId= rBikeId;
END$$

-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `getUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUser`( IN `UserId` VARCHAR(128))
    READS SQL DATA
BEGIN


	if UserId='' then set UserId=null;end if;

	
	select u.UserId, u.Username, u.UserTypeId, u.StudentCardId ,u.Email, u.Password  
        from Users u  
        where   (UserId is null or u.UserId = UserId);

END$$

-- --------------------------------------------------------

DROP PROCEDURE IF EXISTS `getOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getOrder`( IN `OrderId` INT(10))
    READS SQL DATA
BEGIN


	if OrderId='' then set OrderId=null;end if;
	
	
	select o.OrderId, o.BikeId, o.UserId, o.PaidStatus, o.MoneyConsumed, o.OrderTime 
        from Orders o  
        where   (OrderId is null or o.OrderId = OrderId);

END$$

DELIMITER ;


-- --------------------------------------------------------
--
-- Table Creation
--
--
-- Table structure for table `UserType`
--

CREATE TABLE IF NOT EXISTS `UserType` (
`UserTypeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
`UserTypeName` varchar(128) NOT NULL,
PRIMARY KEY (`UserTypeId`)
);

--
-- Table structure for table `UserType`
--

CREATE TABLE IF NOT EXISTS `StudentCard` (
`StudentCardId` int(10) unsigned NOT NULL AUTO_INCREMENT,
`Balance` float(10) NOT NULL,
PRIMARY KEY (`StudentCardId`)
);

--
--
-- Table structure for table `Nodes`
--

CREATE TABLE IF NOT EXISTS `Nodes` (
`NodeId` int(10) unsigned NOT NULL AUTO_INCREMENT, 
`Position` varchar(128) NOT NULL,
PRIMARY KEY (`NodeId`)
);

-- Table Structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
`UserId` int(10) unsigned NOT NULL AUTO_INCREMENT,
`StudentCardId` int(10) unsigned NULL,
`UserTypeId` int(10) unsigned NOT NULL,
`Username` varchar(128) NOT NULL,
`Email` varchar(128) NOT NULL,
`Password` varchar(128) NOT NULL,
`isBanned` boolean NOT NULL DEFAULT 0,
PRIMARY KEY (`UserId`),
CONSTRAINT FOREIGN KEY (`StudentCardId`) REFERENCES `StudentCard`(`StudentCardId`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`UserTypeId`) REFERENCES `UserType`(`UserTypeId`) ON DELETE CASCADE ON UPDATE CASCADE
);
-- --------------------------------------------------------


--
-- Table structure for table `Bikes`
--

CREATE TABLE IF NOT EXISTS `Bikes` (
`BikeId` int(10) unsigned NOT NULL AUTO_INCREMENT, 
`BikeType` varchar(128) NOT NULL,
`NodeId` int(10) unsigned NOT NULL,
`Position` varchar(128) NOT NULL,
`isAvailable` boolean NOT NULL DEFAULT 0,
`LastUserId` int(10) unsigned NOT NULL ,
PRIMARY KEY (`BikeId`),
CONSTRAINT FOREIGN KEY (`NodeId`) REFERENCES `Nodes`(`NodeId`)ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`LastUserId`) REFERENCES `Users`(`UserId`)ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Table structure for table `Orders`
--

CREATE TABLE IF NOT EXISTS `Orders` (
`OrderId` int(10) unsigned NOT NULL AUTO_INCREMENT, 
`BikeId` int(10) unsigned NOT NULL,
`UserId` int(10) unsigned NOT NULL,
`isPaid` boolean NOT NULL DEFAULT 0,
`MoneyConsumed` int(10) NOT NULL DEFAULT 0,
`OrderTime` datetime NOT NULL,
PRIMARY KEY (`OrderId`),
CONSTRAINT FOREIGN KEY (`BikeId`) REFERENCES `Bikes`(`BikeId`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`UserId`) REFERENCES `Users`(`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
--
-- Table structure for table `Reports`
--

CREATE TABLE IF NOT EXISTS `Reports` (
`ReportId` int(10) unsigned NOT NULL AUTO_INCREMENT,
`BikeId` int(10) unsigned NOT NULL,
`UserId` int(10) unsigned NOT NULL,
`ReportText` varchar(256) NOT NULL,
PRIMARY KEY (`ReportId`),
CONSTRAINT FOREIGN KEY (`BikeId`) REFERENCES `Bikes`(`BikeId`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`UserId`) REFERENCES `Users`(`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
--
-- Table structure for table `Collections`
--

CREATE TABLE IF NOT EXISTS `Collections` (
`CollectionId` int(10) unsigned NOT NULL AUTO_INCREMENT,
`DriverId` int(10) unsigned NOT NULL,
`AdminId` int(10) unsigned NOT NULL,
PRIMARY KEY (`CollectionId`),
CONSTRAINT FOREIGN KEY (`DriverId`) REFERENCES `Users`(`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`AdminId`) REFERENCES `Users`(`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
--
-- Table structure for table `CollectionScheduledBikes`
--

CREATE TABLE IF NOT EXISTS `CollectionScheduledBikes` (
`BikeId` int(10) unsigned NOT NULL,
`CollectionId` int(10) unsigned NOT NULL,
PRIMARY KEY (`BikeId`),
CONSTRAINT FOREIGN KEY (`BikeId`) REFERENCES `Bikes`(`BikeId`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`CollectionId`) REFERENCES `Collections`(`CollectionId`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
--
-- Table structure for table `Repairs`
--

CREATE TABLE IF NOT EXISTS `Repairs` (
`RepairId` int(10) unsigned NOT NULL AUTO_INCREMENT,
`DriverId` int(10) unsigned NOT NULL,
`AdminId` int(10) unsigned NOT NULL,
PRIMARY KEY (`RepairId`),
CONSTRAINT FOREIGN KEY (`DriverId`) REFERENCES `Users`(`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`AdminId`) REFERENCES `Users`(`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
--
-- Table structure for table `RepairScheduledBikes`
--

CREATE TABLE IF NOT EXISTS `RepairScheduledBikes` (
`BikeId` int(10) unsigned NOT NULL,
`RepairId` int(10) unsigned NOT NULL,
PRIMARY KEY (`BikeId`),
CONSTRAINT FOREIGN KEY (`BikeId`) REFERENCES `Bikes`(`BikeId`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (`RepairId`) REFERENCES `Repairs`(`RepairId`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- --------------------------------------------------------

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
