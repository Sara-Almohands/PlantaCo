#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>

/*Put your SSID & Password*/
const char* ssid = "ESP8266";  // Enter SSID here
const char* password = "24126315";  //Enter Password here

ESP8266WebServer server(80);
int sensor = A0;
int pump = 7;
int val = 0;

void setup() {
  Serial.begin(9600);
  pinMode (pump, OUTPUT);

  delay(100);

  Serial.println("Connecting to ");
  Serial.println(ssid);

  //connect to your local wi-fi network
  WiFi.begin(ssid, password);

  //check wi-fi is connected to wi-fi network
  while (WiFi.status() != WL_CONNECTED) {
  delay(1000);
  }
  Serial.println("");
  Serial.println("WiFi connected..!");
  Serial.print("Got IP: ");  Serial.println(WiFi.localIP());

  server.on("/", handle_OnConnect);
  server.onNotFound(handle_NotFound);

  server.begin();
  Serial.println("HTTP server started");
}
void loop() {
  // read the input on analog pin 0:
  val = analogRead(sensor);
  if (val <= 320) 
  {
    digitalWrite(pump,LOW);
  }
  else 
  {
    digitalWrite(pump,HIGH);
  }
  // print out the value you read:
  Serial.println(val);
  delay(3000); // delay in between reads for stability
  
  digitalWrite(pump,LOW);
  delay(2000);
  val = analogRead(sensor);
  if (val >= 320)
  {
   Serial.println("Empty");
  }

  server.handleClient();
void handle_OnConnect() {
  Serial.println("GPIO7 Status: OFF | GPIO6 Status: OFF");
  server.send(200, "text/html", SendHTML(LED1status,LED2status)); 
}

void handle_NotFound(){
  server.send(404, "text/plain", "Not found");
}

String SendHTML(uint8_t led1stat,uint8_t led2stat){
  String ptr = "<!DOCTYPE html> <html>\n";
  ptr +="<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\">\n";
  ptr +="<title>Container</title>\n";
  ptr +="<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}\n";
  ptr +="body{margin-top: 50px;} h1 {color: #444444;margin: 50px auto 30px;} h3 {color: #444444;margin-bottom: 50px;}\n";
  ptr +=".button {display: block;width: 80px;background-color: #1abc9c;border: none;color: white;padding: 13px 30px;text-decoration: none;font-size: 25px;margin: 0px auto 35px;cursor: pointer;border-radius: 4px;}\n";
  ptr +=".button-on {background-color: #1abc9c;}\n";
  ptr +=".button-on:active {background-color: #16a085;}\n";
  ptr +=".button-off {background-color: #34495e;}\n";
  ptr +=".button-off:active {background-color: #2c3e50;}\n";
  ptr +="p {font-size: 14px;color: #888;margin-bottom: 10px;}\n";
  ptr +="</style>\n";
  ptr +="</head>\n";
  ptr +="<body>\n";
  ptr +="<h1>ESP8266 Web Server</h1>\n";
    ptr +="<h3>Using Station(STA) Mode</h3>\n";
  
  if(water)
  {ptr +="<p>Full</p><a class=\"button button-off\"";}
  else
  {ptr +="<p>Empty</p><a class=\"button button-on\"";}

  ptr +="</body>\n";
  ptr +="</html>\n";
  return ptr;
}
