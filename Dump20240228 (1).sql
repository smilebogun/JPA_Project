-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ynidb
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_no` int NOT NULL AUTO_INCREMENT,
  `admin_email` varchar(100) NOT NULL,
  `admin_id` varchar(50) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_pw` varchar(100) DEFAULT NULL,
  `admin_role` varchar(50) NOT NULL,
  `admin_tel` varchar(100) NOT NULL,
  `r_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`admin_no`),
  UNIQUE KEY `UK_mtqil421ligixgt45rjvyfg0f` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'smilebogun1@gmail.com','admin','최고관리자','{bcrypt}$2a$10$jQ/uOUWVol50myr61.iiI.5eKws4RVKyriXzqI/x8kKEexjGsIG6m','ROLE_ADMIN','010-1234-1234','2024-02-15 13:15:20.175000'),(2,'smilebogun1@gmail.com','manager','중간관리자','{bcrypt}$2a$10$0e1grG5mfgo4BnuwlgFbRO.c4FrL/qe0YYkARA5/jHaTcMKi5ABHS','ROLE_MANAGER','010-1234-1234','2024-02-15 13:15:28.824000'),(3,'smilebogun1@gmail.com','user','유저테스트','{bcrypt}$2a$10$w4tzqg4HA2yva62Airwky.mw6gzTq4MdmCTpWhH4WllWQrGuVHPFO','ROLE_USER','010-1234-1234','2024-02-15 13:15:39.889000');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adopt`
--

DROP TABLE IF EXISTS `adopt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adopt` (
  `adopt_no` int NOT NULL AUTO_INCREMENT,
  `adopt_cnt` int NOT NULL,
  `adopt_create_date` datetime(6) DEFAULT NULL,
  `adopt_status` varchar(255) NOT NULL,
  `adopt_update_date` datetime(6) DEFAULT NULL,
  `detail_addr` varchar(100) NOT NULL,
  `o_auth` varchar(255) DEFAULT NULL,
  `pet_adopt` varchar(255) NOT NULL,
  `pet_age` varchar(100) NOT NULL,
  `pet_gender` varchar(255) NOT NULL,
  `pet_image` varchar(255) NOT NULL,
  `pet_inoculation` varchar(255) NOT NULL,
  `pet_name` varchar(255) NOT NULL,
  `pet_neutering` varchar(255) NOT NULL,
  `pet_no` int NOT NULL,
  `pet_place` varchar(255) NOT NULL,
  `pet_remarks` varchar(255) NOT NULL,
  `pet_species` varchar(100) DEFAULT NULL,
  `pet_sysdate` varchar(255) NOT NULL,
  `pet_update` varchar(255) NOT NULL,
  `plus_addr` varchar(100) DEFAULT NULL,
  `road_addr` varchar(100) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `shel_area` varchar(255) NOT NULL,
  `shel_name` varchar(50) NOT NULL,
  `user_age` varchar(10) NOT NULL,
  `user_create_date` varchar(255) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_no` int NOT NULL,
  `user_pw` varchar(200) NOT NULL,
  `user_tel` varchar(20) NOT NULL,
  `user_update_date` varchar(255) NOT NULL,
  `zipcode` varchar(5) NOT NULL,
  `list_status` varchar(255) NOT NULL,
  PRIMARY KEY (`adopt_no`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adopt`
--

LOCK TABLES `adopt` WRITE;
/*!40000 ALTER TABLE `adopt` DISABLE KEYS */;
INSERT INTO `adopt` VALUES (1,0,'2024-02-19 17:48:46.923000','3','2024-02-19 17:50:21.708000','123','YNI','입양대기','5','수컷','1708332391439_20240118_142510.jpg','완료','말티즈','완료',10,'123','123','강아지','2024-02-19 17:46:31.477','2024-02-19 17:46:31.477',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강서보호소','33','2024-02-15 13:29:46.797','smilebogun1@gmail.com','user','테스트',3,'{bcrypt}$2a$10$yqwd.AwqUh11nr3NKoRvE.C4au52wTzu/mG2.odN1o7PhJWIY/d/2','010-1234-1234','2024-02-15 13:29:46.797','07505',''),(2,0,'2024-02-19 17:48:56.188000','2','2024-02-19 17:51:52.851000','123','YNI','입양대기','5','암컷','1708328877649_Figure_1.png','미완료','잡종','미완료',9,'123','123','고양이','2024-02-19 16:47:57.676','2024-02-19 16:47:57.676',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강서보호소','33','2024-02-15 13:29:46.797','smilebogun1@gmail.com','user','테스트',3,'{bcrypt}$2a$10$yqwd.AwqUh11nr3NKoRvE.C4au52wTzu/mG2.odN1o7PhJWIY/d/2','010-1234-1234','2024-02-15 13:29:46.797','07505',''),(3,0,'2024-02-19 17:49:15.804000','2','2024-02-19 17:50:21.731000','123','YNI','입양대기','5','수컷','1708332391439_20240118_142510.jpg','완료','말티즈','완료',10,'123','123','강아지','2024-02-19 17:46:31.477','2024-02-19 17:46:31.477',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강서보호소','33','2024-02-15 16:57:27.876','smilebogun1@gmail.com','test','테스트',4,'{bcrypt}$2a$10$EuCR2yYTleUc5hLoZ45fZOquSYq.nr2MXPI4AMKv.i1AAkGsQxdzq','1234','2024-02-15 16:57:27.877','07505',''),(4,0,'2024-02-19 17:49:19.907000','3','2024-02-19 17:51:52.867000','123','YNI','입양대기','5','암컷','1708328877649_Figure_1.png','미완료','잡종','미완료',9,'123','123','고양이','2024-02-19 16:47:57.676','2024-02-19 16:47:57.676',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강서보호소','33','2024-02-15 16:57:27.876','smilebogun1@gmail.com','test','테스트',4,'{bcrypt}$2a$10$EuCR2yYTleUc5hLoZ45fZOquSYq.nr2MXPI4AMKv.i1AAkGsQxdzq','1234','2024-02-15 16:57:27.877','07505',''),(5,0,'2024-02-19 17:49:50.546000','2','2024-02-19 17:50:21.736000','KakaoDetailAddr','KAKAO','입양대기','5','수컷','1708332391439_20240118_142510.jpg','완료','말티즈','완료',10,'123','123','강아지','2024-02-19 17:46:31.477','2024-02-19 17:46:31.477','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강서보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000',''),(6,0,'2024-02-19 17:49:54.618000','2','2024-02-19 17:51:52.893000','KakaoDetailAddr','KAKAO','입양대기','5','암컷','1708328877649_Figure_1.png','미완료','잡종','미완료',9,'123','123','고양이','2024-02-19 16:47:57.676','2024-02-19 16:47:57.676','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강서보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000',''),(7,0,'2024-02-20 11:26:52.596000','3','2024-02-23 17:20:17.880000','123','YNI','입양대기','5','수컷','1708338626723_20240118_142510.jpg','완료','말티즈','완료',11,'123','123','강아지','2024-02-19 19:30:26.789','2024-02-19 19:30:26.789',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강서보호소','33','2024-02-15 13:29:46.797','smilebogun1@gmail.com','user','테스트',3,'{bcrypt}$2a$10$X0rPCZ4H1MVIozXCumx1MuBO6R8506tiDdhuxDTUru9aMvALstLqe','010-1234-1234','2024-02-19 21:31:06.86','07505',''),(8,0,'2024-02-20 11:26:56.753000','3','2024-02-24 11:23:41.115000','123','YNI','입양대기','5','암컷','1708338640745_Figure_1.png','미완료','잡종','미완료',12,'123','','고양이','2024-02-19 19:30:40.749','2024-02-19 19:30:40.749',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강서보호소','33','2024-02-15 13:29:46.797','smilebogun1@gmail.com','user','테스트',3,'{bcrypt}$2a$10$X0rPCZ4H1MVIozXCumx1MuBO6R8506tiDdhuxDTUru9aMvALstLqe','010-1234-1234','2024-02-19 21:31:06.86','07505',''),(9,0,'2024-02-24 11:21:09.177000','3','2024-02-26 15:55:55.900000','KakaoDetailAddr','KAKAO','입양대기','5','암컷','41.jpeg','미완료','잡종','미완료',19,'123','123','고양이','2024-02-23 17:00:16.365','2024-02-23 17:00:16.365','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강서보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000',''),(10,2,'2024-02-24 11:21:13.859000','1','2024-02-27 17:43:07.316000','KakaoDetailAddr','KAKAO','입양대기','5','암컷','93.jpeg','미완료','잡종','미완료',16,'123','123','고양이','2024-02-22 16:55:27.809','2024-02-22 16:55:27.809','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강북보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000','2'),(15,4,'2024-02-26 16:05:00.886000','1','2024-02-28 10:25:57.548000','KakaoDetailAddr','KAKAO','입양대기','5','수컷','1708740522642_20240118_142510.jpg','완료','말티즈','완료',24,'123','123','강아지','2024-02-24 11:08:42.648','2024-02-24 11:08:42.648','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강동보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000','2'),(17,1,'2024-02-27 11:14:46.127000','1','2024-02-27 11:15:06.489000','KakaoDetailAddr','KAKAO','입양대기','5','암컷','13.jpeg','미완료','잡종','미완료',14,'123','123','고양이','2024-02-22 16:52:53.589','2024-02-22 16:52:53.589','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강남보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000','2'),(18,1,'2024-02-27 11:14:50.350000','1','2024-02-27 11:15:06.498000','KakaoDetailAddr','KAKAO','입양대기','5','수컷','OIP-1khVvX2oExW0mG6G2Y2WTwAAAA.jpeg','완료','말티즈','완료',15,'123','123','강아지','2024-02-22 16:54:10.619','2024-02-22 16:54:10.619','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강북보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000','2'),(19,1,'2024-02-27 11:14:57.733000','3','2024-02-27 17:39:53.656000','KakaoDetailAddr','KAKAO','입양대기','5','암컷','1708592869483_70.jpeg','미완료','잡종','미완료',18,'123','123','고양이','2024-02-22 18:07:49.49','2024-02-22 18:07:49.49','KakaoPlusAddr','KakaoRoadAddr','ROLE_USER','서울','강서보호소','KakaoAge','2024-02-15 13:20:48.311','qldotjq@naver.com','qldotjq@naver.com','KakaoName',1,'{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoTel','2024-02-15 13:20:48.311','00000','2'),(21,0,'2024-02-27 11:16:55.988000','1','2024-02-27 11:16:55.988000','123','YNI','입양대기','5','수컷','1708740522642_20240118_142510.jpg','완료','말티즈','완료',24,'123','123','강아지','2024-02-24 11:08:42.648','2024-02-26 16:51:10.014',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강동보호소','33','2024-02-20 17:40:56.612','smilebogun1@gmail.com','test2','테스트',7,'{bcrypt}$2a$10$EcyMwINyv52qLpfLxFAf0uap1XmVx7/RxK05f5jeq83YyPTCF5feq','123456','2024-02-20 17:40:56.612','07505','1'),(22,0,'2024-02-27 11:17:08.361000','1','2024-02-27 11:17:08.361000','123','YNI','입양대기','5','수컷','1708740522642_20240118_142510.jpg','완료','말티즈','완료',24,'123','123','강아지','2024-02-24 11:08:42.648','2024-02-26 16:51:10.014',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강동보호소','33','2024-02-20 17:37:19.688','smilebogun1@gmail.com','test1','테스트',6,'{bcrypt}$2a$10$SW4B10q.LpA2YFRU9QA4.OSRtkmKML1ECGIRTaQ1maWjcm5u4OmfK','12345','2024-02-20 17:37:19.688','07505','1'),(23,0,'2024-02-27 11:17:20.508000','1','2024-02-27 11:17:20.508000','123','YNI','입양대기','5','수컷','1708740522642_20240118_142510.jpg','완료','말티즈','완료',24,'123','123','강아지','2024-02-24 11:08:42.648','2024-02-26 16:51:10.014',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강동보호소','33','2024-02-15 16:57:27.876','smilebogun1@gmail.com','test','테스트',4,'{bcrypt}$2a$10$EuCR2yYTleUc5hLoZ45fZOquSYq.nr2MXPI4AMKv.i1AAkGsQxdzq','1234','2024-02-15 16:57:27.877','07505','1'),(24,0,'2024-02-27 17:42:43.726000','1','2024-02-27 17:42:43.726000','123','YNI','입양대기','5','암컷','93.jpeg','미완료','잡종','미완료',16,'123','123','고양이','2024-02-22 16:55:27.809','2024-02-22 16:55:27.809',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강북보호소','33','2024-02-15 13:29:46.797','smilebogun1@gmail.com','user','테스트',3,'{bcrypt}$2a$10$oR52LnnCYmnKCN9EaS8NRu8Eij0aaOiEhTTWAzm43v3zWVH7A4nh.','010-1234-1234','2024-02-27 10:13:10.724','07505','1'),(25,1,'2024-02-28 13:07:33.751000','1','2024-02-28 13:08:36.554000','123','YNI','입양대기','5','암컷','1709092963544_20240118_142510.jpg','미완료','말티즈','미완료',49,'123','123','고양이','2024-02-28 13:02:43.55','2024-02-28 13:02:43.55',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','서울','강동보호소','33','2024-02-15 13:29:46.797','smilebogun1@gmail.com','user','테스트',3,'{bcrypt}$2a$10$oR52LnnCYmnKCN9EaS8NRu8Eij0aaOiEhTTWAzm43v3zWVH7A4nh.','010-1234-1234','2024-02-27 10:13:10.724','07505','2'),(26,1,'2024-02-28 13:08:50.147000','1','2024-02-28 13:09:01.049000','123','YNI','입양대기','5','수컷','1709092703171_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','완료','잡종','완료',34,'집앞','애교가 많음','강아지','2024-02-28 12:58:23.183','2024-02-28 12:58:23.183',' (방화동)','서울 강서구 개화동로 424','ROLE_USER','전남','전남보호소','33','2024-02-15 13:29:46.797','smilebogun1@gmail.com','user','테스트',3,'{bcrypt}$2a$10$oR52LnnCYmnKCN9EaS8NRu8Eij0aaOiEhTTWAzm43v3zWVH7A4nh.','010-1234-1234','2024-02-27 10:13:10.724','07505','2');
/*!40000 ALTER TABLE `adopt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `history_no` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `detail_addr` varchar(100) NOT NULL,
  `o_auth` varchar(255) DEFAULT NULL,
  `pet_adopt` varchar(255) NOT NULL,
  `pet_age` varchar(100) NOT NULL,
  `pet_gender` varchar(255) NOT NULL,
  `pet_image` varchar(255) NOT NULL,
  `pet_inoculation` varchar(255) NOT NULL,
  `pet_name` varchar(255) NOT NULL,
  `pet_neutering` varchar(255) NOT NULL,
  `pet_no` int DEFAULT NULL,
  `pet_place` varchar(255) NOT NULL,
  `pet_remarks` varchar(255) NOT NULL,
  `pet_species` varchar(100) DEFAULT NULL,
  `pet_sysdate` datetime(6) DEFAULT NULL,
  `pet_update` datetime(6) DEFAULT NULL,
  `plus_addr` varchar(100) DEFAULT NULL,
  `road_addr` varchar(100) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `shel_area` varchar(255) NOT NULL,
  `shel_name` varchar(50) NOT NULL,
  `user_age` varchar(10) NOT NULL,
  `user_create_date` datetime(6) DEFAULT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_no` int DEFAULT NULL,
  `user_pw` varchar(200) NOT NULL,
  `user_tel` varchar(20) NOT NULL,
  `zipcode` varchar(5) NOT NULL,
  PRIMARY KEY (`history_no`),
  UNIQUE KEY `UK_fuutexvtx28fs971iq0kbfbmp` (`user_id`),
  UNIQUE KEY `UK_a8v57pb2cib3u11mh5t00c476` (`user_tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `pet_no` int NOT NULL AUTO_INCREMENT,
  `pet_adopt` varchar(255) NOT NULL,
  `pet_age` varchar(100) NOT NULL,
  `pet_gender` varchar(255) NOT NULL,
  `pet_image` varchar(255) NOT NULL,
  `pet_inoculation` varchar(255) NOT NULL,
  `pet_name` varchar(255) NOT NULL,
  `pet_neutering` varchar(255) NOT NULL,
  `pet_place` varchar(255) NOT NULL,
  `pet_remarks` varchar(255) NOT NULL,
  `pet_species` varchar(100) DEFAULT NULL,
  `pet_sysdate` datetime(6) DEFAULT NULL,
  `pet_update` datetime(6) DEFAULT NULL,
  `shel_area` varchar(255) NOT NULL,
  `shel_name` varchar(50) NOT NULL,
  PRIMARY KEY (`pet_no`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (14,'입양대기','5','암컷','13.jpeg','미완료','페르시안','미완료','강동빌라 부근','사나움','고양이','2024-02-22 16:52:53.589000','2024-02-28 12:39:29.993000','서울','강동보호소'),(15,'입양대기','5','수컷','OIP-1khVvX2oExW0mG6G2Y2WTwAAAA.jpeg','완료','푸들','완료','백화점 근처','온순함','강아지','2024-02-22 16:54:10.619000','2024-02-28 12:38:31.404000','서울','강북보호소'),(16,'입양대기','3','암컷','93.jpeg','미완료','샴','미완료','송화초등학교 후문쪽','다리를 다침','고양이','2024-02-22 16:55:27.809000','2024-02-28 12:37:55.020000','서울','강북보호소'),(24,'입양대기','5','암컷','1708740522642_20240118_142510.jpg','완료','말티즈','완료','도레미 놀이터 근처','발목에 상처가 있음','강아지','2024-02-24 11:08:42.648000','2024-02-28 12:36:41.156000','서울','강서보호소'),(27,'입양대기','2개월','암컷','1709091685550_20240118_142510.jpg','미완료','허스키','미완료','휴지공장 사거리','개빠름','강아지','2024-02-28 12:41:25.559000','2024-02-28 12:41:25.559000','경기','경기서부보호소'),(28,'입양대기','3','암컷','1709091773502_20240118_142510.jpg','미완료','웰시코기','미완료','우리집','없음','강아지','2024-02-28 12:42:53.505000','2024-02-28 12:42:53.505000','경기','경기북부보호소'),(29,'입양대기','2','암컷','1709091848898_20240118_142510.jpg','미완료','러시안블루','완료','삼거리','유미를 잘함','고양이','2024-02-28 12:44:08.902000','2024-02-28 12:44:08.902000','강원','강원보호소'),(30,'입양대기','2','수컷','1709091894582_20240118_142510.jpg','미완료','아비시니안','미완료','오거리','없음','고양이','2024-02-28 12:44:54.587000','2024-02-28 12:44:54.587000','충북','충청도보호소'),(31,'입양대기','11','암컷','1709091955059_20240118_142510.jpg','미완료','페르시안','미완료','방화 사거리','배에 큰점이 있음','고양이','2024-02-28 12:45:55.063000','2024-02-28 12:45:55.063000','충북','충청도보호소'),(32,'입양대기','2','암컷','1709092000345_20240118_142510.jpg','미완료','랙돌','완료','한서고등학교 후문','지랄맞음','고양이','2024-02-28 12:46:40.348000','2024-02-28 12:46:40.348000','충남','충남보호소'),(33,'입양대기','2','암컷','1709092108003_20240118_142510.jpg','미완료','스핑크스','완료','초등학교 근처','없음','고양이','2024-02-28 12:48:28.007000','2024-02-28 12:48:28.007000','경북','경상도보호소'),(34,'입양대기','5','수컷','1709092703171_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','완료','잡종','완료','집앞','애교가 많음','강아지','2024-02-28 12:58:23.183000','2024-02-28 12:58:23.183000','전남','전남보호소'),(35,'입양대기','5','암컷','1709092727102_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','미완료','말티즈','미완료','23','123','고양이','2024-02-28 12:58:47.108000','2024-02-28 12:58:47.108000','전북','전라도보호소'),(36,'입양대기','5','수컷','1709092746046_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','완료','페르시안','완료','123','123','강아지','2024-02-28 12:59:06.049000','2024-02-28 12:59:06.049000','경남','경남보호소'),(37,'입양대기','5','수컷','1709092767220_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','완료','잡종','완료','123','123','고양이','2024-02-28 12:59:27.224000','2024-02-28 12:59:27.224000','경남','경남보호소'),(38,'입양대기','5','암컷','1709092785980_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','미완료','샴','미완료','123','123','고양이','2024-02-28 12:59:45.983000','2024-02-28 12:59:45.983000','경북','경상도보호소'),(39,'입양대기','5','수컷','1709092806258_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','완료','말티즈','완료','123','123','강아지','2024-02-28 13:00:06.262000','2024-02-28 14:22:46.479000','충남','충남보호소'),(40,'입양대기','5','수컷','1709092821518_OIP-1eSn_DkdvhsY2h1nFyntvAHaJE.jpeg','완료','푸들','완료','123','123','강아지','2024-02-28 13:00:21.522000','2024-02-28 13:00:21.522000','충북','충청도보호소'),(41,'입양대기','5','수컷','다운로드.jpg','미완료','말티즈','미완료','123','123','강아지','2024-02-28 13:00:34.483000','2024-02-28 14:22:30.163000','강원','강원보호소'),(42,'입양대기','5','암컷','1709097730655_OIP-2KOy2hmC0cAe1-Ubcr2pfwHaHa.jpeg','미완료','말티즈','미완료','123','123','강아지','2024-02-28 13:00:51.010000','2024-02-28 14:22:10.663000','경기','경기서부보호소'),(43,'입양대기','5','암컷','OIP-2KOy2hmC0cAe1-Ubcr2pfwHaHa.jpeg','미완료','말티즈','미완료','123','123','고양이','2024-02-28 13:01:07.708000','2024-02-28 14:22:02.440000','경기','경기북부보호소'),(44,'입양대기','5','암컷','news-p.v1.20231021.f4a65f0ed9b54f45a84d38335176a44d_P1.png','미완료','말티즈','미완료','123','123','고양이','2024-02-28 13:01:23.053000','2024-02-28 14:21:55.708000','경기','경기남부보호소'),(45,'입양대기','5','수컷','6982fdc1054c503af88bdefeeb7c8fa8.jpg','완료','말티즈','완료','1234','234','강아지','2024-02-28 13:01:41.444000','2024-02-28 14:12:41.466000','경기','경기동부보호소'),(46,'입양대기','5','암컷','523.jpeg','미완료','러시안블루','미완료','123','123','고양이','2024-02-28 13:01:57.495000','2024-02-28 14:14:40.867000','충북','충청도보호소'),(47,'입양대기','5','암컷','8f8610825cfcec05c020bb7c62b0716b.jpg','미완료','말티즈','미완료','123','123','고양이','2024-02-28 13:02:13.703000','2024-02-28 14:12:02.151000','서울','강북보호소'),(48,'입양대기','5','수컷','6a4c967c5b14197dd5d2c47424ae8e82.jpg','완료','말티즈','완료','123','123','강아지','2024-02-28 13:02:27.722000','2024-02-28 14:11:54.981000','서울','강남보호소'),(49,'입양대기','5','암컷','_104449495_c32b76d3-5990-4bea-9304-ba818d87be15.jpg','미완료','차우차우','미완료','123','123','고양이','2024-02-28 13:02:43.550000','2024-02-28 14:14:03.793000','서울','강동보호소');
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_no` int NOT NULL AUTO_INCREMENT,
  `post_category` varchar(255) DEFAULT NULL,
  `post_content` varchar(255) DEFAULT NULL,
  `post_image1` longtext,
  `post_image2` varchar(255) DEFAULT NULL,
  `post_image3` varchar(255) DEFAULT NULL,
  `post_indate` datetime(6) DEFAULT NULL,
  `post_temp` int NOT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  `post_view_cnt` int NOT NULL,
  `post_writer` varchar(255) DEFAULT NULL,
  `admin_no` int DEFAULT NULL,
  `user_no` int DEFAULT NULL,
  PRIMARY KEY (`post_no`),
  KEY `FKhtoivkvtw8d7xqmwge6ll5oxp` (`admin_no`),
  KEY `FK7ftrk6ajjmds0cy6ryihq213u` (`user_no`),
  CONSTRAINT `FK7ftrk6ajjmds0cy6ryihq213u` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FKhtoivkvtw8d7xqmwge6ll5oxp` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`)
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (15,'찾습니다','3123',NULL,NULL,NULL,'2024-02-20 16:03:09.868000',0,'12312',1,'테스트',NULL,3),(16,'자유 게시판','123123',NULL,NULL,NULL,'2024-02-21 18:22:10.197000',0,'공지사항1',96,'최고관리자',1,NULL),(17,'자유 게시판','test','0',NULL,NULL,'2024-02-22 12:13:00.315000',0,'test',6,'KakaoName',NULL,1),(18,'자유 게시판','test',NULL,NULL,NULL,'2024-02-22 12:29:03.351000',0,'test',7,'KakaoName',NULL,1),(21,'자유 게시판','123','1708575422475_20240118_142510.jpg',NULL,NULL,'2024-02-22 13:17:02.482000',0,'공지사항2',10,'최고관리자',1,NULL),(22,'자유 게시판','123','0',NULL,NULL,'2024-02-22 13:18:42.958000',0,'123',27,'KakaoName',NULL,1),(23,'자유 게시판','1231','1708579201331_20240118_142510.jpg',NULL,NULL,'2024-02-22 14:20:01.338000',0,'123',11,'테스트',NULL,3),(24,'자유 게시판','156161652222','0',NULL,NULL,'2024-02-22 14:29:03.548000',0,'1231322222',3,'테스트',NULL,3),(25,'자유 게시판','5555','1708579806066_20240118_142510.jpg',NULL,NULL,'2024-02-22 14:30:06.068000',0,'5555',24,'테스트',NULL,3),(26,'자유 게시판','123','1708579868150_20240118_142510.jpg',NULL,NULL,'2024-02-22 14:31:08.154000',0,'공지사항3',17,'최고관리자',1,NULL),(27,'자유 게시판','tt','1708581804681_Figure_1.png',NULL,NULL,'2024-02-22 14:49:34.905000',0,'tt',37,'테스트',NULL,3),(28,'자유 게시판','ff',NULL,NULL,NULL,'2024-02-22 15:06:42.919000',0,'ff',6,'테스트',NULL,3),(29,'자유 게시판','aa11','1708582376040_Figure_1.png',NULL,NULL,'2024-02-22 15:07:01.842000',0,'aa',13,'테스트',NULL,3),(30,'자유 게시판','test','1708582190632_20240118_142510.jpg',NULL,NULL,'2024-02-22 15:09:50.644000',1,'test',0,'테스트',NULL,3),(31,'자유 게시판','123123123','1708582500324_Figure_1.png',NULL,NULL,'2024-02-22 15:14:04.833000',0,'123123123',122,'테스트',NULL,3),(48,'자유 게시판','1231','1708753037590_20240118_142510.jpg',NULL,NULL,'2024-02-24 14:37:05.153000',0,'공지사항4',16,'최고관리자',1,NULL),(55,'자유 게시판','123123','1708755640262_Figure_1.png',NULL,NULL,'2024-02-24 15:19:14.485000',0,'123123',5,'KakaoName',NULL,1),(59,'자유 게시판','123123123','1708931185331_20240118_142510.jpg',NULL,NULL,'2024-02-26 16:06:25.335000',0,'123123123',6,'KakaoName',NULL,1),(61,'자유 게시판','123123',NULL,NULL,NULL,'2024-02-27 10:02:08.164000',0,'123123',4,'테스트',NULL,7),(63,'자유 게시판','333','0',NULL,NULL,'2024-02-27 17:54:30.863000',0,'333',1,'KakaoName',NULL,1),(65,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:12.000000',0,'제목입니당',0,NULL,NULL,3),(66,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:12.000000',0,'제목입니당',0,NULL,NULL,3),(67,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:13.000000',0,'제목입니당',0,NULL,NULL,3),(68,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:13.000000',0,'제목입니당',0,NULL,NULL,3),(69,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:13.000000',0,'제목입니당',0,NULL,NULL,3),(70,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:13.000000',0,'제목입니당',0,NULL,NULL,3),(71,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:13.000000',0,'제목입니당',0,NULL,NULL,3),(72,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:13.000000',0,'제목입니당',0,NULL,NULL,3),(73,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:14.000000',0,'제목입니당',0,NULL,NULL,3),(74,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:14.000000',0,'제목입니당',0,NULL,NULL,3),(75,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:14.000000',0,'제목입니당',0,NULL,NULL,3),(76,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:14.000000',0,'제목입니당',0,NULL,NULL,3),(77,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:14.000000',0,'제목입니당',0,NULL,NULL,3),(78,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:14.000000',0,'제목입니당',0,NULL,NULL,3),(79,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:14.000000',0,'제목입니당',0,NULL,NULL,3),(80,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:15.000000',0,'제목입니당',0,NULL,NULL,3),(81,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:15.000000',0,'제목입니당',0,NULL,NULL,3),(82,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:15.000000',0,'제목입니당',0,NULL,NULL,3),(83,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:15.000000',0,'제목입니당',0,NULL,NULL,3),(84,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:15.000000',0,'제목입니당',0,NULL,NULL,3),(85,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:15.000000',0,'제목입니당',0,NULL,NULL,3),(86,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:15.000000',0,'제목입니당',0,NULL,NULL,3),(87,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:16.000000',0,'제목입니당',0,NULL,NULL,3),(88,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:16.000000',0,'제목입니당',0,NULL,NULL,3),(89,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:16.000000',0,'제목입니당',0,NULL,NULL,3),(90,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:16.000000',0,'제목입니당',0,NULL,NULL,3),(91,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:16.000000',0,'제목입니당',0,NULL,NULL,3),(92,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:16.000000',0,'제목입니당',0,NULL,NULL,3),(93,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:17.000000',0,'제목입니당',0,NULL,NULL,3),(94,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:17.000000',0,'제목입니당',0,NULL,NULL,3),(95,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:17.000000',0,'제목입니당',0,NULL,NULL,3),(96,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:17.000000',0,'제목입니당',0,NULL,NULL,3),(97,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:17.000000',0,'제목입니당',0,NULL,NULL,3),(98,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:20.000000',0,'제목입니당',0,NULL,NULL,3),(99,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:20.000000',0,'제목입니당',0,NULL,NULL,3),(100,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:20.000000',0,'제목입니당',0,NULL,NULL,3),(101,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:20.000000',0,'제목입니당',0,NULL,NULL,3),(102,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:21.000000',0,'제목입니당',0,NULL,NULL,3),(103,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:21.000000',0,'제목입니당',0,NULL,NULL,3),(104,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:21.000000',0,'제목입니당',0,NULL,NULL,3),(105,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:21.000000',0,'제목입니당',0,NULL,NULL,3),(106,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:21.000000',0,'제목입니당',0,NULL,NULL,3),(107,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:21.000000',0,'제목입니당',0,NULL,NULL,3),(108,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:22.000000',0,'제목입니당',0,NULL,NULL,3),(109,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:22.000000',0,'제목입니당',0,NULL,NULL,3),(110,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:22.000000',0,'제목입니당',0,NULL,NULL,3),(111,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:22.000000',0,'제목입니당',0,NULL,NULL,3),(112,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:22.000000',0,'제목입니당',0,NULL,NULL,3),(113,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:22.000000',0,'제목입니당',0,NULL,NULL,3),(114,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:23.000000',0,'제목입니당',0,NULL,NULL,3),(115,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:23.000000',0,'제목입니당',0,NULL,NULL,3),(116,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:23.000000',0,'제목입니당',0,NULL,NULL,3),(117,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:23.000000',0,'제목입니당',0,NULL,NULL,3),(118,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:23.000000',0,'제목입니당',0,NULL,NULL,3),(119,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:23.000000',0,'제목입니당',0,NULL,NULL,3),(120,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:24.000000',0,'제목입니당',0,NULL,NULL,3),(121,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:24.000000',0,'제목입니당',0,NULL,NULL,3),(122,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:24.000000',0,'제목입니당',0,NULL,NULL,3),(123,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:24.000000',0,'제목입니당',0,NULL,NULL,3),(124,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:26.000000',0,'제목입니당',0,NULL,NULL,3),(125,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:26.000000',0,'제목입니당',0,NULL,NULL,3),(126,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:26.000000',0,'제목입니당',0,NULL,NULL,3),(127,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:26.000000',0,'제목입니당',0,NULL,NULL,3),(128,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:26.000000',0,'제목입니당',0,NULL,NULL,3),(129,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(130,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(131,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(132,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(133,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(134,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(135,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(136,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(137,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:27.000000',0,'제목입니당',0,NULL,NULL,3),(138,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:28.000000',0,'제목입니당',0,NULL,NULL,3),(139,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:28.000000',0,'제목입니당',0,NULL,NULL,3),(140,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:28.000000',0,'제목입니당',0,NULL,NULL,3),(141,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:28.000000',0,'제목입니당',0,NULL,NULL,3),(142,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:28.000000',0,'제목입니당',0,NULL,NULL,3),(143,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:28.000000',0,'제목입니당',0,NULL,NULL,3),(144,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:28.000000',0,'제목입니당',0,NULL,NULL,3),(145,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:29.000000',0,'제목입니당',0,NULL,NULL,3),(146,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:29.000000',0,'제목입니당',0,NULL,NULL,3),(147,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:29.000000',0,'제목입니당',0,NULL,NULL,3),(148,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:29.000000',0,'제목입니당',0,NULL,NULL,3),(149,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:29.000000',0,'제목입니당',0,NULL,NULL,3),(150,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:29.000000',0,'제목입니당',0,NULL,NULL,3),(151,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:30.000000',0,'제목입니당',0,NULL,NULL,3),(152,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:30.000000',0,'제목입니당',0,NULL,NULL,3),(153,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:30.000000',0,'제목입니당',0,NULL,NULL,3),(154,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:30.000000',0,'제목입니당',0,NULL,NULL,3),(155,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:30.000000',0,'제목입니당',0,NULL,NULL,3),(156,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:30.000000',0,'제목입니당',0,NULL,NULL,3),(157,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:30.000000',0,'제목입니당',0,NULL,NULL,3),(158,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:31.000000',0,'제목입니당',0,NULL,NULL,3),(159,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:32.000000',0,'제목입니당 ',0,NULL,NULL,3),(160,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:33.000000',0,'제목입니당 ',0,NULL,NULL,3),(161,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:33.000000',0,'제목입니당 ',0,NULL,NULL,3),(162,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:33.000000',0,'제목입니당 ',0,NULL,NULL,3),(163,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:33.000000',0,'제목입니당 ',0,NULL,NULL,3),(164,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:33.000000',0,'제목입니당 ',0,NULL,NULL,3),(165,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:33.000000',0,'제목입니당 ',0,NULL,NULL,3),(166,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:34.000000',0,'제목입니당 ',0,NULL,NULL,3),(167,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:34.000000',0,'제목입니당 ',0,NULL,NULL,3),(168,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:34.000000',0,'제목입니당 ',0,NULL,NULL,3),(169,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:34.000000',0,'제목입니당 ',0,NULL,NULL,3),(170,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:34.000000',0,'제목입니당 ',0,NULL,NULL,3),(171,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:34.000000',0,'제목입니당 ',0,NULL,NULL,3),(172,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:34.000000',0,'제목입니당 ',0,NULL,NULL,3),(173,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:35.000000',0,'제목입니당 ',0,NULL,NULL,3),(174,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:35.000000',0,'제목입니당 ',0,NULL,NULL,3),(175,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:35.000000',0,'제목입니당 ',0,NULL,NULL,3),(176,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:35.000000',0,'제목입니당 ',0,NULL,NULL,3),(177,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:35.000000',0,'제목입니당 ',0,NULL,NULL,3),(178,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:35.000000',0,'제목입니당 ',0,NULL,NULL,3),(179,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:36.000000',0,'제목입니당 ',0,NULL,NULL,3),(180,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:36.000000',0,'제목입니당 ',0,NULL,NULL,3),(181,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:36.000000',0,'제목입니당 ',0,NULL,NULL,3),(182,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:36.000000',0,'제목입니당 ',0,NULL,NULL,3),(183,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:36.000000',0,'제목입니당 ',0,NULL,NULL,3),(184,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:36.000000',0,'제목입니당 ',0,NULL,NULL,3),(185,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:36.000000',0,'제목입니당 ',0,NULL,NULL,3),(186,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:37.000000',0,'제목입니당 ',0,NULL,NULL,3),(187,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:37.000000',0,'제목입니당 ',0,NULL,NULL,3),(188,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:37.000000',0,'제목입니당 ',0,NULL,NULL,3),(189,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:37.000000',0,'제목입니당 ',0,NULL,NULL,3),(190,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:37.000000',0,'제목입니당 ',0,NULL,NULL,3),(191,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:37.000000',0,'제목입니당 ',0,NULL,NULL,3),(192,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:37.000000',0,'제목입니당 ',0,NULL,NULL,3),(193,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:38.000000',0,'제목입니당 ',0,NULL,NULL,3),(194,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:38.000000',0,'제목입니당 ',0,NULL,NULL,3),(195,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:47.000000',0,'제목입니당',0,NULL,NULL,1),(196,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:48.000000',0,'제목입니당',0,NULL,NULL,1),(197,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:48.000000',0,'제목입니당',0,NULL,NULL,1),(198,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:48.000000',0,'제목입니당',0,NULL,NULL,1),(199,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:48.000000',0,'제목입니당',0,NULL,NULL,1),(200,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:48.000000',0,'제목입니당',0,NULL,NULL,1),(201,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:48.000000',0,'제목입니당',0,NULL,NULL,1),(202,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:48.000000',0,'제목입니당',0,NULL,NULL,1),(203,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:49.000000',0,'제목입니당',0,NULL,NULL,1),(204,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:49.000000',0,'제목입니당',0,NULL,NULL,1),(205,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:49.000000',0,'제목입니당',0,NULL,NULL,1),(206,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:49.000000',0,'제목입니당',0,NULL,NULL,1),(207,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:49.000000',0,'제목입니당',0,NULL,NULL,1),(208,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:49.000000',0,'제목입니당',0,NULL,NULL,1),(209,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:49.000000',0,'제목입니당',0,NULL,NULL,1),(210,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:50.000000',0,'제목입니당',0,NULL,NULL,1),(211,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:50.000000',0,'제목입니당',0,NULL,NULL,1),(212,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:50.000000',0,'제목입니당',0,NULL,NULL,1),(213,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:50.000000',0,'제목입니당',0,NULL,NULL,1),(214,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:50.000000',0,'제목입니당',0,NULL,NULL,1),(215,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:50.000000',0,'제목입니당',0,NULL,NULL,1),(216,'자유 게시판','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:51.000000',0,'제목입니당',0,NULL,NULL,1),(217,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:52.000000',0,'제목입니당',0,NULL,NULL,1),(218,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:52.000000',0,'제목입니당',0,NULL,NULL,1),(219,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:52.000000',0,'제목입니당',0,NULL,NULL,1),(220,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:52.000000',0,'제목입니당',0,NULL,NULL,1),(221,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:52.000000',0,'제목입니당',0,NULL,NULL,1),(222,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:52.000000',0,'제목입니당',0,NULL,NULL,1),(223,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:52.000000',0,'제목입니당',0,NULL,NULL,1),(224,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:53.000000',0,'제목입니당',0,NULL,NULL,1),(225,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:53.000000',0,'제목입니당',0,NULL,NULL,1),(226,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:53.000000',0,'제목입니당',0,NULL,NULL,1),(227,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:53.000000',0,'제목입니당',0,NULL,NULL,1),(228,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:53.000000',0,'제목입니당',0,NULL,NULL,1),(229,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:53.000000',0,'제목입니당',0,NULL,NULL,1),(230,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:54.000000',0,'제목입니당',0,NULL,NULL,1),(231,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:54.000000',0,'제목입니당',0,NULL,NULL,1),(232,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:54.000000',0,'제목입니당',0,NULL,NULL,1),(233,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:54.000000',0,'제목입니당',0,NULL,NULL,1),(234,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:54.000000',0,'제목입니당',0,NULL,NULL,1),(235,'찾습니다','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:54.000000',0,'제목입니당',0,NULL,NULL,1),(236,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:55.000000',0,'제목입니당',0,NULL,NULL,1),(237,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:56.000000',0,'제목입니당',0,NULL,NULL,1),(238,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:56.000000',0,'제목입니당',0,NULL,NULL,1),(239,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:56.000000',0,'제목입니당',0,NULL,NULL,1),(240,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:56.000000',0,'제목입니당',0,NULL,NULL,1),(241,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:56.000000',0,'제목입니당',0,NULL,NULL,1),(242,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:56.000000',0,'제목입니당',0,NULL,NULL,1),(243,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:57.000000',0,'제목입니당',0,NULL,NULL,1),(244,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:57.000000',0,'제목입니당',0,NULL,NULL,1),(245,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:57.000000',0,'제목입니당',0,NULL,NULL,1),(246,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:57.000000',0,'제목입니당',0,NULL,NULL,1),(247,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:57.000000',0,'제목입니당',0,NULL,NULL,1),(248,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:57.000000',0,'제목입니당',0,NULL,NULL,1),(249,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:58.000000',0,'제목입니당',0,NULL,NULL,1),(250,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:58.000000',0,'제목입니당',0,NULL,NULL,1),(251,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:58.000000',0,'제목입니당',0,NULL,NULL,1),(252,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:58.000000',0,'제목입니당',0,NULL,NULL,1),(253,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:58.000000',0,'제목입니당',0,NULL,NULL,1),(254,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:58.000000',0,'제목입니당',0,NULL,NULL,1),(255,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:59.000000',0,'제목입니당',0,NULL,NULL,1),(256,'우리 이제 함께에요','내용입니당',NULL,NULL,NULL,'2024-02-28 14:01:59.000000',0,'제목입니당',0,NULL,NULL,1),(257,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:00.000000',0,'제목입니당 ',0,NULL,NULL,1),(258,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:00.000000',0,'제목입니당 ',0,NULL,NULL,1),(259,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:00.000000',0,'제목입니당 ',0,NULL,NULL,1),(260,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:00.000000',0,'제목입니당 ',0,NULL,NULL,1),(261,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:01.000000',0,'제목입니당 ',0,NULL,NULL,1),(262,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:01.000000',0,'제목입니당 ',0,NULL,NULL,1),(263,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:01.000000',0,'제목입니당 ',0,NULL,NULL,1),(264,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:01.000000',0,'제목입니당 ',0,NULL,NULL,1),(265,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:01.000000',0,'제목입니당 ',0,NULL,NULL,1),(266,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:01.000000',0,'제목입니당 ',0,NULL,NULL,1),(267,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:02.000000',0,'제목입니당 ',0,NULL,NULL,1),(268,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:02.000000',0,'제목입니당 ',0,NULL,NULL,1),(269,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:02.000000',0,'제목입니당 ',0,NULL,NULL,1),(270,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:02.000000',0,'제목입니당 ',0,NULL,NULL,1),(271,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:02.000000',0,'제목입니당 ',0,NULL,NULL,1),(272,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:02.000000',0,'제목입니당 ',0,NULL,NULL,1),(273,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:03.000000',0,'제목입니당 ',0,NULL,NULL,1),(274,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:03.000000',0,'제목입니당 ',0,NULL,NULL,1),(275,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:03.000000',0,'제목입니당 ',0,NULL,NULL,1),(276,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:03.000000',0,'제목입니당 ',0,NULL,NULL,1),(277,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:03.000000',0,'제목입니당 ',0,NULL,NULL,1),(278,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:03.000000',0,'제목입니당 ',0,NULL,NULL,1),(279,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:04.000000',0,'제목입니당 ',0,NULL,NULL,1),(280,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:04.000000',0,'제목입니당 ',0,NULL,NULL,1),(281,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:04.000000',0,'제목입니당 ',0,NULL,NULL,1),(282,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:04.000000',0,'제목입니당 ',0,NULL,NULL,1),(283,'너와 나 그리고 우리','내용입니당',NULL,NULL,NULL,'2024-02-28 14:02:04.000000',0,'제목입니당 ',0,NULL,NULL,1),(284,'찾습니다','공지사항1',NULL,NULL,NULL,'2024-02-28 14:04:30.646000',0,'공지사항1',0,'최고관리자',1,NULL),(285,'찾습니다','공지사항2',NULL,NULL,NULL,'2024-02-28 14:04:54.371000',0,'공지사항2',0,'최고관리자',1,NULL),(286,'우리 이제 함께에요','공지사항1',NULL,NULL,NULL,'2024-02-28 14:05:11.259000',0,'공지사항1',0,'최고관리자',1,NULL),(287,'너와 나 그리고 우리','공지사항1',NULL,NULL,NULL,'2024-02-28 14:05:22.675000',0,'공지사항1',0,'최고관리자',1,NULL);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qa`
--

DROP TABLE IF EXISTS `qa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qa` (
  `qa_no` int NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `qa_content` varchar(255) DEFAULT NULL,
  `qa_indate` datetime(6) DEFAULT NULL,
  `qa_title` varchar(255) DEFAULT NULL,
  `user_no` int DEFAULT NULL,
  PRIMARY KEY (`qa_no`),
  KEY `FKq5u859ou7lncpvf2u01pkb1es` (`user_no`),
  CONSTRAINT `FKq5u859ou7lncpvf2u01pkb1es` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qa`
--

LOCK TABLES `qa` WRITE;
/*!40000 ALTER TABLE `qa` DISABLE KEYS */;
INSERT INTO `qa` VALUES (14,'답변 대기 중','123123123','2024-02-24 15:48:25.798000','123123123123',1),(23,'답변완료','123123','2024-02-26 19:48:01.536000','123123',1),(24,'답변완료','12313','2024-02-26 19:48:04.774000','123123',1),(26,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:30.000000','제목입니다2',3),(27,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:31.000000','제목입니다2',3),(28,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:31.000000','제목입니다2',3),(29,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:31.000000','제목입니다2',3),(30,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:32.000000','제목입니다2',3),(31,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:32.000000','제목입니다2',3),(32,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:32.000000','제목입니다2',3),(33,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:32.000000','제목입니다2',3),(34,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:32.000000','제목입니다2',3),(35,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:32.000000','제목입니다2',3),(36,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:33.000000','제목입니다2',3),(37,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:33.000000','제목입니다2',3),(38,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:33.000000','제목입니다2',3),(39,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:33.000000','제목입니다2',3),(40,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:33.000000','제목입니다2',3),(41,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:33.000000','제목입니다2',3),(42,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:34.000000','제목입니다2',3),(43,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:34.000000','제목입니다2',3),(44,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:34.000000','제목입니다2',3),(45,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:34.000000','제목입니다2',3),(46,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:42.000000','제목입니다2',1),(47,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:42.000000','제목입니다2',1),(48,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:43.000000','제목입니다2',1),(49,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:43.000000','제목입니다2',1),(50,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:43.000000','제목입니다2',1),(51,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:43.000000','제목입니다2',1),(52,'답변완료','내용입니다','2024-02-28 14:00:43.000000','제목입니다2',1),(53,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:43.000000','제목입니다2',1),(54,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:44.000000','제목입니다2',1),(55,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:44.000000','제목입니다2',1),(56,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:44.000000','제목입니다2',1),(57,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:44.000000','제목입니다2',1),(58,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:44.000000','제목입니다2',1),(59,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:45.000000','제목입니다2',1),(60,'답변 등록 대기 중','내용입니다','2024-02-28 14:00:45.000000','제목입니다2',1),(61,'답변완료','내용입니다','2024-02-28 14:00:45.000000','제목입니다2',1),(62,'답변완료','내용입니다','2024-02-28 14:00:45.000000','제목입니다2',1);
/*!40000 ALTER TABLE `qa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qareply`
--

DROP TABLE IF EXISTS `qareply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qareply` (
  `qar_no` int NOT NULL AUTO_INCREMENT,
  `qareply_content` varchar(255) DEFAULT NULL,
  `qareply_update` datetime(6) DEFAULT NULL,
  `admin_no` int DEFAULT NULL,
  `qa_no` int DEFAULT NULL,
  `user_no` int DEFAULT NULL,
  PRIMARY KEY (`qar_no`),
  KEY `FK9fq22a70fn00h711tsxqmk1q2` (`admin_no`),
  KEY `FKbp3j25gfih8t7ewdww7cgwbm2` (`qa_no`),
  KEY `FKnq58o1ksfx2ypmtm559lf2hwx` (`user_no`),
  CONSTRAINT `FK9fq22a70fn00h711tsxqmk1q2` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`),
  CONSTRAINT `FKbp3j25gfih8t7ewdww7cgwbm2` FOREIGN KEY (`qa_no`) REFERENCES `qa` (`qa_no`),
  CONSTRAINT `FKnq58o1ksfx2ypmtm559lf2hwx` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qareply`
--

LOCK TABLES `qareply` WRITE;
/*!40000 ALTER TABLE `qareply` DISABLE KEYS */;
INSERT INTO `qareply` VALUES (37,'1231233333','2024-02-26 15:55:13.480000',1,14,NULL),(40,'sadf123123','2024-02-26 19:54:37.040000',NULL,14,1),(41,'12313','2024-02-26 20:22:12.300000',1,24,NULL),(42,'123','2024-02-27 17:53:30.659000',1,23,NULL),(43,'답변답변답변','2024-02-28 14:08:54.330000',1,52,NULL),(44,'답변','2024-02-28 14:09:06.573000',1,62,NULL),(45,'답변답변','2024-02-28 14:09:21.224000',1,61,NULL);
/*!40000 ALTER TABLE `qareply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `reply_no` int NOT NULL AUTO_INCREMENT,
  `reply_content` varchar(255) DEFAULT NULL,
  `reply_update` datetime(6) DEFAULT NULL,
  `post_no` int DEFAULT NULL,
  `user_no` int DEFAULT NULL,
  `admin_no` int DEFAULT NULL,
  PRIMARY KEY (`reply_no`),
  KEY `FKsckrvyod7g7w5f2durxy3dkxf` (`post_no`),
  KEY `FKebqkbvyh1sqihitd2ppvrv797` (`user_no`),
  KEY `FK8yluhp7taxnfa7sjg6bp37x8y` (`admin_no`),
  CONSTRAINT `FK8yluhp7taxnfa7sjg6bp37x8y` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`),
  CONSTRAINT `FKebqkbvyh1sqihitd2ppvrv797` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FKsckrvyod7g7w5f2durxy3dkxf` FOREIGN KEY (`post_no`) REFERENCES `post` (`post_no`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (11,'123','2024-02-22 13:09:49.598000',16,NULL,1),(14,'123123','2024-02-22 13:16:53.953000',18,NULL,1),(15,'123','2024-02-22 14:16:35.020000',22,3,NULL),(16,'123123423','2024-02-22 14:18:58.270000',16,3,NULL),(17,'123123','2024-02-22 14:19:08.092000',16,3,NULL),(18,'234234','2024-02-22 14:19:28.301000',22,3,NULL),(19,'123123','2024-02-22 14:20:35.059000',23,3,NULL),(20,'1231','2024-02-22 14:21:47.934000',16,1,NULL),(21,'123213123','2024-02-22 14:21:56.868000',16,1,NULL),(22,'tetst','2024-02-22 14:39:58.133000',26,3,NULL),(23,'testtest','2024-02-22 14:42:31.348000',25,3,NULL),(24,'123123123123','2024-02-22 15:15:30.683000',31,1,NULL),(26,'asdf','2024-02-23 12:48:13.910000',28,3,NULL),(27,'123123123','2024-02-24 15:03:22.645000',31,NULL,1),(28,'123123123','2024-02-24 15:05:31.083000',31,NULL,1),(30,'123123123','2024-02-24 15:06:58.581000',16,1,NULL),(37,'123123','2024-02-26 19:31:32.161000',21,1,NULL),(38,'123232','2024-02-26 19:31:34.912000',21,1,NULL),(39,'123213','2024-02-26 20:06:46.588000',25,NULL,1),(40,'23213','2024-02-26 20:06:49.888000',25,NULL,1),(41,'123123','2024-02-27 10:01:57.509000',59,7,NULL),(42,'123123','2024-02-27 10:02:21.134000',61,7,NULL);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc`
--

DROP TABLE IF EXISTS `sc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc` (
  `sc_no` int NOT NULL AUTO_INCREMENT,
  `scindate` datetime(6) DEFAULT NULL,
  `sc_category` varchar(255) DEFAULT NULL,
  `sc_content` varchar(255) DEFAULT NULL,
  `sc_image` varchar(255) DEFAULT NULL,
  `sc_title` varchar(255) DEFAULT NULL,
  `admin_no` int DEFAULT NULL,
  PRIMARY KEY (`sc_no`),
  KEY `FK2bl0ygmik4x00hla5dgkmmmkj` (`admin_no`),
  CONSTRAINT `FK2bl0ygmik4x00hla5dgkmmmkj` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc`
--

LOCK TABLES `sc` WRITE;
/*!40000 ALTER TABLE `sc` DISABLE KEYS */;
INSERT INTO `sc` VALUES (2,'2024-02-19 20:55:38.021000',NULL,'사이트 공지사항4',NULL,'사이트 공지사항4',2),(4,'2024-02-22 15:21:15.318000',NULL,'사이트 공지사항3',NULL,'사이트 공지사항3',1),(6,'2024-02-22 15:21:33.763000',NULL,'사이트 공지사항2','1708583272818_Figure_1.png','사이트 공지사항2',1),(10,'2024-02-27 17:52:15.370000',NULL,'사이트 공지사항1',NULL,'사이트 공지사항1',1),(11,'2024-02-28 14:02:29.000000',NULL,'사이트 공지사항5',NULL,'사이트 공지사항5',1);
/*!40000 ALTER TABLE `sc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelter`
--

DROP TABLE IF EXISTS `shelter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shelter` (
  `shelter_no` int NOT NULL AUTO_INCREMENT,
  `shel_address` varchar(255) NOT NULL,
  `shel_area` varchar(100) DEFAULT NULL,
  `shel_detail_address` varchar(100) NOT NULL,
  `shel_name` varchar(50) NOT NULL,
  `shel_postcode` varchar(255) NOT NULL,
  `shel_sysdate` datetime(6) DEFAULT NULL,
  `shel_tel` varchar(255) NOT NULL,
  PRIMARY KEY (`shelter_no`),
  UNIQUE KEY `UK_dtavq0tscmrb8u2mx5bpe7y2b` (`shel_name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelter`
--

LOCK TABLES `shelter` WRITE;
/*!40000 ALTER TABLE `shelter` DISABLE KEYS */;
INSERT INTO `shelter` VALUES (2,'서울 강서구 개화동로 424','서울','123','강서보호소','07505','2024-02-15 13:16:21.542000','010-1234-1234'),(3,'서울 강북구 덕릉로 88','서울','123123','강북보호소','01128','2024-02-22 16:48:11.538000','010-1234-1234'),(4,'서울 서초구 강남대로 475','서울','123','강남보호소','06541','2024-02-22 16:49:13.111000','010-1234-1234'),(5,'서울 강서구 개화동로 424','서울','123','강동보호소','07505','2024-02-24 10:49:44.855000','010-1234-1234'),(9,'서울 강동구 상일동 463-5','경기','123123','경기서부보호소','05291','2024-02-26 15:50:10.874000','010-1234-1234'),(11,'경기 남양주시 다산동 4114-18','경기','123','경기북부보호소','12259','2024-02-28 11:48:31.384000','010-1234-1234'),(12,'경기 광명시 가락골길 7','경기','123','경기남부보호소','14302','2024-02-28 11:50:08.429000','010-1234-1234'),(13,'서울 강동구 상일동 463-5','경기','123','경기동부보호소','05291','2024-02-28 11:51:52.479000','010-1234-1234'),(14,'강원특별자치도 속초시 골말길 5','강원','123','강원보호소','24904','2024-02-28 12:03:45.031000','010-1234-1234'),(15,'충북 영동군 학산면 서곡길 16','충북','123','충청도보호소','29166','2024-02-28 12:04:46.607000','010-1234-1234'),(16,'전북특별자치도 전주시 덕진구 가련산로 5','전북','123','전라도보호소','54891','2024-02-28 12:06:58.622000','010-1234-1234'),(17,'부산 해운대구 APEC로 17','경북','123','경상도보호소','48060','2024-02-28 12:07:32.509000','010-1234-1234'),(18,'충남 서산시 갈산매수길 10','충남','모모아파트','충남보호소','31963','2024-02-28 12:28:36.457000','010-5252-5151'),(19,'전남 순천시 가곡길 2','전남','모모빌라','전남보호소','57923','2024-02-28 12:29:14.183000','01052420101'),(20,'경남 함안군 칠북면 화천리 136-39','경남','123','경남보호소','52006','2024-02-28 12:57:40.507000','010-1234-1234'),(21,'서울 용산구 한강대로 102','서울','1212','서울역보호소2','01234','2024-02-28 13:59:14.000000','02-2152-1324'),(22,'서울 강동구 상일동 463-3','경기도','1212','경기도보호소2','58421','2024-02-28 13:59:16.000000','123-123'),(23,'강원특별자치도 강릉시 가작로 8','강원도','3321','강원도보호소2','65433','2024-02-28 13:59:17.000000','342-231'),(24,'서울 금천구 금하로 732-5','충청북도','3321','충척북도보호소2','432122','2024-02-28 13:59:20.000000','06-5566-555'),(25,'서울 성동구 고산자로18길 8-1','충청남도','3321','충척남도보호소2','212541','2024-02-28 13:59:21.000000','07-232-235'),(26,'경북 문경시 가은읍 가은공단길 17','경상북도','21533','경상북도보호소2','652342','2024-02-28 13:59:22.000000','08-2152-1324'),(27,'경남 창원시 성산구 가양로 12','경상남도','1212','경상남도보호소2','123543','2024-02-28 13:59:23.000000','09-2152-1324'),(28,'전북특별자치도 고창군 고수면 가협길 32','전라북도보호소','87676','전라북도보호소2','37644','2024-02-28 13:59:26.000000','10-4543-4543'),(29,'전남 순천시 가곡길 7','전라남도','3321','전라남도보호소2','4534543','2024-02-28 13:59:28.000000','11-2152-1324');
/*!40000 ALTER TABLE `shelter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `test_id` varchar(20) NOT NULL,
  `test_pw` varchar(100) NOT NULL,
  `test_name` varchar(20) NOT NULL,
  `test_email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES ('test','1234','홍길동','test@gmail.com');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_no` int NOT NULL AUTO_INCREMENT,
  `age` varchar(10) NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `detail_addr` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `o_auth` varchar(255) DEFAULT NULL,
  `plus_addr` varchar(100) DEFAULT NULL,
  `pw` varchar(200) NOT NULL,
  `road_addr` varchar(100) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `tel` varchar(20) NOT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `zipcode` varchar(5) NOT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `UK_6jvqtxgs6xvh0h0t261hurgqo` (`id`),
  UNIQUE KEY `UK_nekwtc70sk6c2dofve0axwnhb` (`tel`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'KakaoAge','2024-02-15 13:20:48.311000','KakaoDetailAddr','qldotjq@naver.com','qldotjq@naver.com','KakaoName','KAKAO','KakaoPlusAddr','{bcrypt}$2a$10$dBr81Yrgcy2wV9jBHXz1v.99ylB53pfprLoPMRgR0T5HMcpoeSeRy','KakaoRoadAddr','ROLE_USER','KakaoTel','2024-02-15 13:20:48.311000','00000'),(3,'33','2024-02-15 13:29:46.797000','123','smilebogun1@gmail.com','user','테스트','YNI',' (방화동)','{bcrypt}$2a$10$oR52LnnCYmnKCN9EaS8NRu8Eij0aaOiEhTTWAzm43v3zWVH7A4nh.','서울 강서구 개화동로 424','ROLE_USER','010-1234-1234','2024-02-27 10:13:10.724000','07505'),(4,'33','2024-02-15 16:57:27.876000','123','smilebogun1@gmail.com','test','테스트','YNI',' (방화동)','{bcrypt}$2a$10$EuCR2yYTleUc5hLoZ45fZOquSYq.nr2MXPI4AMKv.i1AAkGsQxdzq','서울 강서구 개화동로 424','ROLE_USER','1234','2024-02-15 16:57:27.877000','07505'),(6,'33','2024-02-20 17:37:19.688000','123','smilebogun1@gmail.com','test1','테스트','YNI',' (방화동)','{bcrypt}$2a$10$SW4B10q.LpA2YFRU9QA4.OSRtkmKML1ECGIRTaQ1maWjcm5u4OmfK','서울 강서구 개화동로 424','ROLE_USER','12345','2024-02-20 17:37:19.688000','07505'),(7,'33','2024-02-20 17:40:56.612000','123','smilebogun1@gmail.com','test2','테스트','YNI',' (방화동)','{bcrypt}$2a$10$EcyMwINyv52qLpfLxFAf0uap1XmVx7/RxK05f5jeq83YyPTCF5feq','서울 강서구 개화동로 424','ROLE_USER','123456','2024-02-20 17:40:56.612000','07505'),(19,'33','2024-02-28 12:10:04.726000','123','smilebogun1@gmail.com','test3','테스트','YNI',' (방화동)','{bcrypt}$2a$10$wwtx6FAWXgzSuDaU3Ff1P.Hm06vLQgLzimM45ZY.HKCnqVgWSh2N6','서울 강서구 개화동로 424','ROLE_USER','1234567','2024-02-28 12:10:04.726000','07505'),(20,'33','2024-02-28 12:10:43.733000','123','smilebogun1@gmail.com','test4','테스트','YNI',' (방화동)','{bcrypt}$2a$10$9DSfmG7o8jR55upprzM6m.rZpdpsAm3zC/k15t0rC5SvpduF6aez2','서울 강서구 개화동로 424','ROLE_USER','12345678','2024-02-28 12:10:43.733000','07505'),(21,'33','2024-02-28 12:11:37.142000','88340e','smilebogun1@gmail.com','test5','테스트','YNI',' (방화동)','{bcrypt}$2a$10$QKz5lWtFmC3FkonIhhamL.2B.Gc.nrajgEO2bxlPwHp3fJ/WUNS/a','서울 강서구 개화동로 424','ROLE_USER','123456789','2024-02-28 12:11:37.142000','07505'),(22,'33','2024-02-28 12:12:36.083000','123','smilebogun1@gmail.com','test6','테스트','YNI',' (방화동)','{bcrypt}$2a$10$EF.VtCtz2oKZlYwPcc5sCeBOJWezfPhOU7YMHeOppomtx8Ia4oLNq','서울 강서구 개화동로 424','ROLE_USER','1122','2024-02-28 12:12:36.083000','07505'),(23,'33','2024-02-28 12:18:39.741000','123','smilebogun1@gmail.com','test7','테스트','YNI',' (방화동)','{bcrypt}$2a$10$ywR3ju6WFMdvOaWDDWjyYeoZ.kVpKLVftCyAW4ae6plFD7krkrEYK','서울 강서구 개화동로 424','ROLE_USER','112233','2024-02-28 12:18:39.741000','07505'),(24,'33','2024-02-28 12:19:31.355000','123','smilebogun1@gmail.com','test8','테스트','YNI',' (방화동)','{bcrypt}$2a$10$DRjP5vkd/dxRARcuBBOJH.WbjFK5dte8l7jNgqtYsdW/mWR3pYzCO','서울 강서구 개화동로 424','ROLE_USER','1123344','2024-02-28 12:19:31.355000','07505');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-28 14:25:08
