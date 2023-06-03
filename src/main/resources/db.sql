# 项目所需表
create table user
(
    id            int auto_increment
        primary key,
    name          varchar(255)  not null,
    password      varchar(255)  not null,
    role          int default 0 not null,
    address       varchar(255)  null,
    lastLoginTime datetime      null
);

create table `image_file`
(
      id int not null primary key auto_increment comment '主键id',
      path varchar(255) default null comment '绝对路径',
      code varchar(11) default null comment '对应的集装箱编号',
      flag int(1) default null comment '识别结果是否正确(0:错误, 1:正确)',
      upload_time datetime null comment '图片上传时间'
);