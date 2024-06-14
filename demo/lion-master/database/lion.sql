/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : lion

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 09/05/2020 17:57:36
*/

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('lion-client', NULL, '{bcrypt}$2a$10$iq0/gR20ZXaSPkxyQAWlleRHZsl/8cfmpQ4JXqqccjiNSKh88y4LG', 'all', 'authorization_code,password,refresh_token', 'https://github.com/micyo202/lion', NULL, 25200, 108000, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_id
-- ----------------------------
DROP TABLE IF EXISTS `sys_id`;
CREATE TABLE `sys_id` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '编码',
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `max_id` bigint(20) DEFAULT NULL COMMENT '已分配的最大ID',
  `step` bigint(20) DEFAULT NULL COMMENT '步长',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='自增ID规则表';

-- ----------------------------
-- Records of sys_id
-- ----------------------------
BEGIN;
INSERT INTO `sys_id` VALUES (1, 'user', '用户ID生成规则', 0, 10, 1, '2019-04-28 16:38:40', '2019-11-27 14:46:39');
INSERT INTO `sys_id` VALUES (2, 'order', '订单ID生成规则', 0, 1000, 1, '2019-04-28 16:39:05', '2019-04-28 16:39:05');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `p_id` bigint(20) DEFAULT NULL COMMENT '父主键',
  `p_code` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父编码',
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求地址',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_menu` tinyint(1) DEFAULT NULL COMMENT '是否菜单（0：否，1：是）',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '1', 0, '0', '系统管理', '/manager', 1, 1, 1, NULL, 1, '2019-04-15 11:16:02', NULL);
INSERT INTO `sys_menu` VALUES (2, '2', 1, '1', '用户管理', '/manager/user', 2, 1, 1, NULL, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, '3', 1, '1', '角色管理', '/manager/role', 2, 2, 1, NULL, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, '4', 1, '1', '菜单管理', '/manager/menu', 2, 3, 1, NULL, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (5, '5', 1, '1', '用户角色管理', '/manager/user_role', 2, 4, 1, NULL, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (6, '6', 1, '1', '角色菜单管理', '/manager/role_menu', 2, 5, 1, NULL, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`name`),
  UNIQUE KEY `unique_role_value` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 'super', '超级管理员', 1, '2017-06-20 15:08:45', NULL);
INSERT INTO `sys_role` VALUES (2, 'admin', '管理员', 1, '2017-06-20 15:07:13', '2017-06-26 12:46:09');
INSERT INTO `sys_role` VALUES (3, 'user', '一般用户', 1, '2017-06-28 18:50:39', '2017-07-21 09:41:28');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单主键',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 2, 1, 1, '2019-04-15 11:18:26', NULL);
INSERT INTO `sys_role_menu` VALUES (2, 2, 2, 1, '2019-04-19 13:41:36', NULL);
INSERT INTO `sys_role_menu` VALUES (3, 2, 3, 1, '2019-04-19 13:41:39', NULL);
INSERT INTO `sys_role_menu` VALUES (4, 2, 4, 1, '2019-04-19 13:41:42', NULL);
INSERT INTO `sys_role_menu` VALUES (5, 2, 5, 1, '2019-04-19 13:41:45', NULL);
INSERT INTO `sys_role_menu` VALUES (6, 2, 6, 1, '2019-04-19 13:42:20', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_schedule
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule`;
CREATE TABLE `sys_schedule` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '任务名称',
  `cron` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '执行周期表达式',
  `app_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '所属应用名称',
  `class_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '执行类',
  `method` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '执行方法 各服务范围应一致',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='定时任务计划表';

-- ----------------------------
-- Records of sys_schedule
-- ----------------------------
BEGIN;
INSERT INTO `sys_schedule` VALUES (1, '测试定时任务1', '0/10 * * * * ?', 'lion-demo-', 'scheduleDemo', 'firstMethod', 1, NULL, NULL);
INSERT INTO `sys_schedule` VALUES (2, '测试定时任务2', '5/15 * * * * ?', 'lion-demo-', 'scheduleDemo', 'secondMethod', 1, NULL, NULL);
INSERT INTO `sys_schedule` VALUES (3, '测试定时任务3', '30 0/1 * * * ?', 'lion-demo-', 'scheduleDemo', 'thirdMethod', 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '启用标志（0：不启用，1：启用）',
  `account_non_expired` tinyint(1) DEFAULT NULL COMMENT '账户不过期（0：过期，1：正常）',
  `credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '凭证不过期（0：过期，1：正常）',
  `account_non_locked` tinyint(1) DEFAULT NULL COMMENT '账户不锁定（0：锁定，1：正常）',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'super', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', '超级管理员', '1989-06-22', 1, NULL, NULL, NULL, 1, 1, 1, 1, 1, '2017-06-20 15:12:16', '2019-04-19 13:30:26');
INSERT INTO `sys_user` VALUES (2, 'admin', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', '管理员', '1990-08-08', 1, NULL, NULL, NULL, 1, 1, 1, 1, 1, '2017-06-26 17:31:41', NULL);
INSERT INTO `sys_user` VALUES (3, 'user', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', '普通用户', '1991-05-01', 0, NULL, NULL, NULL, 1, 1, 1, 1, 1, '2017-09-18 16:11:15', NULL);
INSERT INTO `sys_user` VALUES (4, 'test', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', '测试用户', '1996-07-20', 0, NULL, NULL, NULL, 1, 1, 1, 1, 1, '2017-09-21 17:09:51', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1, 1, '2019-04-10 11:57:40', NULL);
INSERT INTO `sys_user_role` VALUES (2, 2, 2, 1, '2019-04-10 11:58:02', NULL);
INSERT INTO `sys_user_role` VALUES (3, 2, 3, 1, '2019-04-10 11:58:19', NULL);
INSERT INTO `sys_user_role` VALUES (4, 3, 3, 1, '2019-04-10 11:58:44', NULL);
COMMIT;

-- ----------------------------
-- Table structure for temp_mybatis
-- ----------------------------
DROP TABLE IF EXISTS `temp_mybatis`;
CREATE TABLE `temp_mybatis` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='MyBatis示例表';

-- ----------------------------
-- Table structure for temp_order
-- ----------------------------
DROP TABLE IF EXISTS `temp_order`;
CREATE TABLE `temp_order` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `product_code` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '产品编码',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for temp_product
-- ----------------------------
DROP TABLE IF EXISTS `temp_product`;
CREATE TABLE `temp_product` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编码',
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `valid` tinyint(1) DEFAULT NULL COMMENT '有效标志（0：无效，1：有效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
-- Records of temp_product
-- ----------------------------
BEGIN;
INSERT INTO `temp_product` VALUES (1, 'product-1', '有库存的商品', 9, 1, '2020-03-20 16:50:22', '2020-03-20 16:50:22');
INSERT INTO `temp_product` VALUES (2, 'product-2', '无库存的商品', 0, 1, '2020-03-30 10:52:00', '2020-03-30 10:52:00');
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;