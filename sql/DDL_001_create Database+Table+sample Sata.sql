drop database if exists HTLDBot;

create database HTLDBot;

use HTLDBot;

drop user if exists 'htld_bot_user'@'localhost';
create user 'htld_bot_user'@'localhost' identified by '12345';

grant all privileges on HTLDBot.* to 'htld_bot_user'@'localhost'
with grant option;


drop table if exists userdata;
create table userdata(
chat_id bigint not null,
vname Varchar(20),
nname Varchar(20),
klass varchar(4),

Primary Key (chat_id)
#KEY ix_leength_chat_id(chat_id(255))
);

insert into userdata (chat_id,vname,nname,klass) values (234567765,'husein','jusic','4cWI');

select * from userdata WHERE chat_id =234567765;

drop table if exists station;
create table station(
s_id int not null AUTO_INCREMENT,
chat_id bigint not null,
dstation varchar(50),
slink varchar(100),

Primary Key (s_id),
FOREIGN KEY (chat_id) REFERENCES userdata(chat_id)
);