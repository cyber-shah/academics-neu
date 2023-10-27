void setup(){
  size(800, 800);
  frameRate(30);
}
  float x = 1;
  
void draw(){
  background(255);
}

void rotateDraw() {
  translate(width/2, height/2);
  rotate(radians(x));
  axes();
  if (x > 360) {
    x = 0;
  }
  x += 1;
}

void axesDraw() {
  translate(30, 30);
  axes();

  translate(30, 30);
  axes();

  translate(250, 0);
  rotate(PI/6);
  axes();

  translate(0, 250);
  rotate(-PI/6);
  axes();

  translate(0, 250);
  scale(0.5);
  axes();

  translate(500, 0);
  scale(2, -2);
  axes();
  ellipse(50, 50, 20, 20);
}

void axes(){
  stroke(0);
  strokeWeight(1.5);
  fill(0);
  line(0, 0, 200, 0);
  text("x", 210, 0);
  line(0, 0, 0, 200);
  text("y", 0, 210);

  fill(255, 0, 0);
  rect(0, 0, 40, 40);
}
