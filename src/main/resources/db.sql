/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : quake

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-10 18:11:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_authority`
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('4', '1', '1');
INSERT INTO `sys_authority` VALUES ('5', '1', '2');
INSERT INTO `sys_authority` VALUES ('6', '1', '3');
INSERT INTO `sys_authority` VALUES ('7', '1', '4');
INSERT INTO `sys_authority` VALUES ('8', '1', '13');
INSERT INTO `sys_authority` VALUES ('11', '2', '1');
INSERT INTO `sys_authority` VALUES ('12', '2', '2');
INSERT INTO `sys_authority` VALUES ('13', '2', '3');
INSERT INTO `sys_authority` VALUES ('14', '2', '6');
INSERT INTO `sys_authority` VALUES ('15', '2', '12');

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `department_name` varchar(100) NOT NULL DEFAULT '' COMMENT '部门名称',
  `department_code` varchar(20) NOT NULL DEFAULT '' COMMENT '部门编码',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '部门层级',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级部门ID',
  `create_id` varchar(20) DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`,`department_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '业务发展部', '1', '1', '0', '', '2018-05-10 10:00:31', '', null, '0');
INSERT INTO `sys_department` VALUES ('2', '人力资源部', '2', '1', '0', '', '2018-05-10 10:00:38', '', null, '0');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `menu_name` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '菜单名称',
  `menu_code` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '菜单编码',
  `menu_icon` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '菜单图标',
  `target_url` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '跳转url',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '菜单层级',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级菜单ID',
  `create_id` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`,`menu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '', 'fa fa-columns', '', '1', '0', '', '2018-04-16 14:59:53', '', '2018-02-24 15:52:37', '0');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '', '', 'user/manager', '2', '1', '', '2018-03-30 15:59:18', '', '2018-02-24 15:52:49', '0');
INSERT INTO `sys_menu` VALUES ('3', '角色管理', '', '', 'role/manager', '2', '1', '', '2018-03-30 13:35:07', '', '2018-02-24 15:53:02', '0');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '', '', 'menu/manager', '2', '1', '', '2018-03-30 15:59:39', '', '2018-02-24 15:53:17', '0');
INSERT INTO `sys_menu` VALUES ('6', 'caidan', '', '', '', '1', '0', '', '2018-04-16 16:17:29', '', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '1222', '', '', '', '2', '6', '', '2018-04-16 16:17:37', '', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '权限管理', '', '', 'authority/manager', '2', '1', '', '2018-04-16 17:02:34', '', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '部门管理', '', '', 'department/manager', '2', '1', '', '2018-05-10 10:00:10', '', null, '0');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '角色编码',
  `description` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '角色描述',
  `create_id` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`,`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '1', '超级管理员', 'wz', '2018-04-02 09:29:36', 'wz', '2018-04-02 09:34:21', '0');
INSERT INTO `sys_role` VALUES ('2', '测试', '', '测试人员', '', '2018-04-17 10:02:46', '', null, '0');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '密码',
  `real_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '姓名',
  `age` varchar(10) CHARACTER SET utf8 DEFAULT '' COMMENT '年龄',
  `tel` varchar(15) CHARACTER SET utf8 DEFAULT '' COMMENT '电话',
  `update_id` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '更新人ID',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `create_id` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '创建人ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '小王子', '2', '16602708722', '', '2018-02-22 16:48:16', '', '2018-02-22 16:48:16', '0');
INSERT INTO `sys_user` VALUES ('2', '王志', 'admin', '1122', '4', '1211212', '', '2018-02-22 16:48:16', '', '2018-02-22 16:48:16', '0');
INSERT INTO `sys_user` VALUES ('3', '彭鹏', '', '彭鹏', '11', '111', '', null, '', null, '0');
INSERT INTO `sys_user` VALUES ('4', '武工院', '', '武工院', '11', '11', '', null, '', null, '0');
INSERT INTO `sys_user` VALUES ('5', '武工院2', '', '武工院', '11', '1123', '', null, '', null, '0');
INSERT INTO `sys_user` VALUES ('9', '121', '', '12', '', '12', '', null, '', null, '0');
INSERT INTO `sys_user` VALUES ('15', '1211', '', '1', '1', '1', '', null, '', null, '0');
INSERT INTO `sys_user` VALUES ('17', '11', '', '', '2', '2', '', null, '', null, '0');
INSERT INTO `sys_user` VALUES ('20', '5', '', '5', '5', '5', '', null, '', null, '0');
INSERT INTO `sys_user` VALUES ('21', '122121221', '', '21212121', '', '', '', null, '', null, '0');
