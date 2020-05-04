
//***********ASC712
#define SW A0                 //設定接線腳位
#define ananlogmax 1024
#define ACS712_30A 66
#define readCurrentCount 1000
int adc_zero = 500;           // 數位歸0校正
const int mi = 54 ;           // 數位歸0校正
static int count = 0;


//************繼電器設定
#define relayPin 6          //設定接線腳位
#define turnon true
#define turnoff false
boolean RelayStatus = false ;

void setup() {

  Serial.begin(115200);
  pinMode(relayPin, OUTPUT) ;
  digitalWrite(relayPin, turnon) ;
  initcurrent() ;               //初始化與校正


}


void loop() {

  //********讀取電流表資料透過子函式由LoRa模組送出*****
  double currentAcc = 0;
  currentAcc = Current() ;
  Serial.println(currentAcc);

  //*******監控是否有資料由LoRa端進來，再去控制Relay開關
  char cmd ;
  if (Serial.available())                 //LORA端有資料進來
  {
    cmd = Serial.read() ;
    if (!isAlpha(cmd))
      return ;
  }
  switch (cmd)
  {
    case 'O' :                          //Open
      digitalWrite(relayPin, turnon) ;
      RelayStatus = turnon ;
      delay(100);
      break ;

    case 'C' :                         //Close
      digitalWrite(relayPin, turnoff) ;
      RelayStatus = turnoff ;
      delay(100);
      break ;

    default :
      break ;
  }

  delay(1000);
}

int Current()               //讀取電流資料
{
  int tmp ;
  tmp = read_CurrentRMS() ;
  return ( tmp * mi) ;
}

void initcurrent()              //初始化與校正
{
  int sensorV ;
  long sum = 0  ;
  delay(500);
  for (int i = 0; i < readCurrentCount; i++)
  {
    sensorV = analogRead(SW); sum = sum + sensorV ;

  }
  adc_zero = (unsigned int)(sum / readCurrentCount) ;
  Serial.println("init Current is :(");
  Serial.print(adc_zero);
  Serial.print(")\n");
}

double read_CurrentRMS()    //計算方均根
{
  int sensorValue = 0;
  int tmp = 0 ;
  long summ = 0 ;
  //讀取一千次，取得最高與最低值，相減得到峰對峰值
  for (int i = 0; i < readCurrentCount; i++)
  {
    sensorValue = analogRead(SW);
    tmp = (sensorValue - adc_zero) ;
    summ = summ + tmp * tmp;
  }
  return (sqrt((summ / readCurrentCount) ) ) ;
}

int read_Current()          //計算方均根
{
  unsigned int sensorValue = 0;
  unsigned int uiCurrentMaxValue = 0;
  unsigned int uiCurrentMinValue = 65535;
  //讀取一千次，取得最高與最低值，相減得到峰對峰值
  for (int i = 0; i < readCurrentCount; i++)
  {
    sensorValue = analogRead(SW);
    if (sensorValue > uiCurrentMaxValue)
    {
      uiCurrentMaxValue = sensorValue;
    }
    if (sensorValue < uiCurrentMinValue)
    {
      uiCurrentMinValue = sensorValue;
    }
  }
  return (uiCurrentMaxValue - uiCurrentMinValue) ;
}
