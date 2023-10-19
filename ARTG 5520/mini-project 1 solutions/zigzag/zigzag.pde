boolean down = true;
float inc = 50;

void setup(){
  size(800, 800);
  background(255);
}

void draw(){
  background(255);
  noFill();
  strokeWeight(15);
  strokeJoin(ROUND);

  float incx = 50;
  beginShape();
  vertex(width/2, 0);
  for(int i = 1; i < 12; i++){
    // for every even number of vertex
    // move it to the left 
    if(i % 2 == 0){
      vertex(width/2 - incx, i*inc);
    } 
    // else move it to the right
    else {
      vertex(width/2 + incx, i*inc);
    }
  }
  endShape();
  
  // switch statement, if inc is greater than hundred, go up
  if(inc > 100){
    down = false;
  }
  // if less than 50, move down
  if(inc <= 50){
    down = true;
  }
  
  // if down keep increasing inc
  if(down){
    inc+=0.5; 
  } 
  // else reduce it, bounce back
  else {
    inc-=5;
  }

}
