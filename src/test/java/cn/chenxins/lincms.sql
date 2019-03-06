/*
Navicat MySQL Data Transfer

Source Server         : localData
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : lincms

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-03-06 14:06:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `delete_time` timestamp NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(30) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('2019-01-22 11:43:50', '2019-01-22 11:43:50', null, '1', '的说法打发', '阿道夫', '阿道夫', '打发');
INSERT INTO `book` VALUES ('2019-02-01 11:26:59', '2019-02-01 11:31:49', null, '2', '产品设计、策划相关组sss', 'jandy.chensss', '查看lin的信息查询日志记录的用户ss', 'daa.jpg');
INSERT INTO `book` VALUES ('2019-02-01 11:32:07', null, '2019-02-01 11:32:50', '3', '产品设计、策划相关组', 'jandy.chen', '查看lin的信息查询日志记录的用户', 'daa.jpg');
INSERT INTO `book` VALUES ('2019-02-02 10:23:00', null, null, '4', 'a da ', 'adsf ', 'adf', 'das ');

-- ----------------------------
-- Table structure for lin_auth
-- ----------------------------
DROP TABLE IF EXISTS `lin_auth`;
CREATE TABLE `lin_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `auth` varchar(60) DEFAULT NULL,
  `module` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lin_auth
-- ----------------------------
INSERT INTO `lin_auth` VALUES ('1', '1', '搜索日志', '日志');
INSERT INTO `lin_auth` VALUES ('2', '1', '查询所有日志', '日志');
INSERT INTO `lin_auth` VALUES ('3', '1', '查询日志记录的用户', '日志');
INSERT INTO `lin_auth` VALUES ('4', '1', '删除图书', '图书');
INSERT INTO `lin_auth` VALUES ('5', '1', '查看lin的信息', '信息');
INSERT INTO `lin_auth` VALUES ('6', '2', '删除图书', '图书');
INSERT INTO `lin_auth` VALUES ('10', '5', '查看lin的信息', '信息');
INSERT INTO `lin_auth` VALUES ('12', '5', '查询所有日志', '日志');
INSERT INTO `lin_auth` VALUES ('15', '6', '查看lin的信息', '信息');
INSERT INTO `lin_auth` VALUES ('20', '8', '查询日志记录的用户', '日志');
INSERT INTO `lin_auth` VALUES ('21', '6', '删除图书', '图书');
INSERT INTO `lin_auth` VALUES ('24', '9', '删除图书', '图书');
INSERT INTO `lin_auth` VALUES ('25', '11', '查询所有日志', '日志');
INSERT INTO `lin_auth` VALUES ('27', '2', '查询日志记录的用户', '日志');
INSERT INTO `lin_auth` VALUES ('28', '2', '搜索日志', '日志');

-- ----------------------------
-- Table structure for lin_event
-- ----------------------------
DROP TABLE IF EXISTS `lin_event`;
CREATE TABLE `lin_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `message_events` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lin_event
-- ----------------------------

-- ----------------------------
-- Table structure for lin_group
-- ----------------------------
DROP TABLE IF EXISTS `lin_group`;
CREATE TABLE `lin_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lin_group
-- ----------------------------
INSERT INTO `lin_group` VALUES ('1', 'admin组', '管理团队');
INSERT INTO `lin_group` VALUES ('2', '运营组', '分管运营');
INSERT INTO `lin_group` VALUES ('5', '产品2部', '产品设计、策划相关组');
INSERT INTO `lin_group` VALUES ('6', '产品3部', '产品设计、策划相关组啊啊啊');
INSERT INTO `lin_group` VALUES ('8', '产品5部', '产品设计、策划相关组');
INSERT INTO `lin_group` VALUES ('9', '特殊组是', '特别的你，贴吧是');
INSERT INTO `lin_group` VALUES ('11', 'AB主', '打发');

-- ----------------------------
-- Table structure for lin_log
-- ----------------------------
DROP TABLE IF EXISTS `lin_log`;
CREATE TABLE `lin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(450) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `status_code` int(11) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `authority` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lin_log
-- ----------------------------
INSERT INTO `lin_log` VALUES ('1', 'super登陆成功获取了令牌', '2019-01-22 11:41:57', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('2', 'super登陆成功获取了令牌', '2019-01-22 11:45:20', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('3', '管理员新建了一个权限组', '2019-01-22 11:46:20', '1', 'super', '201', 'POST', '/cms/admin/group', '');
INSERT INTO `lin_log` VALUES ('4', '管理员新建了一个用户', '2019-01-22 11:47:13', '1', 'super', '201', 'POST', '/cms/user/register', '');
INSERT INTO `lin_log` VALUES ('5', 'super登陆成功获取了令牌', '2019-01-22 15:38:30', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('6', 'super登陆成功获取了令牌', '2019-01-22 16:32:12', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('7', 'super登陆成功获取了令牌', '2019-01-22 17:00:02', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('8', 'super登陆成功获取了令牌', '2019-01-24 11:23:44', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('9', '管理员新建了一个用户', '2019-01-24 11:49:47', '1', 'super', '201', 'POST', '/cms/user/register', '');
INSERT INTO `lin_log` VALUES ('10', 'super登陆成功获取了令牌', '2019-01-25 09:24:13', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('11', 'super登陆成功获取了令牌', '2019-01-25 10:22:26', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('12', 'super登陆成功获取了令牌', '2019-01-25 22:41:29', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('13', 'super登陆成功获取了令牌', '2019-01-25 22:43:28', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('14', 'test登陆成功获取了令牌', '2019-01-25 22:44:26', '8', 'test', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('15', 'super登陆成功获取了令牌', '2019-01-28 08:48:22', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('16', 'super登陆成功获取了令牌', '2019-01-28 10:17:06', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('17', 'super登陆成功获取了令牌', '2019-01-29 08:20:32', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('18', 'super登陆成功获取了令牌', '2019-01-29 14:17:44', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('19', 'super登陆成功获取了令牌', '2019-01-29 16:02:50', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('20', '管理员新建了一个权限组', '2019-01-29 16:06:31', '1', 'super', '201', 'POST', '/cms/admin/group', '');
INSERT INTO `lin_log` VALUES ('21', 'super登陆成功获取了令牌', '2019-01-29 16:09:20', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('22', 'super登陆成功获取了令牌', '2019-01-30 09:20:13', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('23', 'super登陆成功获取了令牌', '2019-01-31 14:16:34', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('24', 'super登陆成功获取了令牌', '2019-01-31 14:17:53', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('25', '管理员新建了一个权限组', '2019-02-01 08:50:09', '7', 'admin', '200', 'POST', '/cms/admin/group', 'wu');
INSERT INTO `lin_log` VALUES ('26', '管理员新建了一个权限组', '2019-02-01 09:05:06', '7', 'admin', '200', 'POST', '/cms/admin/group', null);
INSERT INTO `lin_log` VALUES ('27', '管理员新建了一个用户', '2019-02-01 09:18:32', '7', 'admin', '200', 'POST', '/cms/user/register', null);
INSERT INTO `lin_log` VALUES ('28', 'super登陆成功获取了令牌', '2019-02-02 10:55:52', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('29', '管理员新建了一个用户', '2019-02-02 11:33:34', '7', 'admin', '200', 'POST', '/cms/user/register', null);
INSERT INTO `lin_log` VALUES ('30', 'super登陆成功获取了令牌', '2019-02-02 14:18:21', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('31', 'super登陆成功获取了令牌', '2019-02-02 14:29:22', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('32', 'super登陆成功获取了令牌', '2019-02-02 14:35:45', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('33', '管理员新建了一个权限组', '2019-02-02 14:59:55', '1', 'super', '201', 'POST', '/cms/admin/group', '');
INSERT INTO `lin_log` VALUES ('34', '管理员新建了一个权限组', '2019-02-02 15:01:02', '1', 'super', '201', 'POST', '/cms/admin/group', '');
INSERT INTO `lin_log` VALUES ('35', '管理员删除一个权限组', '2019-02-02 15:01:19', '1', 'super', '201', 'DELETE', '/cms/admin/group/10', '');
INSERT INTO `lin_log` VALUES ('36', '管理员新建了一个用户', '2019-02-02 15:05:47', '1', 'super', '201', 'POST', '/cms/user/register', '');
INSERT INTO `lin_log` VALUES ('37', '管理员新建了一个用户', '2019-02-02 15:09:17', '1', 'super', '201', 'POST', '/cms/user/register', '');
INSERT INTO `lin_log` VALUES ('38', '管理员新建了一个用户', '2019-02-02 15:10:26', '1', 'super', '201', 'POST', '/cms/user/register', '');
INSERT INTO `lin_log` VALUES ('39', '管理员删除了一个用户', '2019-02-02 15:11:22', '1', 'super', '201', 'DELETE', '/cms/admin/15', '');
INSERT INTO `lin_log` VALUES ('40', 'super登陆成功获取了令牌', '2019-02-02 15:14:08', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('41', '管理员新建了一个用户', '2019-02-02 15:14:41', '1', 'super', '201', 'POST', '/cms/user/register', '');
INSERT INTO `lin_log` VALUES ('42', '管理员新建了一个权限组', '2019-02-02 15:19:07', '1', 'super', '201', 'POST', '/cms/admin/group', '');
INSERT INTO `lin_log` VALUES ('43', '管理员新建了一个用户', '2019-02-02 15:19:54', '1', 'super', '201', 'POST', '/cms/user/register', '');
INSERT INTO `lin_log` VALUES ('44', 'dedd登陆成功获取了令牌', '2019-02-02 15:35:14', '16', 'dedd', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('45', 'super登陆成功获取了令牌', '2019-02-02 15:35:54', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('46', 'dedd登陆成功获取了令牌', '2019-02-02 15:36:31', '16', 'dedd', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('47', 'dedd修改了自己的密码', '2019-02-02 15:37:12', '16', 'dedd', '400', 'PUT', '/cms/user/change_password', '');
INSERT INTO `lin_log` VALUES ('48', 'dedd修改了自己的密码', '2019-02-02 15:37:17', '16', 'dedd', '201', 'PUT', '/cms/user/change_password', '');
INSERT INTO `lin_log` VALUES ('49', 'dedd登陆成功获取了令牌', '2019-02-02 15:37:34', '16', 'dedd', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('50', 'super登陆成功获取了令牌', '2019-02-02 15:37:54', '1', 'super', '200', 'post', '/cms/user/login', '无');
INSERT INTO `lin_log` VALUES ('51', '管理员新建了一个用户', '2019-02-02 15:49:35', '7', 'admin', '200', 'POST', '/cms/user/register', null);
INSERT INTO `lin_log` VALUES ('52', '管理员新建了一个用户', '2019-02-02 16:56:44', '7', 'admin', '200', 'POST', '/cms/user/register', null);
INSERT INTO `lin_log` VALUES ('53', '管理员新建了一个权限组', '2019-02-02 16:58:38', '7', 'admin', '200', 'POST', '/cms/admin/group', null);

-- ----------------------------
-- Table structure for lin_user
-- ----------------------------
DROP TABLE IF EXISTS `lin_user`;
CREATE TABLE `lin_user` (
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `delete_time` timestamp NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(24) NOT NULL,
  `super` smallint(6) NOT NULL,
  `active` smallint(6) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lin_user
-- ----------------------------
INSERT INTO `lin_user` VALUES ('2019-01-22 11:41:38', '2019-01-22 11:41:38', null, '1', 'super', '2', '1', '1234995678@qq.com', null, 'pbkdf2:sha256:50000$cJ4JKV7C$43dae305145c5f0c727de39847e05ca794e6647f7ba670fea7ac75d7d33eef5d');
INSERT INTO `lin_user` VALUES ('2019-01-24 09:41:06', '2019-01-24 09:41:06', null, '7', 'admin', '2', '1', '12345@qq.com', null, 'Jandy:MD532$WLqyv64Oijw=$d8d720a44449d543b6ca6cfee10aefbf');
INSERT INTO `lin_user` VALUES ('2019-01-24 11:49:47', '2019-01-25 22:44:16', null, '8', 'test', '1', '1', '111@qq.com', '1', 'pbkdf2:sha256:50000$BMmtrdzo$07ab3eb23edae89b2e39a2837b862d6faf4b0866f5557d5889d960ea100d3938');
INSERT INTO `lin_user` VALUES ('2019-02-01 09:18:32', null, null, '12', 'jandy', '1', '1', 'adfbddd@139.com', '8', 'Jandy:MD532$WLqyv64Oijw=$d8d720a44449d543b6ca6cfee10aefbf');
INSERT INTO `lin_user` VALUES ('2019-02-02 15:05:47', '2019-02-02 15:05:47', null, '14', '啊安', '1', '1', '俺的沙发@12.com', '9', 'pbkdf2:sha256:50000$TWdARZwc$1382dddadbaf4bbdfda97d162cfcfc49a209bd8447de9b41cd8d4e55856f9fcb');
INSERT INTO `lin_user` VALUES ('2019-02-02 15:10:26', '2019-03-06 13:46:27', null, '16', 'dedd', '1', '1', 'd@13.com', '5', 'pbkdf2:sha256:50000$SLCY0EmH$d7906f8c6ad2c2c5f05a2283690890a59266fe8fd7533bb013f6fd1868d7f85b');
INSERT INTO `lin_user` VALUES ('2019-02-02 15:19:54', '2019-02-02 15:19:54', null, '18', 'ad改', '1', '1', 'ja@12.com', '1', 'pbkdf2:sha256:50000$kwIzqOh9$16beadbfd815cb3da4467750326e8ac6d62a069cf352d17afc6dc034da7944ba');
INSERT INTO `lin_user` VALUES ('2019-02-02 15:49:35', '2019-02-02 16:56:09', null, '19', 'test3', '1', '1', '121@qq.coms', '5', 'Jandy:MD532$zdSat8LGcRa5BztOfx1c8w==$a3a401dbf21f05df357de3dcc8a0a5d4');
INSERT INTO `lin_user` VALUES ('2019-02-02 16:56:44', null, null, '20', 'jandy.chen', '1', '1', 'jadn@12.com', '2', 'Jandy:MD532$zdSat8LGcRa5BztOfx1c8w==$a3a401dbf21f05df357de3dcc8a0a5d4');
SET FOREIGN_KEY_CHECKS=1;
