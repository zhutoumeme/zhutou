drop table if exists user_login_info;
create table user_login_info(
 login_id int(11) primary key auto_increment comment '主键',
 login_name varchar(50) not null comment '账户名',
 password varchar(100) not null comment '密码',
 name varchar(50) not null comment '姓名',
 add_time DATETIME  not null default current_timestamp() comment '添加时间',
 update_time DATETIME  not null default current_timestamp() comment '修改时间'
) engine =innodb default charset =utf8;