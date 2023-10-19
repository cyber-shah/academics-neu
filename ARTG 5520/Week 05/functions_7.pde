float hue = 0;
float weight = 10;

float px = -1;
float py = -1;

float[] pointsX = { 150 };
float[] pointsY = { 150 };
float[] hues = { hue };

void setup() {
  size(800, 800);
  colorMode(HSB, 360, 100, 100, 100);
  background(0, 0, 100);
  noFill();
}

void draw() {
  background(0, 0, 100);
  drawShape();
  pointer();
}

void pointer() {
  hue = map(sin(frameCount/100.0), -1, 1, 0, 360);
  weight = map(sin(frameCount/100.0), -1, 1, 10, 30);
  
  float xp = pointsX[pointsX.length - 1];
  float xy = pointsY[pointsY.length - 1];
  
  strokeWeight(weight);
  stroke(hue, 100, 100, 50);
  line(xp, xy, mouseX, mouseY);
}

void drawShape() {
  strokeWeight(weight);
  for (int i = 1; i < pointsX.length; i++) {
    stroke(hues[i], 100, 100, 50);
    line(pointsX[i], pointsY[i], pointsX[i-1], pointsY[i-1]);
  }
}

void mouseDragged() {
  pointsX = append(pointsX, mouseX);
  pointsY = append(pointsY, mouseY);
  hues = append(hues, hue);
}

void mouseClicked() {
  pointsX = append(pointsX, mouseX);
  pointsY = append(pointsY, mouseY);
  hues = append(hues, hue);
}

void keyPressed() {
  switch(key) {
  case 'c':
    pointsX = new float[]{ mouseX };
    pointsY = new float[]{ mouseY };
    hues = new float[]{ hue };
  }
}
