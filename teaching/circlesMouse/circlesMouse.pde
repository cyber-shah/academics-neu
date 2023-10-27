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
    lifespan -= 0.01;
  }
}













ArrayList<Circle> circles = new ArrayList();

void setup(){
  size(900, 600);
}


void draw(){
  background(255);
  for(Circle c : circles){
    c.move();
    c.display();
  }
  
  for(int i = circles.size() - 1; i >= 0; i--){
    Circle c = circles.get(i);
    if(c.lifespan <= 0){
      circles.remove(i);
    }
  }
}

void mouseClicked() {
  circles.add(new Circle(mouseX, mouseY, random(1,10), random(1,60)));
}
