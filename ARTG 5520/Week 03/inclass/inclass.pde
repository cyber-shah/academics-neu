void setup() {
  size(800, 800);
  background(255);
  fill(0);
  frameRate(2);
}

float curveTightness = 0;

// shapes and curve tightness
void shapes() {
  background(255);
  beginShape();
  curveTightness(curveTightness);
  noFill();
  curveVertex(0,0);
  for (int i = 0; i < 7; i++) {
    float x = (i+1) * 90;
    float y = height/2 + random(-height/3,height/3);
    curveVertex(x,y);
    circle(x,y, 5);
  }
  curveVertex(width, height);
  endShape();
  text(curveTightness, 30, 30);
}

void keyPressed() {
  if (keyCode == UP) {
    curveTightness += 0.09;
  } else if (keyCode == DOWN) {
    curveTightness -= 0.09;
  }
}




// Part 1: Red to green
void lines() {
  float r; float g;
  for (int i = 0; i < width; i++) {
    //r = i % 255;
    //g = (255 - i);
    
    r = map(i, 0, width, 0, 255);
    g = map(i, 0, width, 255, 0);
    
    stroke(r,g,0);
    line(i,0, i,height);
  }
}

void points() {
    //Loop through every pixel
  for(int y = 0; y < height; y++) {
    for(int x = 0; x < width; x++) {
      
      //Red increases with x
      float r = map(x, 0, width, 0, 255); 
      
      //Green increases with y  
      float g = map(y, 0, width, 0, 255);
    
      //Blue decreases with x
      float b = map(y, 0, width, 255, 0);
      
      //Draw pixel with color
      stroke(r, g, b);
      point(x, y);
    }
  }
}


void ellipses() {  
  
  float hue = 0;
  for (int radius = 0; radius < width/2; radius ++) {
    colorMode(HSB, 360, 100, 100);
    hue = map(radius, 0, width/2, 0, 360);
    noFill(); stroke(hue, 100, 100);
    ellipse(width/2, width/2, radius *2, radius * 2);
  }
}


void ellipses2(){
  
  float hue = 0;
  float diagonal = sqrt(sq(width) + sq(height));
  
  for (int radius = 0; radius < diagonal/2; radius ++) {
    colorMode(HSB, 360, 100, 100);
    hue = map(radius, 0, diagonal/2, 0, 360);
    noFill(); stroke(hue, 100, 100);
    ellipse(width/2, width/2, radius *2, radius * 2);
  }
}

void ellipses3() {
  float hue = 0;
  float diagonal = sqrt(sq(width) + sq(height));
  
  for (int radius = 0; radius < diagonal/2; radius ++) {
    colorMode(HSB, 360, 100, 100);
    hue = map(sin(radius/5.0), -1, 1, 0, 360);
    noFill(); stroke(hue, 100, 100);
    ellipse(width/2, width/2, radius *2, radius * 2);
  }
}


void draw() {
  
  //lines();
  //points();
  ellipses();
  //ellipses2();
  //ellipses3();
  //shapes();
}
