void setup() {
  size(800, 800);
  background(100);
}

float step = 20;
float init = step;
float x_e = 800;

void draw() {
  // clear background each frame
  background(200);
  stroke(0);
  
  // draws line from height/4 to height/2
  // just changes x everytime based on
  for (float x = init; x > init-width/2; x -= step) {
    fill(0);
    line(x, 200, x-height/4, height/2);
  }
  
  // draws ellipses, 30 ellipses
  // moves every frame by -0.5, and Y stays the same
  x_e = x_e - 0.5;
  noFill();
  for (int i = 1; i<30; i++) {
    fill(0, 10);
    noStroke();
    ellipse(x_e, height/2, i*20, i*20);
  }
  
  // the lines start drawing from init + 1 after each draw call
  init++;
  if (init > width*2) init = 0;
  // circles
  // reset to right edge if goes past it
  if (x_e < 0) x_e = width;
}
