/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : home

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-09-06 10:10:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` varchar(255) DEFAULT NULL,
  `cate_id` varchar(255) DEFAULT NULL,
  `name_zh` varchar(255) DEFAULT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `logoimg` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `sort` varchar(255) DEFAULT '0',
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` varchar(255) DEFAULT NULL,
  `lmname` varchar(255) DEFAULT NULL COMMENT '类目名称',
  `pare_id` varchar(255) DEFAULT NULL,
  `ispare` int(11) DEFAULT '0' COMMENT '是否顶级',
  `sorts` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '生鲜', '0', '1', '0');
INSERT INTO `category` VALUES ('2', '当季水果', '1', '0', '1');
INSERT INTO `category` VALUES ('3', '蔬菜蛋品', '1', '0', '2');
INSERT INTO `category` VALUES ('4', '热销水果', '2', '0', '1');
INSERT INTO `category` VALUES ('5', '地标水果', '2', '0', '2');
INSERT INTO `category` VALUES ('6', '时令水果', '2', '0', '3');
INSERT INTO `category` VALUES ('7', '热带水果', '2', '0', '4');
INSERT INTO `category` VALUES ('8', '其他', '2', '0', '5');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `product_id` varchar(50) DEFAULT NULL,
  `star` varchar(50) DEFAULT NULL,
  `isorder` varchar(50) DEFAULT NULL,
  `comment` varchar(5000) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(50) DEFAULT NULL,
  `comm_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for farm
-- ----------------------------
DROP TABLE IF EXISTS `farm`;
CREATE TABLE `farm` (
  `id` varchar(50) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `auth` int(50) DEFAULT '0' COMMENT '是否授权1',
  `phone` varchar(255) DEFAULT NULL,
  `disscope` int(11) DEFAULT NULL COMMENT '配送范围',
  `address` varchar(255) DEFAULT NULL,
  `geom` geometry DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '0无效',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of farm
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(50) DEFAULT NULL COMMENT '订单',
  `user_id` varchar(50) DEFAULT NULL,
  `product_id` varchar(50) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `paytype` varchar(50) DEFAULT NULL,
  `distributiontype` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(50) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `farm_id` varchar(255) DEFAULT NULL COMMENT '店铺',
  `category_id` varchar(255) DEFAULT NULL COMMENT '分类',
  `brand_id` varchar(50) DEFAULT NULL COMMENT '品牌',
  `price` varchar(50) DEFAULT NULL COMMENT '原价格',
  `saleprice` varchar(50) DEFAULT NULL COMMENT '折扣价格范围',
  `paytype` varchar(50) DEFAULT NULL COMMENT '支付类型',
  `distributiontype` varchar(50) DEFAULT NULL COMMENT '配送方式',
  `describe` varchar(500) DEFAULT NULL COMMENT '描述',
  `geom` geometry DEFAULT NULL,
  `time` varchar(500) DEFAULT NULL COMMENT '折扣时间',
  `status` int(11) DEFAULT '0' COMMENT '1上架',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品表';

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for product_imgs
-- ----------------------------
DROP TABLE IF EXISTS `product_imgs`;
CREATE TABLE `product_imgs` (
  `id` varchar(50) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `istype` varchar(255) DEFAULT NULL COMMENT '是否主图',
  `status` int(11) DEFAULT '1' COMMENT '1有效0无效',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_imgs
-- ----------------------------

-- ----------------------------
-- Table structure for product_pros
-- ----------------------------
DROP TABLE IF EXISTS `product_pros`;
CREATE TABLE `product_pros` (
  `id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `proname_id` varchar(255) DEFAULT NULL,
  `provalue_id` varchar(255) DEFAULT NULL,
  `issku` int(11) DEFAULT '0' COMMENT '是否sku 默认否',
  `sku_id` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '0无效',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_pros
-- ----------------------------

-- ----------------------------
-- Table structure for proname
-- ----------------------------
DROP TABLE IF EXISTS `proname`;
CREATE TABLE `proname` (
  `id` varchar(255) DEFAULT NULL,
  `attrname` varchar(255) DEFAULT NULL,
  `cate_id` varchar(255) DEFAULT NULL COMMENT '类目id',
  `isinput` int(11) DEFAULT '0' COMMENT '是否输入属性',
  `issales` int(11) DEFAULT '0' COMMENT '是否销售属性',
  `iscolor` int(11) DEFAULT '0' COMMENT '是否颜色属性',
  `isrequired` int(11) DEFAULT '0' COMMENT '是否必填',
  `ismselect` int(11) DEFAULT '0' COMMENT '是否多选',
  `isselect` int(11) DEFAULT '0' COMMENT '是否枚举select',
  `sorts` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '0无效',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proname
-- ----------------------------

-- ----------------------------
-- Table structure for provalue
-- ----------------------------
DROP TABLE IF EXISTS `provalue`;
CREATE TABLE `provalue` (
  `id` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `proname_id` varchar(255) DEFAULT NULL COMMENT '属性名称id',
  `status` int(11) DEFAULT '1' COMMENT '0不可用',
  `sorts` int(11) DEFAULT NULL,
  `cate_id` varchar(255) DEFAULT NULL COMMENT '类目id',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of provalue
-- ----------------------------

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` varchar(50) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL COMMENT '库存',
  `price` varchar(255) DEFAULT NULL,
  `product_id` varchar(50) DEFAULT NULL COMMENT '产品id',
  `status` int(11) DEFAULT '1' COMMENT '0无效',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sku
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `wxid` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `img` varchar(500) DEFAULT NULL,
  `home` varchar(500) DEFAULT NULL,
  `geom` geometry DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zyx', 'osBDb4jBLMR9Pc5Oe4KbeLSRDvOA', null, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epZMhxC2Lfk7kHibA7YRicdNrLIjwbXichAmOSWbcRR8OXbsHDSndOTEQib8pP1ibVZgCxJvP2yb8YjV9A/132', null, GeomFromText('POINT(108.94878 34.22259)'), '2018-08-21 11:57:47');

-- ----------------------------
-- View structure for user_geom
-- ----------------------------
DROP VIEW IF EXISTS `user_geom`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_geom` AS select `user`.`id` AS `id`,st_distance_sphere(point(119.29648,26.074506),`user`.`geom`) AS `distance`,st_astext(`user`.`geom`) AS `geom` from `user` where st_contains(st_makeenvelope(point((119.29648 + (10 / 111)),(26.074506 + (10 / 111))),point((119.29648 - (10 / 111)),(26.074506 - (10 / 111)))),`user`.`geom`) order by `distance` limit 10 ;
