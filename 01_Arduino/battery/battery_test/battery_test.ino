 void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);

}
void loop() {
  // put your main code here, to run repeatedly:
  double voltage = (double)analogRead(A0) / 1023 * 3.3;
  Serial.println(voltage);
  delay(500);
}
