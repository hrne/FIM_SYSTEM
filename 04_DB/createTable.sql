use fim_system;

-- Sen_Mach 工具機資料
create table Sen_Mach(
	id int not null AUTO_INCREMENT comment 'key值',
	mach_name varchar(100) not null comment '工具機名稱',
	ip varchar(40) not null comment 'ip位址',
	mach_enable boolean not null default true comment '工具機是否啟用',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '工具機資料';

-- Sen_Mod 感應模組
create table Sen_Mod(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_name varchar(100) not null comment '感應模組名稱',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '感應模組';

-- Sen_Parm 感應模組參數
create table Sen_Parm(
	id int not null AUTO_INCREMENT comment 'key值',
    sen_mod_id int not null comment '感應模組id',
	parm_name varchar(100) not null comment '參數名稱',
    parm_code varchar(40) not null comment '參數代號',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
    FOREIGN KEY (sen_mod_id) REFERENCES Sen_Mod (id)
)comment '感應模組參數';

-- Sen_Mach_Mod_R 工具機感應模組
create table Sen_Mach_Mod_R(
	id int not null AUTO_INCREMENT comment 'key值',
    sen_mach_id int not null comment '工具機資料id',
    sen_mod_id int not null comment '感應模組id',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
    FOREIGN KEY (sen_mach_id) REFERENCES Sen_Mach (id),
    FOREIGN KEY (sen_mod_id) REFERENCES Sen_Mod (id)
)comment '工具機感應模組';

-- Sen_Mach_Log 工具機資料更新紀錄
create table Sen_Mach_Log(
	sen_mach_id int not null comment '工具機資料id',
	bef_mach_name varchar(100) not null comment '修改前工具機名稱',
	aft_mach_name varchar(100) not null comment '修改後工具機名稱',
	bfe_ip varchar(40) not null comment '修改前ip位址',
	aft_ip varchar(40) not null comment '修改後ip位址',
	bfe_mach_enable boolean not null comment '修改前是否啟用',
	aft_mach_enable boolean not null comment '修改後是否啟用',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	FOREIGN KEY (sen_mach_id) REFERENCES Sen_Mach (id)
)comment '工具機資料更新紀錄';

-- Sen_Resp_Log 工具機感應紀錄
create table Sen_Resp_Log(
	sen_mach_id int not null comment '工具機資料id',
    suc_status boolean not null comment '查詢是否成功',
    error_log varchar(1000) comment '失敗訊息',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
    FOREIGN KEY (sen_mach_id) REFERENCES Sen_Mach (id)
)comment '工具機感應紀錄';

-- Sen_Dht11 溫濕度dht11感應資料
create table Sen_Dht11(
	sen_mach_id int not null comment '工具機資料id',
	humidity decimal(5,2) not null comment '濕度',
	temp_cal decimal(5,2) not null comment '溫度(攝氏H)',
	temp_fah decimal(5,2) not null comment '溫度(華氏C)',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	FOREIGN KEY (sen_mach_id) REFERENCES sen_mach (id)
)comment '溫濕度dht11感應資料';
--drop table
drop table sen_mach_log;
drop table sen_dht11;
drop table sen_mach;
