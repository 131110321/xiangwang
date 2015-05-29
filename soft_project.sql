/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : soft_project

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-05-29 09:58:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsID` int(10) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `unitPrice` float(10,0) DEFAULT NULL,
  `goodsQuantity` int(10) DEFAULT NULL,
  PRIMARY KEY (`goodsID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '耐克篮球鞋', '150', '20');
INSERT INTO `goods` VALUES ('4', '我我我我', '100', '0');
INSERT INTO `goods` VALUES ('5', '擦擦擦擦', '150', '0');

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(10) NOT NULL,
  `countNum` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of login
-- ----------------------------

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `saleID` int(10) NOT NULL AUTO_INCREMENT,
  `saleQuantity` int(10) DEFAULT NULL,
  `goodsID` int(10) DEFAULT NULL,
  PRIMARY KEY (`saleID`),
  KEY `goodsID` (`goodsID`),
  CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`goodsID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES ('1', '10', '1');
INSERT INTO `sale` VALUES ('4', '1', '1');
INSERT INTO `sale` VALUES ('5', '10', '1');
INSERT INTO `sale` VALUES ('6', '150', '1');
INSERT INTO `sale` VALUES ('7', '150', '1');
INSERT INTO `sale` VALUES ('8', '150', '1');
INSERT INTO `sale` VALUES ('9', '9', '1');
INSERT INTO `sale` VALUES ('10', '0', '4');
INSERT INTO `sale` VALUES ('11', '0', '5');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `inID` int(10) NOT NULL AUTO_INCREMENT,
  `inPrice` float(10,0) DEFAULT NULL,
  `inQuantity` int(10) DEFAULT NULL,
  `goodsID` int(10) DEFAULT NULL,
  PRIMARY KEY (`inID`),
  KEY `goodsID` (`goodsID`),
  CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`goodsID`) REFERENCES `goods` (`goodsID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('1', '80', '10', '1');
INSERT INTO `stock` VALUES ('11', '50', '13', '1');
INSERT INTO `stock` VALUES ('15', '100', '10', '1');
INSERT INTO `stock` VALUES ('16', '100', '10', '1');
INSERT INTO `stock` VALUES ('17', '100', '120', '1');
INSERT INTO `stock` VALUES ('18', '100', '317', '1');
INSERT INTO `stock` VALUES ('19', '100', '10', '1');
INSERT INTO `stock` VALUES ('20', '0', '0', '4');
INSERT INTO `stock` VALUES ('21', '0', '0', '5');
