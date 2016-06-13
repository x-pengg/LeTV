/*
Navicat MySQL Data Transfer

Source Server         : lcoal
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : live

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-06-03 17:00:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for live_content
-- ----------------------------
DROP TABLE IF EXISTS `live_content`;
CREATE TABLE `live_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `summary` varchar(500) DEFAULT NULL,
  `tid` int(11) NOT NULL,
  `play_mode` int(1) NOT NULL,
  `code_rate` varchar(15) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for live_token
-- ----------------------------
DROP TABLE IF EXISTS `live_token`;
CREATE TABLE `live_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(500) NOT NULL,
  `expiry_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for live_type
-- ----------------------------
DROP TABLE IF EXISTS `live_type`;
CREATE TABLE `live_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for live_user
-- ----------------------------
DROP TABLE IF EXISTS `live_user`;
CREATE TABLE `live_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` int(1) DEFAULT '0',
  `head_img` varchar(500) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`,`email`),
  UNIQUE KEY `qu_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
