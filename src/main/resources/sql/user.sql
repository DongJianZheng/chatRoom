/*
Navicat MySQL Data Transfer

Source Server         : mysqlT
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : chatroom

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-03-01 09:39:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `head_img` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'http://192.168.10.59/upload/cea93623-26a8-4313-beef-0968c73ff89e.jpg', '1', '1', '1');
INSERT INTO `user` VALUES ('2', '2', '2', '2', '2');
INSERT INTO `user` VALUES ('15501998156', 'http://192.168.25.133/', '3', '3', '3');
INSERT INTO `user` VALUES ('15504537179', 'http://192.168.25.133/', '7', '7', '7');
INSERT INTO `user` VALUES ('15504574830', 'http://192.168.25.133/', '8', '88', '88');
INSERT INTO `user` VALUES ('15504577138', 'http://192.168.25.133/', '9', '9', '9');
INSERT INTO `user` VALUES ('15504578539', 'http://192.168.25.133/', '5', '5', '5');
INSERT INTO `user` VALUES ('15504587976', 'http://192.168.10.59/upload/780d0b08-c9d7-4425-a5aa-cca7fe76cbd2.jpg', '不问归期', 'dongjz', 'dongjz');
INSERT INTO `user` VALUES ('15504789017', 'http://192.168.10.59/upload/cea93623-26a8-4313-beef-0968c73ff89e.jpg', '好像很有道理的样子', '123456', 'yaoff');
