void setup() {
  size(800, 800);
  frameRate(30);
}
  
float ypos = 1;
boolean coming_down = true;

void draw() {
  background(255);
  strokeWeight(15);
  strokeJoin(ROUND);
  int multiplier = 15;
  beginShape();
  vertex(width/2, 0);
  for (int i = 1; i < 12; i++) {
    if (i % 2 == 0) {
      vertex(width/2 - 50, ypos * multiplier * i);
    }
    else {
      vertex(width/2 + 50, ypos * multiplier * i);
    }
  }
  endShape();
  
  // if under the screen size, keep bringing it down
  if (ypos > 4) {
     coming_down = false;
  }
  else if (ypos < 1) {
    coming_down = true;
  }
  
  if (coming_down) {
    ypos = ypos + 0.05;
  }
  else {
    ypos = ypos - 0.3;
  }
}
