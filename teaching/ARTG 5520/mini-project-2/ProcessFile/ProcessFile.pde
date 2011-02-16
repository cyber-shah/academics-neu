// PROCESS FILE

PFont font;


void setup() {
  size(400,600);
  frameRate(30);
  //font = createFont("IBMPlexSans-SemiBold.otf", 24);
}

// array of lines
String[] lines = {"hey", "how are ya"};
String currentLine;


void draw() {
  
  background(220);
  textSize(20);
  //textFont(font);
  
  currentLine = lines[lines.length - 1];
  
  // 1. create a render text
  for (int i = 0; i < lines.length; i++) {
    fill(10, 23, 32);
    text(lines[i], 150, 50 + i * 50);
  }
  
  // 2. remove from text
  removeText();
  
  // 3. add to text
  for (int i = 0; i < lines.length; i++) {
    fill(100, 50, 80);
    text(lines[i] + "add", 150, 500 + i * 50);
  }
  
}


void removeText() {
  for (int i = 0; i < lines.length; i++) {
    fill(80, 53, 3);
    text(lines[i].substring(0, lines[i].length() - 1), 150, 250 + i * 50);
  }
}
