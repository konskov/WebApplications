drop database if exists app_prog;
create database app_prog;
use app_prog;

create table if not exists allxml (
	nct_id varchar(255) unique not null,
    mesh_terms longtext,
    enrollment_type varchar(255) default null,
    enrollment_number int default 0
);