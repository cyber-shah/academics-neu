/**
  Created by Pranchal Shah
  
  1. To begin withGet the current point and append it to the array.
  2. A line is made between i and i+1, i+1 and i+2.
  2. Get the current mouse location and draw a line between the last element in the array and current mouse location
  3. Whenever a mouse is clicked, it is added to the array.
*/


// array of floats taking in points
float[] pointsX = { 150 };
float[] pointsY = { 150 };

float weight = 10;
float hue = 50;

void setup() {
  size(800, 800);
  colorMode(HSB, 360, 100, 100, 100);
  background(0, 0, 100);
  noFill();
}

void draw() {
  background(0, 0, 100);
  drawLineClick();
  drawLinePointers();
}

void drawLineClick() {
  strokeWeight(weight);
  for (int i = 1; i < pointsX.length; i ++) {
    stroke(hue, 100, 100, 50);
    line(pointsX[i], pointsY[i], pointsX[i-1], pointsY[i-1]);
  }
}


// we will modify the hues and weights here only
void drawLinePointers() {
  hue = map(sin(frameCount/50.0), -1, 1,0 ,360);
  weight = map(sin(frameCount/100.0), -1, 1,10 ,36);
  strokeWeight(weight);
  stroke(hue, 100, 100, 50);
  // make a line with the last point in the array and 
  // the current cursor point
  line(pointsX[pointsX.length - 1], pointsY[pointsY.length - 1], mouseX, mouseY);
}

// draws line whenever just clicked once
void mouseClicked() {
  pointsX = append(pointsX, mouseX);
  pointsY = append(pointsY, mouseY);
}

// draws line whenever pressed and drawn
void mouseDragged() {
  pointsX = append(pointsX, mouseX);
  pointsY = append(pointsY, mouseY);
}
