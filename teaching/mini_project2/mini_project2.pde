import processing.sound.*;
SoundFile file;
SoundFile enterSound;
PFont font;
//https://mixkit.co/free-sound-effects/typewriter/
//https://freesound.org/browse/tags/typewriter/
//https://fonts.google.com

String[] text = {""};
float leading = 36;

void setup() {
  size(400, 600);
  font = createFont("IBMPlexSans-SemiBold.otf", 24);
  file = new SoundFile(this, "mixkit-typewriter-soft-click-1125.wav");
  enterSound = new SoundFile(this, "mixkit-mechanical-typewriter-hit-1365.wav");
  pixelDensity(2);
}

String currentLine;
boolean cursor = false;
int cursorCounter = 10;
float marginL = 30;

void draw() {
  background(240);
  fill(0);
  textSize(24);
  textLeading(leading);
  textFont(font);

  currentLine = text[text.length-1];
  float theight = 30;

  int i = 0;
  for (String line : text) {
    if (i == text.length - 1)
      fill(0);
    else fill(170);
    text(line, marginL, theight);
    theight += leading;
    i++;
  }

  //cursor
  if (frameCount % 20 == 0) {
    cursor = true;
    cursorCounter = 10;
  }

  if (cursor) {
    rect(textWidth(currentLine)+marginL + 2, 30+((text.length-1)*leading) + 3, 3, -24);
    cursorCounter--;
    if (cursorCounter <= 0) {
      cursor = false;
    }
  } 
}

void keyPressed() {
  if (key != CODED) {
    if (key != BACKSPACE) {
      if (textWidth(currentLine) < width - marginL*2) {
        file.play();
        text[text.length-1] = text[text.length-1]+key;
      }
    } else {
      file.play();
      String ss = text[text.length-1];
      if (ss.length() > 0) {
        
        text[text.length-1] = ss.substring(0, ss.length()-1);
      } else {
        if (text.length > 1)
          text = shorten(text);
      }
    }
    if (key == ENTER || key == RETURN) {
      enterSound.play();
      text = append(text, "");
    }
  }
}
