/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-MySql
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : 127.0.0.1:3306
 Source Schema         : base-frame

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 11/08/2020 16:00:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role_menu_mapping
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_mapping`;
CREATE TABLE `role_menu_mapping`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_mapping
-- ----------------------------
INSERT INTO `role_menu_mapping` VALUES (15, 15, 26);
INSERT INTO `role_menu_mapping` VALUES (16, 15, 27);
INSERT INTO `role_menu_mapping` VALUES (17, 15, 28);
INSERT INTO `role_menu_mapping` VALUES (18, 15, 29);
INSERT INTO `role_menu_mapping` VALUES (19, 16, 26);
INSERT INTO `role_menu_mapping` VALUES (20, 16, 28);
INSERT INTO `role_menu_mapping` VALUES (23, 30, 26);
INSERT INTO `role_menu_mapping` VALUES (24, 30, 27);
INSERT INTO `role_menu_mapping` VALUES (25, 30, 28);
INSERT INTO `role_menu_mapping` VALUES (26, 30, 29);
INSERT INTO `role_menu_mapping` VALUES (29, 17, 27);
INSERT INTO `role_menu_mapping` VALUES (30, 17, 28);
INSERT INTO `role_menu_mapping` VALUES (31, 17, 29);

-- ----------------------------
-- Table structure for type_code
-- ----------------------------
DROP TABLE IF EXISTS `type_code`;
CREATE TABLE `type_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型节点',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型代码',
  `description` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型描述',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) NULL DEFAULT NULL COMMENT '更新人id',
  `state` int(11) NULL DEFAULT 1 COMMENT '状态: 0 = 作废, 1 = 有效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type_code
-- ----------------------------
INSERT INTO `type_code` VALUES (1, 'root', '测试节点', 'test_node', '测试节点', '2020-07-21 11:04:04', 1, '2020-08-07 08:15:13', 1, 1);
INSERT INTO `type_code` VALUES (2, 'test_node2', '测试节点1', 'test_node1', '测试节点1', '2020-07-21 11:05:00', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (3, 'root', '菜单', 'menu', '菜单配置', '2020-07-21 11:06:27', 1, '2020-08-07 08:55:42', 1, 1);
INSERT INTO `type_code` VALUES (4, 'root', '角色', 'role', '角色配置', '2020-07-21 11:09:00', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (5, 'root', '测试节点2', 'test_node2', '测试节点2', '2020-08-07 03:07:13', 1, '2020-08-07 08:15:39', 1, 1);
INSERT INTO `type_code` VALUES (6, 'test_node2', '测试节点2-测试122', 'test_node2_test122', '测试节点2-测试1222', '2020-08-07 03:07:35', 1, '2020-08-07 07:27:28', 1, 1);
INSERT INTO `type_code` VALUES (7, 'root', '测试节点3', 'test_node3', '测试节点3', '2020-08-07 03:08:35', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (8, 'root', '测试节点4', 'test_node4', '测试节点4', '2020-08-07 03:12:31', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (9, 'test_node4', '测试节点4-1', 'test_node4_1', '测试节点4-1', '2020-08-07 03:14:31', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (10, 'test_node4', '测试节点4-2', 'test_node4-2', '测试节点4-2', '2020-08-07 03:15:28', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (12, 'root', '测试节点5', 'test_node5', '测试节点5', '2020-08-07 03:17:04', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (14, 'root', 'test_node6', 'test_node6', 'test_node6', '2020-08-07 03:21:10', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (15, 'role', '系统管理员', 'System_Administrator', '系统管理员', '2020-08-07 05:18:21', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (16, 'role', '管理员', 'Administrator', '管理员', '2020-08-07 05:18:47', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (17, 'role', '普通用户', 'Ordinary_User', '普通用户', '2020-08-07 05:20:18', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (18, 'root', 'test_node7', 'test_node7', 'test_node7', '2020-08-07 08:11:01', 1, '2020-08-07 08:16:54', 1, 1);
INSERT INTO `type_code` VALUES (19, 'test_node2', 'test_node_1', 'test_node_1', 'test_node_1', '2020-08-07 08:13:40', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (20, 'test_node2', 'test_node_2', 'test_node_2', 'test_node_2', '2020-08-07 08:13:49', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (22, 'root', 'test_node23', 'test_node23', 'test_node23', '2020-08-07 08:15:53', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (23, 'test_node', 'test_node_1_1', 'test_node_1_1', 'test_node_1_1', '2020-08-07 08:16:14', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (24, 'test_node', 'test_node_1_2', 'test_node_1_2', 'test_node_1_2', '2020-08-07 08:16:20', 1, NULL, NULL, 0);
INSERT INTO `type_code` VALUES (25, 'root', 'test_node8', 'test_node8', 'test_node8', '2020-08-07 08:32:23', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (26, 'menu', '基础维护-类型代码', 'Menu_TypeCode', '基础维护-类型代码', '2020-08-07 08:58:59', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (27, 'menu', '基础维护-代码管理', 'Menu_CodeMangement', '基础维护-代码管理', '2020-08-07 08:59:21', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (28, 'menu', '基础维护-用户管理', 'Menu_UserManagement', '基础维护-用户管理', '2020-08-07 09:00:00', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (29, 'menu', '基础维护-权限管理', 'Menu_Authorization', '基础维护-权限管理', '2020-08-07 09:02:47', 1, NULL, NULL, 1);
INSERT INTO `type_code` VALUES (30, 'role', '测试角色', 'Test_Role', '测试使用', '2020-08-11 01:59:11', 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码(MD5加密)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', '202cb962ac59075b964b07152d234b70', '2020-06-28 15:59:34', 0);
INSERT INTO `users` VALUES (2, 'super', 'super', '202cb962ac59075b964b07152d234b70', '2020-06-28 15:59:40', 0);

SET FOREIGN_KEY_CHECKS = 1;
