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

 Date: 24/06/2020 18:07:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill_new
-- ----------------------------
DROP TABLE IF EXISTS `bill_new`;
CREATE TABLE `bill_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `credit` double NULL DEFAULT NULL,
  `mid` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bizregion_new
-- ----------------------------
DROP TABLE IF EXISTS `bizregion_new`;
CREATE TABLE `bizregion_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment_new
-- ----------------------------
DROP TABLE IF EXISTS `comment_new`;
CREATE TABLE `comment_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `oid` int(11) NULL DEFAULT NULL,
  `rate` double NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `url` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for coupon_new
-- ----------------------------
DROP TABLE IF EXISTS `coupon_new`;
CREATE TABLE `coupon_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `coupon_type` int(11) NULL DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `discount` double NULL DEFAULT NULL,
  `discount_money` double NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `status` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `target_money` double NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hotel_new
-- ----------------------------
DROP TABLE IF EXISTS `hotel_new`;
CREATE TABLE `hotel_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `biz_region` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `hotel_description` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `hotel_name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `hotel_serve` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `hotel_star` int(11) NULL DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `manager_id` int(11) NULL DEFAULT NULL,
  `phone_num` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `picture` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hoteledit_new
-- ----------------------------
DROP TABLE IF EXISTS `hoteledit_new`;
CREATE TABLE `hoteledit_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `hotel_description` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `hotel_name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `hotel_serve` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `manager_id` int(11) NULL DEFAULT NULL,
  `phone_num` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `picture` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `reply_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `reply_time` datetime(0) NULL DEFAULT NULL,
  `status` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hotelorder_new
-- ----------------------------
DROP TABLE IF EXISTS `hotelorder_new`;
CREATE TABLE `hotelorder_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_in_date` datetime(0) NULL DEFAULT NULL,
  `check_out_date` datetime(0) NULL DEFAULT NULL,
  `client_name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `have_child` bit(1) NOT NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `hotel_name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `order_state` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `people_num` int(11) NULL DEFAULT NULL,
  `phone_number` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `price` double NULL DEFAULT NULL,
  `real_cancel_date` datetime(0) NULL DEFAULT NULL,
  `real_check_in_date` datetime(0) NULL DEFAULT NULL,
  `real_check_out_date` datetime(0) NULL DEFAULT NULL,
  `real_error_date` datetime(0) NULL DEFAULT NULL,
  `real_pass_date` datetime(0) NULL DEFAULT NULL,
  `real_refuse_date` datetime(0) NULL DEFAULT NULL,
  `room_num` int(11) NULL DEFAULT NULL,
  `room_type` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hotelroom_new
-- ----------------------------
DROP TABLE IF EXISTS `hotelroom_new`;
CREATE TABLE `hotelroom_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cur_num` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `room_type` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `serve` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `total` int(11) NULL DEFAULT NULL,
  `url` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `valid_people` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hotelserve_new
-- ----------------------------
DROP TABLE IF EXISTS `hotelserve_new`;
CREATE TABLE `hotelserve_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hotelsituation_new
-- ----------------------------
DROP TABLE IF EXISTS `hotelsituation_new`;
CREATE TABLE `hotelsituation_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_num` int(11) NULL DEFAULT NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `order_bad_num` int(11) NULL DEFAULT NULL,
  `order_done_num` int(11) NULL DEFAULT NULL,
  `room_total_number` int(11) NULL DEFAULT NULL,
  `today` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `total_client_num` int(11) NULL DEFAULT NULL,
  `vip_num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply_new
-- ----------------------------
DROP TABLE IF EXISTS `reply_new`;
CREATE TABLE `reply_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `username` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roomtype_new
-- ----------------------------
DROP TABLE IF EXISTS `roomtype_new`;
CREATE TABLE `roomtype_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_new
-- ----------------------------
DROP TABLE IF EXISTS `user_new`;
CREATE TABLE `user_new`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `credit` double NOT NULL,
  `email` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `password` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `phone_number` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `totalcredit` double NOT NULL,
  `url` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_name` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_type` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `vip_info` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `vip_type` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
