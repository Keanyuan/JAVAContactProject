-- 创建一个名为 country 的表井插入一些简单的数据
use mybatis ;
-- 创建表
CREATE TABLE IF NOT EXISTS`country` (
	`id` int NOT NULL AUTO_INCREMENT,
	`countryname` VARCHAR(255) NULL,
	`countrycode` VARCHAR(255) NULL,
	PRIMARY KEY(`id`)
);

-- 删除数据
DELETE FROM country;
-- 插入数据
INSERT INTO country(countryname, countrycode) 
VALUES ('中国', 'CN'),('美国', 'US'),
('俄罗斯', 'RU'),('英国', 'GB'),('法国', 'FR');

-- 创建用户表
CREATE TABLE IF NOT EXISTS sys_user(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	user_name VARCHAR(50) COMMENT '用户名',
	user_password VARCHAR(50) COMMENT '密码',
	user_email VARCHAR(50) COMMENT '邮箱',
	user_info text COMMENT '简介',
	head_img BLOB COMMENT '头像',
	create_time datetime COMMENT '创建时间',
	PRIMARY KEY(id)
);

-- 修改表的注释
ALTER TABLE sys_user COMMENT '用户表';

-- 创建角色表
CREATE TABLE IF NOT EXISTS sys_role(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID',
	role_name VARCHAR(50) COMMENT '角色名',
	enabled INT COMMENT '有效标志',
	create_by BIGINT COMMENT '创建人',
	create_time datetime COMMENT '创建时间',
	PRIMARY KEY(id)
);

ALTER TABLE sys_role COMMENT '角色表';

-- 创建权限表
CREATE TABLE IF NOT EXISTS sys_privilege(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT '权限ID',
	privilege_name VARCHAR(50) COMMENT '权限名称',
	privilege_url VARCHAR(200) COMMENT '权限URL',
	PRIMARY KEY(id)
);

ALTER TABLE sys_privilege COMMENT '权限表';

-- 创建用户角色关联表
CREATE TABLE IF NOT EXISTS sys_user_role(
	user_id BIGINT COMMENT '用户ID',
	role_id BIGINT COMMENT '角色ID'
);

ALTER TABLE sys_user_role COMMENT '用户角色关联表';

-- 创建角色权限关联表
CREATE TABLE IF NOT EXISTS sys_role_privilege(
	role_id BIGINT COMMENT '角色ID',
	privilege_id BIGINT COMMENT '权限ID'
);

ALTER TABLE sys_role_privilege COMMENT '角色权限关联表';


-- 插入数据
INSERT INTO sys_user VALUES('1', 'admin', '123456', 'admin@mybatis.tk', '管理员', NULL, '2019-02-27 13:42:30');
INSERT INTO sys_user VALUES('1006', 'test', '123456', 'test@mybatis.tk', '测试用户', NULL, '2019-02-27 13:42:31');

INSERT INTO sys_role VALUES('1', '管理员', '1', '1', '2019-02-27 13:42:31');
INSERT INTO sys_role VALUES('2', '普通用户', '1', '1', '2019-02-27 13:42:31');

INSERT INTO sys_user_role VALUES('1', '1');
INSERT INTO sys_user_role VALUES('1', '2');
INSERT INTO sys_user_role VALUES('1001', '2');


INSERT INTO sys_privilege VALUES('1', '用户管理', '/users');
INSERT INTO sys_privilege VALUES('2', '角色管理', '/roles');
INSERT INTO sys_privilege VALUES('3', '系统日志', '/logs');
INSERT INTO sys_privilege VALUES('4', '人员维护', '/persons');
INSERT INTO sys_privilege VALUES('5', '单位维护', '/companies');


INSERT INTO sys_role_privilege VALUES('1', '1');
INSERT INTO sys_role_privilege VALUES('1', '3');
INSERT INTO sys_role_privilege VALUES('1', '2');
INSERT INTO sys_role_privilege VALUES('1', '4');
INSERT INTO sys_role_privilege VALUES('1', '5');










