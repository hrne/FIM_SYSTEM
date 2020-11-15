use demo
create table customer(
	id int not null AUTO_INCREMENT
	,first_name varchar(40)
	,last_name varchar(40)
	,email varchar(40)
	,PRIMARY KEY(id)
)



insert into mod_main(mod_name,ip_address,mod_enabled) values('test41','192.168.50.102',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test42','192.168.50.101',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test43','192.168.50.101',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test44','192.168.50.102',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test45','192.168.50.101',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test46','192.168.50.102',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test47','192.168.50.102',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test48','192.168.50.101',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test49','192.168.50.101',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;

insert into mod_main(mod_name,ip_address,mod_enabled) values('test50','192.168.50.102',1);
insert into mod_main_sen_r(mod_main_id,mod_sen_id) 
select max(id),1 from mod_main;