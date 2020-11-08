use fim_system;
insert into Mod_Main(mod_name,ip_address) values('印刷機','192.168.50.102'); 

insert into Mod_Sen(sen_name,sen_code) values('溫濕度模組','dht11'); 
insert into Mod_Sen(sen_name,sen_code) values('重量感應模組','hx711'); 
insert into Mod_Sen(sen_name,sen_code) values('電源控制模組','switch'); 
insert into Mod_Sen(sen_name,sen_code) values('火災警報模組','fireAlm'); 

insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'濕度','humidity' from Mod_Sen where sen_code='dht11';
insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'溫度(攝氏C)','temp_cal' from Mod_Sen where sen_code='dht11';
insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'溫度(華氏F)','temp_fah' from Mod_Sen where sen_code='dht11';

insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'重量(g)','weight' from Mod_Sen where sen_code='hx711';

insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'電池電力(V)','battery_volt' from Mod_Sen where sen_code='switch';
insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'電源開關狀態','pow_status' from Mod_Sen where sen_code='switch';

insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'火光警示','fire_status' from Mod_Sen where sen_code='fireAlm';
insert into Mod_ParmData(mod_sen_id,parm_name,parm_code) 
select id,'一氧化碳警示','mq7_status' from Mod_Sen where sen_code='fireAlm';

insert into Sys_Resp_Status(status_code,status_name) values('00','連線正常');
insert into Sys_Resp_Status(status_code,status_name) values('01','感應裝置連線失敗');
insert into Sys_Resp_Status(status_code,status_name) values('02','讀取不到感應模組資料');
insert into Sys_Resp_Status(status_code,status_name) values('99','未知原因');

insert into Mod_Main_Sen_R(mod_main_id,mod_sen_id) values(1,1); 

insert into Sys_Exp_Record(exp_content,ser_number) values('實驗次數',1);