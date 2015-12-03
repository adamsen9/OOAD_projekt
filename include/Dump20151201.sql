CREATE DATABASE  IF NOT EXISTS `costakalundborg` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `costakalundborg`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: costakalundborg
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hytter`
--

DROP TABLE IF EXISTS `hytter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hytter` (
  `plads_id` int(11) NOT NULL,
  `antal_personer` int(11) DEFAULT NULL,
  `størelse` int(11) DEFAULT NULL,
  `hytte_nummer` int(11) DEFAULT NULL,
  `hytte_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`plads_id`),
  CONSTRAINT `hytte_id` FOREIGN KEY (`plads_id`) REFERENCES `hytterpladser` (`plads_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hytter`
--

LOCK TABLES `hytter` WRITE;
/*!40000 ALTER TABLE `hytter` DISABLE KEYS */;
/*!40000 ALTER TABLE `hytter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hytterpladser`
--

DROP TABLE IF EXISTS `hytterpladser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hytterpladser` (
  `plads_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `iStatus` int(11) DEFAULT NULL,
  `sStatus` varchar(50) DEFAULT NULL,
  `elmaaler_id` int(11) DEFAULT NULL,
  `maaler_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`plads_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hytterpladser`
--

LOCK TABLES `hytterpladser` WRITE;
/*!40000 ALTER TABLE `hytterpladser` DISABLE KEYS */;
INSERT INTO `hytterpladser` VALUES (1,1,0,'',NULL,NULL),(2,0,0,'',NULL,NULL);
/*!40000 ALTER TABLE `hytterpladser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kunde`
--

DROP TABLE IF EXISTS `kunde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kunde` (
  `kunde_navn` varchar(100) DEFAULT NULL,
  `kunde_id` int(11) NOT NULL,
  `tlf` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kunde_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kunde`
--

LOCK TABLES `kunde` WRITE;
/*!40000 ALTER TABLE `kunde` DISABLE KEYS */;
INSERT INTO `kunde` VALUES ('test',1,'376576'),('ldsfkg',2,'37865'),('Adamsen Skovæk',3,'50917285'),('Test2',4,'11111111'),('Test',5,'22222222');
/*!40000 ALTER TABLE `kunde` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `res_id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `start_dato` date DEFAULT NULL,
  `slut_dato` date DEFAULT NULL,
  `plads_type` int(11) DEFAULT NULL,
  `plads_id` int(11) DEFAULT NULL,
  `kunde_id` int(11) DEFAULT NULL,
  `antal_voksne` int(11) DEFAULT NULL,
  `antal_børn` int(11) DEFAULT NULL,
  `antal_hunde` int(11) DEFAULT NULL,
  `start_el` int(11) DEFAULT NULL,
  `slut_el` int(11) DEFAULT NULL,
  PRIMARY KEY (`res_id`),
  KEY `reservation_kunde_idx` (`kunde_id`),
  CONSTRAINT `reservation_kunde` FOREIGN KEY (`kunde_id`) REFERENCES `kunde` (`kunde_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,0,'2015-11-01','2015-11-08',1,1,1,1,0,0,0,0),(2,0,'2015-12-01','2015-12-08',1,0,3,1,0,0,0,0),(3,1,'2015-12-01','2015-12-08',0,0,3,1,0,0,0,0);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-01 23:15:14
