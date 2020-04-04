use fim_system;
insert into Sen_Mach(mach_name,ip) values('印刷機','192.168.50.102'); 

insert into Sen_Mod(mod_name) values('溫濕度dht11感應資料'); 

insert into Sen_Parm(sen_mod_id,parm_name,parm_code) values(1,'濕度','humidity'); 
insert into Sen_Parm(sen_mod_id,parm_name,parm_code) values(1,'溫度(攝氏H)','temp_cal'); 
insert into Sen_Parm(sen_mod_id,parm_name,parm_code) values(1,'溫度(華氏C)','temp_fah'); 

insert into Sen_Mach_Mod_R(sen_mach_id,sen_mod_id) values(1,1); 
