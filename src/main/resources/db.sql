/*
 Navicat Premium Data Transfer

 Source Server         : titan-test
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 139.198.14.192:3306
 Source Schema         : fid

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 05/01/2019 11:49:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES (116, 1, 1);
INSERT INTO `sys_authority` VALUES (117, 1, 4);
INSERT INTO `sys_authority` VALUES (118, 1, 15);
INSERT INTO `sys_authority` VALUES (119, 1, 16);
INSERT INTO `sys_authority` VALUES (120, 1, 17);
INSERT INTO `sys_authority` VALUES (121, 1, 18);
INSERT INTO `sys_authority` VALUES (122, 1, 28);

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `department_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `department_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门编码',
  `department_type_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '机构类型',
  `level` tinyint(4) NOT NULL DEFAULT 1 COMMENT '部门层级',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级部门ID',
  `create_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`, `department_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES (12, '鄂托克农商行', '79000', 1, 1, 0, '', '2018-12-04 23:38:33', '', NULL, 0);
INSERT INTO `sys_department` VALUES (13, '人力资源部', '01', 2, 2, 12, '', '2018-12-04 23:39:19', '', NULL, 0);
INSERT INTO `sys_department` VALUES (14, '营业部', '79003', 3, 2, 12, '', '2018-12-04 23:39:45', '', NULL, 0);

-- ----------------------------
-- Table structure for sys_department_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_department_type`;
CREATE TABLE `sys_department_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '机构类型编码',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '机构类型名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type_code`(`type_code`) USING BTREE,
  UNIQUE INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department_type
-- ----------------------------
INSERT INTO `sys_department_type` VALUES (1, '0', '总公司');
INSERT INTO `sys_department_type` VALUES (2, '1', '部门');
INSERT INTO `sys_department_type` VALUES (3, '2', '支行');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `menu_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单编码',
  `menu_icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `target_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '跳转url',
  `level` tinyint(4) NOT NULL DEFAULT 1 COMMENT '菜单层级',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级菜单ID',
  `create_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`, `menu_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '', 'fa fa-columns', '', 1, 0, '', '2018-04-16 14:59:53', '', '2018-02-24 15:52:37', 0);
INSERT INTO `sys_menu` VALUES (4, '菜单管理', '', '', 'menu/manager', 2, 1, '', '2018-03-30 15:59:39', '', '2018-02-24 15:53:17', 0);
INSERT INTO `sys_menu` VALUES (15, '组织管理', '02', '', '', 1, 0, '', '2018-11-15 11:10:56', '', NULL, 0);
INSERT INTO `sys_menu` VALUES (16, '用户管理', '', '', 'user/manager', 2, 15, '', '2018-11-15 11:11:33', '', NULL, 0);
INSERT INTO `sys_menu` VALUES (17, '机构管理', '', '', 'department/manager', 2, 15, '', '2018-11-15 11:12:02', '', NULL, 0);
INSERT INTO `sys_menu` VALUES (18, '角色管理', '', '', 'role/manager', 2, 15, '', '2018-11-15 11:12:56', '', NULL, 0);
INSERT INTO `sys_menu` VALUES (28, '机构类型管理', '', '', 'departmentType/manager', 2, 15, '', '2018-12-17 11:45:52', '', NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色描述',
  `create_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '1', '超级管理员', 'wz', '2018-04-02 09:29:36', 'wz', '2018-04-02 09:34:21', 0);
INSERT INTO `sys_role` VALUES (5, '测试1', '', '测试1', '', '2018-11-29 11:46:00', '', NULL, 0);
INSERT INTO `sys_role` VALUES (6, '会计', '', '会计', '', '2018-12-19 15:55:25', '', NULL, 0);
INSERT INTO `sys_role` VALUES (7, '柜员', '', '柜员', '', '2018-12-19 15:55:36', '', NULL, 0);
INSERT INTO `sys_role` VALUES (8, '行长', '', '行长', '', '2018-12-19 16:29:10', '', NULL, 0);
INSERT INTO `sys_role` VALUES (9, '啊', '', '啊', '', '2018-12-19 16:29:18', '', NULL, 0);
INSERT INTO `sys_role` VALUES (10, '测试', '', '测试2', '', '2018-12-19 16:29:26', '', NULL, 0);
INSERT INTO `sys_role` VALUES (13, '日本', '', '日本', '', '2018-12-19 16:30:04', '', NULL, 0);
INSERT INTO `sys_role` VALUES (14, '新加坡', '', '新加坡', '', '2018-12-19 16:30:15', '', NULL, 0);
INSERT INTO `sys_role` VALUES (15, '印度尼西亚', '', '印度尼西亚', '', '2018-12-19 16:30:25', '', NULL, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `real_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `birthday` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '出生日期',
  `tel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '电话',
  `update_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '更新人ID',
  `update_time` timestamp(0) DEFAULT NULL COMMENT '更新时间',
  `create_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人ID',
  `create_time` timestamp(0) DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '小王子', '2', '16602708722', '', '2018-02-22 16:48:16', '', '2018-02-22 16:48:16', 0);
INSERT INTO `sys_user` VALUES (28, '11111', '', '测试', '1000', '13111111111', '', NULL, '', NULL, 0);
INSERT INTO `sys_user` VALUES (29, '11111', '', '111', '11', '13111111111', '', NULL, '', NULL, 0);
INSERT INTO `sys_user` VALUES (30, '22222', '', '22', '222', '13122222222', '', NULL, '', NULL, 0);
INSERT INTO `sys_user` VALUES (31, '33333', '', '33', '333', '13133333333', '', NULL, '', NULL, 0);
INSERT INTO `sys_user` VALUES (32, '555555', '', '55', '455', '13133333333', '', NULL, '', NULL, 0);
INSERT INTO `sys_user` VALUES (33, '66666', '', '66', '66', '13111111111', '', NULL, '', NULL, 0);
INSERT INTO `sys_user` VALUES (34, '77777', '', '77', '11', '13111111111', '', NULL, '', NULL, 0);

-- ----------------------------
-- Table structure for sys_user_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_department`;
CREATE TABLE `sys_user_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `department_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '机构ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_department
-- ----------------------------
INSERT INTO `sys_user_department` VALUES (17, 1, 12);
INSERT INTO `sys_user_department` VALUES (18, 28, 13);
INSERT INTO `sys_user_department` VALUES (19, 29, 12);
INSERT INTO `sys_user_department` VALUES (20, 30, 12);
INSERT INTO `sys_user_department` VALUES (21, 31, 12);
INSERT INTO `sys_user_department` VALUES (22, 32, 12);
INSERT INTO `sys_user_department` VALUES (23, 33, 13);
INSERT INTO `sys_user_department` VALUES (24, 34, 13);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 0, 0);
INSERT INTO `sys_user_role` VALUES (59, 28, 13);
INSERT INTO `sys_user_role` VALUES (76, 1, 1);
INSERT INTO `sys_user_role` VALUES (77, 28, 1);

SET FOREIGN_KEY_CHECKS = 1;
