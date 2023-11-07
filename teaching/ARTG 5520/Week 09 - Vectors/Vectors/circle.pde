class Circle{
  PVector pos;
  PVector vel;
  float size;
  float lifeSpan = random(0, 1);
  float speed;
  float hue;
  float saturation = random(50, 100);
  float balance = random(50, 100);
  
  Circle(float xpos, float ypos){
    pos = new PVector(xpos, ypos);
    vel = PVector.random2D();
    // create a random speed and then adjust size based on it
    speed = random(1, 7);
    // Map the speed to the size of the circle
    size = map(speed, 1, 7, 10, 2); 
    vel.setMag(speed); 
  }
  
    Circle(float xpos, float ypos, float h){
    pos = new PVector(xpos, ypos);
    vel = PVector.random2D();
    speed = random(1, 5);
    hue = h;
    size = map(speed, 1, 5, 7, 1); 
    vel.setMag(speed); 
  }

  void display(){
    noStroke();
    float alpha = map(lifeSpan, 0, 1, 0, 360); // Map lifeSpan to alpha
    fill(hue, saturation, balance, alpha);
    for (int i = 0; i < 20; i++) {
      ellipse(pos.x, pos.y, map(i, 0, 20, 0, size), map(i, 0, 20, 00, size));
    }
    lifeSpan -= 0.02; // Decrease lifeSpan over time
  }


  void move(){
    if(pos.x < 0 || pos.x > width){
      vel.x *= -1;
    }
    if(pos.y < 0 || pos.y > height){
      vel.y *= -1;
    }
    pos.add(vel);
    
    if (speed > 0) {
    speed -= 0.08;  // Increase this value to slow down faster
  } else {
    speed = 0;
  }
  vel.setMag(speed);
  }
}
