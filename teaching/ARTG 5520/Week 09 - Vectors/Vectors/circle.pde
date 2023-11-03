class Circle{
  PVector pos;
  PVector vel;
  float size;
  
  Circle(float xpos, float ypos){
    size = 20;
    pos = new PVector(xpos, ypos);
    vel = new PVector(-3, -3);
  }
  
  void display(){
    fill(0);
    ellipse(pos.x, pos.y, size, size);
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
