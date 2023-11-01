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
    //fill(random(0,255), random(0,255), random(0,255))

class Circle{
  float x, y, velocity, size, r, g, b;
  
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
    velocity += 1.5;
    y += velocity;
    if((y > (height - size/2)) || (y < size/2)){
      velocity *= -1;
    }
    else if (y > (height - size / 3) ) {
      velocity = 0;
    }
  }
}
