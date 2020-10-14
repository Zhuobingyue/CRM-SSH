CREATE TABLE `cst_customer` (
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE sys_user(
	user_id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	user_code VARCHAR(32) NOT NULL COMMENT '用户账户',
	user_name VARCHAR(32) NOT NULL COMMENT '用户名称',
	user_password VARCHAR(32) NOT NULL COMMENT '用户密码',
	user_state CHAR(1) NOT NULL COMMENT '1:正常，0：暂定',
	PRIMARY KEY (user_id)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET= UTF8;
CREATE TABLE base_dict(
	dict_id  varchar(32) NOT NULL COMMENT '数据字典id(主键)',
	dict_type_code VARCHAR(10) NOT NULL COMMENT '数据字典类别代码',
	dict_type_name VARCHAR(64) NOT NULL COMMENT '数据字典类别名称',
	dict_item_name VARCHAR(64) NOT NULL COMMENT '数据字典项目名称',
	dict_item_code VARCHAR(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
	dict_sort INT(10) DEFAULT NULL COMMENT '排序字段',
	dict_enabl CHAR(1) NOT NULL COMMENT '1：使用，0：停用',
	dict_meno VARCHAR(64) DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (dict_id) 
)ENGINE = INNODB DEFAULT CHARSET =UTF8;

CREATE TABLE `cst_linkman` (
  `lkm_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
  `lkm_name` VARCHAR(16) DEFAULT NULL COMMENT '联系人姓名',
  `lkm_cust_id` BIGINT(32) DEFAULT NULL COMMENT '客户id',
  `lkm_gender` CHAR(1) DEFAULT NULL COMMENT '联系人性别',
  `lkm_phone` VARCHAR(16) DEFAULT NULL COMMENT '联系人办公电话',
  `lkm_mobile` VARCHAR(16) DEFAULT NULL COMMENT '联系人手机',
  `lkm_email` VARCHAR(64) DEFAULT NULL COMMENT '联系人邮箱',
  `lkm_qq` VARCHAR(16) DEFAULT NULL COMMENT '联系人qq',
  `lkm_position` VARCHAR(16) DEFAULT NULL COMMENT '联系人职位',
  `lkm_memo` VARCHAR(512) DEFAULT NULL COMMENT '联系人备注',
  PRIMARY KEY (`lkm_id`),
  KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
  CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;