drop database IF EXISTS puzzle;
CREATE SCHEMA `puzzle` DEFAULT CHARACTER SET utf8mb4;
use puzzle;
CREATE TABLE IF NOT EXISTS space (
	`space_id` bigint(20) NOT NULL auto_increment,
	`tenant_id` bigint(20) NULL,
	`name` varchar(100) default '',
	`description` varchar(100) default '',
	`params` varchar(100) default '',
	`date_created` datetime,
	`date_updated` datetime,
	PRIMARY KEY  (space_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tenant (
	`tenant_id` bigint(20) NOT NULL auto_increment,
	`name` varchar(100) default '',
	`params` varchar(100) default '',
	`date_created` datetime,
	`date_updated` datetime,
	PRIMARY KEY  (tenant_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user (
        `user_id` bigint(20) NOT NULL auto_increment,
        `first_name` varchar(100) default '',
        `last_name` varchar(100) default '',
        `login` varchar(100) default '',
        `password` varchar(100) default '',
        `tenant_id` bigint(20) NULL,
        `params` varchar(100) default '',
        `date_created` datetime,
        `date_updated` datetime,
        `date_deleted` datetime,
        `date_disable` datetime,
        PRIMARY KEY  (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user (user_id, tenant_id, first_name, last_name, login, password) values ("1","1","Guillaume","Admin","admin","admin");
insert into tenant (tenant_id, name) values ("1","Tenant1");

CREATE TABLE IF NOT EXISTS board (
        `board_id` bigint(20) NOT NULL auto_increment,
        `level` varchar(100) default '',
        `params` TEXT,
        PRIMARY KEY  (board_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;