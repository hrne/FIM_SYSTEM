#include <ESP8266WiFi.h>      // 提供Wi-Fi功能的程式庫
#include <ESP8266WebServer.h>  // 提供網站伺服器功能的程式庫
#include <ArduinoJson.h>
#include "DHT.h"
#include <HX711.h>


/**
   Dht11   Data:D3
   Hx711   Data:D4、D5
*/

//wifi ssid
const char ssid[] = "lin2";
const char pass[] = "29883713";

//webService設定
char recArray[256], recCount = 0;
ESP8266WebServer server(80);   // 宣告網站伺服器物件與埠號

//sensor設定
//dht11設定
#define dhtType DHT11 //選用DHT11  
#define dhtPin D3      //讀取DHT11 Data
DHT dht(dhtPin, dhtType); // Initialize DHT sensor

//hx711設定
#define DOUT D4
#define CLK D5
const int scale_factor = 378; //比例參數，從校正程式中取得
HX711 scale; // Initialize load cell amplifire

//sensor變數
//dht11
float humidity = 0; //濕度
float tempCal = 0; //溫度(攝氏H)
float tempFah = 0; //溫度(華氏C)

//hx711
float weight = 0;//重量

//sensor回傳值
String respJsonMes = ""; //回傳json值

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, pass);

  //啟動dht11
  dht.begin();

  //啟動hx711
  scale.begin(DOUT, CLK);
  scale.get_units(5);
  scale.set_scale(scale_factor);       // 設定比例參數
  scale.tare();               // 歸零
  scale.get_units(5);

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
  server.on("/sensor", []() {
    respJsonMes = "";

    //接受Json
    DynamicJsonDocument rootGet(1024);
    deserializeJson(rootGet, server.arg("plain"));

    String stateDht11 = rootGet["dht11"];
    String stateHx711 = rootGet["hx711"];

    //回傳Json
    DynamicJsonDocument rootResp(1024);

    //判斷需要感應dht11時，讀取資料
    if (stateDht11 == "1") {
      getDht11();
      rootResp["humidity"] = humidity;
      rootResp["temp_cal"] = tempCal;
      rootResp["temp_fah"] = tempFah;
    }

    //判斷需要感應hx711時，讀取資料
    if (stateHx711 == "1") {
      getHx711();
      rootResp["weight"] = weight;
    }

    //將回傳Json放入
    serializeJson(rootResp, respJsonMes);
    Serial.println(respJsonMes);

    //回傳
    server.send(200, "application/json", respJsonMes);
  });

  server.onNotFound([]() {  // 處理「找不到指定路徑」的事件
    server.send(404, "text/plain", "File NOT found!");
  });

  server.begin();
}

void loop() {
  server.handleClient();  // 處理用戶連線
}

//讀取溫濕度dht11資料
void getDht11() {
  humidity = dht.readHumidity();//讀取濕度
  tempCal = dht.readTemperature();//讀取攝氏溫度
  tempFah = dht.readTemperature(true);//讀取華氏溫度
}

void getHx711() {
  weight = scale.get_units(10);
  scale.power_down();             // 進入睡眠模式
  scale.power_up();               // 結束睡眠模式
}
