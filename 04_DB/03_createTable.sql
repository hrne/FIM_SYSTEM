use fim_system;

-- Mod_Data 感應裝置
create table Mod_Data(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_name varchar(100) not null comment '感應裝置名稱',
	ip_address varchar(40) not null comment 'ip位址',
	mod_enable boolean not null default true comment '感應裝置是否啟用,1啟用、0關閉',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '感應裝置';

-- Mod_Sen 感應模組
create table Mod_Sen(
	id int not null AUTO_INCREMENT comment 'key值',
	sen_name varchar(100) not null comment '感應模組名稱',
	sen_code varchar(40) not null comment '感應模組代號',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id)
)comment '感應模組';

-- Mod_Parm 感應模組參數
create table Mod_Parm(
	id int not null AUTO_INCREMENT comment 'key值',
    mod_sen_id int not null comment '感應模組id',
	parm_name varchar(100) not null comment '參數名稱',
    parm_code varchar(40) not null comment '參數代號',
	upper_limit decimal(5,2) default 0 comment '上限警示值',
	lower_limit decimal(5,2) default 0 comment '下限警示值',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
    FOREIGN KEY (mod_sen_id) REFERENCES Mod_Sen (id)
)comment '感應模組參數';

-- Mod_Data_Sen_R 感應裝置使用模組
create table Mod_Data_Sen_R(
    mod_data_id int not null comment '感應裝置id',
    mod_sen_id int not null comment '感應模組id',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
    FOREIGN KEY (mod_data_id) REFERENCES Mod_Data (id),
    FOREIGN KEY (mod_sen_id) REFERENCES Mod_Sen (id)
)comment '感應裝置使用模組';

-- Mod_Data_Log 感應裝置更新紀錄
create table Mod_Data_Log(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_data_id int not null comment '感應裝置id',
	bef_mod_name varchar(100) not null comment '修改前感應裝置名稱',
	aft_mod_name varchar(100) not null comment '修改後感應裝置名稱',
	bef_ip_address varchar(40) not null comment '修改前ip位址',
	aft_ip_address varchar(40) not null comment '修改後ip位址',
	bfe_mod_enable boolean not null comment '修改前是否啟用',
	aft_mod_enable boolean not null comment '修改後是否啟用',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_data_id) REFERENCES Mod_Data (id)
)comment '感應裝置更新紀錄';

-- Mod_Resp_Log 感應紀錄
create table Mod_Resp_Log(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_data_id int not null comment '感應裝置id',
    suc_status boolean not null comment '查詢是否成功',
    resp_message varchar(2000) comment '回傳訊息',
    update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
    FOREIGN KEY (mod_data_id) REFERENCES Mod_Data (id)
)comment '感應紀錄';

-- Sen_Dht11 溫濕度dht11感應資料
create table Sen_Dht11(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_data_id int not null comment '感應裝置id',
	humidity decimal(5,2) not null comment '濕度',
	temp_cal decimal(5,2) not null comment '溫度(攝氏H)',
	temp_fah decimal(5,2) not null comment '溫度(華氏C)',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_data_id) REFERENCES Mod_Data (id)
)comment '溫濕度dht11感應資料';


-- Sen_Hx711 重量hx711感應資料
create table Sen_Hx711(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_data_id int not null comment '感應裝置id',
	weight decimal(5,2) not null comment '重量',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_data_id) REFERENCES Mod_Data (id)
)comment '重量hx711感應資料';

-- Sen_Acs712 電流acs712感應資料
create table Sen_Acs712(
	id int not null AUTO_INCREMENT comment 'key值',
	mod_data_id int not null comment '感應裝置id',
	ampere decimal(5,2) not null comment '安培',
	update_date timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新時間',
	PRIMARY KEY(id),
	FOREIGN KEY (mod_data_id) REFERENCES Mod_Data (id)
)comment '電流acs712感應資料';


-- drop table
drop table Sen_Acs712;
drop table Sen_Hx711;
drop table sen_dht11;
drop table Mod_Resp_Log;
drop table Mod_Data_Log;
drop table Mod_Data_Sen_R;
drop table Mod_Parm;
drop table Mod_Sen;
drop table Mod_Data;
