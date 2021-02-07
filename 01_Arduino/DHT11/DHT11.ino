#include "DHT.h"
#define dhtPin D4      //讀取DHT11 Data
#define dhtType DHT11 //選用DHT11   

DHT dht(dhtPin, dhtType); // Initialize DHT sensor

void setup() {
  Serial.begin(115200);//設定鮑率9600
  dht.begin();//啟動DHT
}

void loop() {
  float h = dht.readHumidity();//讀取濕度
  float t = dht.readTemperature();//讀取攝氏溫度
  float f = dht.readTemperature(true);//讀取華氏溫度

  Serial.print("濕度: ");
  Serial.print(h);
  Serial.print("%\t");
  Serial.print("攝氏溫度: ");
  Serial.print(t);
  Serial.print("*C\t");
  Serial.print("華氏溫度: ");
  Serial.print(f);
  Serial.print("*F\n");
  delay(2000);//延時5秒
}
