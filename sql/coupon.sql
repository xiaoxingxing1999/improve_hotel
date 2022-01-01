/*
 Navicat Premium Data Transfer

 Source Server         : myComputer
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 21/06/2020 15:17:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `coupon_type` int(11) NULL DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `discount` double NOT NULL,
  `discount_money` double NOT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `status` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `target_money` double NOT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (1, 'save', 2, 'save', 0, 0, '2020-06-22 15:15:49', 1, '2020-06-21 15:15:49', '1', 0, 0);
INSERT INTO `coupon` VALUES (2, 'save', 2, 'save', 0, 0, '2020-06-22 15:15:49', -1, '2020-06-21 15:15:49', '1', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
