float voltage;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);


}
void loop() {
  // put your main code here, to run repeatedly:
  Serial.println(analogRead(A0));
  voltage = analogRead(A0);
  voltage = voltage / 1023;
  voltage = voltage * 3.3 * 4.9;
  Serial.println(voltage);
  delay(500);
}
