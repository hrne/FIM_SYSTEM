#include <HX711.h>

// HX711 circuit wiring
#define DOUT D2 // uno pin 2
#define CLK D3 // uno pin 3
//scale.begin(DOUT, CLK);

const int sample_weight = 130;  //基準物品的真實重量(公克)
HX711 scale; // initialize load cell amplifire

void setup() {
  Serial.begin(9600);
  scale.begin(DOUT, CLK);
  scale.set_scale();  // 開始取得比例參數
  scale.tare();
  Serial.println("Nothing on it.");
  Serial.println(scale.get_units(10));
  Serial.println("Please put sapmple object on it..."); //提示放上基準物品
  
}

void loop() {
  float current_weight=scale.get_units(10);  // 取得10次數值的平均
  float scale_factor=(current_weight/sample_weight);
  Serial.print("Scale number:  ");
  Serial.println(scale_factor,0);  // 顯示比例參數，記起來，以便用在正式的程式中
  
}
