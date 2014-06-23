drop table if exists sessions;
DROP TABLE IF EXISTS s_user;
drop table if exists sys_organization;
drop table if exists sys_resource;
drop table if exists sys_role;

create table sessions (
  id varchar(200),
  session varchar(2000),
  constraint pk_sessions primary key(id)
) charset=utf8 ENGINE=InnoDB;



create table s_user (
  id bigint auto_increment,
  organization_id bigint,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  status varchar(5) DEFAULT NULL,
  role_ids varchar(100),
  locked bool default false,
  constraint pk_sys_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_s_user_username on s_user(username);
create index idx_s_user_organization_id on s_user(organization_id);

create table s_organization (
  id bigint auto_increment,
  name varchar(100),
  parent_id bigint,
  parent_ids varchar(100),
  available bool default false,
  constraint pk_sys_organization primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_s_organization_parent_id on s_organization(parent_id);
create index idx_s_organization_parent_ids on s_organization(parent_ids);


create table s_resource (
  id bigint auto_increment,
  name varchar(100),
  type varchar(50),
  url varchar(200),
  parent_id bigint,
  parent_ids varchar(100),
  permission varchar(100),
  available bool default false,
  constraint pk_s_resource primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_resource_parent_id on s_resource(parent_id);
create index idx_sys_resource_parent_ids on s_resource(parent_ids);


create table s_role (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  resource_ids varchar(100),
  available bool default false,
  constraint pk_s_role primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_role_resource_ids on s_role(resource_ids);


create table s_app (
  id bigint auto_increment,
  name varchar(100),
  app_key varchar(100),
  app_secret varchar(100),
  available bool default false,
  constraint pk_s_app primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_s_app_app_key on s_app(app_key);


create table s_user_app_roles (
  id bigint auto_increment,
  user_id bigint,
  app_id bigint,
  role_ids varchar(100),
  constraint pk_s_user_app_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index s_user_app_roles_user_id_app_id on s_user_app_roles(user_id, app_id);
