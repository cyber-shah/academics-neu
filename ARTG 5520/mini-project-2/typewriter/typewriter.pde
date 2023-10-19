void setup() {
  size(800,600);
  frameRate(30);
}

// array of lines
String[] linesArray = {"hey", "how are ya"};
String currentLine;

void draw() {
  background(220);
  textSize(20);
  // for loop 
  for (int i = 0; i < linesArray.length; i++) {
    println(i, linesArray[i]);
    if (i == linesArray.length - 1) {
        fill(5);
        text(linesArray[i], 150, 200 + i * 100);
    }
    else {
        fill(255);
        text(linesArray[i], 150, 200 + i * 100);
    }
   }
}

void keyPressed() {
  if (key != CODED) {
    if (key == RETURN) {
      linesArray = append(linesArray, " ");
    }
    currentLine += key;
    linesArray[linesArray.length - 1] = currentLine; 
  }
}
