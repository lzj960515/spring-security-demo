CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(32) NOT NULL COMMENT '用户电话',
  `team` varchar(50) DEFAULT NULL COMMENT '团队',
  `status` int(16) NOT NULL DEFAULT '1' COMMENT '状态 1:有效0:无效 2:锁定',
  `create_time` datetime DEFAULT NULL COMMENT '账号创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `last_change_password_time` datetime DEFAULT NULL COMMENT '最后一次修改密码时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

CREATE TABLE `t_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';

CREATE TABLE `t_permission` (
  `id` int(10) NOT NULL COMMENT '主键',
  `pid` int(11) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '资源图标',
  `permission_type` int(1) DEFAULT NULL COMMENT '权限类型：1->目录；2->菜单；3->按钮（接口绑定权限）',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态{1:启用,2:禁用}',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统资源表';

CREATE TABLE `t_role_permission` (
  `role_id` int(10) unsigned NOT NULL COMMENT '角色ID',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`permission_id`) USING BTREE,
  KEY `fk_sys_role_resc_sys_resource` (`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';