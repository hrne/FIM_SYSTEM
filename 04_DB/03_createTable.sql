use fim_system;


-- Sys_Resp_Status 回傳狀態代碼檔
create table Sys_Resp_Status(
	id int not null AUTO_INCREMENT comment 'key值',
	status_code varchar(2) not null comment '回傳代碼',
	status_name varchar(40) not null comment '代碼說明',	
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '回傳狀態代碼檔';

-- Sys_Exp_Record 實驗紀錄資料
create table Sys_Exp_Record(
	id int not null AUTO_INCREMENT comment 'key值',
	exp_content varchar(100)  comment '實驗內容',
	ser_number decimal(10,0)  comment '次數',	
    cost_time decimal(10,0) comment '花費時間',	
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '實驗紀錄資料';

-- Sys_Exp_Record 實驗紀錄資料
create table Sys_Exp_Record(
	id int not null AUTO_INCREMENT comment 'key值',
	exp_content varchar(2)  comment '實驗內容',
	ser_number decimal(10,0)  comment '次數',	
    cost_time decimal(10,0) comment '花費時間',	
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '實驗紀錄資料';

-- Mod_Main 感應裝置主檔
create table Mod_Main(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_name varchar(100) not null comment '裝置名稱',
	ip_address varchar(40) not null comment 'ip位址',
	mod_enabled decimal(1,0) not null default 1 comment '是否啟用:1:啟用、0:停用',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '感應裝置主檔';

-- Mod_Sen 感應模組
create table Mod_Sen(
	id int not null AUTO_INCREMENT comment 'key值',
	sen_code varchar(40) not null comment '模組代號',
	sen_name varchar(100) not null comment '模組名稱',	
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '感應模組';

-- Mod_ParmData 模組參數資料
create table Mod_ParmData(
	id int not null AUTO_INCREMENT comment 'key值',
    mod_sen_id int not null comment '感應模組id',
	parm_code varchar(40) not null comment '參數代號',
	parm_name varchar(100) not null comment '參數名稱',    
	upper_limit decimal(5,2) default 0 comment '上限警示值',
	lower_limit decimal(5,2) default 0 comment '下限警示值',
	limit_enabled decimal(1,0) not null default 0 comment '是否啟用警示:1:啟用、0:停用',
	show_enabled decimal(1,0) not null default 1 comment '是否於修改畫面顯示:1:顯示、0:不顯示只能從DB修正',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
    FOREIGN KEY (mod_sen_id) REFERENCES Mod_Sen (id)
)comment '模組參數資料';

-- Mod_Main_Sen_R 感應裝置使用模組
create table Mod_Main_Sen_R(
    mod_main_id int not null comment '感應裝置主檔id',
    mod_sen_id int not null comment '感應模組id',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
    FOREIGN KEY (mod_main_id) REFERENCES Mod_Main (id),
    FOREIGN KEY (mod_sen_id) REFERENCES Mod_Sen (id)
)comment '感應裝置使用模組';

-- Mod_Update_Log 感應裝置更新紀錄
create table Mod_Update_Log(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_main_id int not null comment '感應裝置主檔id',
	bef_mod_name varchar(100) comment '修改前裝置名稱',
	aft_mod_name varchar(100) comment '修改後裝置名稱',
	bef_ip_address varchar(40) comment '修改前ip位址',
	aft_ip_address varchar(40) comment '修改後ip位址',
	bfe_mod_enabled decimal(1,0) comment '修改前是否啟用',
	aft_mod_enabled decimal(1,0) comment '修改後是否啟用',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_main_id) REFERENCES Mod_Main (id)
)comment '感應裝置更新紀錄';

-- Mod_Resp_Log 感應紀錄
create table Mod_Resp_Log(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_main_id int not null comment '感應裝置主檔id',
	mod_sen_id int not null comment '感應模組id',
    sys_resp_status_id int not null comment '回傳狀態代碼檔id',
    resp_message TEXT comment '回傳訊息',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
    FOREIGN KEY (mod_main_id) REFERENCES Mod_Main (id),
	FOREIGN KEY (mod_sen_id) REFERENCES Mod_Sen (id),
	FOREIGN KEY (sys_resp_status_id) REFERENCES Sys_Resp_Status (id)
)comment '感應紀錄';

-- Sen_Dht11 溫濕度dht11感應資料
create table Sen_Dht11(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_main_id int not null comment '感應裝置主檔id',
	humidity decimal(5,2) not null comment '濕度',
	temp_cal decimal(5,2) not null comment '溫度(攝氏H)',
	temp_fah decimal(5,2) not null comment '溫度(華氏C)',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_main_id) REFERENCES Mod_Main (id)
)comment '溫濕度dht11感應資料';


-- Sen_Hx711 重量hx711感應資料
create table Sen_Hx711(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_main_id int not null comment '感應裝置主檔id',
	weight decimal(5,2) not null comment '重量(g)',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_main_id) REFERENCES Mod_Main (id)
)comment '重量hx711感應資料';

-- Sen_Switch 電源開關感應資料
create table Sen_Switch(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_main_id int not null comment '感應裝置主檔id',
	pow_status decimal(1,0) not null comment '電源狀態:1:打開、0:關閉',
	battery_volt decimal(5,2) not null comment '電池電力(V)',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_main_id) REFERENCES Mod_Main (id)
)comment '電源開關感應資料';

-- Sen_FireAlm 火災警報感應資料
create table Sen_FireAlm(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_main_id int not null comment '感應裝置主檔id',
	fire_status decimal(1,0) not null comment '火光警示:1:有火光、0:安全',
	mq7_status decimal(1,0) not null comment '一氧化碳警示:1:超標、0:安全',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_main_id) REFERENCES Mod_Main (id)
)comment '火災警報感應資料';


-- drop table
drop table Sen_FireAlm;
drop table Sen_Switch;
drop table Sen_Hx711;
drop table sen_dht11;
drop table Mod_Resp_Log;
drop table Mod_Update_Log;
drop table Mod_Main_Sen_R;
drop table Mod_ParmData;
drop table Mod_Sen;
drop table Mod_Main;
drop table Sys_Resp_Status;