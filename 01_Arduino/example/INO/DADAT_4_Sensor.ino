
//***********ASC712
#define SW A0                 //設定接線腳位
#define ananlogmax 1024 
#define ACS712_20A 100
#define readCurrentCount 1000
int adc_zero = 500;           // 數位歸0校正
const int mi = 54 ;           // 數位歸0校正
static int count = 0;


//************繼電器設定
#define relayPin 2          //設定接線腳位
#define turnon true 
#define turnoff false
boolean RelayStatus = false ;

int lora_out;

void setup(){
  
  Serial.begin(115200);
  pinMode(relayPin, OUTPUT) ; 
  digitalWrite(relayPin, turnon) ; 
  initcurrent() ;               //初始化與校正
  S76S_node();                  //設定S76S 

}


void loop() {

  //********讀取電流表資料透過子函式由LoRa模組送出*****
  double currentAcc = 0; 
  currentAcc = Current() ;
  lora_out = currentAcc;
  LoRa_send();

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
    for(int i=0;i<readCurrentCount;i++) 
    {
      sensorV = analogRead(SW); sum = sum + sensorV ;
      
    }
      adc_zero = (unsigned int)(sum / readCurrentCount) ; 
      Serial.print("init Current is :("); 
      Serial.print(adc_zero);
      Serial.print(")\n");
    }
    
double read_CurrentRMS()    //計算方均根
  {
    int sensorValue=0;
    int tmp = 0 ; 
    long summ = 0 ;
    //讀取一千次，取得最高與最低值，相減得到峰對峰值 
    for(int i=0;i<readCurrentCount;i++)
    {
      sensorValue = analogRead(SW);
      tmp = (sensorValue - adc_zero) ;
      summ = summ + tmp * tmp;
    }
      return (sqrt((summ/readCurrentCount) ) ) ; 
  }
  
int read_Current()          //計算方均根
  {
    unsigned int sensorValue=0;
    unsigned int uiCurrentMaxValue = 0;
    unsigned int uiCurrentMinValue = 65535;
    //讀取一千次，取得最高與最低值，相減得到峰對峰值 
    for(int i=0;i<readCurrentCount;i++)
      {
        sensorValue = analogRead(SW); 
        if(sensorValue > uiCurrentMaxValue) 
        {
          uiCurrentMaxValue = sensorValue; 
        }
        if(sensorValue < uiCurrentMinValue) 
        {
          uiCurrentMinValue = sensorValue; 
        }
       }
     return (uiCurrentMaxValue-uiCurrentMinValue) ; 
    }



 

void S76S_node(){
  Serial.println("SetSystemMode inNormal");//設定模式，有標準模式（inNormal ）跟Ping Pong（inTD）測試模式
  delay(100);
  
  Serial.println("LoraMode SLAVE");        //使用指令設定為Master或是Slave
  delay(100);
  
  Serial.println("LoRaSetPower 20");       //使用指令設定TX Power範圍為5-20
  delay(100);

  Serial.println("LoraSetSF 10");          //使用者令設定SF範圍為8--12
  delay(100);

  Serial.println("LoraSetMyAddr 2 2 2");   //設定位址，這個很重要，不然怎麼知道要給誰
  delay(100);

  Serial.println("LoraSetUpLinkTimeSlot 2 ");//設定發送時間
  delay(100);

  Serial.println("LoraSetGateWayAddr 8 8 8 ");//設定Gateway的位址
  delay(100);

 
  }

void LoRa_send(){

  Serial.println("LoraStartWork ENABLE");      //設定啟用就可以開始傳送資料
  delay(100);

  Serial.print("LoraSlavePld 0");          // Mode 0 傳送資料
  Serial.println(lora_out);            
  delay(100);

}
