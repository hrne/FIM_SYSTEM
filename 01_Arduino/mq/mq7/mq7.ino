int sensorValue = 0;
void setup()
{
  Serial.begin(115200); // 設定Serial 傳輸速度
  pinMode(D7, INPUT);//sets the pin as an input to the arduino
}
void loop()
{
  Serial.print("Value:");
  sensorValue = analogRead(A0); //讀取數位 pin 0
  Serial.println(sensorValue, DEC); // 顯示資料
  delay(1000);
  int keystate = digitalRead(D7); // 取得輸入PIN 腳的值
  Serial.println(keystate); // 安全
  if (keystate == 0)
  {
    Serial.print(" Safe"); // 安全
  } else {
    Serial.print(" Help"); // 偵測到了
  }
  Serial.println(" ");

}
