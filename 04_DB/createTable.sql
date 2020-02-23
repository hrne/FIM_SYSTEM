use fim_system;

-- 工具機資料
create table sen_mach(
	id int not null AUTO_INCREMENT comment 'key值',
	name varchar(100) not null comment '工具機名稱',
	ip varchar(40) not null comment 'ip位址',
	enable boolean not null default true comment '是否啟用',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '工具機資料';

-- 工具機資料更新紀錄
create table sen_mach_log(
	sen_mach_id int not null comment 'sen_mach的key值',
	bfe_name varchar(100) not null comment '修改前工具機名稱',
	aft_name varchar(100) not null comment '修改後工具機名稱',
	bfe_ip varchar(40) not null comment '修改前ip位址',
	aft_ip varchar(40) not null comment '修改後ip位址',
	bfe_enable boolean not null comment '修改前是否啟用',
	aft_enable boolean not null comment '修改後是否啟用',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	FOREIGN KEY (sen_mach_id) REFERENCES sen_mach (id)
)comment '工具機資料更新紀錄';

-- dht11溫濕度感應模組資料
create table sen_dht11(
	sen_mach_id int not null comment 'sen_mach的key值',
	humidity decimal(5,2) not null comment '濕度',
	temp_cal decimal(5,2) not null comment '溫度(攝氏H)',
	temp_fah decimal(5,2) not null comment '溫度(華氏C)',
	create_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '寫入時間',
	FOREIGN KEY (sen_mach_id) REFERENCES sen_mach (id)
)comment 'dht11溫濕度感應模組資料';

-- 建立index
CREATE INDEX idx_sen_mach_log_fk_id ON sen_mach_log (sen_mach_id);
CREATE INDEX idx_sen_dht11_fk_id ON sen_dht11 (sen_mach_id);

--drop table
drop table sen_mach_log;
drop table sen_dht11;
drop table sen_mach;
