

SET FOREIGN_KEY_CHECKS=0;

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
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(50) DEFAULT NULL,
  `comm_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

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
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(50) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `type` varchar(50) DEFAULT NULL COMMENT '商品类型',
  `price` varchar(50) DEFAULT NULL COMMENT '原价格',
  `saleprice` varchar(50) DEFAULT NULL COMMENT '折扣价格范围',
  `paytype` varchar(50) DEFAULT NULL COMMENT '支付类型',
  `distributiontype` varchar(50) DEFAULT NULL COMMENT '配送方式',
  `describe` varchar(500) DEFAULT NULL COMMENT '描述',
  `geom` geometry DEFAULT NULL,
  `time` varchar(500) DEFAULT NULL COMMENT '折扣时间',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货品表';

-- ----------------------------
-- Table structure for product_imgs
-- ----------------------------
DROP TABLE IF EXISTS `product_imgs`;
CREATE TABLE `product_imgs` (
  `id` varchar(50) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `istype` varchar(255) DEFAULT NULL COMMENT '是否主图',
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_pros
-- ----------------------------
DROP TABLE IF EXISTS `product_pros`;
CREATE TABLE `product_pros` (
  `id` varchar(255) DEFAULT NULL,
  `goods_id` varchar(255) DEFAULT NULL,
  `proname_id` varchar(255) DEFAULT NULL,
  `provalue_id` varchar(255) DEFAULT NULL,
  `issku` int(11) DEFAULT '0' COMMENT '是否sku 默认否',
  `sku_id` varchar(255) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` varchar(50) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `auth` int(50) DEFAULT NULL,
  `geom` geometry DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- View structure for user_geom
-- ----------------------------
DROP VIEW IF EXISTS `user_geom`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_geom` AS select `user`.`id` AS `id`,st_distance_sphere(point(119.29648,26.074506),`user`.`geom`) AS `distance`,st_astext(`user`.`geom`) AS `geom` from `user` where st_contains(st_makeenvelope(point((119.29648 + (10 / 111)),(26.074506 + (10 / 111))),point((119.29648 - (10 / 111)),(26.074506 - (10 / 111)))),`user`.`geom`) order by `distance` limit 10 ;
