/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : wmms

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-10-09 04:56:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `prd_census`
-- ----------------------------
DROP TABLE IF EXISTS `prd_census`;
CREATE TABLE `prd_census` (
  `census_id` varchar(32) NOT NULL,
  `company_id` varchar(32) DEFAULT '',
  `meter_id` varchar(32) DEFAULT '',
  `pipe_type` varchar(20) DEFAULT '',
  `valve_type` varchar(20) DEFAULT '',
  `valve_size` int(11) DEFAULT NULL,
  `flange_hole_num` int(11) DEFAULT NULL,
  `meter_last_value` double DEFAULT NULL,
  `meter_value` double DEFAULT NULL,
  `census_place` varchar(60) DEFAULT '',
  `census_image` varchar(40) DEFAULT '',
  `census_desc` text,
  `census_result` varchar(10) DEFAULT '',
  `census_user_id` varchar(32) DEFAULT '',
  `check_time` date DEFAULT NULL,
  `has_collocation` int(11) NOT NULL DEFAULT '0',
  `census_status` int(11) NOT NULL DEFAULT '0',
  `census_action` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`census_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of prd_census
-- ----------------------------
INSERT INTO `prd_census` VALUES ('284038a2804342b483ed6b8cfe94f98a', '873caa4e3b2f45b9a5b7ff2ca4ff888d', 'SY001', 'PE', '截止阀', '40', '5', '5000', '5000', '都市之门B座', null, '有问题', '1', null, '2018-06-02', '0', '0', '1');
INSERT INTO `prd_census` VALUES ('2e895ac7a3d04a6396a098a20aec4a85', '873caa4e3b2f45b9a5b7ff2ca4ff888d', 'SY001', 'PE', '截止阀', '40', '5', '0', '5000', '都市之门B座', null, '有问题', '1', null, '2018-06-02', '0', '0', '1');
INSERT INTO `prd_census` VALUES ('43fad85b98314859b6f142cc0590c9e8', 'D0103', '000001', 'PE', '截止阀', '40', '3', '500.5', '5000.5', '都市之门B座', null, '无问题', '0', null, '2018-06-20', '0', '0', '0');
INSERT INTO `prd_census` VALUES ('6b3e444de87e460bbbae4dcbe06a8f41', '6b0af6c62ad642c8bc540ef5c14b5dfb', 'SY001', 'PE', '截止阀', '40', '5', '0', '5000', '无', null, '正常', '0', null, '2018-06-19', '0', '0', '0');
INSERT INTO `prd_census` VALUES ('8ee8c7835453446eb2ca6d57c6a4d472', 'D0103', '000002', 'PE', '截止阀', '40', '3', '2000.2', '5000.5', '都市之门B座', null, '无问题', '1', null, '2018-06-20', '0', '0', '1');
INSERT INTO `prd_census` VALUES ('8ff64319e580482b9e52e7500f1010cb', '873caa4e3b2f45b9a5b7ff2ca4ff888d', '000002', '', '截止阀', '40', '2', '5000.5', '500', '都市之门B座', null, '有问题', '1', null, '2018-06-07', '0', '0', '1');

-- ----------------------------
-- Table structure for `prd_chaobiao`
-- ----------------------------
DROP TABLE IF EXISTS `prd_chaobiao`;
CREATE TABLE `prd_chaobiao` (
  `chaobiao_id` varchar(32) NOT NULL,
  `meter_id` varchar(32) DEFAULT NULL,
  `chaobiao_date` date DEFAULT NULL,
  `pre_value` double DEFAULT NULL,
  `current_value` double DEFAULT NULL,
  `image` varchar(60) DEFAULT NULL,
  `submiter_id` varchar(32) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`chaobiao_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prd_chaobiao
-- ----------------------------
INSERT INTO `prd_chaobiao` VALUES ('2deeac25', '000001', '2018-06-21', '2000', '6000', '', 'U01', '2018-06-01', '2018-06-20');
INSERT INTO `prd_chaobiao` VALUES ('9cb16575', 'SY001', '2018-06-21', '0', '3000', '', '8c45183e52464f88a9fc55adbb709e78', '2018-06-01', '2018-06-19');
INSERT INTO `prd_chaobiao` VALUES ('cba715e9', '000001', '2018-06-21', '2000', '6000', '', 'U01', '2018-06-01', '2018-06-20');

-- ----------------------------
-- Table structure for `prd_collocation`
-- ----------------------------
DROP TABLE IF EXISTS `prd_collocation`;
CREATE TABLE `prd_collocation` (
  `id` varchar(32) NOT NULL,
  `old_meter_id` varchar(32) DEFAULT '',
  `new_meter_id` varchar(32) DEFAULT NULL,
  `census_id` varchar(32) DEFAULT NULL,
  `meter_old_type_id` varchar(32) DEFAULT NULL,
  `meter_new_type_id` varchar(32) DEFAULT NULL,
  `range_ratio` int(11) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `operator_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prd_collocation
-- ----------------------------
INSERT INTO `prd_collocation` VALUES ('1', 'SY001', 'SY002', 'de2b810c6b174bd1bb004ac8abaa3fc6', '42f532e2', '95256e14', '15', '2018-06-19', null, null);

-- ----------------------------
-- Table structure for `prd_meter`
-- ----------------------------
DROP TABLE IF EXISTS `prd_meter`;
CREATE TABLE `prd_meter` (
  `meter_id` varchar(32) NOT NULL,
  `meter_name` varchar(60) NOT NULL,
  `reg_id` varchar(32) DEFAULT NULL COMMENT '所属地区',
  `meter_company_id` varchar(32) DEFAULT '',
  `meter_type_id` varchar(32) DEFAULT '',
  `meter_type` varchar(60) DEFAULT NULL,
  `parent_meter_id` varchar(32) DEFAULT '',
  `meter_level` varchar(32) DEFAULT NULL,
  `meter_value` double DEFAULT NULL,
  `meter_use_time` int(11) DEFAULT NULL,
  `meter_address` varchar(80) DEFAULT NULL,
  `meter_installer` varchar(20) DEFAULT NULL,
  `meter_owner_id` varchar(20) DEFAULT NULL,
  `meter_creater_id` varchar(20) DEFAULT NULL,
  `meter_create_time` date DEFAULT NULL,
  `meter_editer_id` varchar(20) DEFAULT NULL,
  `meter_edit_time` date DEFAULT NULL,
  `meter_status` int(11) NOT NULL DEFAULT '1',
  `meter_setup_time` date DEFAULT NULL,
  `meter_last_check_time` date DEFAULT NULL,
  PRIMARY KEY (`meter_id`),
  KEY `reg_id` (`reg_id`),
  KEY `meter_company_id` (`meter_company_id`),
  KEY `meter_type_id` (`meter_type_id`),
  CONSTRAINT `meter_company_id` FOREIGN KEY (`meter_company_id`) REFERENCES `sys_department` (`dep_id`),
  CONSTRAINT `meter_type_id` FOREIGN KEY (`meter_type_id`) REFERENCES `prd_meter_type_define` (`meter_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prd_meter
-- ----------------------------
INSERT INTO `prd_meter` VALUES ('000001', '宝鸡1号表', '1', 'D0103', 'c57d14de', '机械水表', '', '1', '6000', '10', null, null, null, null, null, null, null, '-2', '2015-06-15', '2015-06-15');
INSERT INTO `prd_meter` VALUES ('000002', '宝鸡2号表', '0', 'D0103', '42f532e2', '机械水表', '', '1', '500', '10', null, null, null, null, null, null, null, '-1', '2010-06-13', '2018-06-13');
INSERT INTO `prd_meter` VALUES ('SY001', '出厂总表', '0', '6b0af6c62ad642c8bc540ef5c14b5dfb', 'c72cdbc7', '远传水表', '', '1', '5000', '5', null, null, null, null, null, null, null, '1', '2018-06-05', '2018-06-05');

-- ----------------------------
-- Table structure for `prd_meter_type_define`
-- ----------------------------
DROP TABLE IF EXISTS `prd_meter_type_define`;
CREATE TABLE `prd_meter_type_define` (
  `meter_type_id` varchar(32) NOT NULL COMMENT '编号',
  `meter_brand` varchar(20) DEFAULT '' COMMENT '品牌名称',
  `meter_size` int(11) NOT NULL DEFAULT '0' COMMENT '口径',
  `meter_type` varchar(20) DEFAULT '' COMMENT '具体规格名称',
  `meter_size_name` varchar(20) DEFAULT '' COMMENT '口径名称',
  `meter_type_name` varchar(20) DEFAULT '' COMMENT '具体型号名称',
  PRIMARY KEY (`meter_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prd_meter_type_define
-- ----------------------------
INSERT INTO `prd_meter_type_define` VALUES ('42f532e2', '亚翔', '100', 'yx100', '100', 'LXS');
INSERT INTO `prd_meter_type_define` VALUES ('53ddcb14', '真兰', '40', 'NS40', '40', 'LXS');
INSERT INTO `prd_meter_type_define` VALUES ('71185f7c', '胜德', '40', 'SD40', '40', 'LXS');
INSERT INTO `prd_meter_type_define` VALUES ('79f344a5', '真兰', '65', 'DD65', '65', 'LXS');
INSERT INTO `prd_meter_type_define` VALUES ('95256e14', '真兰', '50', 'NS50', '50', 'LXS');
INSERT INTO `prd_meter_type_define` VALUES ('9a88fb0b', '胜德', '200', 'SD200', '200', 'LXS');
INSERT INTO `prd_meter_type_define` VALUES ('c57d14de', '宁波', '65', 'DN40', '65', 'LXS');
INSERT INTO `prd_meter_type_define` VALUES ('c72cdbc7', '宁波', '40', 'DN40', '40', 'LXS');

-- ----------------------------
-- Table structure for `prd_order`
-- ----------------------------
DROP TABLE IF EXISTS `prd_order`;
CREATE TABLE `prd_order` (
  `prd_order_id` varchar(32) NOT NULL COMMENT '工单编码',
  `prd_id` varchar(32) DEFAULT NULL COMMENT '仪表编码',
  `prd_order_type` int(11) DEFAULT NULL COMMENT '工单类型：-1待更换，-2待检验',
  `submit_user_id` varchar(32) DEFAULT NULL COMMENT '提交人编码',
  `submit_datetime` datetime DEFAULT NULL COMMENT '提交时间',
  `handle_user_id` varchar(32) DEFAULT NULL COMMENT '处理人编码',
  `handle_datetime` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_state` int(11) DEFAULT NULL COMMENT '处理状态：0已处理，1待处理',
  `send_email` varchar(255) DEFAULT NULL COMMENT '发件地址',
  `collocation_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`prd_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工单记录表';

-- ----------------------------
-- Records of prd_order
-- ----------------------------
INSERT INTO `prd_order` VALUES ('d0559306b0e542f59adfe7a3018ebb78', 'SY001', '-2', 'U01', '2018-06-02 00:00:00', null, null, null, null, '');
INSERT INTO `prd_order` VALUES ('fdf82fab5d354e65b1c34b57d8f56e73', 'SY001', '-2', 'U01', '2018-06-02 00:00:00', null, null, null, null, '');

-- ----------------------------
-- Table structure for `rpt_amount_mon`
-- ----------------------------
DROP TABLE IF EXISTS `rpt_amount_mon`;
CREATE TABLE `rpt_amount_mon` (
  `rpt_meter_id` varchar(32) NOT NULL,
  `rpt_amount_year` varchar(6) NOT NULL COMMENT '年份',
  `rpt_amount_month` varchar(4) NOT NULL COMMENT '月份',
  `gongshui_value` varchar(255) DEFAULT NULL COMMENT '供水量',
  `chanshui_value` varchar(255) DEFAULT NULL COMMENT '产水量',
  PRIMARY KEY (`rpt_meter_id`,`rpt_amount_year`,`rpt_amount_month`) USING BTREE,
  CONSTRAINT `fk_rpt_amount_mon` FOREIGN KEY (`rpt_meter_id`) REFERENCES `prd_meter` (`meter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rpt_amount_mon
-- ----------------------------

-- ----------------------------
-- Table structure for `rpt_amount_year`
-- ----------------------------
DROP TABLE IF EXISTS `rpt_amount_year`;
CREATE TABLE `rpt_amount_year` (
  `rpt_meter_id` varchar(32) NOT NULL,
  `rpt_amount_year` varchar(6) NOT NULL COMMENT '年份',
  `gongshui_value` varchar(255) DEFAULT NULL COMMENT '供水量',
  `chanshui_value` varchar(255) DEFAULT NULL COMMENT '产水量',
  PRIMARY KEY (`rpt_meter_id`,`rpt_amount_year`) USING BTREE,
  CONSTRAINT `fk_rpt_amount_year` FOREIGN KEY (`rpt_meter_id`) REFERENCES `prd_meter` (`meter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rpt_amount_year
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `dep_id` varchar(32) NOT NULL COMMENT '部门编码',
  `dep_code` varchar(50) DEFAULT '' COMMENT '部门代码',
  `dep_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `dep_note` varchar(200) DEFAULT '' COMMENT '部门说明',
  `pdep_id` varchar(32) DEFAULT '' COMMENT '上级部门编码',
  `dep_state` int(11) DEFAULT '0' COMMENT '部门状态（0：正常   9：停用）',
  `dep_order` int(11) DEFAULT NULL COMMENT '部门序号',
  PRIMARY KEY (`dep_id`) USING BTREE,
  KEY `idx_dep_state` (`dep_state`) USING BTREE,
  KEY `idx_dep_code_state` (`dep_code`,`dep_state`) USING BTREE,
  KEY `idx_dep_id_state` (`dep_id`,`dep_state`) USING BTREE,
  KEY `idx_pdep_id_state` (`pdep_id`,`dep_state`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门组织';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('0f327ed28ba54dcfbefd89e25b9d67d5', '2018-06-20 21:35:13', '自来水公司', '', '5b441283c29744ada8a9488b88c22999', '0', '30');
INSERT INTO `sys_department` VALUES ('5b441283c29744ada8a9488b88c22999', '2018-06-20 21:26:45', '蒲城税务', '', 'D01', '0', '30');
INSERT INTO `sys_department` VALUES ('6b0af6c62ad642c8bc540ef5c14b5dfb', '2018-06-20 16:22:36', '三原县水务公司', '', 'D01', '0', '30');
INSERT INTO `sys_department` VALUES ('873caa4e3b2f45b9a5b7ff2ca4ff888d', '2018-06-20 16:23:02', '大客户1', '', '6b0af6c62ad642c8bc540ef5c14b5dfb', '0', '1');
INSERT INTO `sys_department` VALUES ('98c42c2ffce449acb87310a8ca5d57ed', '2018-06-20 19:57:44', '户县燃气公司', '', 'ba39d05383a74ff6ae213594be48e7b9', '0', '1');
INSERT INTO `sys_department` VALUES ('ba39d05383a74ff6ae213594be48e7b9', '2018-06-20 19:57:02', '户县水务公司', '', 'D01', '0', '2');
INSERT INTO `sys_department` VALUES ('c6adc56ce153400885d16ebfc8a9872a', '2018-06-20 16:23:28', '大客户3', '', '6b0af6c62ad642c8bc540ef5c14b5dfb', '0', '3');
INSERT INTO `sys_department` VALUES ('D01', 'D01', '真兰表务服务', '真兰表务服务', '', '0', '0');
INSERT INTO `sys_department` VALUES ('D0101', 'D0101', '西安分公司', '西安分公司', 'D01', '-1', '1');
INSERT INTO `sys_department` VALUES ('D010101', 'D010101', '网络部', '网络部', 'D0101', '-1', '1');
INSERT INTO `sys_department` VALUES ('D0102', 'D0102', '铜川分公司', '铜川分公司', 'D01', '-1', '2');
INSERT INTO `sys_department` VALUES ('D0103', 'D0103', '宝鸡分公司', '宝鸡分公司', 'D01', '-1', '3');
INSERT INTO `sys_department` VALUES ('D0104', 'D0104', '咸阳分公司', '咸阳分公司', 'D01', '-1', '10');
INSERT INTO `sys_department` VALUES ('D0105', 'D0105', '渭南分公司', '渭南分公司', 'D01', '-1', '5');
INSERT INTO `sys_department` VALUES ('D0106', 'D0106', '延安分公司', '延安分公司', 'D01', '-1', '6');
INSERT INTO `sys_department` VALUES ('D0107', 'D0107', '汉中分公司', '汉中分公司', 'D01', '-1', '7');
INSERT INTO `sys_department` VALUES ('D0108', 'D0108', '榆林分公司', '榆林分公司', 'D01', '-1', '8');
INSERT INTO `sys_department` VALUES ('D0109', 'D0109', '安康分公司', '安康分公司', 'D01', '-1', '9');
INSERT INTO `sys_department` VALUES ('D0110', 'D0110', '商洛分公司', '商洛分公司', 'D01', '-1', '4');
INSERT INTO `sys_department` VALUES ('e054354a17e54fdc8ab0566a268c600a', '2018-05-28 19:02:50', '劳资公司', '', 'D01', '-1', '30');
INSERT INTO `sys_department` VALUES ('e091c0bbb2694babb0671f0fb7b3283d', '2018-05-28 19:02:33', '水务公司', '', 'D01', '-1', '30');
INSERT INTO `sys_department` VALUES ('e9506a872fb44a32b066b97b7287cc35', '2018-06-20 16:23:20', '大客户2', '', '6b0af6c62ad642c8bc540ef5c14b5dfb', '0', '2');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `sys_id` varchar(32) NOT NULL COMMENT '系统编码',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单编码',
  `menu_code` varchar(50) DEFAULT '' COMMENT '菜单代码',
  `menu_name` varchar(50) DEFAULT '' COMMENT '菜单名称',
  `menu_url` varchar(500) DEFAULT '' COMMENT '菜单URL',
  `menu_icon` varchar(50) DEFAULT '' COMMENT '菜单图标',
  `menu_note` varchar(500) DEFAULT '' COMMENT '菜单说明',
  `pmenu_id` varchar(32) DEFAULT '' COMMENT '上级菜单编码',
  `menu_state` int(11) DEFAULT '0' COMMENT '菜单状态（0：正常  9：停用）',
  `menu_order` int(11) DEFAULT NULL COMMENT '菜单序号',
  PRIMARY KEY (`sys_id`,`menu_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE,
  CONSTRAINT `sys_menu_ibfk_1` FOREIGN KEY (`sys_id`) REFERENCES `sys_system` (`sys_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('S00', 'S00M0101', 'S00M0101', '型号管理', 'tpl/meter/status/meter-type-list.html', 'xiao', '', 'S00', '0', '1');
INSERT INTO `sys_menu` VALUES ('S00', 'S00M0102', 'S00M0102', '仪表录入', 'tpl/meter/status/meter-add.html', 'xiao', '', 'S00', '0', '2');
INSERT INTO `sys_menu` VALUES ('S00', 'S00M0103', 'S00M0103', '仪表管理', 'tpl/meter/status/meter-status-list.html', 'xiao', '', 'S00', '0', '3');
INSERT INTO `sys_menu` VALUES ('S00', 'S00M0104', 'S00M0104', '仪表筛选', 'tpl/meter/manage/meter-list.html', 'xiao', '', 'S00', '0', '4');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M01', 'S01M01', '普查任务', '', 'xiao', '', 'S01', '0', '1');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M0101', 'S01M0101', '普查录入', 'tpl/meter/pucha/pucha-addmew.html', 'xiao', '', 'S01M01', '0', '1');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M0102', 'S01M0102', '普查记录', 'tpl/meter/pucha/pucha-list.html', 'xiao', '', 'S01M01', '0', '2');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M02', 'S01M02', '抄表任务', '', 'xiao', '', 'S01', '0', '2');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M0201', 'S01M0201', '抄表录入', 'tpl/meter/pucha/chaobiao-addnew.html', 'xiao', '', 'S01M02', '0', '1');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M0202', 'S01M0202', '抄表信息', 'tpl/meter/pucha/chaobiao-list.html', 'xiao', '', 'S01M02', '0', '2');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M03', 'S01M03', '工单任务', '', 'xiao', '', 'S01', '0', '3');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M0301', 'S01M0301', '待更换工单', 'tpl/meter/order/change-list.html', 'xiao', '', 'S01M03', '0', '1');
INSERT INTO `sys_menu` VALUES ('S01', 'S01M0302', 'S01M0302', '待检验工单', 'tpl/meter/order/check-list.html', 'xiao', '', 'S01M03', '0', '2');
INSERT INTO `sys_menu` VALUES ('S02', 'S02M0101', 'S02M0101', '普查统计', 'tpl/meter/pucha/pucha-check.html', 'xiao', '', 'S02', '0', '1');
INSERT INTO `sys_menu` VALUES ('S02', 'S02M0102', 'S02M0102', '年份汇总', 'tpl/meter/jiankong/year-sum.html', 'xiao', '', 'S02', '0', '2');
INSERT INTO `sys_menu` VALUES ('S02', 'S02M0103', 'S02M0103', '同比', 'tpl/meter/jiankong/tongbi.html', 'xiao', '', 'S02', '0', '3');
INSERT INTO `sys_menu` VALUES ('S02', 'S02M0104', 'S02M0104', '环比', 'tpl/meter/jiankong/huanbi.html', 'xiao', '', 'S02', '0', '4');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M01', 'S04M01', '用户角色管理', '', 'xiao', '', 'S04', '0', '2');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0101', 'S04M0101', '系统用户管理', 'tpl/system/user/user-list.html', 'xiao', '', 'S04M01', '0', '1');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0102', 'S04M0102', '系统角色管理', 'tpl/system/role/role-list.html', 'xiao', '', 'S04M01', '0', '2');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0103', 'S04M0103', '角色用户管理', 'tpl/system/role/data-auth.html', 'xiao', '', 'S04M01', '0', '3');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0104', 'S04M0104', '地市区县管理', 'tpl/system/region/sys-region-manage.html', 'xiao', '', 'S04M01', '0', '4');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M02', 'S04M02', '用户权限管理', '', 'xiao', '', 'S04', '0', '3');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0201', 'S04M0201', '用户管理区域', 'tpl/system/user/user_region_manage.html', 'xiao', '', 'S04M02', '0', '1');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0202', 'S04M0202', '角色权限管理', 'tpl/system/role/rolepermissions.html', 'xiao', '', 'S04M02', '0', '2');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M03', 'S04M03', '用户单位管理', '', 'xiao', '', 'S04', '0', '1');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0301', 'S04M0301', '组织机构管理', 'tpl/system/department/sys-departmen-manage.html', 'xiao', '', 'S04M03', '0', '1');
INSERT INTO `sys_menu` VALUES ('S04', 'S04M0302', 'S04M0302', '用户管理部门', 'tpl/system/user/user_department_manage.html', 'xiao', '', 'S04M03', '0', '2');
INSERT INTO `sys_menu` VALUES ('S04', 'S08M0101', 'S08M0101', '菜单管理', 'tpl/system/sys-menu-manage.html', 'xiao', '', 'S08', '0', '1');

-- ----------------------------
-- Table structure for `sys_region`
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region` (
  `reg_id` varchar(32) NOT NULL COMMENT '区域编码',
  `reg_code` varchar(50) DEFAULT '' COMMENT '区域代码',
  `reg_name` varchar(50) DEFAULT '' COMMENT '区域名称',
  `reg_note` varchar(200) DEFAULT '' COMMENT '区域说明',
  `preg_id` varchar(32) DEFAULT '' COMMENT '上级区域编码',
  `reg_state` int(11) DEFAULT '0' COMMENT '区域状态（0：正常   9：停用）',
  `reg_order` int(11) DEFAULT NULL COMMENT '区域序号',
  PRIMARY KEY (`reg_id`) USING BTREE,
  KEY `idx_reg_code` (`reg_code`) USING BTREE,
  KEY `idx_reg_id` (`reg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='行政区域';

-- ----------------------------
-- Records of sys_region
-- ----------------------------
INSERT INTO `sys_region` VALUES ('610000', 'SN', '陕西省', '陕西省行政区域', '', '0', '61');
INSERT INTO `sys_region` VALUES ('610100', '610100', '西安市', '市辖区', '610000', '0', '1');
INSERT INTO `sys_region` VALUES ('61010001', '61010001', '三原县', '', '610100', '0', '1');
INSERT INTO `sys_region` VALUES ('6101000101', '6101000101', '城北区', '', '61010001', '0', '1');
INSERT INTO `sys_region` VALUES ('6101000102', '6101000102', '城东区', '', '61010001', '0', '2');
INSERT INTO `sys_region` VALUES ('6101000103', '6101000103', '城南区', '', '61010001', '0', '3');
INSERT INTO `sys_region` VALUES ('6101000104', '6101000104', '城西区', '', '61010001', '0', '4');
INSERT INTO `sys_region` VALUES ('61010002', '61010002', '户县', '', '610100', '0', '2');
INSERT INTO `sys_region` VALUES ('610101', '610101', '东区', '东区', '610100', '-1', '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色编码',
  `role_code` varchar(50) DEFAULT '' COMMENT '角色代码',
  `role_name` varchar(50) DEFAULT '' COMMENT '角色名称',
  `role_note` varchar(200) DEFAULT '' COMMENT '角色说明',
  `role_state` int(11) DEFAULT '0' COMMENT '角色状态（0：正常   9：停用）',
  PRIMARY KEY (`role_id`) USING BTREE,
  KEY `idx_role_state` (`role_state`) USING BTREE,
  KEY `idx_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'R03', '单位负责人', '单位负责人', '0');
INSERT INTO `sys_role` VALUES ('d4415f55697b441899712d5532cb6783', '11111111', 'zhenlan', '', '0');
INSERT INTO `sys_role` VALUES ('R00', 'R00', '系统管理员', '系统管理员', '0');
INSERT INTO `sys_role` VALUES ('R02', 'R02', '数据采集员', '数据采集员', '0');

-- ----------------------------
-- Table structure for `sys_rolemenu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_rolemenu`;
CREATE TABLE `sys_rolemenu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色编码',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单编码',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_rolemenu
-- ----------------------------
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S00');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S00');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S00');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S00M0101');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S00M0101');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S00M0102');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S00M0102');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S00M0103');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S00M0103');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S00M0104');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S00M0104');
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S01');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M01');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M01');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M0101');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M0101');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M0102');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M0102');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M02');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M02');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M0201');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M0201');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M0202');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M0202');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M03');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M03');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M0301');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M0301');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S01M0302');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S01M0302');
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S02');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S02');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S02');
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S02M0101');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S02M0101');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S02M0101');
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S02M0102');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S02M0102');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S02M0102');
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S02M0103');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S02M0103');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S02M0103');
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S02M0104');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S02M0104');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S02M0104');
INSERT INTO `sys_rolemenu` VALUES ('43fe0e38b01b4b89af085a8728069ffd', 'S04');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S04');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M01');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S04M01');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0101');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0102');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0103');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0104');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S04M0104');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M02');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S04M02');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0201');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0202');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M03');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S04M03');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0301');
INSERT INTO `sys_rolemenu` VALUES ('R02', 'S04M0301');
INSERT INTO `sys_rolemenu` VALUES ('R00', 'S04M0302');

-- ----------------------------
-- Table structure for `sys_roleuser`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roleuser`;
CREATE TABLE `sys_roleuser` (
  `role_id` varchar(32) NOT NULL COMMENT '角色编码',
  `user_id` varchar(32) NOT NULL COMMENT '用户编码',
  PRIMARY KEY (`role_id`,`user_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色用户关系';

-- ----------------------------
-- Records of sys_roleuser
-- ----------------------------
INSERT INTO `sys_roleuser` VALUES ('43fe0e38b01b4b89af085a8728069ffd', '2b25e8270653491f845c200d2dd48fb1');
INSERT INTO `sys_roleuser` VALUES ('R02', '8c45183e52464f88a9fc55adbb709e78');
INSERT INTO `sys_roleuser` VALUES ('R02', 'f9bbfb50142e4003a2fd967aaf936b4b');
INSERT INTO `sys_roleuser` VALUES ('R00', 'U01');
INSERT INTO `sys_roleuser` VALUES ('R02', 'U01');

-- ----------------------------
-- Table structure for `sys_system`
-- ----------------------------
DROP TABLE IF EXISTS `sys_system`;
CREATE TABLE `sys_system` (
  `sys_id` varchar(32) NOT NULL COMMENT '系统编码',
  `sys_code` varchar(50) DEFAULT '' COMMENT '系统代码',
  `sys_name` varchar(100) DEFAULT '' COMMENT '系统简称',
  `sys_fullname` varchar(100) DEFAULT '' COMMENT '系统全称',
  `sys_context` varchar(100) DEFAULT '' COMMENT '上下文根路径',
  `sys_icon` varchar(100) DEFAULT '' COMMENT '系统图标',
  `sys_note` varchar(100) DEFAULT '' COMMENT '系统说明',
  `sys_key` varchar(2000) DEFAULT '' COMMENT '注册密钥',
  `sys_state` int(11) DEFAULT NULL COMMENT '系统状态（0：正常   9：停用）',
  `sys_order` int(11) DEFAULT NULL COMMENT '系统序号',
  PRIMARY KEY (`sys_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='注册系统';

-- ----------------------------
-- Records of sys_system
-- ----------------------------
INSERT INTO `sys_system` VALUES ('S00', 'S00', '仪表信息', 'http://localhost:8080/WMMS-SYS/', 'http://localhost:8080/WMMS-SYS/', 'meter', null, null, '0', '0');
INSERT INTO `sys_system` VALUES ('S01', 'S01', '仪表业务', 'http://localhost:8080/WMMS-SYS/', 'http://localhost:8080/WMMS-SYS/', 'gongdan', null, null, '0', '1');
INSERT INTO `sys_system` VALUES ('S02', 'S02', '监控统计', 'http://localhost:8080/WMMS-SYS/', 'http://localhost:8080/WMMS-SYS/', 'jiankong', null, null, '0', '2');
INSERT INTO `sys_system` VALUES ('S04', 'S04', '系统管理', 'http://localhost:8080/WMMS-SYS/', 'http://localhost:8080/WMMS-SYS/', 'kehu', null, null, '0', '4');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编码',
  `dep_id` varchar(32) DEFAULT '' COMMENT '用户归属部门编码',
  `reg_id` varchar(32) DEFAULT '' COMMENT '用户归属区域编码',
  `user_loginname` varchar(50) DEFAULT '' COMMENT '用户账号',
  `user_password` varchar(50) DEFAULT '' COMMENT '用户密码',
  `user_code` varchar(50) DEFAULT '' COMMENT '用户代码',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户姓名',
  `user_phone` varchar(50) DEFAULT '' COMMENT '用户电话',
  `user_email` varchar(50) DEFAULT '' COMMENT '用户电子邮件',
  `user_addr` varchar(200) DEFAULT '' COMMENT '用户地址',
  `user_state` int(11) DEFAULT NULL COMMENT '用户状态（0：正常   9：停用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `FK_Reference_6` (`dep_id`) USING BTREE,
  KEY `FK_Reference_7` (`reg_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`dep_id`) REFERENCES `sys_department` (`dep_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`reg_id`) REFERENCES `sys_region` (`reg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2b25e8270653491f845c200d2dd48fb1', '98c42c2ffce449acb87310a8ca5d57ed', '61010002', 'lisi', '62c8ad0a15d9d1ca38d5dee762a16e01', 'li4', '李四', '15810998798', '15810998798@qq.com', '', '0', '2018-06-20 20:05:45', null);
INSERT INTO `sys_user` VALUES ('6cba8fc472ca4f3eb84737645174aa71', '5b441283c29744ada8a9488b88c22999', '610100', 'zhenlan', '62c8ad0a15d9d1ca38d5dee762a16e01', '11111111', 'zhenlan1', '', '', '', '0', '2018-06-20 21:43:56', null);
INSERT INTO `sys_user` VALUES ('8c45183e52464f88a9fc55adbb709e78', '6b0af6c62ad642c8bc540ef5c14b5dfb', '61010001', 'sanyuan', '62c8ad0a15d9d1ca38d5dee762a16e01', 'wangwu', '王五', '15810775897', '15810775897@qq.com', '', '0', '2018-06-20 20:14:19', null);
INSERT INTO `sys_user` VALUES ('f9bbfb50142e4003a2fd967aaf936b4b', '873caa4e3b2f45b9a5b7ff2ca4ff888d', '6101000101', 'dkh1', '62c8ad0a15d9d1ca38d5dee762a16e01', 'zs3', '张三', '17792626501', '17792626501@qq.com', '大客户地址', '0', '2018-06-20 16:24:58', '2018-06-20 15:17:46');
INSERT INTO `sys_user` VALUES ('U01', 'D0104', '610000', 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'U01', '系统管理员', '13572290043', '196095123@qq.com', '用户地址', '0', '2018-01-10 15:51:07', '2018-03-28 14:26:28');

-- ----------------------------
-- Table structure for `sys_userdepartment`
-- ----------------------------
DROP TABLE IF EXISTS `sys_userdepartment`;
CREATE TABLE `sys_userdepartment` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编码',
  `dep_id` varchar(32) NOT NULL COMMENT '部门编码',
  PRIMARY KEY (`user_id`,`dep_id`) USING BTREE,
  KEY `dep_id` (`dep_id`) USING BTREE,
  CONSTRAINT `sys_userdepartment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_userdepartment_ibfk_2` FOREIGN KEY (`dep_id`) REFERENCES `sys_department` (`dep_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户管理部门';

-- ----------------------------
-- Records of sys_userdepartment
-- ----------------------------
INSERT INTO `sys_userdepartment` VALUES ('f9bbfb50142e4003a2fd967aaf936b4b', '873caa4e3b2f45b9a5b7ff2ca4ff888d');

-- ----------------------------
-- Table structure for `sys_userregion`
-- ----------------------------
DROP TABLE IF EXISTS `sys_userregion`;
CREATE TABLE `sys_userregion` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编码',
  `reg_id` varchar(32) NOT NULL COMMENT '区域编码',
  PRIMARY KEY (`user_id`,`reg_id`) USING BTREE,
  KEY `FK_Reference_11` (`reg_id`) USING BTREE,
  CONSTRAINT `sys_userregion_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_userregion_ibfk_2` FOREIGN KEY (`reg_id`) REFERENCES `sys_region` (`reg_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户管理区域';

-- ----------------------------
-- Records of sys_userregion
-- ----------------------------
INSERT INTO `sys_userregion` VALUES ('2b25e8270653491f845c200d2dd48fb1', '610000');
INSERT INTO `sys_userregion` VALUES ('8c45183e52464f88a9fc55adbb709e78', '610000');
INSERT INTO `sys_userregion` VALUES ('U01', '610000');
INSERT INTO `sys_userregion` VALUES ('2b25e8270653491f845c200d2dd48fb1', '610100');
INSERT INTO `sys_userregion` VALUES ('8c45183e52464f88a9fc55adbb709e78', '610100');
INSERT INTO `sys_userregion` VALUES ('U01', '610100');
INSERT INTO `sys_userregion` VALUES ('8c45183e52464f88a9fc55adbb709e78', '61010001');
INSERT INTO `sys_userregion` VALUES ('U01', '61010001');
INSERT INTO `sys_userregion` VALUES ('8c45183e52464f88a9fc55adbb709e78', '6101000101');
INSERT INTO `sys_userregion` VALUES ('U01', '6101000101');
INSERT INTO `sys_userregion` VALUES ('8c45183e52464f88a9fc55adbb709e78', '6101000102');
INSERT INTO `sys_userregion` VALUES ('U01', '6101000102');
INSERT INTO `sys_userregion` VALUES ('8c45183e52464f88a9fc55adbb709e78', '6101000103');
INSERT INTO `sys_userregion` VALUES ('U01', '6101000103');
INSERT INTO `sys_userregion` VALUES ('8c45183e52464f88a9fc55adbb709e78', '6101000104');
INSERT INTO `sys_userregion` VALUES ('U01', '6101000104');
INSERT INTO `sys_userregion` VALUES ('2b25e8270653491f845c200d2dd48fb1', '61010002');
INSERT INTO `sys_userregion` VALUES ('U01', '61010002');

-- ----------------------------
-- Procedure structure for `check_meter_status`
-- ----------------------------
DROP PROCEDURE IF EXISTS `check_meter_status`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_meter_status`()
BEGIN
	#Routine body goes here...
	UPDATE prd_meter a, prd_meter_type_define b SET a.meter_status = -2 WHERE ((TIMESTAMPDIFF(DAY, a.meter_last_check_time, NOW()) >= 365 * 2) or (TIMESTAMPDIFF(DAY, a.meter_last_check_time, NOW()) >= 365 AND 40 <= b.meter_size AND a.meter_type_id = b.meter_type_id));
  UPDATE prd_meter a, prd_meter_type_define b SET a.meter_status = -1 WHERE ((TIMESTAMPDIFF(DAY, a.meter_setup_time, NOW()) >= 365 * 6) or (TIMESTAMPDIFF(DAY, a.meter_setup_time, NOW()) >= 365 * 4 AND 40 <= b.meter_size AND a.meter_type_id = b.meter_type_id));

END
;;
DELIMITER ;
