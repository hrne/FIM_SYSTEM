#include <HX711.h>

// HX711 circuit wiring
#define DOUT D2 // uno pin 2
#define CLK D3 // uno pin 3
//scale.begin(DOUT, CLK);

const int scale_factor = 378; //比例參數，從校正程式中取得
HX711 scale; // initialize load cell amplifire
//hx711
float weight = 0;//重量

void setup() {
  Serial.begin(9600);
  Serial.println("Initializing the scale");

  scale.begin(DOUT, CLK);

  Serial.println("Before setting up the scale:");

  Serial.println(scale.get_units(5), 0);  //未設定比例參數前的數值

  scale.set_scale(scale_factor);       // 設定比例參數
  scale.tare();               // 歸零

  Serial.println("After setting up the scale:");

  Serial.println(scale.get_units(5), 0);  //設定比例參數後的數值

  Serial.println("Readings:");  //在這個訊息之前都不要放東西在電子稱上
}

void loop() {
  weight = scale.get_units(10);
  if (weight >= 0) {
    Serial.println(weight);
  }else{
    Serial.println(0);
    }


  scale.power_down();             // 進入睡眠模式
  delay(1000);
  scale.power_up();               // 結束睡眠模式
}
