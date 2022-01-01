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

 Date: 24/06/2020 18:06:57
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
-- Records of bill_new
-- ----------------------------
INSERT INTO `bill_new` VALUES (1, '测试账单', '2020-06-24 18:04:25', 0, 7, 1);

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
-- Records of bizregion_new
-- ----------------------------
INSERT INTO `bizregion_new` VALUES (1, '2020-06-24 18:04:25', '老门东', 7);
INSERT INTO `bizregion_new` VALUES (2, '2020-06-24 18:04:25', '中山陵', 7);
INSERT INTO `bizregion_new` VALUES (3, '2020-06-24 18:04:25', '南京大学仙林校区', 7);
INSERT INTO `bizregion_new` VALUES (4, '2020-06-24 18:04:25', '南京大学鼓楼校区', 7);

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
-- Records of comment_new
-- ----------------------------
INSERT INTO `comment_new` VALUES (1, '评论1号订单', '2020-06-24 18:04:25', 1, 2.5, 1, 'https://software3.oss-cn-beijing.aliyuncs.com/2020-06-21/1592732112963-4a00d8e54e934bd89e.jpeg');
INSERT INTO `comment_new` VALUES (2, '评论1号订单', '2020-06-24 18:04:25', 2, 0, 1, 'https://software3.oss-cn-beijing.aliyuncs.com/2020-06-21/1592732112963-4a00d8e54e934bd89e.jpeg');
INSERT INTO `comment_new` VALUES (3, '评论1号订单', '2020-06-24 18:04:25', 3, 5, 1, 'https://software3.oss-cn-beijing.aliyuncs.com/2020-06-21/1592732112963-4a00d8e54e934bd89e.jpeg');

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
-- Records of coupon_new
-- ----------------------------
INSERT INTO `coupon_new` VALUES (1, '生日优惠券', 0, '生日优惠券描述', 0, 100, '2021-06-24 18:04:25', 1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (2, '多间优惠券', 1, '多间优惠券描述', 0, 100, '2021-06-24 18:04:25', 1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (3, '满减优惠券', 2, '满减优惠券描述', 0, 100, '2021-06-24 18:04:25', 1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (4, '限时优惠券', 3, '限时优惠券描述', 0, 100, '2021-06-24 18:04:25', 1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (5, '生日优惠券', 0, '生日优惠券描述', 0, 100, '2021-06-24 18:04:25', -1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (6, '多间优惠券', 1, '多间优惠券描述', 0, 100, '2021-06-24 18:04:25', -1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (7, '满减优惠券', 2, '满减优惠券描述', 0, 100, '2021-06-24 18:04:25', -1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (8, '限时优惠券', 3, '限时优惠券描述', 0, 100, '2021-06-24 18:04:25', -1, '2020-06-24 18:04:25', '0', 300, 0);
INSERT INTO `coupon_new` VALUES (9, '限时优惠券', 3, '限时优惠券描述', 0, 100, '2021-06-24 18:04:25', -1, '2020-06-24 18:04:25', '1', 300, 0);

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
-- Records of hotel_new
-- ----------------------------
INSERT INTO `hotel_new` VALUES (1, '南京大学仙林大道', '南京大学仙林校区', '2020-06-24 18:04:25', '七天酒店，永远滴神', '七天酒店', 'WIFI|热水', 0, 0, 0, 4, '18851083388', 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/2.jpg', 1.25);
INSERT INTO `hotel_new` VALUES (2, '南京大学广州路', '南京大学鼓楼校区', '2020-06-24 18:04:25', '维度酒店，永远滴神', '维度酒店', 'WIFI|热水', 2, 0, 0, 5, '18851083388', 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/2.jpg', 2.5);
INSERT INTO `hotel_new` VALUES (3, '南京大学老门东', '老门东', '2020-06-24 18:04:25', '老门东度假酒店，永远滴神', '老门东度假酒店', 'WIFI', 4, 0, 0, 6, '18851083388', 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/2.jpg', 3.75);

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
-- Records of hoteledit_new
-- ----------------------------
INSERT INTO `hoteledit_new` VALUES (1, '2020-06-24 18:04:25', '七天酒店信息修改申请', '七天酒店修改', '', 4, '18851083388', '', NULL, NULL, '0');
INSERT INTO `hoteledit_new` VALUES (2, '2020-06-24 18:04:25', '维度酒店信息修改申请', '维度酒店修改', '', 5, '18851083388', '', NULL, NULL, '0');
INSERT INTO `hoteledit_new` VALUES (3, '2020-06-24 18:04:25', '老门东酒店信息修改申请', '老门东度假酒店', '', 6, '18851083388', '', '通过申请', '2020-06-25 18:04:25', '1');

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
-- Records of hotelorder_new
-- ----------------------------
INSERT INTO `hotelorder_new` VALUES (1, '2020-06-25 18:04:25', '2020-06-26 18:04:25', '客户一', '2020-06-24 18:04:25', b'0', 1, '七天酒店', '0', 2, '18851083188', 200, NULL, NULL, NULL, NULL, NULL, NULL, 1, '标准间', 1);
INSERT INTO `hotelorder_new` VALUES (2, '2020-06-25 18:04:25', '2020-06-26 18:04:25', '客户二', '2020-06-24 18:04:25', b'1', 1, '七天酒店', '0', 2, '18851083188', 200, NULL, NULL, NULL, NULL, NULL, NULL, 1, '大床房', 2);
INSERT INTO `hotelorder_new` VALUES (3, '2020-06-25 18:04:25', '2020-06-26 18:04:25', '客户三', '2020-06-24 18:04:25', b'1', 1, '七天酒店', '0', 2, '18851083188', 200, NULL, NULL, NULL, NULL, NULL, NULL, 1, '标准间', 3);
INSERT INTO `hotelorder_new` VALUES (4, '2020-06-25 18:04:25', '2020-06-26 18:04:25', '客户一', '2020-06-24 18:04:25', b'0', 1, '七天酒店', '5', 2, '18851083188', 200, NULL, '2020-06-25 18:04:25', '2020-06-26 18:04:25', NULL, '2020-06-24 23:04:25', NULL, 1, '标准间', 1);
INSERT INTO `hotelorder_new` VALUES (5, '2020-06-25 18:04:25', '2020-06-26 18:04:25', '客户一', '2020-06-24 18:04:25', b'0', 1, '七天酒店', '0', 2, '18851083188', 200, NULL, NULL, NULL, NULL, NULL, NULL, 1, '标准间', 1);
INSERT INTO `hotelorder_new` VALUES (6, '2020-06-25 18:04:25', '2020-06-26 18:04:25', '客户一', '2020-06-24 18:04:25', b'0', 1, '七天酒店', '0', 2, '18851083188', 200, NULL, NULL, NULL, NULL, NULL, NULL, 1, '标准间', 1);

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
-- Records of hotelroom_new
-- ----------------------------
INSERT INTO `hotelroom_new` VALUES (1, '10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10', 1, 200, '大床房', 'WIFI|热水', 10, '', 2);
INSERT INTO `hotelroom_new` VALUES (2, '10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10', 1, 200, '标准间', 'WIFI|热水', 10, '', 2);
INSERT INTO `hotelroom_new` VALUES (3, '10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10', 2, 200, '大床房', 'WIFI|热水', 10, '', 2);
INSERT INTO `hotelroom_new` VALUES (4, '10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10', 2, 200, '标准间', 'WIFI|热水', 10, '', 2);
INSERT INTO `hotelroom_new` VALUES (5, '10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10', 3, 200, '大床房', 'WIFI|热水', 10, '', 2);
INSERT INTO `hotelroom_new` VALUES (6, '10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10', 3, 200, '标准间', 'WIFI|热水', 10, '', 2);

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
-- Records of hotelserve_new
-- ----------------------------
INSERT INTO `hotelserve_new` VALUES (1, '2020-06-24 18:04:25', 'WIFI', 4);
INSERT INTO `hotelserve_new` VALUES (2, '2020-06-24 18:04:25', '热水', 4);

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
-- Records of hotelsituation_new
-- ----------------------------
INSERT INTO `hotelsituation_new` VALUES (1, 10, 1, 7, 6, 20, '2020-06-22', 8, 1);
INSERT INTO `hotelsituation_new` VALUES (2, 12, 2, 0, 8, 20, '2020-06-22', 6, 3);
INSERT INTO `hotelsituation_new` VALUES (3, 10, 1, 7, 6, 20, '2020-06-23', 8, 1);
INSERT INTO `hotelsituation_new` VALUES (4, 12, 2, 0, 8, 20, '2020-06-23', 6, 3);
INSERT INTO `hotelsituation_new` VALUES (5, 10, 2, 7, 6, 20, '2020-06-24', 8, 1);
INSERT INTO `hotelsituation_new` VALUES (6, 12, 3, 0, 8, 20, '2020-06-24', 6, 3);

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
-- Records of reply_new
-- ----------------------------
INSERT INTO `reply_new` VALUES (1, 1, '回复一号订单的一号回复', '2020-06-24 18:04:25', -1, 1, '客户一');
INSERT INTO `reply_new` VALUES (2, 1, '回复一号订单的二号回复', '2020-06-24 19:04:25', -1, 1, '客户一');
INSERT INTO `reply_new` VALUES (3, 1, '回复一号回复的一号二级回复', '2020-06-24 20:04:25', 1, 1, '客户一');
INSERT INTO `reply_new` VALUES (4, 1, '回复一号回复的二号二级回复', '2020-06-24 21:04:25', 1, 1, '客户一');
INSERT INTO `reply_new` VALUES (5, 2, '回复二号订单', '2020-06-24 22:04:25', -1, 1, '客户一');
INSERT INTO `reply_new` VALUES (6, 3, '回复三号订单', '2020-06-24 23:04:25', -1, 1, '客户一');

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
-- Records of roomtype_new
-- ----------------------------
INSERT INTO `roomtype_new` VALUES (1, '2020-06-24 18:04:25', '大床房', 4);
INSERT INTO `roomtype_new` VALUES (2, '2020-06-24 18:04:25', '标准间', 4);
INSERT INTO `roomtype_new` VALUES (3, '2020-06-24 18:04:25', '总统套房', 4);

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

-- ----------------------------
-- Records of user_new
-- ----------------------------
INSERT INTO `user_new` VALUES (1, 10, '1@qq.com', '123456', '18851083188', 10, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '客户一', '0', NULL, '0');
INSERT INTO `user_new` VALUES (2, 200, '2@qq.com', '123456', '18851083188', 200, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '客户二', '0', NULL, '1');
INSERT INTO `user_new` VALUES (3, 200, '3@qq.com', '123456', '18851083188', 200, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '客户三', '0', '2000-09-01', '1');
INSERT INTO `user_new` VALUES (4, 0, '4@qq.com', '123456', '18851083188', 0, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '七天酒店工作人员', '1', NULL, NULL);
INSERT INTO `user_new` VALUES (5, 0, '5@qq.com', '123456', '18851083188', 0, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '维度酒店工作人员', '1', NULL, NULL);
INSERT INTO `user_new` VALUES (6, 0, '6@qq.com', '123456', '18851083188', 0, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '老门东度假酒店工作人员', '1', NULL, NULL);
INSERT INTO `user_new` VALUES (7, 0, '7@qq.com', '123456', '18851083188', 0, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '网站营销人员', '2', NULL, NULL);
INSERT INTO `user_new` VALUES (8, 0, '8@qq.com', '123456', '18851083188', 0, 'https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg', '网站管理员', '3', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
