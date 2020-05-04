/*
  Measuring Current Using ACS712
*/
// 繼電器開關設定
#define relayPin 6      //控制開關
#define turnon true
#define turnoff false
#define readCurrentCount 1000

//ACS712
#define SW A1 //acs712接腳
float value; //電流
int adc_zero = 500;           // 數位歸0校正
int i = 1;
float total;
float RMS;
float W;

void setup() {
  Serial.begin(115200);
  pinMode(relayPin, OUTPUT) ;
  digitalWrite(relayPin, turnon) ;
  initcurrent();
}

void loop() {
  acs712();
}

void initcurrent()              //初始化與校正
{
  int sensorV ;
  long sum = 0  ;
  delay(500);
  for (int i = 0; i < readCurrentCount; i++)
  {
    sensorV = analogRead(SW); 
    sum = sum + sensorV ;

  }
  adc_zero = (unsigned int)(sum / readCurrentCount) ;
  Serial.println("init Current is :(");
  Serial.print(adc_zero);
  Serial.print(")\n");
}

void acs712() {
  value = analogRead(SW);
  Serial.println( value );

  unsigned long start_time = millis();
  total = 0;
  i = 0;
  while (1) {
    if ( millis() - start_time  >= 166 ) break; // 60HZ = 1000/60=16.6

    value = analogRead(A0) - 512;
    total += value * value;
    i++;
  }

  RMS = (sqrt(total / i) - 1 ); // 1 是觀察後在無負載時出現的雜訊值
  if ( RMS >= 0 ) {
    RMS = RMS;
  }
  else {
    RMS = 0;
  }
  W = RMS * 0.074 * 110;
  Serial.print("RMS: ");
  Serial.print(RMS * 0.074);
  Serial.print(" W:");
  Serial.print(W);
  Serial.println();
  delay(500);
}
