create table LoginGroup (id bigint not null, groupname varchar(64) not null, primary key (id)) engine=InnoDB;
create table LoginUser (id bigint not null, password varchar(255) not null, username varchar(64) not null, primary key (id)) engine=InnoDB;
create table LoginUserGroup (userid bigint not null, groupid bigint not null) engine=InnoDB;
alter table LoginGroup add constraint UK_LoginGroup_LoginUser unique (groupname);
alter table LoginUser add constraint UK_LoginUser_LoginGroup unique (username);
alter table LoginUserGroup add constraint FK_LoginUserGroup_LoginGroup foreign key (groupid) references LoginGroup (id);
alter table LoginUserGroup add constraint FK_LoginUserGroup_LoginUser foreign key (userid) references LoginUser (id);

