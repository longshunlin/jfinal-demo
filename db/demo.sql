/*
Navicat MySQL Data Transfer

Source Server         : 47.104.96.68
Source Server Version : 50721
Source Host           : 47.104.96.68:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-02-12 21:32:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dictKey` varchar(50) NOT NULL,
  `dictValue` varchar(8000) DEFAULT '',
  PRIMARY KEY (`dictKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL COMMENT '会员为微信openId',
  `userNickName` varchar(50) DEFAULT '' COMMENT '微信昵称',
  `userPsw` varchar(64) NOT NULL DEFAULT '',
  `birthday` varchar(10) DEFAULT '' COMMENT '生日：1985-03-12',
  `realName` varchar(50) DEFAULT '' COMMENT '姓名',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `status` tinyint(4) DEFAULT '0' COMMENT '用户的状态：0为正常 1为禁用',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `createTime` varchar(30) DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`userId`),
  KEY `sync_index` (`realName`,`avatar`) USING BTREE,
  KEY `userName_index` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=100073 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '', '21232F297A57A5A743894A0E4A801FC3', '', '超级管理员', '1', '/uploadImgs/face/1541663294809.jpg', '105288311@qq.com', '0', '15767976821', '2018-05-10 16:33:54');

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log` (
  `logId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `loginIP` varchar(50) DEFAULT '',
  `loginTime` varchar(50) DEFAULT '',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=1319 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_login_log
-- ----------------------------
INSERT INTO `user_login_log` VALUES ('1316', '1', '0:0:0:0:0:0:0:1', '2019-02-12 21:16:49');
INSERT INTO `user_login_log` VALUES ('1317', '1', '0:0:0:0:0:0:0:1', '2019-02-12 21:17:09');
INSERT INTO `user_login_log` VALUES ('1318', '1', '0:0:0:0:0:0:0:1', '2019-02-12 21:18:26');
