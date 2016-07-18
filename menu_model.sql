/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-07-18 22:05:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu_model
-- ----------------------------
DROP TABLE IF EXISTS `menu_model`;
CREATE TABLE `menu_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `editable` tinyint(1) NOT NULL,
  `has_child` tinyint(1) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `order_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q0kr3u7blusvjq7l6hid2s79` (`name`),
  KEY `FK_6p93hh67fbrr0b2p2c0vt2ik0` (`create_user`),
  KEY `FK_d2a3xnbks52bhvpvo4ne0pn0o` (`parent_id`),
  KEY `FK_acdouob806weas4gn56usegt6` (`update_user`),
  CONSTRAINT `FK_6p93hh67fbrr0b2p2c0vt2ik0` FOREIGN KEY (`create_user`) REFERENCES `user_model` (`id`),
  CONSTRAINT `FK_acdouob806weas4gn56usegt6` FOREIGN KEY (`update_user`) REFERENCES `user_model` (`id`),
  CONSTRAINT `FK_d2a3xnbks52bhvpvo4ne0pn0o` FOREIGN KEY (`parent_id`) REFERENCES `menu_model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_model
-- ----------------------------
INSERT INTO `menu_model` VALUES ('1', '2016-06-28 16:45:38', '0', '0', '1', '首页', '2016-06-28 16:45:43', '/index', '1', null, '1', '1');
INSERT INTO `menu_model` VALUES ('2', '2016-06-28 16:45:49', '0', '1', '1', '关于我们', '2016-07-15 14:10:27', '/about/13', '1', null, '1', '2');
INSERT INTO `menu_model` VALUES ('3', '2016-06-28 16:45:40', '0', '1', '1', '资讯中心', '2016-06-28 16:45:52', '/info/9', '1', null, '1', '3');
INSERT INTO `menu_model` VALUES ('4', '2016-06-28 16:45:41', '0', '1', '1', '投资加盟', '2016-06-28 16:45:52', '/touzi/18', '1', null, '1', '4');
INSERT INTO `menu_model` VALUES ('5', '2016-06-28 16:45:42', '0', '1', '1', '经营范围', '2016-06-28 16:45:52', '/scope', '1', null, '1', '5');
INSERT INTO `menu_model` VALUES ('6', '2016-06-28 16:45:43', '0', '1', '1', '养生专区', '2016-06-28 16:45:52', '/yangsheng/33', '1', null, '1', '6');
INSERT INTO `menu_model` VALUES ('7', '2016-06-28 16:45:44', '0', '0', '1', '联系我们', '2016-06-28 16:45:52', '/contact', '1', null, '1', '7');
INSERT INTO `menu_model` VALUES ('8', '2016-06-28 16:45:45', '0', '1', '1', '招聘专栏', '2016-06-28 16:45:52', '/zhaopin/35', '1', null, '1', '8');
INSERT INTO `menu_model` VALUES ('9', '2016-06-28 16:57:17', '1', '0', '2', '公司新闻', '2016-06-28 16:57:38', '/info/9', '1', '3', '1', '1');
INSERT INTO `menu_model` VALUES ('10', '2016-07-04 16:26:43', '1', '0', '2', '公示公告', '2016-07-04 16:27:09', '/info/10', '1', '3', '1', '2');
INSERT INTO `menu_model` VALUES ('11', '2016-07-04 16:28:32', '1', '0', '2', '行业新闻', '2016-07-04 16:28:56', '/info/11', '1', '3', '1', '3');
INSERT INTO `menu_model` VALUES ('12', '2016-07-04 16:29:41', '1', '0', '2', '投资新闻', '2016-07-04 16:29:52', '/info/12', '1', '3', '1', '4');
INSERT INTO `menu_model` VALUES ('13', '2016-07-04 16:29:41', '1', '0', '2', '公司简介', '2016-07-15 14:11:16', '/about/13', '1', '2', '1', '1');
INSERT INTO `menu_model` VALUES ('14', '2016-07-04 16:29:41', '1', '0', '2', '组织架构', '2016-07-04 16:29:52', '/about/14', '1', '2', '1', '2');
INSERT INTO `menu_model` VALUES ('15', '2016-07-04 16:29:41', '1', '0', '2', '公司资质', '2016-07-04 16:29:52', '/about/15', '1', '2', '1', '3');
INSERT INTO `menu_model` VALUES ('16', '2016-07-04 16:29:41', '1', '0', '2', '发展历程', '2016-07-04 16:29:52', '/about/16', '1', '2', '1', '4');
INSERT INTO `menu_model` VALUES ('17', '2016-07-04 16:29:41', '1', '0', '2', '企业文化', '2016-07-04 16:29:52', '/about/17', '1', '2', '1', '5');
INSERT INTO `menu_model` VALUES ('18', '2016-07-04 16:29:41', '1', '0', '2', '关于项目', '2016-07-04 16:29:52', '/touzi/18', '1', '4', '1', '1');
INSERT INTO `menu_model` VALUES ('19', '2016-07-04 16:29:41', '1', '0', '2', '我们的支持', '2016-07-04 16:29:52', '/touzi/19', '1', '4', '1', '2');
INSERT INTO `menu_model` VALUES ('20', '2016-07-04 16:29:41', '1', '0', '2', '管理一体化', '2016-07-04 16:29:52', '/touzi/20', '1', '4', '1', '3');
INSERT INTO `menu_model` VALUES ('21', '2016-07-04 16:29:41', '1', '0', '2', '管理团队', '2016-07-04 16:29:52', '/touzi/21', '1', '4', '1', '4');
INSERT INTO `menu_model` VALUES ('22', '2016-07-04 16:29:41', '1', '1', '2', '门店展示', '2016-07-04 16:29:52', '/stores/orgstruct', '1', '4', '1', '5');
INSERT INTO `menu_model` VALUES ('23', '2016-07-04 16:29:41', '1', '0', '2', '如何合作', '2016-07-04 16:29:52', '/touzi/23', '1', '4', '1', '6');
INSERT INTO `menu_model` VALUES ('24', '2016-07-04 16:29:41', '1', '0', '2', '疑难问答', '2016-07-04 16:29:52', '/touzi/24', '1', '4', '1', '7');
INSERT INTO `menu_model` VALUES ('25', '2016-07-04 16:29:41', '1', '0', '3', '门店组织构架', '2016-07-04 16:29:52', '/stores/orgstruct', '1', '22', '1', '1');
INSERT INTO `menu_model` VALUES ('26', '2016-07-04 16:29:41', '1', '0', '3', '部门管理', '2016-07-04 16:29:52', '/stores/manager', '1', '22', '1', '2');
INSERT INTO `menu_model` VALUES ('27', '2016-07-04 16:29:41', '1', '1', '3', '门店形象展示', '2016-07-04 16:29:52', '/stores/show', '1', '22', '1', '3');
INSERT INTO `menu_model` VALUES ('28', '2016-07-04 16:29:41', '1', '0', '2', '浴足连锁', '2016-07-04 16:29:52', '/scope/28', '1', '5', '1', '1');
INSERT INTO `menu_model` VALUES ('29', '2016-07-04 16:29:41', '1', '0', '2', '酒店管理', '2016-07-04 16:29:52', '/scope/29', '1', '5', '1', '2');
INSERT INTO `menu_model` VALUES ('30', '2016-07-04 16:29:41', '1', '0', '2', '企业人才培训', '2016-07-04 16:29:52', '/scope/30', '1', '5', '1', '3');
INSERT INTO `menu_model` VALUES ('33', '2016-07-15 14:22:35', '1', '0', '2', '理疗类', '2016-07-15 14:22:52', '/yangsheng/33', '1', '6', '1', '1');
INSERT INTO `menu_model` VALUES ('34', '2016-07-15 14:23:08', '1', '0', '2', '按摩类', '2016-07-15 14:23:13', '/yangsheng/34', '1', '6', '1', '2');
INSERT INTO `menu_model` VALUES ('35', '2016-07-15 14:24:37', '1', '0', '2', '职位发布', '2016-07-15 14:24:37', '/zhaopin/35', '1', '8', '1', '1');
INSERT INTO `menu_model` VALUES ('36', '2016-07-15 14:25:18', '1', '0', '2', '成长伙伴', '2016-07-15 14:25:18', '/zhaopin/36', '1', '8', '1', '2');
INSERT INTO `menu_model` VALUES ('37', '2016-07-15 14:25:46', '1', '0', '2', '加入我们', '2016-07-15 14:25:46', '/zhaopin/37', '1', '8', '1', '3');
