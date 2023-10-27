void setup(){
  size(800, 800);
  frameRate(30);
}


float x = 1;
float y = 1;


void draw(){
  background(255);
  Three();
  

}


void Three() {

  fill(120);
  
  pushMatrix();
  translate(width/2, height/2);
  rotate(radians(x));
  rectMode(CENTER); // Set the rect mode to CENTER
  rect(0, 0, 50, 50);
  popMatrix(); 
  x ++;
  
  pushMatrix();
  translate(width/2 + 55, height/2);
  rotate(radians(y));
  rectMode(CENTER); // Set the rect mode to CENTER
  rect(0, 0, 25, 25);
  popMatrix();
  y+= 4;
  
  pushMatrix();
  translate(width/2, height/2);
  noFill();
  ellipse(0,0, 150,150);
  rotate(radians(x));
  rect(50, 50, 25, 25);
  // draw a rectangle here
  popMatrix();
}


void Falling() {
  fill(120);
  rect(0, height/2, width, height/2);
  for (int i = 0; i < 20; i++) {
    pushMatrix();
    translate(10 + (i * 40), height/2);
    rotate(radians(i * 3)); 
    rect(-15, -60, 15, 60);
    popMatrix();  
  }
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
