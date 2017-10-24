drop database if exists HTLDBot;

create database HTLDBot;

use HTLDBot;

drop user if exists 'htld_bot_user'@'localhost';
create user 'htld_bot_user'@'localhost' identified by '12345';

grant all privileges on HTLDBot.* to 'htld_bot_user'@'localhost'
with grant option;


drop table if exists userdata;
create table userdata(
chat_id long not null,
vname Varchar(20),
nname Varchar(20),
klass varchar(4),
dstation varchar(50),

KEY ix_leength_chat_id(chat_id(255))
);

insert into userdata (chat_id,vname,nname,klass,dstation) values (234567765,'husein','jusic','4cWI','Rheinstraße');


select * from userdata WHERE chat_id =234567765;