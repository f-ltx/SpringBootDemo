/*
修改注释
ALTER TABLE t_organ COMMENT = '组织机构表';
ALTER TABLE t_organ MODIFY COLUMN organid VARCHAR(50) COMMENT '机构编号';
修改主键索引
ALTER TABLE table_name ADD INDEX index_name (column_list),  可以创建普通索引、unique索引、primary key
CREATE INDEX index_name ON table_name (column_list)         不能创建primary key
ALTER TABLE t_organ ADD PRIMARY KEY pk_organid (organid);
CREATE INDEX pk_organid ON t_organ(organid);
创建索引的几种情况：1.主键；2.唯一；3.查询字段；4.排序字段；5.外键；6.分组统计
尽量少见索引的情况：1.记录少；2.经常插入、修改、删除；
like语句尽量少用，like '%xxx%'是不使用索引的；like 'xx%' 会使用索引
not in 使用not exists代替
t_表(Table)
v_视图(View)
p_(或s_)存储过程(Stored Procedure)
*/
-- 初始化脚本
DROP DATABASE IF EXISTS springbootdemo;
CREATE DATABASE IF NOT EXISTS springbootdemo
  CHARSET utf8;
USE springbootdemo;
-- t_organ
CREATE TABLE t_organ (
  organid   INT COMMENT '机构标识'          AUTO_INCREMENT PRIMARY KEY,
  organcode VARCHAR(50) COMMENT '机构编号' UNIQUE,
  organname VARCHAR(200) COMMENT '机构名称' DEFAULT '未定义',
  parentid  INT COMMENT '上级机构标识'
)
  COMMENT '组织机构表';
CREATE INDEX ind_organname
  ON t_organ (organname);
-- t_user
CREATE TABLE t_user (
  userid   INT COMMENT '用户标识' AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) COMMENT '用户名' UNIQUE,
  password VARCHAR(50) COMMENT '密码' NOT NULL,
  organid  INT COMMENT '所属机构标识',
  roleid   INT COMMENT '所属角色'
)
  COMMENT '用户表';
-- t_role
CREATE TABLE t_role (
  roleid   INT COMMENT '角色标识' AUTO_INCREMENT PRIMARY KEY,
  rolename VARCHAR(100) COMMENT '角色名称' NOT NULL
)
  COMMENT '角色表';
-- t_privilege
CREATE TABLE t_privilege (
  privilegeid   INT COMMENT '权限标识' AUTO_INCREMENT PRIMARY KEY,
  privilegename VARCHAR(100) COMMENT '权限名称' NOT NULL
)
  COMMENT '权限表';
-- t_role_privilege
CREATE TABLE t_role_privilege (
  rpid        INT COMMENT '角色权限关系标识' AUTO_INCREMENT PRIMARY KEY,
  roleid      INT COMMENT '角色标识' NOT NULL,
  privilegeid INT COMMENT '权限标识' NOT NULL
)
  COMMENT '角色权限关系表';

