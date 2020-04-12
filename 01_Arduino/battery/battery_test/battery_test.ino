 void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  while (!Serial);
  pinMode(D0, INPUT_PULLUP);
}
void loop() {
  // put your main code here, to run repeatedly:
  double voltage = (double)analogRead(D0) / 1023 * 5;
  Serial.println(voltage);
  delay(500);
}
