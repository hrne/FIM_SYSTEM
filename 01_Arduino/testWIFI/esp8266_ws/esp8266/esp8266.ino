#include <ESP8266WiFi.h>      // 提供Wi-Fi功能的程式庫
#include <ESP8266WebServer.h>  // 提供網站伺服器功能的程式庫
#include <ArduinoJson.h>
#include "DHT.h"
#define dhtPin D8      //讀取DHT11 Data
#define dhtType DHT11 //選用DHT11   

const char ssid[] = "lin2";
const char pass[] = "29883713";

char recArray[256],recCount=0;
ESP8266WebServer server(80);   // 宣告網站伺服器物件與埠號

DHT dht(dhtPin, dhtType); // Initialize DHT sensor

String webString="";      // 顯示的字串


void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, pass);
  dht.begin();//啟動DHT
  
  //若要指定IP位址，請自行在此加入WiFi.config()敘述。
  //WiFi.config(IPAddress(192,168,0,105),    // IP位址
  //           IPAddress(192,168,0,1),     // 閘道（gateway）位址
  //           IPAddress(255,255,255,0));  // 網路遮罩（netmask）
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);   // 等待WiFi連線
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("WiFi connected, IP: ");
  Serial.println(WiFi.localIP());  // 顯示ESP8266裝置的IP位址

  // 處理GET或POST請求
  server.on("/send", []() {
    String message;
    getTemperature();
    StaticJsonBuffer<500> jsonBuffer;
    JsonObject& root = jsonBuffer.createObject();
    for (uint8_t i = 0; i < server.args(); i++) {
      root[server.argName(i)] = webString;
    }  
    root.printTo(message);
    Serial.println(message);
    Serial.println(webString);
    server.send(200, "application/json", message);
  });

  server.onNotFound([]() {  // 處理「找不到指定路徑」的事件
    server.send(404, "text/plain", "File NOT found!");
  });

  server.begin();
}

void loop() {
  server.handleClient();  // 處理用戶連線
}

void getTemperature(){
  float h = dht.readHumidity();//讀取濕度
  float t = dht.readTemperature();//讀取攝氏溫度
  float f = dht.readTemperature(true);//讀取華氏溫度

  webString=String((int)h)+"%\t"+String((int)t)+"*C\t"+String((int)f)+"*F\n";
  /*
  Serial.print("濕度: ");
  Serial.print(h);
  Serial.print("%\t");
  Serial.print("攝氏溫度: ");
  Serial.print(t);
  Serial.print("*C\t");
  Serial.print("華氏溫度: ");
  Serial.print(f);
  Serial.print("*F\n");*/
  Serial.println("get");
  Serial.println(webString);

}
 
