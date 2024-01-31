PFont font;
String[] text;
String passage;

int hue = 5;
int x = width/2;
int balance = 1;
int saturation = 10;

void setup() {
  font = createFont("IBM Plex OTF/IBMPlexSans-Medium.otf", 24);
  size(500, 800);
  pixelDensity(2);
  textFont(font);
  frameRate(30);
}

void draw () {
  background(0);
  String flatland = "Flatland";
  char[] char_array = flatland.toCharArray();
  x = width/2 - 100;
  for(char c : char_array){
    fill(hue, saturation, balance);
    textSize(70);
    text(c, x, height/2);
    x += textWidth(c); 
    hue += 50;
    hue %= 360;
  }
  balance +=1;
  saturation += 4;
  
  balance %= 360;
  saturation %= 360;
}
