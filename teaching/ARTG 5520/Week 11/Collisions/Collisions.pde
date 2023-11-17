class Circle{
  PVector pos;
  PVector vel;
  float radius;
  color co;
  
  Circle(float x, float y){
    pos = new PVector(x, y);
    vel = new PVector(0, 0);
    radius = random(10, 30);
    co = color(random(200,360), 100, 100);
  }
  
  void update(){
    pos.add(vel);
    vel.mult(0.97);
  }
  
  void display(){
    noStroke();
    fill(co);
    circle(pos.x, pos.y, radius * 2);
  }
  
  float getMass(){
    return (PI * radius * radius) / 100;
  }
  
}
  
