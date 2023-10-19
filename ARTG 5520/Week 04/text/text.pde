PFont font;
String[] text;
String passage;
int leading = 24;
int size = 16;
float margin = 18;
int line_number = 0;

void setup() {
  font = createFont("IBM Plex OTF/IBMPlexSans-Medium.otf", 24);
  text = loadStrings("shopping-list.txt");
  size(500, 800);
  pixelDensity(2);
  textFont(font);
  fill(#FA008A);
}

void draw() {
  if (line_number > 19) {
    line_number = 0;
  }
  if ( line_number < 0) {
    line_number = 0;
  }
  passage = text[line_number];
  
  background(0);
  textSize(size);
  textLeading(leading);
  text( passage,
        margin,
        margin,
        width - 2 * margin,
        height - 2 * margin);

}

void keyPressed(){
  if(key == CODED){
    switch(keyCode){
      case UP:
        size++;
        leading ++;
        break;
      case DOWN:
        size--;
        leading --;
        break;
      case LEFT:
        line_number--;
        break;
      case RIGHT:
        line_number++;
    }
  }
}
