PFont font;

void setup() {
  size(400,600);
  frameRate(30);
  font = createFont("IBMPlexSans-SemiBold.otf", 24);
}

// array of lines
String[] linesArray = {""};
String currentLine;
int margins = 5;
int t_size = 20;

void draw() {
  background(220);
  textSize(t_size);
  textFont(font);
  
  // set the current line
  currentLine = linesArray[linesArray.length - 1];
  
  // for loop that prints 
  for (int i = 0; i < linesArray.length; i++) {
    if (i == linesArray.length - 1) {
      fill(0);
    }
    else {
      fill(170);
    }
    text(linesArray[i], margins, t_size + (i * (t_size+2)));
  }
}

void keyPressed() {
  if (key != CODED) {
    // if enter
    if (key == RETURN || key == ENTER) {
      newLine();
    }
    // if backspace
    else if (key == BACKSPACE) {
      backspace();
    }
    // if none of the above apply
    else {
      if (textWidth(currentLine) < width - (4 * margins)) {
        currentLine += key;
        linesArray[linesArray.length - 1] = currentLine;
      }
    }
  }
}


void backspace() {
  // if line is not empty
  if (currentLine.length() > 0) {
    linesArray[linesArray.length - 1] = currentLine.substring(0, currentLine.length() - 1);
  }
  // if there are no other lines and the first line is empty
  else if (linesArray.length == 1 && linesArray[0].length() == 0) {
    linesArray[linesArray.length - 1] = "";
  }
  // else reduce lines
  else {
    linesArray = shorten(linesArray);
  }
}

void newLine() {
  // 
  if ((linesArray.length * (t_size+2)) < height - (t_size)) {
    linesArray = append(linesArray, "");
    currentLine = linesArray[linesArray.length - 1];
  }
  else {
    return;
  }
}
