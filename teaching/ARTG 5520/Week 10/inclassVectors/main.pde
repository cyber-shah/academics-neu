ArrayList <Particle> particles = new ArrayList();
PVector att;

void setup() {
  size(800, 800);
  colorMode(HSB, 360, 100, 100);
  background(0);
  att = new PVector(width/2, height/2);
}

void draw() {
  background(0);
  for (Particle p : particles) {
    p.move();
    p.display();
  }  
  strokeWeight(3);
  stroke(0, 100, 100);
  noFill();
  circle(att.x, att.y, 10);
}

void mousePressed() {
  for(int i = 0; i < 100; i++) {
    Particle p = new Particle();
    particles.add(p);
  }
}
