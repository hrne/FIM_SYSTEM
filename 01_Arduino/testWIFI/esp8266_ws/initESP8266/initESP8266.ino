#include "ESP8266WiFi.h"
 
 const char* ssid = "lin2";
 const char* password = "29883713";
 
 void setup(void)
 {  
   Serial.begin(115200);
   Serial.println("start....");
   WiFi.begin(ssid, password);
   while (WiFi.status() != WL_CONNECTED) {  //不斷嚐試連接
     delay(500);
     Serial.print(".");
   }
   Serial.println("");
   Serial.println("WiFi connected!");  //已連接
   Serial.print("IP: ");
   Serial.println(WiFi.localIP());  //顯示IP位址
 }
 
 void loop() {
 
 }
