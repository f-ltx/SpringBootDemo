/*
1、修改注释
ALTER TABLE t_organ COMMENT = '组织机构表';
ALTER TABLE t_organ MODIFY COLUMN organ_id VARCHAR(50) COMMENT '机构编号';
2、修改主键索引
ALTER TABLE table_name ADD INDEX index_name (column_list),  可以创建普通索引、unique索引、primary key
CREATE INDEX index_name ON table_name (column_list)         不能创建primary key
ALTER TABLE t_organ ADD PRIMARY KEY pk_organ_id (organ_id);
CREATE INDEX pk_organ_id ON t_organ(organ_id);
3、创建索引的几种情况：1.主键；2.唯一；3.查询字段；4.排序字段；5.外键；6.分组统计
尽量少见索引的情况：1.记录少；2.经常插入、修改、删除；
like语句尽量少用，like '%xxx%'是不使用索引的；like 'xx%' 会使用索引
not in 使用not exists代替
4、命名规范
t_表(Table)
v_视图(View)
p_(或s_)存储过程(Stored Procedure)
5、建表建议
建立逻辑主键和物理主键
不建立物理关联，通过程序进行关联；
（少用）触发器保证数据一致性。
建立时间戳
*/
-- 初始化脚本
DROP DATABASE IF EXISTS springbootdemo;
CREATE DATABASE IF NOT EXISTS springbootdemo
    CHARSET utf8;
USE springbootdemo;
-- t_organ
CREATE TABLE t_organ
(
    organ_id    INT COMMENT '机构标识' AUTO_INCREMENT PRIMARY KEY,
    organ_code  VARCHAR(50) COMMENT '机构编号' UNIQUE NOT NULL,
    organ_name  VARCHAR(200) COMMENT '机构名称' DEFAULT '未定义',
    create_date TIMESTAMP COMMENT '创建时间'    DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP COMMENT '修改时间'    DEFAULT CURRENT_TIMESTAMP,
    parent_code VARCHAR(50) COMMENT '上级机构编号'
)
    COMMENT '组织机构表';
CREATE INDEX ind_organ_name
    ON t_organ (organ_name);
CREATE INDEX ind_parent_code
    ON t_organ (parent_code);
-- t_user
CREATE TABLE t_user
(
    user_id       INT COMMENT '用户标识' AUTO_INCREMENT PRIMARY KEY,
    user_name     VARCHAR(50) COMMENT '用户名' UNIQUE NOT NULL,
    user_password VARCHAR(50) COMMENT '密码'         NOT NULL,
    organ_code    INT COMMENT '所属机构标识',
    create_date   TIMESTAMP COMMENT '创建时间' DEFAULT CURRENT_TIMESTAMP,
    modify_date   TIMESTAMP COMMENT '修改时间' DEFAULT CURRENT_TIMESTAMP,
    role_name     INT COMMENT '所属角色'
)
    COMMENT '用户表';
CREATE INDEX ind_user_name
    ON t_user (user_name);
CREATE INDEX ind_role_name
    ON t_user (role_name);
-- t_role
CREATE TABLE t_role
(
    role_id     INT COMMENT '角色标识' AUTO_INCREMENT PRIMARY KEY,
    role_name   VARCHAR(100) COMMENT '角色名称' UNIQUE NOT NULL,
    create_date TIMESTAMP COMMENT '创建时间' DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP COMMENT '修改时间' DEFAULT CURRENT_TIMESTAMP
)
    COMMENT '角色表';
-- t_privilege
CREATE TABLE t_privilege
(
    privilege_id   INT COMMENT '权限标识' AUTO_INCREMENT PRIMARY KEY,
    privilege_name VARCHAR(100) COMMENT '权限名称' UNIQUE NOT NULL,
    create_date    TIMESTAMP COMMENT '创建时间' DEFAULT CURRENT_TIMESTAMP,
    modify_date    TIMESTAMP COMMENT '修改时间' DEFAULT CURRENT_TIMESTAMP
)
    COMMENT '权限表';
-- t_role_privilege
CREATE TABLE t_role_privilege
(
    rp_id        INT COMMENT '角色权限关系标识' AUTO_INCREMENT PRIMARY KEY,
    role_id      INT COMMENT '角色标识' NOT NULL,
    privilege_id INT COMMENT '权限标识' NOT NULL,
    create_date  TIMESTAMP COMMENT '创建时间' DEFAULT CURRENT_TIMESTAMP,
    modify_date  TIMESTAMP COMMENT '修改时间' DEFAULT CURRENT_TIMESTAMP
)
    COMMENT '角色权限关系表';

