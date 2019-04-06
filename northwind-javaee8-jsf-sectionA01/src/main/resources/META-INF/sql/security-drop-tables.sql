alter table LoginUserGroup drop foreign key FK_LoginUserGroup_LoginGroup;
alter table LoginUserGroup drop foreign key FK_LoginUserGroup_LoginUser;
drop table if exists LoginGroup;
drop table if exists LoginUser;
drop table if exists LoginUserGroup;

