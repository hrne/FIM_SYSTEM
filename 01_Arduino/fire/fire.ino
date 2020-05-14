#define firePin D8          //設定接線腳位
#define mq7Pin D9          //設定接線腳位
int val;
void setup() {
  // put your setup code here, to run once:
  pinMode(firePin, INPUT);
  pinMode(mq7Pin, INPUT);
  Serial.begin(115200);
}

void loop() {
  // put your main code here, to run repeatedly:

  val = digitalRead(firePin);
  if (val == HIGH)
  {
    Serial.println("火災發生");
  }
  else
  {
    Serial.println("安全");
  }
}
