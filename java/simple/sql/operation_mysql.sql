-- 查询用户表中用户id为1 的数据
select * from sys_user where id = 1;

-- 查询所有用户表中的数据
select id, user_name, user_password, user_email, user_info, head_img, create_time from sys_user;

-- 内联三张表， 查询所有sys_role表中的数据是 用户表与用户角色关联表的用户id相等，角色表与用户角色关联表中的角色id相等
select r.id, r.role_name, r.enabled, r.create_by, r.create_time, u.user_name, u.user_email
FROM sys_user u 
INNER JOIN sys_user_role ur on u.id = ur.user_id
INNER JOIN sys_role r on ur.role_id = r.id
WHERE u.id = 1;

-- 更新用户信息
UPDATE sys_user SET user_name = 'test name', user_password = '12345678', user_email='testname@mybatis.tk'
WHERE id = 1001;



select 
u.id, u.user_name, u.user_email, u.user_info, u.head_img, u.create_time,
r.id, r.role_name, r.enabled, r.create_by, r.create_time,
p.id, p.privilege_name, p.privilege_url
from sys_user u
inner join sys_user_role ur on u.id = ur.user_id
inner join sys_role r on r.id = ur.role_id
inner join sys_role_privilege rp on rp.role_id = r.id
inner join sys_privilege p on p.id = rp.privilege_id;