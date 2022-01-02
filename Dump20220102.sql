-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ebike
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `biketype`
--

DROP TABLE IF EXISTS `biketype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biketype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `storeid` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `license` varchar(45) DEFAULT NULL,
  `manuafactur` varchar(45) DEFAULT NULL,
  `producer` varchar(45) DEFAULT NULL,
  `cost` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_storeid_idx` (`storeid`),
  CONSTRAINT `fk_storeid` FOREIGN KEY (`storeid`) REFERENCES `store` (`storeid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biketype`
--

LOCK TABLES `biketype` WRITE;
/*!40000 ALTER TABLE `biketype` DISABLE KEYS */;
INSERT INTO `biketype` VALUES (1,1,'Calos','Bike',23,'1','2001','Italy',2300,'Available'),(2,1,'Centa','Bike',42,'2','2002','France',1200,'Available'),(3,1,'Delta','ElectricBike',12,'3','2003','Naiko',1000,'Available'),(4,1,'Chigo','ElectricBike',32,'4','2004','Denta',1500,'Available'),(5,2,'GT','Bike',12,'4','2008','Italy',1255,'Available'),(6,2,'Kona','Bike',8,'1','2009','Brazil',3000,'Available'),(7,2,'Scott','ElectricBike',9,'2','2010','Brazil',4500,'Available'),(8,2,'Merida','Bike',23,'3','2009','Italy',3000,'Available'),(9,3,'Marin','TwinBike',53,'4','2009','Brazil',4500,'Available'),(10,3,'Giant','TwinBike',35,'5','2015','Brazil',4500,'Available'),(11,3,'Romance','ElectricBike',46,'6','2009','Italy',3000,'Available'),(12,3,'Alan','ElectricBike',22,'2','2021','USA',2800,'Available'),(13,4,'Alex','Bike',1,'1','2031','USA',4700,'Available'),(14,4,'Argon','TwinBike',2,'2','2015','France',2800,'Available'),(15,4,'Ascari','Bike',3,'1','2018','USA',2800,'Rent'),(16,4,'Avanti','TwinBike',4,'2','2019','France',4700,'Available');
/*!40000 ALTER TABLE `biketype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rent` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `total` int DEFAULT NULL,
  `timeCreate` datetime DEFAULT NULL,
  `timeFinish` datetime DEFAULT NULL,
  `bikeId` varchar(45) DEFAULT NULL,
  `custId` int DEFAULT NULL,
  `returnId` int DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `fk_idx` (`custId`),
  CONSTRAINT `fk_to` FOREIGN KEY (`custId`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
INSERT INTO `rent` VALUES (262,13000,'2021-12-24 16:54:23','2021-12-24 16:55:02','15',1,4),(263,67000,'2021-12-24 16:55:44','2021-12-24 17:00:58','10',1,3),(264,16000,'2021-12-24 17:03:16','2021-12-24 17:04:08','9',1,3),(265,13000,'2021-12-24 19:25:54','2021-12-24 19:26:32','5',1,2),(266,13000,'2021-12-25 14:58:33','2021-12-25 14:59:10','7',1,2),(267,10000,'2021-12-26 11:50:52','2021-12-26 11:50:57','17',1,1),(268,10000,'2021-12-26 22:13:10','2021-12-26 22:13:22','11',1,3),(269,10000,'2021-12-27 08:23:09','2021-12-27 08:23:27','5',1,2),(270,10000,'2021-12-27 09:15:07','2021-12-27 09:15:22','4',1,1),(271,28000,'2021-12-29 13:07:10','2021-12-29 13:09:03','1',1,1),(272,55500,'2021-12-29 13:09:46','2021-12-29 13:12:20','3',1,1),(273,19000,'2021-12-29 13:12:47','2021-12-29 13:13:58','1',1,1),(274,33000,'2021-12-29 13:14:27','2021-12-29 13:15:43','3',1,1),(275,19500,'2021-12-29 19:35:30','2021-12-29 19:36:03','7',1,2),(276,15000,'2021-12-30 11:38:42','2021-12-30 11:38:47','7',1,2),(277,NULL,'2022-01-01 08:50:13',NULL,'15',2,NULL),(278,52000,'2022-01-01 16:04:13','2022-01-01 16:07:59','1',1,4),(279,10000,'2022-01-01 16:19:22','2022-01-01 16:19:52','1',1,1),(280,49000,'2022-01-01 16:21:11','2022-01-01 16:24:48','1',1,1);
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `storeid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `emptyDock` int DEFAULT '0',
  PRIMARY KEY (`storeid`),
  UNIQUE KEY `storeid_UNIQUE` (`storeid`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `address_UNIQUE` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'F-x Bike','225 Hoàng Hoa Thám, Ba Đình, Hà Nội',0),(2,'Toan Thang Cycles','23 Lê Trọng Tấn, La Khê, Hà Nội',0),(3,'Xe đạp 24h','7A Trường Chinh, Quận Thanh Xuân, Hà Nội ',0),(4,'Xe Đạp Thế Giới','163 Cầu Diễn, Bắc Từ Liêm, Hà Nội',1);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderId` int NOT NULL,
  `message` varchar(100) DEFAULT NULL,
  `money` float DEFAULT NULL,
  `card` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idx` (`orderId`),
  CONSTRAINT `fk` FOREIGN KEY (`orderId`) REFERENCES `rent` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=492 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (455,262,'',400000,'1234567891111111'),(456,262,'1',13000,'1234567891111111'),(457,263,'1',550000,'1234567891111111'),(458,263,'',67000,'1234567891111111'),(459,264,'',550000,'1234567891111111'),(460,264,'1',16000,'1234567891111111'),(461,265,'1',400000,'1234567891111111'),(462,265,'1',13000,'1234567891111111'),(463,266,'1',700000,'1234567891111111'),(464,266,'1',13000,'1234567891111111'),(465,267,'1',700000,'1234567891111111'),(466,267,'1',10000,'1234567891111111'),(467,268,'',700000,'123'),(468,268,'',10000,''),(469,269,'',400000,'1234567891111111'),(470,269,'',10000,'1234567891111111'),(471,270,'1',700000,'1234567891111111'),(472,270,'',10000,'1234567891111111'),(473,271,'1',400000,'1234567891111111'),(474,271,'',28000,'1234567891111111'),(475,272,'1',700000,'1234567891111111'),(476,272,'1',55500,'1234567891111111'),(477,273,'1',400000,'1234567891111111'),(478,273,'1',19000,'1234567891111111'),(479,274,'',700000,'1234567891111111'),(480,274,'',33000,'1234567891111111'),(481,275,'1',700000,'1234567891111111'),(482,275,'1',19500,'1234567891111111'),(483,276,'1',700000,'1234567891111111'),(484,276,'1',15000,'1234567891111111'),(485,277,'1',400000,'1234567891111112'),(486,278,'1',400000,'1234567891111111'),(487,278,'1',52000,'1234567891111111'),(488,279,'1',400000,'1234567891111111'),(489,279,'1',10000,'1234567891111111'),(490,280,'1',400000,'1234567891111111'),(491,280,'1',49000,'1234567891111111');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useraccount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (1,'tu','nam','Tu','23B Tong Dan','dangquoctuhn@gmail.com'),(2,'xuan','phong','Hai','trung','dangquoctanhn@gmail.com');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ebike'
--

--
-- Dumping routines for database 'ebike'
--
/*!50003 DROP FUNCTION IF EXISTS `insertRent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `insertRent`(
   timeT DateTime,bikeId INT,custId INT
) RETURNS int
    DETERMINISTIC
BEGIN

  INSERT INTO rent(timeCreate,bikeId,custId) VALUES (timeT,bikeId,custId);
  UPDATE biketype
SET status = 'Rent'
WHERE id = bikeId;
	
    return 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `insertReturn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `insertReturn`( 
  orID int,bikeID int,totMoney int,timeFns DateTime,id int) RETURNS int
    DETERMINISTIC
BEGIN
   UPDATE rent SET total = totMoney,
                   timeFinish = timeFns,
                   returnId = id
   WHERE rent.orderId = orID;

return 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `updateReturn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `updateReturn`(bikeID int,sID int) RETURNS int
    DETERMINISTIC
BEGIN
 UPDATE  store
  SET emptyDock = emptyDock-1
		WHERE  storeid = sID;
    
    UPDATE biketype
    set   storeid = sID,status='Available'
    where id = bikeID;

RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `updateStoreRent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `updateStoreRent`(
	sId int
) RETURNS int
    DETERMINISTIC
BEGIN
    UPDATE  store
    SET emptyDock = emptyDock+1
    WHERE  storeid = (SELECT storeid from biketype where id = sid );
	RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-02 12:26:39
