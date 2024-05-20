class Circle{
  float x, y, velocity, size, r, g, b;
  float lifespan = 10;
  
  Circle(float xpos, float ypos, float vel, float s){
    x = xpos;
    y = ypos;
    velocity = vel;
    size = s;
    r = 0;
    g = 0;
    b = 0;
  }
  
  Circle(float xpos, float ypos, float vel, float s, float red, float green, float blue){
    x = xpos;
    y = ypos;
    velocity = vel;
    size = s;
    r = red;
    g = green;
    b = blue;
  }
  
  void display(){
    fill(this.r, this.g, this.b);
    ellipse(x, y, size, size);
  }
  
  void move(){
    y += velocity;
    if((y > (height - size/2)) || (y < size/2)){
      velocity *= -1;
    }
    lifespan -= 0.1;
  }
}
