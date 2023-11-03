class Circle{
  PVector pos;
  PVector vel;
  float size;
  float lifeSpan = 2;
  
  Circle(float xpos, float ypos){
    pos = new PVector(xpos, ypos);
    vel = new PVector(-3, -3);
    
    // create a random speed and then adjust size based on it
    float speed = random(1, 20);
    // Map the speed to the size of the circle
    size = map(speed, 1, 20, 50, 10); 
    vel.setMag(speed); 
  }
  
  void display(){
    fill(0);
    ellipse(pos.x, pos.y, size, size);
    lifeSpan -= 0.01;
  }
  
  void move(){
    if(pos.x < 0 || pos.x > width){
      vel.x *= -1;
    }
    if(pos.y < 0 || pos.y > height){
      vel.y *= -1;
    }
    pos.add(vel);
  }
}
