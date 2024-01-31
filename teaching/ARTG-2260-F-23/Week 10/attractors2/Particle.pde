class Particle{
  
  PVector pos;
  PVector vel;
  PVector acc;
  int life;
  float size;
  float hue;
  
  Particle(float hue){
    pos = new PVector(mouseX, mouseY);
    float speed = random(0.5, 1);
    vel = new PVector(0, speed);
    vel.rotate(random(TWO_PI));
    life = int(random(100, 200));
    size = random(2, 10);
    this.hue = hue;
    acc = new PVector(0, 0);
  }
  
  void move(){
    /* computes the acceleration */
    acc = PVector.sub(att, pos);
    float distSq = acc.magSq();
    if(distSq < 300){
      distSq = 300;
    }
    acc.normalize();
    acc.mult(100/distSq);
    
    vel.add(acc);
    pos.add(vel);
  }
  
  void display(){
    noStroke();
    fill(hue, 100, 100);
    ellipse(pos.x, pos.y, size, size);
  }
  
  boolean isOutOfBounds(){
    if(pos.x > width || pos.x < 0 || pos.y > height || pos.y < 0) return true;
    else return false;
  }
}
