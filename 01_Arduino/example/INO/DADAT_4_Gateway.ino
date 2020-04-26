#include <WiFi.h>
#include <WiFiUdp.h>
#include <TimeLib.h>
#include <DS1307RTC.h>

//網路定義
/* WiFi network name and password */
// Your wirelless router ssid and password
const char * ssid = "Neihu"; 
const char * pwd = "Frontekaitgroup";

// IP address to send UDP data to.
// it can be ip address of the server or 
// a network broadcast address
// here is broadcast address
const char * udpAddress = "192.168.0.147"; // your pc ip
const int udpPort = 55056; //port server

int packetSize = 12;                //收到資料的大小
uint8_t packet_In[12];                  //UDP接收使用
unsigned char packet_Out[12];       //UDP發送使用
char lora_in[12];                       //LoRa接收使用
char lora_out;                      //LoRa發送使用

// 透過UDP接收與傳送訊息
WiFiUDP udp;

//NTP Code
IPAddress timeServer(118, 163, 81, 61); // time.stdtime.gov.tw
const int timeZone = +8;     // TW Time
time_t prevDisplay = 0; // when the digital clock was displayed

void setup(){

  Serial.begin(115200);
  Serial1.begin(115200, SERIAL_8N1, 16, 17); // S76S pins 16 rx2, 17 tx2, 19200 bps, 8 bits no parity 1 stop bit
 //Connect to the WiFi network
   WiFi.begin(ssid, pwd);
  Serial.println("");

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
  //This initializes udp and transfer buffer

  //網路校時
  setSyncProvider(getNtpTime);
  if (timeStatus() != timeNotSet) {
      if (now() != prevDisplay) { 
         prevDisplay = now();
           }
                      }
  udp.begin(udpPort);
  S76S_node();                 //設定S76S 

}

//增加NTP網路校時的功能
//NTP Code

//-------- NTP code ----------
const int NTP_PACKET_SIZE = 48; // NTP time is in the first 48 bytes of message
byte NTPBuffer[NTP_PACKET_SIZE]; //buffer to hold incoming & outgoing packets
time_t getNtpTime()
{
  while (udp.parsePacket() > 0) ; // discard any previously received packets
  //Serial.println("Transmit NTP Request");
  sendNTPpacket(timeServer);
  uint32_t beginWait = millis();
  while (millis() - beginWait < 1500) {
    int size = udp.parsePacket();
    if (size >= NTP_PACKET_SIZE) {
      //Serial.println("Receive NTP Response");
      udp.read(NTPBuffer, NTP_PACKET_SIZE);  // read packet into the buffer
      unsigned long secsSince1900;
      // convert four bytes starting at location 40 to a long integer
      secsSince1900 =  (unsigned long)NTPBuffer[40] << 24;
      secsSince1900 |= (unsigned long)NTPBuffer[41] << 16;
      secsSince1900 |= (unsigned long)NTPBuffer[42] << 8;
      secsSince1900 |= (unsigned long)NTPBuffer[43];
      return secsSince1900 - 2208988800UL + timeZone * SECS_PER_HOUR;
    }
  }
  //Serial.println("No NTP Response :-(");
  return 0; // return 0 if unable to get the time
}
// send an NTP request to the time server at the given address
void sendNTPpacket(IPAddress &address)
{
  // set all bytes in the buffer to 0
  //memset(packetBuffer, 0, NTP_PACKET_SIZE);
  memset(NTPBuffer, 0x00, sizeof(NTPBuffer));
  // Initialize values needed to form NTP request
  // (see URL above for details on the packets)
  NTPBuffer[0] = 0b11100011;   // LI, Version, Mode
  NTPBuffer[1] = 0;     // Stratum, or type of clock
  NTPBuffer[2] = 6;     // Polling Interval
  NTPBuffer[3] = 0xEC;  // Peer Clock Precision
  // 8 bytes of zero for Root Delay & Root Dispersion
  NTPBuffer[12]  = 49;
  NTPBuffer[13]  = 0x4E;
  NTPBuffer[14]  = 49;
  NTPBuffer[15]  = 52;
  // all NTP fields have been given values, now
  // you can send a packet requesting a timestamp:                 
  udp.beginPacket(timeServer, 123); //NTP requests are to port 123
  udp.write(NTPBuffer, NTP_PACKET_SIZE);
  udp.endPacket();
}



void loop() {
  
  String Re = "";
   
   if (Serial1.available() > 0)                 //LORA端有資料進來
      {
            
        Re += char(Serial.read());
        delay(2);
      }

   if (Re.length())
      {
        Re.toCharArray(lora_in, 12); 
        Re = "";
      }
      
      for(int i = 0; i<12 ;i++)
   {
      packet_Out[i] = (unsigned char)lora_in[i];     
   }

    udpSend();            //利用函式傳出

if(udp.read(packet_In, 12) > 0){
    Serial1.println((char *)packet_In);     //UDP指令用LoRa送出
    memset(packet_In, 0, packetSize);     //清空陣列資料
    udp.parsePacket();
  }

}



void S76S_node(){
  Serial1.println("SetSystemMode inNormal");//設定模式，有標準模式（inNormal ）跟Ping Pong（inTD）測試模式
  delay(100);
  
  Serial1.println("LoraMode MASTER");        //使用指令設定為Master或是Slave
  delay(100);
  
  Serial1.println("LoRaSetPower 20");       //使用指令設定TX Power範圍為5-20
  delay(100);

  Serial1.println("LoraSetSF 10");          //使用者令設定SF範圍為8--12
  delay(100);

  Serial1.println("LoraSetMyAddr 8 8 8");   //設定位址，這個很重要，不然怎麼知道要給誰
  delay(100);

  Serial1.println("LoraAddSlaveNode 2 2 2");//將Node加入
  delay(100);

  Serial1.println("LoraStartWork ENABLE");      //設定啟用就可以開始傳送資料
  delay(100);
 
  }

void udpSend(){                                              //網路回傳資料的標準格式

           
        udp.beginPacket(udpAddress, udpPort);
        udp.write(packet_Out, packetSize);
        udp.endPacket();
        delay(50);
        memset(packet_Out, 0, packetSize);     //清空陣列資料
        udp.parsePacket();
       
}
