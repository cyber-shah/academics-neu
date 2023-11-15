class Particle{
  
  PVector pos;
  PVector vel;
  PVector acc;
  float size;
  float hue;
  
  Particle(){
    pos = new PVector(mouseX, mouseY);
    float speed = random(1, 5);
    vel = new PVector(0, speed);
    vel.rotate(random(TWO_PI));
    size = random(2, 10);
    acc = new PVector(0, 0);
  }
  
  void move(){
    /* computes the acceleration */
    acc = PVector.sub(att, pos);
    acc.normalize();
   
    vel.add(acc);
    vel.mult(0.99);
    pos.add(vel);
  }
  
  void display(){
    noStroke();
    fill(0, 100, 100);
    ellipse(pos.x, pos.y, size, size);
  }
}
