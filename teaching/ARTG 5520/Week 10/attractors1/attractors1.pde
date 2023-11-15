ArrayList<Particle> particles = new ArrayList();
PVector att;

void setup(){ 
  size(800, 800, OPENGL);
  colorMode(HSB, 360, 100, 100, 100);
  att = new PVector(width/2, height/2);
  pixelDensity(2);
  background(0);
}

void draw(){
  background(0);
  
  for(Particle p : particles){
    p.move();
    p.display();
  }
  
  /* display the attractor */
  strokeWeight(3);
  stroke(0, 100, 100);
  noFill();
  ellipse(att.x, att.y, 10, 10);
  
}

void mousePressed(){
  for(int i = 0; i < 100; i++){
    Particle p = new Particle();
    particles.add(p);
  }
}
