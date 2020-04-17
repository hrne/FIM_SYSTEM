/*
Measuring Current Using ACS712
*/
// ACS712
float value;
int i = 1;
float total;
float RMS;
float W;

 
unsigned char senBuf[9] = {0};
 
static int count = 0;
 
// 繼電器設定
const int relayPin = 5;
 
// 溫濕度設定
float t;
float h;
int   w;
void setup(){ 
 Serial.begin(9600);
}

void loop(){
 
acs712();

 
}
void acs712() {
    value = analogRead(A0);
    Serial.println( value );
 
    unsigned long start_time = millis();
    total = 0;
    i = 0;
    while (1) {
        if ( millis() - start_time  >= 166 ) break; // 60HZ = 1000/60=16.6
 
        value = analogRead(A0) - 825;
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
    Serial.print(RMS);
    Serial.print(" W:");
    Serial.print(W);
    Serial.println();
 
//    mySerial.print("{\"W\":\"");
//    mySerial.print(RMS);
//    mySerial.print("\",\"W\":\"");
//    mySerial.print(W);
//    mySerial.print("\"}");
//    mySerial.println();
// 
    delay(100);
}
