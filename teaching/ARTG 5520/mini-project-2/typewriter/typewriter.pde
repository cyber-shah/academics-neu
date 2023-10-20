PFont font;

void setup() {
  size(400,600);
  frameRate(30);
  font = createFont("IBMPlexSans-SemiBold.otf", 24);
}


// array of lines
String[] linesArray = {"hey", "how are ya"};
String currentLine;



void draw() {
  background(220);
  textSize(20);
  textFont(font);
  
  currentLine = linesArray[linesArray.length - 1];
  
  // for loop that prints 
  for (int i = 0; i < linesArray.length; i++) {
    if (i == linesArray.length - 1) {
        fill(0);
    }
    else {
        fill(170);
    }
    text(linesArray[i], 150, 200 + i * 50);
   }
}

void keyPressed() {
  if (key != CODED) {
    
    // if enter
    if (key == RETURN || key == ENTER) {
      newLine();
    }
    
    // if backspace
    if (key == BACKSPACE) {
      backspace();
    }
    
    currentLine += key;
    linesArray[linesArray.length - 1] = currentLine; 
  }
}


void backspace() {
  if (currentLine.length() > 0) {
    linesArray[linesArray.length - 1] = currentLine.substring(0, currentLine.length() - 1);
  }
  else {
    linesArray = shorten(linesArray);
  }
}

void newLine() {
  linesArray = append(linesArray, "");
  currentLine = linesArray[linesArray.length - 1];
}
