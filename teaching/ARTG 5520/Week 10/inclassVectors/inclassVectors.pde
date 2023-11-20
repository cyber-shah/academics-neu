class Particle {
  PVector pos, vel, acc;
  float size, hue;
  
  Particle() {
    pos = new PVector(mouseX, mouseY);
    float speed = random(1, 5);
    vel = PVector.random2D();
    vel.mult(speed);
    size = random(2, 10);
    acc = new PVector(0, 0);
  }
  
  void move() {
    vel.add(acc);
    pos.add(vel);
    /* vector between two points is subtraction
    subtract A - B = vector from B to A
    
    so with attractor point, we find a vector from particle to
    the attractor
    
    basically implying that the farther a particle is 
    the resulting vector will be larger! so more speed 
    */
  }
  
  void display() {
    noStroke();
    fill(0, 100, 100);
    circle(pos.x, pos.y, size);
  }
}
