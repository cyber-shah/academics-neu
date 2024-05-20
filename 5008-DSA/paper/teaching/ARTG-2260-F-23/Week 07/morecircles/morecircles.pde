Circle c1, c2;

Circle[] circles = new Circle[40];
void setup(){
  size(900, 600);
  for(int i = 0; i < circles.length; i++){
    float x = random(100, width-100);
    float y = random(100, height-100);
    float size = random(10, 60);
    float velocity = random(1, 10);
    circles[i] = new Circle(x, y, velocity, size);
  }
}
void draw(){
  background(255);
  for(Circle c : circles){
    c.move();
    c.display();
  }
}


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
