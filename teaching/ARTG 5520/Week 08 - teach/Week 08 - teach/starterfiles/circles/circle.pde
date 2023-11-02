class Circle{
  float x, y, velocity, size;
  
  Circle(float xpos, float ypos, float vel, float s){
    x = xpos;
    y = ypos;
    velocity = vel;
    size = s;
  }
  
  void display(){
    fill(0);
    ellipse(x, y, size, size);
  }
  
  void move(){
    y += velocity;
    if((y > (height - size/2)) || (y < size/2)){
    velocity *= -1;
    }
  }
}
