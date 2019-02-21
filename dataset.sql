-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: hs
-- ------------------------------------------------------
-- Server version	5.6.11

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
-- Table structure for table `administrator_t`
--

DROP TABLE IF EXISTS `administrator_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator_t` (
  `ID` varchar(50) NOT NULL COMMENT '管理员ID',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `cx` varchar(50) NOT NULL COMMENT '管理员类型：超级管理员、普通管理员...',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `name_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator_t`
--

LOCK TABLES `administrator_t` WRITE;
/*!40000 ALTER TABLE `administrator_t` DISABLE KEYS */;
INSERT INTO `administrator_t` VALUES ('admin','admin','super','2015-07-01 08:08:08'),('martin','martin','normal','2015-07-01 18:18:18');
/*!40000 ALTER TABLE `administrator_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attence_t`
--

DROP TABLE IF EXISTS `attence_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attence_t` (
  `ID` varchar(50) NOT NULL COMMENT '员工ID',
  `date` date NOT NULL COMMENT '考勤日期',
  `time1` time DEFAULT '00:00:00' COMMENT '到岗时间',
  `time2` time DEFAULT '00:00:00',
  `morninggps` varchar(50) NOT NULL DEFAULT '0' COMMENT 'GPS经纬度',
  `ismorningcheck` tinyint(10) NOT NULL DEFAULT '0',
  `eveninggps` varchar(50) NOT NULL DEFAULT '0',
  `iseveningcheck` tinyint(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`,`date`),
  UNIQUE KEY `ID` (`ID`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工考勤表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attence_t`
--

LOCK TABLES `attence_t` WRITE;
/*!40000 ALTER TABLE `attence_t` DISABLE KEYS */;
INSERT INTO `attence_t` VALUES ('040001','2016-05-31','23:44:59','23:45:26','120.437346&36.114783',1,'120.437346&36.114783',1),('040001','2016-06-01','23:47:39','23:47:42','120.43698&36.115117',1,'120.43698&36.115117',1),('040001','2016-06-02','00:00:00','00:00:00','0',0,'0',0),('040001','2016-06-03','15:42:27','15:42:32','0.0&0.0',1,'0.0&0.0',1),('040001','2016-06-04','00:00:00','00:00:00','0',0,'0',0),('040001','2016-06-05','00:00:00','00:00:00','0',0,'0',0),('040001','2016-06-06','00:00:00','00:00:00','0',0,'0',0),('040001','2016-06-07','00:00:00','00:00:00','0',0,'0',0),('050001','2016-06-02','00:00:00','00:00:00','0',0,'0',0),('050001','2016-06-03','16:15:15','16:15:19','0.0&0.0',1,'0.0&0.0',1),('050001','2016-06-04','00:00:00','00:00:00','0',0,'0',0),('050001','2016-06-05','00:00:00','00:00:00','0',0,'0',0),('050001','2016-06-06','16:32:18','16:32:22','0.0&0.0',1,'0.0&0.0',1),('050001','2016-06-07','00:00:00','00:00:00','0',0,'0',0),('050002','2016-06-07','00:00:00','00:00:00','0',0,'0',0);
/*!40000 ALTER TABLE `attence_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competinggoods_t`
--

DROP TABLE IF EXISTS `competinggoods_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competinggoods_t` (
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `price` varchar(50) DEFAULT NULL COMMENT '价格',
  `picPath` varchar(50) DEFAULT NULL COMMENT '图片存储路径',
  `reporterID` varchar(50) NOT NULL DEFAULT '' COMMENT '上报人(销售人员)ID',
  `priceDate` date NOT NULL DEFAULT '0000-00-00' COMMENT '价格更新日期',
  `hsGoodModel1` varchar(50) DEFAULT NULL COMMENT '所竞争的海信产品型号1',
  `hsGoodModel2` varchar(50) DEFAULT NULL COMMENT '所竞争的海信产品型号2',
  `hsGoodModel3` varchar(50) DEFAULT NULL COMMENT '所竞争的海信产品型号3',
  `feature` varchar(200) DEFAULT NULL COMMENT '商品特征',
  PRIMARY KEY (`brand`,`model`,`reporterID`,`priceDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competinggoods_t`
--

LOCK TABLES `competinggoods_t` WRITE;
/*!40000 ALTER TABLE `competinggoods_t` DISABLE KEYS */;
INSERT INTO `competinggoods_t` VALUES ('Apple','iPhone6S','Mobile','5000','upload/1456578392169.jpg','050001','2016-02-22','HS-X8T','HS-U8','HS-X8T','feature1\r\nfeature2\r\nfeature3\r\nfeature4\r\nfeature5\r\nfeature6'),('hjk','sry','Mobile','2600','upload/hjksry','050001','2016-06-06',NULL,NULL,NULL,NULL),('Huawei','P7','Mobile','3000','upload/1456578412220.jpg','050002','2015-07-01','HS-X86','HS-K8','HS-U8','1111\r\n2222\r\n3333\r\n4444\r\n5555\r\n6666'),('Huawei','P8','Mobile','5500','upload/huaweip8','050001','2016-06-03',NULL,NULL,NULL,'gggg'),('hvd','fdh','Mobile','5000','upload/hvdfdh','050001','2016-06-06',NULL,NULL,NULL,NULL),('ii','ii','Mobile','22','upload/iiii','050001','2016-06-03',NULL,NULL,NULL,'uuuujjjj'),('oo','oo','Mobile','3200','upload/oooo','050001','2016-06-05',NULL,NULL,NULL,NULL),('tt','tt','Mobile','2300','upload/tttt','050001','2016-06-05',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `competinggoods_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_t`
--

DROP TABLE IF EXISTS `employee_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_t` (
  `ID` varchar(50) NOT NULL COMMENT '员工ID',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `realName` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `paperType` varchar(50) DEFAULT NULL COMMENT '证件类型',
  `paperNo` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `level` int(11) NOT NULL COMMENT '层级',
  `reporterID` varchar(50) NOT NULL COMMENT '所属直接上级ID',
  `secondMgrID` varchar(50) NOT NULL COMMENT '所属二级销售经理ID',
  `firstMgrID` varchar(50) NOT NULL COMMENT '所属一级销售经理ID',
  `directorID` varchar(50) NOT NULL COMMENT '所属销售总监ID',
  `region` varchar(50) NOT NULL COMMENT '所属区域',
  `storeID` varchar(50) DEFAULT NULL COMMENT '所属卖场ID，只对底层销售人员有效',
  `ownedGoodsType` varchar(50) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_t`
--

LOCK TABLES `employee_t` WRITE;
/*!40000 ALTER TABLE `employee_t` DISABLE KEYS */;
INSERT INTO `employee_t` VALUES ('010001','111111','Terry Wang','M','1980-01-08','ID card','370202198001086666','86-13905328888','wangyidong@hisense.com',1,'NA','NA','NA','NA','All Regions','NA','NA',NULL),('020001','111111','Martin Yan','M','1979-01-01','ID Card','370201197901018888','86-13905326666','yanchangzhong@hisense.com',2,'010001','NA','NA','NA','Jordan','NA','NA',NULL),('020002','111111','Victor','M','1978-02-01','ID card','3333','3333','victor@hisense.com',2,'010001','NA','NA','NA','East Egypt','NA','NA',NULL),('030001','111111','Tom','M','1982-08-01','ID Card','370202198208016666','86-13905329999','smgk@163.com',3,'020001','NA','NA','020001','Jordan','NA','NA',NULL),('040001','111111','Jerry','F','1990-08-01','ID Card','370202199008016666','86-13905322222','jerry@hisene.com',4,'030001','NA','030001','020001','Jordan','NA','NA',NULL),('040002','111111','Sean','M','2015-08-05','ID Card','111111111111','86-13805326666','sean@hisense.com',4,'030001','NA','030001','020001','Jordan','NA','NA',NULL),('040003','111111','Klaus','M','1979-02-01','ID card','2222','2222','klaus@hisense.com',4,'030001','NA','030001','020001','East Egypt','NA','NA',NULL),('050001','111111','Lisa','F','1995-08-01','Passport','1111111111111111','86-13905324444','yang@hisense.com',5,'040001','040001','030001','020001','Jordan','maidelong','Mobile',NULL),('050002','111111','Luci','F','2015-08-01','ID Card','111111111111','86-13805328888','luci@hisense.com',5,'040001','040001','030001','020001','Jordan','maikaile','TV',NULL),('050003','111111','Sean','M','2015-08-05','ID card','111111111111','86-13805327777','ben@hisense.com',5,'040002','040002','030001','020001','Jordan','maikaile','Mobile',NULL),('050004','111111','Alice','F','1990-02-08','ID card','1111','1111','alice@hisense.com',5,'040002','040002','030001','020001','East Egypt','maikaile','Air-Conditioner',NULL);
/*!40000 ALTER TABLE `employee_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeelevel_t`
--

DROP TABLE IF EXISTS `employeelevel_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeelevel_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(50) NOT NULL COMMENT '层级(对应权限)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='员工层级类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeelevel_t`
--

LOCK TABLES `employeelevel_t` WRITE;
/*!40000 ALTER TABLE `employeelevel_t` DISABLE KEYS */;
INSERT INTO `employeelevel_t` VALUES (1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5');
/*!40000 ALTER TABLE `employeelevel_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeregion_t`
--

DROP TABLE IF EXISTS `employeeregion_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeeregion_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region` varchar(50) NOT NULL COMMENT '地区',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='员工区域类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeregion_t`
--

LOCK TABLES `employeeregion_t` WRITE;
/*!40000 ALTER TABLE `employeeregion_t` DISABLE KEYS */;
INSERT INTO `employeeregion_t` VALUES (1,'UAE'),(2,'Jordan'),(3,'East Egypt'),(4,'West Egypt');
/*!40000 ALTER TABLE `employeeregion_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_t`
--

DROP TABLE IF EXISTS `feedback_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback_t` (
  `ID` varchar(30) NOT NULL,
  `time` datetime NOT NULL,
  `content` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_t`
--

LOCK TABLES `feedback_t` WRITE;
/*!40000 ALTER TABLE `feedback_t` DISABLE KEYS */;
INSERT INTO `feedback_t` VALUES ('040001','2016-05-25 00:00:00','helped me ');
/*!40000 ALTER TABLE `feedback_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_t`
--

DROP TABLE IF EXISTS `goods_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_t` (
  `internalModel` varchar(50) NOT NULL COMMENT '内部型号',
  `model` varchar(50) DEFAULT NULL COMMENT '型号',
  `type` varchar(50) DEFAULT NULL,
  `configInfo` varchar(50) DEFAULT NULL COMMENT '配置信息',
  `region` varchar(50) DEFAULT NULL COMMENT '区域',
  `picPath` varchar(50) DEFAULT NULL COMMENT '图片存储路径',
  PRIMARY KEY (`internalModel`),
  UNIQUE KEY `internalModel_UNIQUE` (`internalModel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_t`
--

LOCK TABLES `goods_t` WRITE;
/*!40000 ALTER TABLE `goods_t` DISABLE KEYS */;
INSERT INTO `goods_t` VALUES ('AIR100','HS-A8','Air-Conditioner','none','Jordan','upload/1456575548210.jpg'),('LS8230','HS-K8','Mobile','','East Egypt','upload/1456575290720.jpg'),('LT622','HS-X8T','Mobile','none','Jordan','upload/1456575610158.jpg');
/*!40000 ALTER TABLE `goods_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goodstype_t`
--

DROP TABLE IF EXISTS `goodstype_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goodstype_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goodstype_t`
--

LOCK TABLES `goodstype_t` WRITE;
/*!40000 ALTER TABLE `goodstype_t` DISABLE KEYS */;
INSERT INTO `goodstype_t` VALUES (7,'Mobile'),(8,'TV'),(9,'Air-Conditioner'),(10,'Ice Box'),(11,'Washer');
/*!40000 ALTER TABLE `goodstype_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_t`
--

DROP TABLE IF EXISTS `message_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_t` (
  `senderID` varchar(50) NOT NULL COMMENT '主题发起人ID',
  `receiverID` varchar(50) NOT NULL COMMENT '收信人ID',
  `title` varchar(50) NOT NULL COMMENT '主题',
  `content` varchar(50) DEFAULT NULL COMMENT '正文',
  `readFlag` tinyint(4) NOT NULL COMMENT '已读标志',
  `urgentFlag` tinyint(4) NOT NULL COMMENT '紧急标志',
  `topFlag` tinyint(4) NOT NULL COMMENT '置顶标志',
  `broadcastFlag` tinyint(4) NOT NULL COMMENT '群发/站内广播通告标志：T群发、F私信',
  `addtime` datetime NOT NULL COMMENT '主题发布时间',
  PRIMARY KEY (`senderID`,`receiverID`,`title`,`addtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='留言板消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_t`
--

LOCK TABLES `message_t` WRITE;
/*!40000 ALTER TABLE `message_t` DISABLE KEYS */;
INSERT INTO `message_t` VALUES ('terry','ALL','Hello','This is Terry.',0,0,0,1,'2015-08-17 23:37:25');
/*!40000 ALTER TABLE `message_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messagereply_t`
--

DROP TABLE IF EXISTS `messagereply_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messagereply_t` (
  `senderID` varchar(50) NOT NULL COMMENT '主题发起人ID',
  `receiverID` varchar(50) NOT NULL COMMENT '接收人ID',
  `title` varchar(50) NOT NULL COMMENT '主题',
  `titleTime` datetime NOT NULL COMMENT '主题发布时间',
  `replyerID` varchar(50) NOT NULL COMMENT '回复人ID',
  `content` varchar(50) NOT NULL COMMENT '回复正文',
  `addtime` datetime NOT NULL COMMENT '回复时间',
  PRIMARY KEY (`senderID`,`receiverID`,`title`,`titleTime`,`replyerID`,`addtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='留言板回复消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messagereply_t`
--

LOCK TABLES `messagereply_t` WRITE;
/*!40000 ALTER TABLE `messagereply_t` DISABLE KEYS */;
INSERT INTO `messagereply_t` VALUES ('terry','ALL','Hello','2015-08-17 23:37:25','050001','Hi Terry, I just want to complain.','2016-03-01 22:35:40');
/*!40000 ALTER TABLE `messagereply_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soldgoods_t`
--

DROP TABLE IF EXISTS `soldgoods_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soldgoods_t` (
  `imei` varchar(50) NOT NULL COMMENT '唯一识别号',
  `model` varchar(50) NOT NULL COMMENT '型号',
  `type` varchar(50) DEFAULT NULL COMMENT '商品类型',
  `sellerID` varchar(50) NOT NULL COMMENT '销售人员ID',
  `soldDate` date NOT NULL COMMENT '销售日期',
  `soldPrice` varchar(50) NOT NULL COMMENT '销售价格',
  `comment` varchar(50) DEFAULT NULL COMMENT '备注说明',
  `soldFlag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`imei`),
  UNIQUE KEY `imei_UNIQUE` (`imei`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='售出商品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldgoods_t`
--

LOCK TABLES `soldgoods_t` WRITE;
/*!40000 ALTER TABLE `soldgoods_t` DISABLE KEYS */;
INSERT INTO `soldgoods_t` VALUES ('000','HS-X8T','Mobile','050001','2016-02-05','1000','haha',1),('111','HS-X8T','Mobile','050001','2015-08-05','1000','haha',1),('1111','Wash8','Washer','050001','2016-02-15','3000','haha',1),('2000','HS-K8','Mobile','050001','2016-06-07','2000',NULL,1),('222','HS-X8T','Mobile','050001','2015-08-05','2000','haha',1),('2222','HS-K8','Mobile','050001','2016-05-15','1000','heihei',1),('330590009421','HS-K8','Mobile','050001','2016-06-03','2000',NULL,1),('333','HS-X8T','Mobile','050001','2015-08-01','5000','hehehe',1),('444','HS-X8T','Mobile','050003','2015-08-05','2000',':)',1),('555','HS-X8T','Mobile','050001','2015-08-03','2000','haha',1),('666','HS-X8T','Mobile','050001','2015-12-03','1000','ha',1),('777','HS-X8T','Mobile','050001','2015-12-06','2000','haha',1),('888','HS-X8T','Mobile','050001','2016-02-05','1000','haha',1),('999','HS-A8','Air-Conditioner','050001','2015-12-06','2000','haha',1);
/*!40000 ALTER TABLE `soldgoods_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_t`
--

DROP TABLE IF EXISTS `stock_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_t` (
  `model` varchar(50) NOT NULL COMMENT '型号',
  `quantity` int(11) DEFAULT '0' COMMENT '库存数',
  `stockAlarm` int(11) DEFAULT NULL COMMENT '库存预警阈值',
  `ownerID` varchar(50) NOT NULL COMMENT '责任员工ID',
  PRIMARY KEY (`model`,`ownerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_t`
--

LOCK TABLES `stock_t` WRITE;
/*!40000 ALTER TABLE `stock_t` DISABLE KEYS */;
INSERT INTO `stock_t` VALUES ('HS-K8',2000,1000,'010001'),('HS-K8',98,10,'040001'),('HS-X8T',1000,0,'010001'),('HS-X8T',1000,0,'020001'),('HS-X8T',600,0,'030001'),('HS-X8T',1999,1000,'040001'),('HS-X8T',400,500,'040002');
/*!40000 ALTER TABLE `stock_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockchangedetail_t`
--

DROP TABLE IF EXISTS `stockchangedetail_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockchangedetail_t` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `model` varchar(50) NOT NULL COMMENT '商品型号',
  `txQuantity` int(11) NOT NULL COMMENT '发货仓库库存数',
  `rxQuantity` int(11) NOT NULL COMMENT '收货仓库库存数',
  `changeQuantity` int(11) NOT NULL COMMENT '库存数',
  `newTxQuantity` int(11) NOT NULL COMMENT '新发货仓库库存数',
  `newRxQuantity` int(11) NOT NULL COMMENT '新收货仓库库存数',
  `txOwnerID` varchar(50) NOT NULL COMMENT '发货仓库负责员工ID',
  `rxOwnerID` varchar(50) NOT NULL COMMENT '收货仓库负责员工ID',
  `addtime` datetime NOT NULL COMMENT '添加时间',
  `comment` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`,`model`,`txOwnerID`,`rxOwnerID`,`addtime`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='库存变动明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockchangedetail_t`
--

LOCK TABLES `stockchangedetail_t` WRITE;
/*!40000 ALTER TABLE `stockchangedetail_t` DISABLE KEYS */;
INSERT INTO `stockchangedetail_t` VALUES (20,'HS-X8T',5000,0,4000,1000,4000,'010001','020001','2015-08-20 14:44:45','总经理分给销售总监'),(21,'HS-X8T',4000,1000,4000,0,5000,'020001','010001','2015-08-20 14:45:34','总经理把分给销售总监的库存取回来'),(22,'HS-X8T',5000,0,4000,1000,4000,'010001','020001','2015-08-20 15:00:47','总经理分配给销售总监'),(23,'HS-X8T',4000,0,3000,1000,3000,'020001','030001','2015-08-20 15:04:40','销售总监分配给一级销售经理'),(24,'HS-X8T',3000,0,2000,1000,2000,'030001','040001','2015-08-20 15:06:18','一级销售经理分配给二级销售经理'),(25,'HS-X8T',1000,0,1000,0,1000,'030001','040002','2015-08-20 15:08:25','一级销售经理分配给二级销售经理'),(26,'HS-X8T',2000,0,1500,500,1500,'040001','030001','2016-01-22 16:31:51',''),(27,'HS-X8T',1500,500,1500,0,2000,'030001','040001','2016-01-22 16:36:41',''),(28,'HS-X8T',2000,0,1500,500,1500,'040001','030001','2016-01-22 16:37:03',''),(29,'HS-X8T',1500,500,1500,0,2000,'030001','040001','2016-01-22 16:46:06',''),(30,'HS-X8T',0,2000,1,0,2001,'Consumer','040001','2016-02-05 16:36:41','Delete sold goods!'),(31,'HS-X8T',2001,0,1,2000,0,'040001','Consumer','2016-02-05 18:09:45','Add sold goods!'),(33,'HS-X8T',0,1999,1,0,2000,'Consumer','040001','2016-02-05 18:24:00','Delete sold goods!'),(34,'HS-X8T',0,2000,1,0,2001,'Consumer','040001','2016-02-05 18:24:04','Delete sold goods!'),(35,'HS-X8T',2001,0,1,2000,0,'040001','Consumer','2016-02-05 18:28:28','Add sold goods!'),(36,'HS-X8T',2000,0,1,1999,0,'040001','Consumer','2016-02-05 18:28:28','Add sold goods!'),(37,'HS-X8T',0,1999,1,0,2000,'Consumer','040001','2016-02-05 20:43:23','Delete sold goods!'),(38,'HS-X8T',0,2000,1,0,2001,'Consumer','040001','2016-02-05 20:43:27','Delete sold goods!'),(39,'HS-X8T',2001,0,1,2000,0,'040001','Consumer','2016-02-05 21:05:02','Add sold goods!'),(40,'HS-X8T',2000,0,1,1999,0,'040001','Consumer','2016-02-05 21:05:02','Add sold goods!'),(41,'HS-X8T',1999,0,1,1998,0,'040001','Consumer','2016-03-01 22:41:28','Add sold goods!'),(42,'HS-X8T',0,1998,1,0,1999,'Consumer','040001','2016-03-01 22:42:12','Delete sold goods!'),(43,'HS-K8',99999999,0,2000,99999999,2000,'Mother Company','010001','2016-03-01 22:59:19','Add initial stock!'),(44,'HS-X8T',1000,0,500,500,500,'020001','030001','2016-03-01 23:10:27','Export to 030001.'),(45,'HS-X8T',500,500,500,0,1000,'030001','020001','2016-03-01 23:10:55','Import from 020001.'),(46,'HS-X8T',1000,0,600,400,600,'040002','030001','2016-03-02 20:54:15','');
/*!40000 ALTER TABLE `stockchangedetail_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_t`
--

DROP TABLE IF EXISTS `store_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_t` (
  `storeID` varchar(50) NOT NULL COMMENT '商场名称/ID（主键）',
  `gpsInfo` varchar(50) NOT NULL,
  PRIMARY KEY (`storeID`),
  UNIQUE KEY `storeID_UNIQUE` (`storeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商场信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_t`
--

LOCK TABLES `store_t` WRITE;
/*!40000 ALTER TABLE `store_t` DISABLE KEYS */;
INSERT INTO `store_t` VALUES ('maidelong','11,11'),('maikaile','120.1,36.2');
/*!40000 ALTER TABLE `store_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target_t`
--

DROP TABLE IF EXISTS `target_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `target_t` (
  `ownerID` varchar(50) NOT NULL COMMENT '负责员工ID',
  `type` varchar(50) DEFAULT NULL COMMENT '商品类型',
  `model` varchar(50) NOT NULL COMMENT '型号',
  `target` int(11) NOT NULL COMMENT '销售目标(任务): 台数目标',
  `targetAmount` int(11) DEFAULT NULL COMMENT '销售目标(任务): 销售额目标',
  `price` varchar(50) DEFAULT NULL COMMENT '销售价格',
  `marketPrice` varchar(50) DEFAULT NULL COMMENT '市场价格',
  `targetType` varchar(50) NOT NULL COMMENT '任务类型：年、月、周、特殊',
  `targetTime` varchar(50) NOT NULL COMMENT '任务时间：特殊任务时作为From Date',
  `targetTime2` varchar(50) DEFAULT '' COMMENT '任务时间：To Date，只针对特殊任务',
  `comment` varchar(50) DEFAULT NULL COMMENT '任务说明',
  PRIMARY KEY (`ownerID`,`model`,`targetTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工销售任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_t`
--

LOCK TABLES `target_t` WRITE;
/*!40000 ALTER TABLE `target_t` DISABLE KEYS */;
INSERT INTO `target_t` VALUES ('020001','Mobile','HS-X8T',1000,100000,'2000','1800','Weekly','2015-09-01','',''),('030001','Air-Conditioner','HS-A8',3000,100000,'3000','3000','Monthly','2015-07','',''),('030001','Washer','Washer',20,20000,'NA','NA','Monthly','2016-02','',''),('040001','All types','All types',0,100000,'NA','NA','Monthly','2016-02','',''),('040001','Air-Conditioner','HS-A8',1000,100000,'1000','1000','Monthly','2015-12','',''),('040001','Mobile','HS-K8',10,10000,'1000','1000','Special','2016-05-10','2016-05-15','For sale'),('040001','Mobile','HS-X8T',20000,100000,'2000','1800','Monthly','2015-12','',''),('040001','Mobile','Mobile',20,20000,'NA','NA','Special','2016-05-10','2016-05-15','For sale'),('040001','Washer','Washer',10,10000,'NA','NA','Monthly','2016-02','',''),('050001','Air-Conditioner','HS-A8',50,10000,'1000','1000','Monthly','2015-12','',''),('050001','Mobile','HS-K8',10,100,NULL,NULL,'Special','2016-05-01','2016-05-03',NULL),('050001','Mobile','HS-K8',11,1111,NULL,NULL,'Special','2016-06-01','2016-06-03',NULL),('050001','Mobile','HS-X8T',1000,100000,'2000','1800','Monthly','2015-12','',''),('050001','Mobile','HS-X8T',10,10000,'1000','1100','Special','2016-02-01','2016-02-10','Spring Festival'),('050001','Mobile','Mobile',1,10,'NA','NA','Special','2016-05-01','2016-05-03',NULL),('050001','Mobile','Mobile',50,100,'NA','NA','Monthly','2016-06',NULL,NULL),('050001','Mobile','Mobile',600,6000,'NA','NA','Special','2016-06-01','2016-06-03',NULL),('050002','Mobile','HS-K8',50,50000,NULL,NULL,'Monthly','2016-06',NULL,NULL),('050002','Mobile','HS-K8',20,60000,NULL,NULL,'Special','2016-06-06','2016-06-08',NULL),('050002','Mobile','HS-X8T',60,60000,NULL,NULL,'Monthly','2016-06',NULL,NULL),('050002','Mobile','Mobile',110,110000,NULL,NULL,'Monthly','2016-06',NULL,NULL),('050002','Mobile','Mobile',25,50000,NULL,NULL,'Special','2016-06-06','2016-06-08',NULL),('050002','Mobile','Mobile',28,500,NULL,NULL,'Special','2016-06-07','2016-06-08',NULL);
/*!40000 ALTER TABLE `target_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-07 15:34:50
