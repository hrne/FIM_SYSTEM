int sensorValue = 0;
void setup()
{
  Serial.begin(9600); // 設定Serial 傳輸速度
}
void loop()
{
  Serial.print("Value:");
  sensorValue = analogRead(D6); //讀取數位 pin 0
  Serial.print(sensorValue, DEC); // 顯示資料
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
