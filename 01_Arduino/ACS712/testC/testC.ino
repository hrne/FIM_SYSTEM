
// 繼電器開關設定
#define relayPin D6      //控制開關
#define turnon true
#define turnoff false
#define readCurrentCount 1000

int adc_zero = 500;           // 數位歸0校正
/*
  Measuring AC Current Using ACS712
*/
const int sensorIn = A0;
int mVperAmp = 66; // use 100 for 20A Module and 66 for 30A Module


double Voltage = 0;
double VRMS = 0;
double AmpsRMS = 0;



void setup() {
  Serial.begin(115200);
  pinMode(relayPin, OUTPUT) ;
  digitalWrite(relayPin, turnon) ;
}

void loop() {


  Voltage = getVPP();
  VRMS = (Voltage / 2.0) * 0.707;
  AmpsRMS = (VRMS * 1000) / mVperAmp;
  Serial.print(AmpsRMS);
  Serial.println(" Amps RMS");

}

float getVPP()
{
  float result;

  int readValue;             //value read from the sensor
  int maxValue = 0;          // store max value here
  int minValue = 1024;          // store min value here

  uint32_t start_time = millis();
  while ((millis() - start_time) < 1000) //sample for 1 Sec
  {
    readValue = analogRead(sensorIn)-0;
    // see if you have a new maxValue
    if (readValue > maxValue)
    {
      /*record the maximum sensor value*/
      maxValue = readValue;
    }
    if (readValue < minValue)
    {
      /*record the maximum sensor value*/
      minValue = readValue;
    }
  }

  // Subtract min from max
  result = ((maxValue - minValue) * 5.0) / 1024.0;

  return result;
}

void initcurrent()              //初始化與校正
{
  int sensorV ;
  long sum = 0  ;
  delay(500);
  for (int i = 0; i < readCurrentCount; i++)
  {
    sensorV = analogRead(A0); 
    sum = sum + sensorV ;

  }
  adc_zero = (unsigned int)(sum / readCurrentCount) ;
  Serial.println("init Current is :(");
  Serial.print(adc_zero);
  Serial.print(")\n");
}
