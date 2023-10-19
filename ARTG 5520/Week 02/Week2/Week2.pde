  int lineY = 0;

void setup() {
  size(800, 800);
  background(255);
  // Sets the FPS for draw screen.
  // draw is updated with FPS, everytime draw is called
  // 10 times called per second
  frameRate(30);
}

void draw() {
  background(255);
  make_squares(false);
  make_circles(true);  
} //<>// //<>//


void make_squares(boolean action) {
  if (action == false) {
    return;
  }
  fill(0);
  noStroke();
  int grid = 30;
  for(int x = grid; x < width; x += grid){
    for(int y = grid; y < height; y += grid){
      if (x == y) {
        fill(0);
        rect(x, y, 7, 7);
      }
      else {
        fill(200);
        ellipse(x, y, 7, 7);
      }
    }
  }
}

void make_circles(boolean action) {
  if (action == false) {
    return;
  }
  fill(0);
  noStroke();
  int grid = 30;
  for(int x = grid; x < width; x += grid){
    for(int y = grid; y < height; y += grid){
      for (int i = 3; i > 0; i--) {
        fill(255-i*60);
        ellipse(x, y, i*10, i*10);
      }
    }
  }
}
